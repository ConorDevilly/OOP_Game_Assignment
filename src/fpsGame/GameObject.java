package fpsGame;

import processing.core.*;

public abstract class GameObject {

	PVector pos;
	
	abstract void update();
	
	abstract void render();
}
