package fpsGame;

import processing.core.*;

public class Laser extends GameObject implements Projectile{
	
	int colour;
	PVector to;
	PVector unit;
	float speed;
	Ship parent;
	float dmg;
	boolean inRange;

	Laser(PApplet p, int colour, PVector pos, PVector to, Ship parent) {
		super(p);
		this.colour = colour;
		this.pos = pos;
		this.to = to;
		this.parent = parent;
		inRange = true;
		speed = parent.range / 10;

		float dist = PVector.dist(to, pos);
		float time = dist / speed;
		unit = PVector.sub(to, pos);
		unit = PVector.div(unit, time);
		dmg = 100;
	}
	
	public void applyDamage(Ship target){
		target.shield -= dmg;
	}

	@Override
	void update(){
		pos.add(unit);
		/*
		if(inRange){
			pos.add(unit);
			
			if(pos.z < parent.range){
				inRange = false;
			}
		}
		*/
	}

	@Override
	void render() {
		p.pushMatrix();
		p.pushStyle();
		
		p.fill(colour);
		p.stroke(colour);
		
		p.beginShape();
		p.vertex(pos.x, pos.y - 5, pos.z);
		p.vertex(pos.x, pos.y + 5, pos.z);
		p.vertex(pos.x, pos.y + 5, pos.z - parent.range);
		p.vertex(pos.x, pos.y - 5, pos.z - parent.range);
		p.vertex(pos.x, pos.y - 5, pos.z);
		p.endShape();
		
		p.popStyle();
		p.popMatrix();
	}
}
