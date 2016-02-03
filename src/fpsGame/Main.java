package fpsGame;

import processing.core.*;

import java.util.ArrayList;
import java.util.Iterator;

public class Main extends PApplet{
	
	public static ArrayList<GameObject> objects;
	public static boolean[] keys;
	boolean paused;
	XWing player;

	public void setup(){
		//size(displayWidth, displayHeight, P3D);
		size(800, 600, P3D);
		cursor(CROSS);
		objects = new ArrayList<GameObject>();
		keys = new boolean[512];
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
		
		//for(int i = 0; i < objects.size(); i++){
		//TODO: Change to iterator???
		/*
		int i = objects.size() - 1;
		while(i >= 0){
		*/
		/*
		Iterator<GameObject> go = objects.iterator();
		while(go.hasNext()){
		*/
		for(int i = objects.size() - 1; i >= 0; i--){
			GameObject o = objects.get(i);
			//GameObject o = go.next();
			if(o == null) PApplet.println("Something fucked up");
			
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

			//Check if the laser is out of bounds
			if(o instanceof Laser){
				if(o.pos.z < -((Laser) o).parent.range){
						objects.remove(o);
				}
			}
		}
	}
	
	//Key controls
	public void keyPressed(){
		if(key == 'p') paused = !paused;
		if(key == 'w') player.shoot();
		keys[keyCode] = true;
	}
	public void keyRealeased(){
		keys[keyCode] = false;
	}
}
