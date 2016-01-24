package fpsGame;

import processing.core.*;

import java.util.ArrayList;

public class Main extends PApplet{
	
	public boolean[] keys;
	ArrayList<GameObject> objects;
	HUD hud;

	public void setup(){
		//size(displayWidth, displayHeight, P3D);
		size(800, 600, P3D);
		cursor(CROSS);
		keys = new boolean[512];
		objects = new ArrayList<GameObject>();
		
		HUD hud = new HUD(this);
		objects.add(hud);
	}

	public void draw(){
		background(0);
		
		for(GameObject o : objects){
			o.update();
			o.render();
		}
	}

	public void keyPressed(){
		keys[keyCode] = true;
	}

	public void keyReleased(){
		keys[keyCode] = false;
	}
}
