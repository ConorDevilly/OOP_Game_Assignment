package fpsGame;

import processing.core.*;

public abstract class GameObject {
	
	PApplet p;
	PVector pos;

	GameObject(PApplet p){
		this.p = p;
	}
	
	abstract void update();
	
	abstract void render();
}
