package fpsGame;

import processing.core.*;

public class Laser extends GameObject{
	
	int colour;
	PVector from;
	PVector to;
	PVector unit;
	float speed;
	Ship parent;

	Laser(PApplet p, int colour, PVector from, PVector to, Ship parent) {
		super(p);
		this.colour = colour;
		this.from = from;
		this.to = to;
		this.parent = parent;
		speed = parent.range / 10;

		float dist = PVector.dist(to, from);
		float time = dist / speed;
		unit = PVector.sub(to, from);
		unit = PVector.div(unit, time);
	}

	@Override
	void update(){
		from.add(unit);
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
