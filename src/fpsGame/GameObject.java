package fpsGame;

import processing.core.*;

public abstract class GameObject {
	
	PApplet p;
	PVector pos;
	boolean toDelete;

	GameObject(PApplet p){
		this.p = p;
		toDelete = false;
	}
	
	abstract void update();
	
	abstract void render();
}
