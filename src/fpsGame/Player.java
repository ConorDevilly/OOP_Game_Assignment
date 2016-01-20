package fpsGame;

import processing.core.*;

public class Player extends GameObject{
	
	PVector eyePos;
	PVector centerPos;
	PApplet p;
	float visionRadius;
	boolean[] keys;
	
	Player(PApplet p, PVector pos, boolean[] keys){
		super(pos);
		this.p = p;
		this.keys = keys;
		visionRadius = 500;
		//eyePos = new PVector(p.width / 2, (p.height / 2) - 100, (p.height / 2) / PApplet.tan(PApplet.PI / 6));
		eyePos = new PVector(p.width / 2, (p.height / 2) - 100, 0);
		centerPos = new PVector(p.width / 2, p.height / 2, eyePos.z - visionRadius);
	}
	
	void update(){

		float speed = 5f;
		centerPos.y = p.mouseY;
		centerPos.x = p.mouseX;
		
		/*
		if(p.mouseX <= 0){
			p.mouseX--;
		}
		if(p.mouseX >= p.width){
			p.mouseX++;
		}
		*/
		
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
		
		if(xDif > 0 && zDif > 0){
			theta = theta + 90;
		}
		else if(xDif > 0 && zDif < 0){
			theta = theta;
		}
		else if(xDif < 0 && zDif > 0){
			theta = theta + 180;
		}
		else if(xDif < 0 && zDif < 0){
			theta = 360 - theta;
		}
		
		/*
		 * if mouseX near edge
		 * if mouseX > pMouseX
		 * 	change Z
		 */
		
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
		p.popStyle();

		/*
		p.pushMatrix();
		p.pushStyle();

		p.translate(eyePos.x, eyePos.y, eyePos.z);


		p.beginShape();

		p.fill(255, 0, 0);
		p.stroke(255, 0, 0);

		p.vertex(-25, -50, centerPos.z);
		p.vertex(25, -50, centerPos.z);
		p.vertex(25, -100, centerPos.z);
		p.vertex(-25, -100, centerPos.z);

		p.endShape(PApplet.CLOSE);
		
		p.popStyle();
		p.popMatrix();
		*/
	}

}
