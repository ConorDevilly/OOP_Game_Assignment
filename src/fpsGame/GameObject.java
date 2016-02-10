package fpsGame;

import processing.core.*;

//The base class for all other objects
public abstract class GameObject {
	
	PApplet p;
	PVector pos;
	float size;
	float points;

	GameObject(PApplet p){
		this.p = p;
		pos = new PVector();
	}
	
	abstract void update();
	
	abstract void render();
}
