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
		//TieFighter tf = new TieFighter(this, new PVector(width / 2, height / 2, 1));
		//TieFighter tf = new TieFighter(this, new PVector(random(0, width), random(0, height), random(-5000, -100)));
		for(int i = 0; i < 1; i++){
			TieFighter tf = new TieFighter(this, new PVector(random(0, width), random(0, height), random(-4000, -1000)));
			objects.add(tf);
		}
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
