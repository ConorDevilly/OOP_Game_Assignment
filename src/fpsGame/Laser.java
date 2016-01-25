package fpsGame;

import processing.core.*;

public class Laser extends GameObject{
	
	int colour;
	PVector from;
	PVector to;
	float speed;

	Laser(PApplet p, int colour, PVector from, PVector to) {
		super(p);
		this.colour = colour;
		this.from = from;
		this.to = to;
	}

	@Override
	void update(){
		
	}

	@Override
	void render() {
		p.pushMatrix();
		p.pushStyle();
		
		p.fill(colour);
		p.stroke(colour);
		
		p.beginShape();
		p.vertex(from.x - 10, from.y, from.z);
		p.vertex(from.x - 5, from.y - 5, from.z);
		p.vertex(from.x - 10, from.y, from.z);
		p.vertex(from.x + 5, from.y - 5, from.z);
		p.vertex(from.x - 10, from.y, from.z);
		p.vertex(from.x + 5, from.y + 5, from.z);
		p.vertex(from.x - 10, from.y, from.z);
		p.vertex(from.x - 5, from.y + 5, from.z);
		p.endShape();
		
		p.popStyle();
		p.popMatrix();
	}
}
