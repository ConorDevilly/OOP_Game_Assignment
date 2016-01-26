package fpsGame;

import processing.core.*;

public class TieFighter extends Ship{
	
	float size;
	float hsize;
	float qsize;
	float rot;
	float radX;
	float radY;
	float radZ;
	int flightPath;

	TieFighter(PApplet p, PVector pos){
		super(p);
		this.pos = pos;
		size = 100;
		speed = 5;
		hsize = size / 2;
		qsize = hsize / 2;
		flightPath = (int) p.random(2, 2);
		
		//Some updates have to made to the position, depending on flight path
		switch(flightPath){
		
		case 1:{
			pos.z = -1000;
			rot = 0;
		}
		case 2:{
			rot = 50;
			radX = -100;
			radY = 100;
			radZ = 100;
		}
		
		}
	}

	@Override
	void update() {
		/*
		pos.x += speed;
		pos.y += speed;
		pos.z += speed;
		*/
		
		switch(flightPath){
		
		//Spinny spinny
		case 1:{
			rot += 1;
			pos.z += speed;
		}
		
		case 2:{
			rot += 0.05;
			pos.x += ((radX / speed) * PApplet.cos(rot));  
			pos.y += ((radY / speed) * PApplet.sin(rot));
			pos.z += ((radZ / speed) * PApplet.sin(rot));
		}
		
		}
		
		/*
		 * TODO: Flight Path
		 * 
		 * Picking spawn point:
		 * 1. Pick either Side or Top / Bottom
		 * 2. Pick random coord for entery point
		 * 3. Pick exit side
		 * 4. Pick random coord for exit point
		 * 5. Pick Z between large -Z values
		 * (???)
		 * 6. Loopy shit near turning point.
		 * 6a. Turning point is with +/- RAND of screen center
		 * (???)
		 * 7. Pick speed (bet. A & B)
		 * (???)
		 * 8. RotX => Barrell roll turning weird stuff
		 * (???)
		 * 
		 */
		
		/*
		 * Update:
		 * IF(!WithinTurnPoint)
		 *   Move toward point
		 * ELSE
		 *   Turn by portion of angle 
		 */
	}

	@Override
	void render() {
		//TODO: FIX 3D!!!
		//TODO: Fix multiple functions
		p.pushMatrix();
		p.pushStyle();

		p.translate(pos.x, pos.y, pos.z);
		/* DEBUG */ //p.rotateY(PApplet.PI / 2);
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
		
		p.popStyle();
		p.popMatrix();
	}
	
	
}
