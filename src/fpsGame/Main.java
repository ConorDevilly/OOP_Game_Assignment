package fpsGame;

import processing.core.*;

import java.util.ArrayList;

public class Main extends PApplet{
	
	ArrayList<GameObject> objects;
	HUD hud;
	public static boolean[] keys;

	public void setup(){
		//size(displayWidth, displayHeight, P3D);
		size(800, 600, P3D);
		cursor(CROSS);
		objects = new ArrayList<GameObject>();
		keys = new boolean[512];
		
		HUD hud = new HUD(this);
		objects.add(hud);
		TieFighter tf = new TieFighter(this);
		objects.add(tf);
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
	public void keyRealeased(){
		keys[keyCode] = false;
	}
}
