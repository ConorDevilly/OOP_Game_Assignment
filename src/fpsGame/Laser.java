package fpsGame;

import processing.core.*;

//The xwings laser 
public class Laser extends GameObject implements Projectile{
	
	int colour;
	PVector to;
	PVector unit;
	PVector origin;
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
		origin = new PVector(pos.x, pos.y, pos.z);
		inRange = true;
		speed = parent.fireRate;

		float dist = PVector.dist(to, pos);
		float time = dist / speed;
		
		unit = PVector.sub(to, pos);
		unit = PVector.div(unit, time);
		dmg = 1000;
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
		/*
		p.vertex(pos.x, pos.y, pos.z);
		p.vertex(pos.x, pos.y, pos.z);
		p.vertex(p.mouseX, p.mouseY, pos.z);
		*/
		p.vertex(origin.x, origin.y + 5, origin.z);
		p.vertex(origin.x, origin.y - 5, origin.z);
		p.vertex(p.mouseX, p.mouseY, pos.z);

		p.endShape();
		
		p.popStyle();
		p.popMatrix();
	}
}
