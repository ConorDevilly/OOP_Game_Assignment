package fpsGame;

import processing.core.*;

import java.util.ArrayList;

public class Main extends PApplet{
	
	public static ArrayList<GameObject> objects;
	public static boolean[] keys;

	public void setup(){
		//size(displayWidth, displayHeight, P3D);
		size(800, 600, P3D);
		cursor(CROSS);
		objects = new ArrayList<GameObject>();
		keys = new boolean[512];
		
		HUD hud = new HUD(this);
		objects.add(hud);
		Space space = new Space(this);
		objects.add(space);
		for(int i = 0; i < 1; i++){
			TieFighter tf = new TieFighter(this, new PVector(random(0, width), random(0, height), random(-4500, -1000)));
			objects.add(tf);
		}
	}

	public void draw(){
		background(0);
		
		for(int i = 0; i < objects.size(); i++){
			GameObject o = objects.get(i);
			o.update();
			o.render();
			
			/*
			if(o instanceof Laser ){
				if(o.pos.z < -4000){
					objects.remove(i);
					i--;
				}
			}
			*/
		}
		
		chkCollisions();
	}
	
	void chkCollisions(){
		for(int i = objects.size() - 1; i >= 0; i--){
			GameObject o = objects.get(i);

			if(o instanceof TieFighter){
				for(int j = objects.size() - 1; j >= 0; j--){
					GameObject other = objects.get(j);
					
					if(other instanceof Laser){
						if(other.pos.z < -4000){
							objects.remove(other);
							j++;
						}
					}
				}
			}
		}
	}
	
	public void keyPressed(){
		keys[keyCode] = true;
	}
	public void keyRealeased(){
		keys[keyCode] = false;
	}
}
