package fpsGame;

import processing.core.*;

public class TieFighter extends Ship{
	
	float size;
	float hsize;
	float qsize;
	float theta;
	float thetaInc;
	float rot;
	float rotInc;
	float radX;
	float radY;
	float radZ;
	float oPosZ;
	boolean towards;
	boolean rotating;
	int flightPath;

	TieFighter(PApplet p, PVector pos){
		super(p);
		this.pos = pos;
		size = 100;
		speed = 35;
		hsize = size / 2;
		qsize = hsize / 2;
		
		calcFlightPath();
	}
	
	//Set the flight path and calculate and variables needed for it to work
	void calcFlightPath(){
		flightPath = (int) p.random(0, 2);

		switch(flightPath){

		//Elliptically loop around the XWing
		case 0:{
			theta = 0;
			/*
			radX = p.random(-pos.x / 2, (p.width - pos.x) / 2);
			radY = p.random(-pos.y / 2, (p.height - pos.y) / 2);
			radZ = p.random(0, -pos.z); 
			*/

			radX = p.random(-speed, speed);
			radY = p.random(-speed, speed);
			radZ = p.random(0, speed);
			thetaInc = p.random(1, speed / 2) / 100;
			
			PApplet.println(radX + "\t" + radY + "\t" + radZ);
			break;
		}
		
		//Move towards & from XWing
		case 1:{
			oPosZ = pos.z;
			towards = true;
			break;
		}

		}
	}

	@Override
	void update() {
		switch(flightPath){

		//Elliptically loop around the XWing
		case 0:{
			theta += thetaInc;
			pos.x += radX * PApplet.cos(theta);  
			pos.y += radY * PApplet.sin(theta);
			pos.z += radZ * PApplet.sin(theta);;
			
			//If the TF has completed one rotation of its current path, recalculate it
			if(theta < PApplet.TWO_PI + thetaInc && theta > PApplet.TWO_PI - thetaInc){
				calcFlightPath();
			}
			break;
		}
		
		//Move straight towards and away from XWing
		case 1:{
			//Move towards the XWing, unless its past the XWing Z pos, then move away from it
			towards = (pos.z >= 500) ? false : towards;
			pos.z += (towards) ? speed : -speed;
			
			//Recalc if near star position
			if(pos.z < oPosZ + speed && pos.z > oPosZ - speed){
				calcFlightPath();
			}
			break;
		}

		}
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
