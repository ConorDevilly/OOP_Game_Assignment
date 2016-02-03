package fpsGame;

import processing.core.*;

public class TieFighter extends Ship{
	
	//TODO: Reduce to necessary (scope)
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
		super(p, 200, pos);
		this.pos = pos;
		speed = 30;
		shield = 100;
		fireRate = 50;
		
		calcFlightPath();
	}
	
	void shoot(){
		//Need to copy the current position. Cannot pass the pos PVector as passing objects work like pass by reference
		PVector currPos = new PVector(pos.x, pos.y, pos.z);
		Rocket r = new Rocket(p, currPos, new PVector(p.width / 2, p.height / 2, 0), this);
		Main.objects.add(r);
	}
	
	//Set the flight path and calculate and variables needed for it to work
	void calcFlightPath(){
		//TODO: Make chance based
		flightPath = (int) p.random(0, 2);

		float shootChance = p.random(0, 100);
		if(shootChance > 75) shoot();
		
		switch(flightPath){
			//Elliptically loop around the XWing
			case 0:{
				//Set the angle & pick an increment
				theta = 0;
				thetaInc = p.random(1, speed / 2) / 100;
				
				//Pick a random radius for the orbit
				radX = p.random(-speed, speed);
				radY = p.random(-speed, speed);
				radZ = p.random(0, speed / 2);
				break;
			}
			
			//Move towards & away from XWing
			case 2:{
				//Save the origional position and set the direction
				oPosZ = pos.z;
				towards = true;
				break;
			}
			
			//Move towards a random point
			case 1:{
				//Generate random destination
				dest = new PVector(p.random(0, p.width), p.random(0, p.height), p.random(-4500, -1000));

				//Calculate the distance and time it should take to get there
				float dist = PVector.dist(dest, pos);
				float time = dist / speed;
				
				//Make a PVector of the distance between the src and dst
				unit = PVector.sub(dest, pos);
				
				//Divide the distance by the time to get the speed it should travel in each direction
				unit = PVector.div(unit, time);

				break;
			}
		}
	}

	@Override
	void update(){
		
		if(shield <= 0){
			Main.objects.remove(this);
		}
		
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
			case 2:{
				//Move towards the XWing, unless its past the XWing Z pos, then move away from it
				towards = (pos.z >= 500) ? false : towards;
				pos.z += (towards) ? speed : -speed;
				
				//Recalc if near star position
				if(pos.z < oPosZ + speed && pos.z > oPosZ - speed){
					calcFlightPath();
				}
				break;
			}
			
			//Move toward a random point
			case 1:{
				pos.add(unit);
				if(pos.dist(dest) < unit.mag()) calcFlightPath();
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
		p.sphere(qsize);

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
