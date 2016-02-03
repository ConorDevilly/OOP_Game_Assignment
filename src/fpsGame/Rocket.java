package fpsGame;

import processing.core.*;

public class Rocket extends GameObject implements Projectile{

	float dmg;
	float size;
	float theta;
	float speed;
	PVector unit;
	
	Rocket(PApplet p, PVector pos, PVector to, Ship parent){
		super(p);
		this.pos = pos;
		dmg = 1;
		size = 20;
		theta = 0;
		speed = parent.fireRate;


		float dist = PVector.dist(to, pos);
		float time = dist / speed;
		unit = PVector.sub(to, pos);
		unit = PVector.div(unit, time);
	}

	@Override
	public void applyDamage(Ship target){
		target.shield -= dmg;
	}

	@Override
	void update(){
		theta += 0.1f;
		pos.add(unit);
	}

	@Override
	void render(){
		p.pushMatrix();
		p.pushStyle();

		p.translate(pos.x, pos.y, pos.z);
		p.rotateX(theta);
		
		p.noFill();
		p.stroke(255, 127, 0);

		p.sphereDetail(6);
		p.sphere(size);
		
		p.popStyle();
		p.popMatrix();
	}
}
