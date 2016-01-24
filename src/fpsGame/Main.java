package fpsGame;

import processing.core.*;

import java.util.ArrayList;

public class Main extends PApplet{
	
	ArrayList<GameObject> objects;
	HUD hud;

	public void setup(){
		//size(displayWidth, displayHeight, P3D);
		size(800, 600, P3D);
		cursor(CROSS);
		objects = new ArrayList<GameObject>();
		
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
}
