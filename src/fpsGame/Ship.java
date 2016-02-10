package fpsGame;

import processing.core.*;
import ddf.minim.*;

//Basic ship template
public abstract class Ship extends GameObject{
	
	float speed;
	float range;
	float size;
	float hsize;
	float qsize;
	float shield;
	float fireRate;
	int score;

	Ship(PApplet p, float size, PVector pos){
		super(p);
		this.pos = pos;
		this.size = size;
		hsize = size / 2;
		qsize = hsize / 2;
	}
	
	Ship(PApplet p){
		this(p, 100, new PVector(0, 0, 0));
	}
	
	void shoot(){
	}
}
