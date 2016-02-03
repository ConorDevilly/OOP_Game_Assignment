package fpsGame;

import processing.core.*;

import java.util.ArrayList;

public class Main extends PApplet{
	
	public static ArrayList<GameObject> objects;
	boolean paused;
	XWing player;
	HUD hud;

	public void setup(){
		size(800, 600, P3D);
		cursor(CROSS);
		objects = new ArrayList<GameObject>();
		paused = false;
		
		hud = new HUD(this);
		objects.add(hud);
		player = new XWing(this, new PVector(width / 2, height, 0));
		objects.add(player);
		Space space = new Space(this);
		objects.add(space);
		
		genWave();
	}
	
	void genWave(){
		for(int i = 0; i < hud.wave; i++){
			TieFighter tf = new TieFighter(this, new PVector(random(0, width), random(0, height), random(-4500, -2000)));
			objects.add(tf);
		}
	}

	public void draw(){
		background(0);
		
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
			hud.wave++;
			genWave();
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
								//Messes up
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
			}
		}
	}
	
	//Key controls
	public void keyPressed(){
		if(key == 'p') paused = !paused;
		if(key == ' ') player.shoot();
	}
}
