package fpsGame;

import processing.core.*;

public abstract class GameObject {

	PVector forward;
	float theta;
	
	GameObject(){
		forward = new PVector(0, 0, 0);
		theta = 0.0f;
	}
	
	abstract void update();
	
	abstract void render();
}
