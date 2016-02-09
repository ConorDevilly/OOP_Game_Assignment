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
	String mode;
	GameObject o;
	
	/*
	 * MASTER TODO LIST:
	 * 
	 * 1. Add sound
	 * 2. Re-add credits
	 * 3. Create other ships (Interceptor, bomber)
	 * 4. Add animations for dying, exploding
	 *
	 */
	

	public void setup(){
		size(800, 600, P3D);
		cursor(CROSS);
		mode = "Menu";
		objects = new ArrayList<GameObject>();
		paused = false;
		name = "";
		wave = 1;

		//Need to load a font, otherwise text appears blurry
		textFont(loadFont("Carlito-48.vlw"));

		changeMode("Menu");
	}
	
	//Clears the object array and initalises anything needed for that particular screen
	void changeMode(String m){
		objects.clear();
		
		switch(m){
			case "Menu":{
				Space space = new Space(this);
				objects.add(space);
				Menu menu = new Menu(this);
				objects.add(menu);
				mode = m;
				break;
			}
			case "Play":{
				player = new XWing(this, new PVector(width / 2, height, 0));
				objects.add(player);
				HUD hud = new HUD(this, player);
				objects.add(hud);
				Space space = new Space(this);
				objects.add(space);
				wave = 1;
				genWave();
				break;
			}
			case "Record":{
				RecordScoreScreen recScore = new RecordScoreScreen(this, player.score);
				objects.add(recScore);
				Space space = new Space(this);
				objects.add(space);
				break;
			}
			case "Highscores":{
				try{
					ArrayList<String> highscores = readScores();
					HighscoresScreen screen = new HighscoresScreen(this, highscores);
					objects.add(screen);
					Space space = new Space(this);
					objects.add(space);
				}catch (Exception e){
					e.printStackTrace();
				}
				break;
			}
		}

		mode = m;
	}

	public void draw(){
		background(0);

		boolean enemyFound = false;
		for(int i = 0; i < objects.size(); i++){
			o = objects.get(i);
			o.render();
			if(!paused) o.update();
			if(!paused && mode == "Play") chkCollisions();
			if(mode == "Play") enemyFound = (o instanceof TieFighter) ? true : enemyFound;
		}

		//Spawn a new wave of tie fighters if none are left
		if(!enemyFound && mode == "Play"){
			wave++;
			genWave();
		}
	}
	
	//Check for collisions
	void chkCollisions(){
		if(o instanceof Laser){
			//Check if the laser is shooting a ship
			for(int j = objects.size() - 1; j >= 0; j--){
				GameObject target = objects.get(j);

				//Check if Target is a ship
				if(target instanceof Ship && target != player){
					
					//We need to find where the target appears to be so we can accurately get the shooting working
					PVector screenT = new PVector(
							screenX(target.pos.x, target.pos.y, target.pos.z),
							screenY(target.pos.x, target.pos.y, target.pos.z)
							);
					
					//Check if the screen position of the ship is close to the mouse
					if(screenT.x <= mouseX + ((Ship) target).qsize && screenT.x >= mouseX - ((Ship) target).qsize){
						if(screenT.y <= mouseY + ((Ship) target).qsize && screenT.y >= mouseY - ((Ship) target).qsize){
							((Laser) o).applyDamage((Ship) target);
							player.score += target.points;
							objects.remove(o);
						}
					}
				//Check if laser shooting an enemy rocket
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
					changeMode("Record");
				}
				objects.remove(o);
			}
		}
	}

	//Spawn a wave of tie fighters
	void genWave(){
		for(int i = 0; i < wave; i++){
			TieFighter tf = new TieFighter(this, new PVector(
					//TODO: Un-unify spawning in center. I.e: Spread spawnign
					//random(i * (width / wave), (i + 1) * (width / wave)), 
					//random(i * height / wave, (i + 1) * (height / wave)), 
					random(0, (i + 1) * (width / wave)), 
					random(0, (i + 1) * (height / wave)), 
					random(-5000, -2000))
					);
			objects.add(tf);
		}
	}
	
	//Listens for mouse clicks. Checks if user is over a button and switchs the mode accordingly
	public void mouseClicked(){
		if(mode == "Menu" || mode == "Highscores"){
			//Looping unavoidable here
			for(int i = objects.size() - 1; i >= 0; i--){
				GameObject go = objects.get(i);
				if(go instanceof MenuObject){
					if(((MenuObject) go).active){
						changeMode(((MenuObject) go).text);
						break;
					}
				}
			}
		}
	}
	
	//Key controls
	public void keyPressed(){
		if(mode == "Play"){
			if(key == 'p') paused = !paused;
			if(key == ' ') player.shoot();
		}else if(mode == "menu"){
			if(key == ENTER){
				changeMode("Play");
			}else if(key == ' '){
				mode = "Highscores";
			}
		}else if(mode == "Highscores"){
			if(key == ' ' || key == ENTER) mode = "Menu";
		}
	}
	
	//Takes the user's name when they die
	public void keyTyped(){
		if(mode == "Record"){
			if(key == BACKSPACE){
				if(name.length() > 0)
					name = name.substring(0, name.length() - 1);
			}else if(key == ENTER){
				try {
					chkHighscore();
				} catch (Exception e) {
					e.printStackTrace();
				}
				changeMode("Menu");
			}else if(name.length() < 5){
				name += key;
			}

			for(GameObject go : objects){
				if(go instanceof RecordScoreScreen) ((RecordScoreScreen) go).name = name;
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
