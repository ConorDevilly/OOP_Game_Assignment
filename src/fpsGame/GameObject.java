package fpsGame;

import processing.core.*;

public abstract class GameObject {

	PVector forward;
	PVector pos;
	float theta;
	
	GameObject(PVector pos){
		this.pos = pos;
		forward = new PVector(0, 0, 0);
		theta = 0.0f;
	}
	
	abstract void update();
	
	abstract void render();
}
