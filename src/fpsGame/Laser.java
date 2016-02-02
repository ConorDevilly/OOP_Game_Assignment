package fpsGame;

import processing.core.*;

public class Laser extends GameObject implements Projectile{
	
	int colour;
	PVector from;
	PVector to;
	PVector unit;
	float speed;
	Ship parent;
	float dmg;

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
		dmg = 100;
	}
	
	public void applyDamage(Ship target){
		target.shield -= dmg;
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
		p.vertex(from.x, from.y - 5, from.z);
		p.vertex(from.x, from.y + 5, from.z);
		p.vertex(from.x, from.y + 5, from.z - 50);
		p.vertex(from.x, from.y - 5, from.z - 50);
		p.vertex(from.x, from.y - 5, from.z);
		p.endShape();
		
		p.popStyle();
		p.popMatrix();
	}
}
