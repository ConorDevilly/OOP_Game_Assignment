package fpsGame;

import processing.core.*;

public class TieFighter extends Ship{
	
	float theta;
	float thetaInc;
	float rot;
	float rotInc;
	float radX;
	float radY;
	float radZ;
	float fireChance;
	float initChance;
	PVector dest;
	PVector unit;
	boolean turning;
	boolean dying;
	int flightPath;

	TieFighter(PApplet p, PVector pos){
		super(p, 200, pos);
		this.pos = pos;
		speed = 30;
		shield = 100;
		fireRate = 50;
		fireChance = 50;
		initChance = fireChance;
		points = 100;
		turning = false;
		rot = 0;
		rotInc = 0.1f;
		
		calcFlightPath();
	}
	
	void shoot(){
		//Need to copy the current position. Cannot pass the pos PVector as passing objects work like pass by reference
		PVector currPos = new PVector(pos.x, pos.y, pos.z);
		Rocket r = new Rocket(p, currPos, new PVector(p.width / 2, p.height / 2, 0), this);
		Main.audio = Main.minim.loadFile("sounds/TFFire.mp3");
		Main.audio.play();
		Main.objects.add(r);
	}
	
	//Set the XWing turning
	void setTurn(){
		turning = true;
		rot = 0;
		rotInc = p.random(0.025f, 0.075f);
	}
	
	//Set the flight path and calculate and variables needed for it to work
	void calcFlightPath(){
		flightPath = (int) p.random(0, 2);
		
		switch(flightPath){
			//Elliptically loop around the XWing
			case 0:{
				//Set the angle & pick an increment
				theta = 0;
				thetaInc = p.random(1, speed / 2) / 100;
				
				//Pick a random radius for the orbit and start turning the TF
				radX = p.random(-speed, speed);
				radY = p.random(-speed, speed);
				radZ = p.random(0, speed / 2);
				setTurn();
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
				setTurn();
				break;
			}
		}
	}

	@Override
	void update(){
		
		if(!dying){
			if(turning) rot += rotInc;
			if(rot > PApplet.PI) turning = false;
			
			if(shield <= 0){
				//Chance to animate death
				if(p.random(0, 10) < 2){
					dying = true;
					turning = false;
					rot = 0;
					rotInc = 0.02f;
					fireChance = 0;
					points = 0;

					Main.audio = Main.minim.loadFile("sounds/TFExplode.mp3");
					Main.audio.play();
				}else{
					Main.objects.remove(this);
				}
			}

			//Check that the TF is on screen before it shoots
			fireChance = (onScreen()) ? initChance : 0;
			if((p.frameCount % 60 == 0) && (p.random(0, 100) > 100 - fireChance))
				shoot();
			
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
				
				//Move toward a random point
				case 1:{
					pos.add(unit);
					if(pos.dist(dest) < unit.mag()) calcFlightPath();
					break;
				}

			}
		}else{
			if(rot < PApplet.HALF_PI){
				rot += rotInc;
			}else{
				Main.objects.remove(this);
			}
		}
	}

	@Override
	void render() {
		p.pushMatrix();
		p.pushStyle();

		p.translate(pos.x, pos.y, pos.z);
		if(turning && flightPath == 0) p.rotateZ(rot);
		if(turning && flightPath == 1) p.rotateY(rot);

		p.noFill();
		p.stroke(0, 255, 0);
		
		if(!dying){
			renderBody();
		}

		renderWing(new PVector(-hsize, 0, 0));
		renderWing(new PVector(hsize, 0, 0));
		
		p.popStyle();
		p.popMatrix();
	}
	
	void renderBody(){
		//Sphere
		p.sphereDetail(4);
		p.sphere(qsize);

		//Links between wings
		p.beginShape();
		p.vertex(-hsize, 0, 0);
		p.vertex(hsize, 0, 0);
		p.vertex(hsize, -qsize / 4, 0);
		p.vertex(-hsize, -qsize / 4, 0);
		p.vertex(-hsize,qsize / 4, 0);
		p.vertex(hsize, qsize / 4, 0);
		p.endShape();

		//Gun
		p.fill(255, 0, 0);
		p.stroke(255, 0, 0);
		p.beginShape();
		p.vertex(0, qsize, 0);
		p.vertex(0, qsize + 1, 0);
		p.vertex(0, qsize + size / 20, 0);
		p.translate(0, qsize, 0);
		p.sphere(3);
		p.translate(0, -qsize, 0);
		p.endShape();
	}
	
	void renderWing(PVector location){
		p.noFill();
		p.stroke(0, 255, 0);

		p.beginShape();

		//Dying animation
		if(dying){
			p.translate(location.x + 100 * rot, location.y, location.z);
			p.rotateX(rot);
			p.rotateY(-rot);
		}else{
			p.translate(location.x, location.y, location.z);
		}

		p.vertex(0, 0, 0);
		p.vertex(0, 0, hsize);
		p.vertex(0, -hsize, qsize);
		p.vertex(0, -hsize, -qsize);
		p.vertex(0, 0, -hsize);
		p.vertex(0, hsize, -qsize);
		p.vertex(0, hsize, qsize);
		p.vertex(0, 0, hsize);
		p.vertex(0, 0, -hsize);
		p.vertex(0, hsize, -qsize);
		p.vertex(0, -hsize, qsize);
		p.vertex(0, -hsize, -qsize);
		p.vertex(0, hsize, qsize);
		p.endShape();
		p.translate(-location.x, -location.y, -location.z);
	}
	
	//Check that the TF is on the screen
	boolean onScreen(){
		boolean ret = true;
		float screenX = p.screenX(pos.x, pos.y, pos.z);
		float screenY = p.screenY(pos.x, pos.y, pos.z);
		
		if(screenX > p.width || screenX < 0 || screenY > p.height || screenY < 0) 
			ret = false;

		return ret;
	}
}
