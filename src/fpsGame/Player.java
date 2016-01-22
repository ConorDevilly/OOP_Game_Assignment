package fpsGame;

import processing.core.*;

public class Player extends GameObject{
	
	PVector eyePos;
	PVector centerPos;
	PApplet p;
	float visionRadius;
	boolean[] keys;
	float speed;
	
	Player(PApplet p, PVector pos, boolean[] keys){
		super(pos);
		this.p = p;
		this.keys = keys;
		visionRadius = 500;
		speed = 5;
		//eyePos = new PVector(p.width / 2, (p.height / 2) - 100, (p.height / 2) / PApplet.tan(PApplet.PI / 6));
		eyePos = new PVector(p.width / 2, (p.height / 2) - 100, 0);
		centerPos = new PVector(p.width / 2, p.height / 2, eyePos.z - visionRadius);
	}
	
	void update(){

		//TODO: Fix inverted mouse upon turning around
		
		/* 
		 * TODO: Check if theta needs to change at the start of the method
		 * If so, change it.
		 * If not, calc theta & proceed
		 */
		if(p.mouseX < (p.width / 2) - 250){

		}else if(p.mouseX > (p.width / 2) + 250){

		}

		PVector straight = new PVector(eyePos.x, eyePos.y, centerPos.z);
		float distStraight = eyePos.dist(straight);
		float distCenter = eyePos.dist(centerPos);
		theta = PApplet.degrees(PApplet.acos(distStraight / distCenter));
		
		//Correct angles
		float xDif = centerPos.x - eyePos.x;
		float zDif = centerPos.z - eyePos.z;
		
		p.pushStyle();
		p.fill(0, 255, 0);
		p.text("xDif: " + xDif + "\nzDif: " + zDif, p.mouseX, p.mouseY + 50, centerPos.z);
		p.popStyle();
		
		if(xDif > 0 && zDif > 0){
			theta = 180 - theta;
		}
		else if(xDif > 0 && zDif < 0){
			theta = theta;
		}
		else if(xDif < 0 && zDif > 0){
			theta = 180 + theta;
		}
		else if(xDif < 0 && zDif < 0){
			theta = 360 - theta;
		}

		//Allow rotation +/- 180 degrees
		//TODO: Find a way of figuring centPosx based off theta
		if(p.mouseX < (p.width / 2) - 250){
			if(theta > 0){
				//theta--;
				theta -= speed;
				PApplet.print("\n" + theta);
			}else{
				theta = 360;
			}
		}else if(p.mouseX > (p.width / 2) + 250){
			if(theta < 360){
				//theta++;
				theta += speed;
				PApplet.print("\n" + theta);
			}else{
				theta = 0;
			}
		}
		
		centerPos.y = p.mouseY;
		centerPos.x = p.mouseX;
		centerPos.z = -(visionRadius * PApplet.cos(PApplet.radians(theta)));

		forward.x = PApplet.sin(PApplet.radians(theta));
		forward.z = PApplet.cos(PApplet.radians(theta));
		forward.mult(speed);

		//TODO: Change to apply movement
		if (keys['W'])
		{
		}
		if (keys['S'])
		{
		}
		if (keys['A'])
		{

		}
		if (keys['D'])
		{
		}
	}
	
	void render(){
		p.camera(eyePos.x, eyePos.y, eyePos.z, centerPos.x, centerPos.y, centerPos.z, 0, 1, 0);

		//DEBUG
		p.pushStyle();
		p.stroke(0, 0, 255);
		p.pushMatrix();
		p.translate(centerPos.x - 10, centerPos.y - 10, centerPos.z);
		p.rotateY(PVector.angleBetween(eyePos, centerPos));
		p.beginShape();
		p.vertex(0, 0, 0);
		p.vertex(0, 10, 0);
		p.vertex(0, 5, 0);
		p.vertex(5, 5, 0);
		p.vertex(-5, 5, 0);
		p.endShape();
		p.popMatrix();
		p.popStyle();

		//DEBUG
		p.pushStyle();
		p.fill(255, 0, 0);
		p.textAlign(PApplet.CENTER, PApplet.BOTTOM);
		p.text(theta, p.mouseX, p.mouseY + 10, centerPos.z);
		p.text("mouseX: " + p.mouseX, p.mouseX, p.mouseY, centerPos.z);
		p.text("Width: " + p.width, p.mouseX, p.mouseY + 20, centerPos.z);
		p.popStyle();
	}
}
