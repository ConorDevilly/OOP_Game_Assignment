package fpsGame;

import processing.core.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main extends PApplet{
	
	public static ArrayList<GameObject> objects;
	public static XWing player;
	public static int wave;
	boolean paused;
	boolean dead;
	String name;

	public void setup(){
		size(800, 600, P3D);
		cursor(CROSS);
		objects = new ArrayList<GameObject>();
		paused = false;
		name = "";
		wave = 1;
		
		player = new XWing(this, new PVector(width / 2, height, 0));
		objects.add(player);
		HUD hud = new HUD(this, player);
		objects.add(hud);
		Space space = new Space(this);
		objects.add(space);
		
		genWave();
	}
	
	void genWave(){
		for(int i = 0; i < wave; i++){
			TieFighter tf = new TieFighter(this, new PVector(
					random(0, (i + 1) * (width / wave)), 
					random(0, (i + 1) * (height / wave)), 
					random(-4500, -2000))
					);
			objects.add(tf);
		}
	}

	public void draw(){
		background(0);
		
		if(!dead){
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
			
			//Create a new wave if no tie fighters were found
			if(!enemyFound){
				wave++;
				genWave();
			}

		}else{
			textAlign(CENTER);
			text("Score: " + player.score, width / 2, height / 2);
			text("Name: " + name, width / 2, height / 2 + textAscent() + textDescent());
		}
	}
	
	void chkCollisions(){
		//for(int i = objects.size() - 1; i >= 0; i--){
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
					if(player.shield <= 0) dead = true;
					objects.remove(o);
				}
			}
		}
	}
	
	//Key controls
	public void keyPressed(){
		if(key == 'p') paused = !paused;
		if(key == ' ') player.shoot();
	}
	
	public void keyTyped(){
		if(dead){
			if(key == BACKSPACE){
				name = name.substring(0, name.length() - 1);
			}else if(key == ENTER){
				saveScore(name, player.score);
			}else{
				name += key;
			}
		}
	}
	
	void saveScore(String name, float score){
		File scores = new File("scores.txt");

		try {
			FileWriter writer;
			writer = new FileWriter(scores, true);
			writer.write(name + ":" + player.score + "\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
