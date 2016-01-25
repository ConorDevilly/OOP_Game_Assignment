package fpsGame;

import processing.core.*;

public class TieFighter extends Ship{
	
	float rot = 0;
	float size;
	float hsize;
	float ssize;
	float tsize;

	TieFighter(PApplet p){
		super(p);
		size = 100;
		hsize = size / 2;
		ssize = size / 6;
		tsize = size / 3;
	}

	@Override
	void update() {
		rot += 0.1f;
	}

	@Override
	void render() {
		//TODO: FIX 3D!!!
		//TODO: Fix multiple functions
		PVector pos = new PVector(p.width / 2, p.height / 2, 1);
		
		p.pushMatrix();
		p.pushStyle();

		p.translate(pos.x, pos.y, pos.z);
		/* DEBUG */ p.rotateY(PApplet.PI / 3);
		p.noFill();
		p.stroke(0, 255, 0);

		//TODO: Sphere sucks
		/*
		p.sphereDetail(4);
		p.sphere(15);
		*/
		//Link between wings
		p.beginShape();
		p.vertex(size - ssize, ssize, 0);
		p.vertex(-size - ssize, ssize, 0);
		p.vertex(-size - ssize, -ssize, 0);
		p.vertex(size - ssize, -ssize, 0);
		p.endShape();
		
		p.beginShape();
		p.endShape();
		
		
		//Wings
		renderWing(size - ssize);
		renderWing(-size - ssize);
		
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
		p.vertex(x, size, -size);
		p.vertex(x, size, size);
		p.vertex(x, 0, size + hsize);
		p.vertex(x, -size, size);
		p.vertex(x, -size, -size);
		p.vertex(x, 0, -size - hsize);
		p.vertex(x, 0, size + hsize);
		p.vertex(x, size, size);
		p.vertex(x, -size, -size);
		p.vertex(x, -size, size);
		p.vertex(x, size, -size);
		p.vertex(x, 0, -size - hsize);
		p.endShape();
	}
}
