package fpsGame;

import processing.core.*;
import ddf.minim.*;

public abstract class Ship extends GameObject{
	
	float speed;
	float range;
	float size;
	float hsize;
	float qsize;
	float shield;
	float fireRate;
	int score;
	Minim minim;
	AudioPlayer audio;

	Ship(PApplet p, float size, PVector pos){
		super(p);
		this.pos = pos;
		this.size = size;
		hsize = size / 2;
		qsize = hsize / 2;

		minim = new Minim(p);
	}
	
	Ship(PApplet p){
		this(p, 100, new PVector(0, 0, 0));
	}
	
	void shoot(){
	}
}
