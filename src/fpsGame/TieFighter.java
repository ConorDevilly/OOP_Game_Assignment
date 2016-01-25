package fpsGame;

import processing.core.*;

public class TieFighter extends Ship{
	
	float size;
	float hsize;
	float qsize;
	float rot = 0;

	TieFighter(PApplet p, PVector pos){
		super(p);
		this.pos = pos;
		size = 100;
		hsize = size / 2;
		qsize = hsize / 2;
	}

	@Override
	void update() {
		rot += 0.1f;
	}

	@Override
	void render() {
		//TODO: FIX 3D!!!
		//TODO: Fix multiple functions
		p.pushMatrix();
		p.pushStyle();

		p.translate(pos.x, pos.y, pos.z);
		/* DEBUG */ //p.rotateY(PApplet.PI / 2);
		p.rotateY(rot);
		p.noFill();
		p.stroke(0, 255, 0);

		//Links between wings
		p.beginShape();
		p.vertex(-hsize, 0, 0);
		p.vertex(hsize, 0, 0);
		p.vertex(hsize, -qsize / 4, 0);
		p.vertex(-hsize, -qsize / 4, 0);
		p.vertex(-hsize,qsize / 4, 0);
		p.vertex(hsize, qsize / 4, 0);
		p.endShape();
		
		//Sphere
		p.sphereDetail(4);
		p.sphere(size / 4);

		//Left Wing
		p.beginShape();
		p.vertex(-hsize, 0, 0); 
		p.vertex(-hsize, 0, hsize);
		p.vertex(-hsize, -hsize, qsize);
		p.vertex(-hsize, -hsize, -qsize);
		p.vertex(-hsize, 0, -hsize);
		p.vertex(-hsize, hsize, -qsize);
		p.vertex(-hsize, hsize, qsize);
		p.vertex(-hsize, 0, hsize);
		p.vertex(-hsize, 0, -hsize);
		p.vertex(-hsize, hsize, -qsize);
		p.vertex(-hsize, -hsize, qsize);
		p.vertex(-hsize, -hsize, -qsize);
		p.vertex(-hsize, hsize, qsize);
		p.endShape();

		//Right Wing
		p.beginShape();
		p.vertex(hsize, 0, 0);
		p.vertex(hsize, 0, hsize);
		p.vertex(hsize, -hsize, qsize);
		p.vertex(hsize, -hsize, -qsize);
		p.vertex(hsize, 0, -hsize);
		p.vertex(hsize, hsize, -qsize);
		p.vertex(hsize, hsize, qsize);
		p.vertex(hsize, 0, hsize);
		p.vertex(hsize, 0, -hsize);
		p.vertex(hsize, hsize, -qsize);
		p.vertex(hsize, -hsize, qsize);
		p.vertex(hsize, -hsize, -qsize);
		p.vertex(hsize, hsize, qsize);
		p.endShape();
		
		//Gun
		p.fill(255, 0, 0);
		p.stroke(255, 0, 0);
		p.vertex(0, qsize, 0);
		p.vertex(0, qsize + 1, 0);
		p.vertex(0, qsize + size / 20, 0);
		p.translate(0, qsize, 0);
		p.sphere(3);
		


		//TODO: Sphere sucks
		//Link between wings
		/*
		p.beginShape();
		p.vertex(size - ssize, ssize, 0);
		p.vertex(-size - ssize, ssize, 0);
		p.vertex(-size - ssize, -ssize, 0);
		p.vertex(size - ssize, -ssize, 0);
		p.endShape();
		*/
		
		/*
		p.beginShape();
		p.vertex();
		p.endShape();
		*/
		
		
		//Wings
		/*
		renderWing();
		renderWing();
		*/
		
		p.popStyle();
		p.popMatrix();
	}
	
	void renderWing(){
		//Wing
		p.beginShape();
		p.strokeWeight(2);
		p.stroke(0, 255, 0);
		p.noFill();

		/*
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
		*/
		p.endShape();
	}
}
