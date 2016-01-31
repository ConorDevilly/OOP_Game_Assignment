package fpsGame;

import processing.core.*;

public class XWing extends Ship{
	
	XWing(PApplet p){
		super(p);
		pos = new PVector(p.width / 2, p.height / 2, 0);
	}

	XWing(PApplet p, PVector pos){
		super(p);
		this.pos = pos;
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void render() {
		//Ship
		
		p.pushMatrix();
		p.pushStyle();
		
		p.stroke(255, 0, 0);
		p.strokeWeight(2);
		p.noFill();
		
		p.translate(pos.x, pos.y, pos.z);
		//p.rotateY(PApplet.HALF_PI);

		//Ship center
		p.beginShape();
		p.vertex(-30, 0, -250);
		p.vertex(-30, -25, 50);
		p.vertex(-30, 0, -250);
		p.vertex(30, 0, -250);
		p.vertex(30, -25, 50);
		p.vertex(30, 0, -250);
		p.vertex(50, 0, -200);
		p.vertex(50, 0, 0);
		p.vertex(-50, 0, 0);
		p.vertex(-50, 0, -200);
		p.vertex(-30, 0, -250);
		p.endShape();
		
		renderGun(new PVector(-p.width / 2, -250, 0));
		renderGun(new PVector(p.width / 2, -250, 0));
		renderGun(new PVector(-p.width / 2, 0, 0));
		renderGun(new PVector(p.width / 2, 0, 0));

		p.popStyle();
		p.popMatrix();
	}
	
	void renderGun(PVector o){
		p.pushMatrix();
		p.pushStyle();

		p.translate(o.x, o.y, o.z);

		//Inner gun 
		p.beginShape();
		p.stroke(0, 255, 0);
		p.vertex(0, 5, 0);
		p.vertex(0, 5, -75);
		p.vertex(0, 0, -100);
		p.vertex(0, -5, -75);
		p.vertex(0, -5, 0);
		p.endShape();

		//Shield shape
		p.beginShape();
		p.stroke(255, 0, 0);
		p.vertex(-25, 25, 0);
		p.vertex(25, 25, 0);
		p.vertex(25, -25, 0);
		p.vertex(-25, -25, 0);
		p.vertex(-25, 25, 0);
		p.vertex(-25, 50, -25);
		p.vertex(25, 50, -25);
		p.vertex(25, 25, 0);
		p.vertex(25, 50, -25);
		p.vertex(25, 40, -25);
		p.vertex(25, 25, -10);
		p.vertex(25, -25, -10);
		p.vertex(25, -40, -25);
		p.vertex(25, -50, -25);
		p.vertex(25, -25, 0);
		p.vertex(25, -50, -25);
		p.vertex(-25, -50, -25);
		p.vertex(-25, -25, 0);
		p.vertex(-25, -50, -25);
		p.vertex(-25, -40, -25);
		p.vertex(-25, -25, -10);
		p.vertex(-25, 25, -10);
		p.vertex(-25, 40, -25);
		p.vertex(-25, 50, -25);
		p.endShape();
		
		p.popStyle();
		p.popMatrix();
	}
}
