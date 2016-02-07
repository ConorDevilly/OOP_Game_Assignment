package fpsGame;

import processing.core.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends PApplet{
	
	public static ArrayList<GameObject> objects;
	public static XWing player;
	public static int wave;
	ArrayList<String> highscores;
	boolean paused;
	String name;

	//List of modes
	enum Mode{ MENU, GAME, RECORD, SCORES }
	Mode mode;
	

	public void setup(){
		size(800, 600, P3D);
		cursor(CROSS);
		mode = Mode.MENU;
		objects = new ArrayList<GameObject>();
		paused = false;
		name = "";
		wave = 1;

		background(0);
		
		//Need to load a font, otherwise text appears blurry
		textFont(loadFont("Carlito-48.vlw"));
	}
	
	

	public void draw(){
		background(0);
		
		switch(mode){
			//The start screen
			case MENU:{
				textAlign(CENTER);
				text("Star Battles", width / 2, height / 2);
				text("Press enter to start", width / 2, height / 2 + textAscent() + textDescent());
				text("Press space to see highscores", width / 2, height / 2 + 2 * (textAscent() + textDescent()));
				break;
			}

			//Normal game loop
			case GAME:{
				boolean enemyFound = false;
				for(int i = objects.size() - 1; i >= 0; i--){
					GameObject o = objects.get(i);
					
					if(paused == false){
						o.update();
					}
					o.render();
					
					enemyFound = (o instanceof TieFighter) ? true : enemyFound;
				}

				chkCollisions();
				
				//Spawn a new wave of tie fighters if none are left
				if(!enemyFound){
					wave++;
					genWave();
				}
				break;
			}
			
			//Take the user's score when they die
			case RECORD:{
				textAlign(CENTER);
				text("Your Score: " + player.score, width / 2, height / 2);
				text("Name: " + name, width / 2, height / 2 + textAscent() + textDescent());
				break;
			}
			
			//Display the highscores
			case SCORES:{
				if(highscores == null){
					try{
						highscores = readScores();
						
					}catch (Exception e){
						e.printStackTrace();
					}
				}

				for(int i = 0; i < highscores.size(); i++){
					String[] record = highscores.get(i).split(":");

					textAlign(CENTER, TOP);
					text(
							(highscores.size() - i)	+ ". " + record[0] + "    " + record[1], 
							width / 2, height / 2 + 
							((highscores.size() - 1) - i) * (textAscent() + textDescent())
						);
				}
				break;
			}
		}
	}
	
	void chkCollisions(){
		for(int i = 0; i < objects.size(); i++){
			GameObject o = objects.get(i);

			if(o instanceof Laser){
				//Check if the laser is shooting a ship
				for(int j = objects.size() - 1; j >= 0; j--){
					GameObject target = objects.get(j);

					if(target instanceof Ship && target != player){
						
						//We need to find where the target appears to be so we can accurately get the shooting working
						PVector screenT = new PVector(
								screenX(target.pos.x, target.pos.y, target.pos.z),
								screenY(target.pos.x, target.pos.y, target.pos.z)
								);
						
						if(screenT.x <= mouseX + ((Ship) target).qsize && screenT.x >= mouseX - ((Ship) target).qsize){
							if(screenT.y <= mouseY + ((Ship) target).qsize && screenT.y >= mouseY - ((Ship) target).qsize){
								((Laser) o).applyDamage((Ship) target);
								player.score += target.points;
								objects.remove(o);
							}
						}
					}else if(target instanceof Rocket && target != o){
						PVector screenT = new PVector(
								screenX(target.pos.x, target.pos.y, target.pos.z),
								screenY(target.pos.x, target.pos.y, target.pos.z)
								);

						if(screenT.x <= mouseX + ((Rocket) target).size && screenT.x >= mouseX - ((Rocket) target).size){
							if(screenT.y <= mouseY + ((Rocket) target).size && screenT.y >= mouseY - ((Rocket) target).size){
								player.score += target.points;
								objects.remove(target);
								objects.remove(o);
							}
						}
					}
				}

				//Check if the laser is out of bounds
				if(o.pos.z < ((Laser) o).parent.range){
						objects.remove(o);
				}

			}else if(o instanceof Rocket){
				if(o.pos.z >= 0){
					((Rocket) o).applyDamage(player);
					if(player.shield <= 0){
						mode = Mode.RECORD;
						objects.clear();
						wave = 1;
					}
					objects.remove(o);
				}
			}
		}
	}

	//Spawn a wave of tie fighters
	void genWave(){
		for(int i = 0; i < wave; i++){
			TieFighter tf = new TieFighter(this, new PVector(
					//random(i * (width / wave), (i + 1) * (width / wave)), 
					//random(i * height / wave, (i + 1) * (height / wave)), 
					random(0, (i + 1) * (width / wave)), 
					random(0, (i + 1) * (height / wave)), 
					random(-5000, -2000))
					);
			objects.add(tf);
		}
	}
	
	//Create objects for the game
	void initGame(){
		player = new XWing(this, new PVector(width / 2, height, 0));
		objects.add(player);
		HUD hud = new HUD(this, player);
		objects.add(hud);
		Space space = new Space(this);
		objects.add(space);
		
	}
	
	//Key controls
	public void keyPressed(){
		if(mode == Mode.GAME){
			if(key == 'p') paused = !paused;
			if(key == ' ') player.shoot();
		}else if(mode == Mode.MENU){
			if(key == ENTER){
				mode = Mode.GAME; 
				initGame();
				genWave();
			}else if(key == ' '){
				mode = Mode.SCORES;
			}
		}else if(mode == Mode.SCORES){
			if(key == ' ' || key == ENTER) mode = Mode.MENU;
		}
	}
	
	//Takes the user's name when they die
	public void keyTyped(){
		if(mode == Mode.RECORD){
			if(key == BACKSPACE){
				if(name.length() > 0)
					name = name.substring(0, name.length() - 1);
			}else if(key == ENTER){
				try {
					chkHighscore();
				} catch (Exception e) {
					e.printStackTrace();
				}
				mode = Mode.MENU;
			}else{
				name += key;
			}
		}
	}
	
	ArrayList<String> readScores() throws Exception{
		ArrayList<String> scores = new ArrayList<String>();
		
		BufferedReader reader = new BufferedReader(new FileReader("scores.txt"));
		String line;
		try {
			while((line = reader.readLine()) != null){
				scores.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return scores;
	}
	
	void chkHighscore() throws Exception{
		ArrayList<String> scores = readScores();
		boolean updated = false;
		int highestRec = -1;
		
		for(int i = 0; i < scores.size(); i++){
			String[] rec = scores.get(i).split(":");
			
			if(player.score > Integer.parseInt(rec[1])){
				updated = true;
				highestRec = i;
			}
		}
		
		if(updated){
			for(int i = 0; i < highestRec; i++){
				scores.set(i, scores.get(i + 1));
			}
			//TODO: Get name
			scores.set(highestRec, name + ":" + player.score);
			saveScore(scores);
		}
	}
	
	void saveScore(ArrayList<String> input){
		File scores = new File("scores.txt");

		try {
			FileWriter writer;
			writer = new FileWriter(scores, false);
			for(String s : input){
				writer.write(s + "\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
