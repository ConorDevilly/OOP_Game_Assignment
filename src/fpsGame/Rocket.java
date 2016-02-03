package fpsGame;

import processing.core.*;

public class Rocket extends GameObject implements Projectile{

	float dmg;
	float size;
	float theta;
	
	Rocket(PApplet p, PVector pos){
		super(p);
		this.pos = pos;
		dmg = 1;
		size = 20;
		theta = 0;
	}

	@Override
	public void applyDamage(Ship target){
		target.shield -= dmg;
	}

	@Override
	void update(){
		theta += 0.1f;
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
