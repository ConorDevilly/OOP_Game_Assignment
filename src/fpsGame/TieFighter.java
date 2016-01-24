package fpsGame;

import processing.core.*;

public class TieFighter extends Ship{
	
	float rot = 0;
	
	TieFighter(PApplet p){
		super(p);

	}

	@Override
	void update() {
		rot += 0.1f;
	}

	@Override
	void render() {
		//TODO: FIX 3D!!!
		//TODO: Fix multiple functions
		PVector pos = new PVector(p.width / 2, p.height / 2, 50);
		
		p.pushMatrix();
		p.pushStyle();

		/* DEBUG */ //p.rotateY(PApplet.PI / 2);
		p.translate(pos.x, pos.y, pos.z);
		p.noFill();
		p.stroke(0, 255, 0);
		//TODO: Sphere sucks
		p.sphere(15);
		renderWing(25);
		renderWing(-25);
		
		p.popStyle();
		p.popMatrix();
	}
	
	void renderWing(float x){
		//Wing
		p.beginShape();
		p.strokeWeight(2);
		p.stroke(0, 255, 0);
		p.noFill();
		p.vertex(x, 0, 0);
		p.vertex(x, 30, -30);
		p.vertex(x, 30, 30);
		p.vertex(x, 0, 45);
		p.vertex(x, -30, 30);
		p.vertex(x, -30, -30);
		p.vertex(x, 0, -45);
		p.vertex(x, 0, 45);
		p.vertex(x, 30, 30);
		p.vertex(x, -30, -30);
		p.vertex(x, -30, 30);
		p.vertex(x, 30, -30);
		p.vertex(x, 0, -45);
		p.endShape();
	}
}
