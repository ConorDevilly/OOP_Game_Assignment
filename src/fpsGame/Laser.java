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
		speed = parent.fireRate;

		float dist = PVector.dist(to, pos);
		float time = dist / speed;
		
		unit = PVector.sub(to, pos);
		unit = PVector.div(unit, time);
		dmg = 25;
	}
	
	public void applyDamage(Ship target){
		target.shield -= dmg;
	}

	@Override
	void update(){
		pos.add(unit);
	}

	@Override
	void render(){
		p.pushMatrix();
		p.pushStyle();
		
		p.fill(colour);
		p.stroke(colour);
		
		p.beginShape();
		p.vertex(pos.x, pos.y - 5, pos.z);
		p.vertex(pos.x, pos.y + 5, pos.z);
		p.vertex(p.mouseX, p.mouseY, pos.z);
		p.endShape();
		
		p.popStyle();
		p.popMatrix();
	}
}
