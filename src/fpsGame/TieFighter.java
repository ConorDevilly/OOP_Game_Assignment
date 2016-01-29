package fpsGame;

import processing.core.*;

public class TieFighter extends Ship{
	
	//TODO: Reduce to necessary (scope)
	float size;
	float hsize;
	float qsize;
	float theta;
	float thetaInc;
	float omega;
	float omegaInc;
	float rot;
	float rotInc;
	float radX;
	float radY;
	float radZ;
	float oPosZ;
	PVector dest;
	PVector unit;
	boolean towards;
	boolean rotating;
	int flightPath;

	TieFighter(PApplet p, PVector pos){
		super(p);
		this.pos = pos;
		size = 100;
		speed = 20;
		hsize = size / 2;
		qsize = hsize / 2;
		
		calcFlightPath();
	}
	
	//Set the flight path and calculate and variables needed for it to work
	void calcFlightPath(){
		//TODO: Make chance based
		flightPath = (int) p.random(0, 3);
		PApplet.println(flightPath);

		switch(flightPath){

		//Elliptically loop around the XWing
		case 0:{
			theta = 0;
			thetaInc = p.random(1, speed / 2) / 100;
			radX = p.random(-speed, speed);
			radY = p.random(-speed, speed);
			radZ = p.random(0, speed);
			break;
		}
		
		//Move towards & away from XWing
		case 1:{
			oPosZ = pos.z;
			towards = true;
			break;
		}
		
		//Move towards a random point
		case 2:{
			//dest = new PVector(p.random(0, p.width), p.random(0, p.height), pos.z);
			/*
			dest = new PVector(p.random(0, p.width), p.random(0, p.height), p.random(-4000, -1000));
			PApplet.println("POS: " + pos.x + "\t" + pos.y + "\t" + pos.z);
			PApplet.println("DEST: " + dest.x + "\t" + dest.y + "\t" + dest.z);
			
			float xDif = dest.x - pos.x;
			float yDif = dest.y - pos.y;
			float zDif = dest.z - pos.z;

			theta = PApplet.atan(yDif/ xDif);
			omega = PApplet.atan(zDif / xDif);
			PApplet.println("T: " + PApplet.degrees(theta));
			PApplet.println("O: " + PApplet.degrees(omega));

			//Correct the angle. Required due to upward movement being negative in processing.
			if((xDif < 0 && yDif < 0) || (xDif < 0 && yDif > 0)){
				theta = PApplet.PI + theta;
			}
			*/
			dest = new PVector(p.random(0, p.width), p.random(0, p.height), p.random(-4500, -2000));
			float dist = PVector.dist(dest, pos);
			float time = dist / speed;
			unit = PVector.sub(dest, pos);
			unit = PVector.div(unit, time);
			
			
			
			PApplet.println("POS: " + pos.x + "\t" + pos.y + "\t" + pos.z);
			PApplet.println("DST: " + dest.x + "\t" + dest.y + "\t" + dest.z);

			//Calculate where we want it to move each time
			//Speed = Dist / Time
			
			
			PApplet.println("UNT: " + unit.x + "\t" + unit.y + "\t" + unit.z);

			break;
		}

		}
	}

	@Override
	void update() {
		switch(flightPath){

		//Elliptically loop around the XWing
		case 0:{
			//TODO: Fix cheat...
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
		
		//Move to a random point
		case 2:{
			//TODO: Make simple...
			/*
			pos.x += speed * PApplet.cos(theta) * PApplet.sin(omega);
			pos.y += speed * PApplet.sin(theta) * PApplet.sin(omega);
			pos.z += speed * PApplet.cos(omega);
			
			if(pos.x < dest.x + speed && pos.x > dest.x - speed
					&& pos.y < dest.y + speed && pos.y > dest.y - speed
					&& pos.z < dest.z + speed && pos.z > dest.z - speed
					){
				calcFlightPath();
			}
			*/
			
			pos.add(unit);
			PApplet.println("POS: " + pos.x + "\t" + pos.y + "\t" + pos.z);
			PApplet.println(pos.dist(dest));
			
			if(pos.dist(dest) < unit.mag()){
				calcFlightPath();
			}
			
			break;
		}

		}
	}

	@Override
	void render() {
		//TODO: Cater for rotations
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
