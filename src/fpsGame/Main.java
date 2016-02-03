package fpsGame;

import processing.core.*;

import java.util.ArrayList;

public class Main extends PApplet{
	
	public static ArrayList<GameObject> objects;
	boolean paused;
	XWing player;

	public void setup(){
		size(800, 600, P3D);
		cursor(CROSS);
		objects = new ArrayList<GameObject>();
		paused = false;
		
		HUD hud = new HUD(this);
		objects.add(hud);
		player = new XWing(this, new PVector(width / 2, height, 0));
		objects.add(player);
		Space space = new Space(this);
		objects.add(space);
		
		for(int i = 0; i < 1; i++){
			TieFighter tf = new TieFighter(this, new PVector(random(0, width), random(0, height), random(-4500, -1000)));
			objects.add(tf);
		}
	}

	public void draw(){
		background(0);
		
		for(int i = objects.size() - 1; i >= 0; i--){
			GameObject o = objects.get(i);
			
			if(paused == false){
				o.update();
			}
			o.render();

		}
		chkCollisions();
	}
	
	void chkCollisions(){
		for(int i = objects.size() - 1; i >= 0; i--){
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
						
						if(screenT.x <= mouseX + ((Ship) target).hsize && screenT.x >= mouseX - ((Ship) target).hsize){
							if(screenT.y <= mouseY + ((Ship) target).hsize && screenT.y >= mouseY - ((Ship) target).hsize){
								//((Laser) o).applyDamage((Ship) target);
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
