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

	TieFighter(PApplet p, PVector pos){
		super(p);
		this.pos = pos;
		size = 100;
		speed = 15;
		hsize = size / 2;
		qsize = hsize / 2;
		
		rot = 0;
		//TODO: Find a way of limiting based on inital pos
		radX = p.random(-p.width / 2, p.width / 2);
		radY = p.random(-p.height / 2, p.height / 2);
		radZ = -pos.z / 2;
	}
	
	void calcFlightPath(){
		rot = 0;
		//TODO: Find a way of limiting based on inital pos
		radX = p.random(-p.width / 2, p.width / 2);
		radY = p.random(-p.height / 2, p.height / 2);
		radZ = -pos.z / 2;
	}

	@Override
	void update() {
		/*
		pos.x += speed;
		pos.y += speed;
		pos.z += speed;
		*/
		
		rot += 0.02;
		pos.x += ((radX / speed) * PApplet.cos(rot));  
		pos.y += ((radY / speed) * PApplet.sin(rot));
		pos.z += ((radZ / speed) * PApplet.sin(rot));
		
		//If the TF has completed one rotation of its current path, recalculate it
		if(rot < PApplet.TWO_PI + 0.03 && rot > PApplet.TWO_PI - 0.03){
			calcFlightPath();
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
