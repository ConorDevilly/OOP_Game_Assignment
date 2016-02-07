package fpsGame;

import processing.core.*;

public abstract class GameObject {
	
	PApplet p;
	PVector pos;
	float points;

	GameObject(PApplet p){
		this.p = p;
		pos = new PVector();
	}
	
	abstract void update();
	
	abstract void render();
}
