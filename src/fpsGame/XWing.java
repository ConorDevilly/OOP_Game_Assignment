package fpsGame;

import processing.core.*;

public class XWing extends Ship{

	boolean firing;
	PVector[] guns;
	float lastFired;
	float fireDelay;
	

	XWing(PApplet p, PVector pos){
		super(p);
		this.pos = pos;
		shield = 10;
		firing = false;
		fireRate = 1000;
		range = 1000;
		fireDelay = 20;
		guns = new PVector[4];
		guns[0] = new PVector(p.width, pos.y - 250, pos.z);
		guns[1] = new PVector(0, pos.y - 250, pos.z);
		guns[2] = new PVector(p.width, pos.y, pos.z);
		guns[3] = new PVector(0, pos.y, pos.z);
	}
	
	void shoot(){
		//Limit firerate
		if(p.frameCount - lastFired > fireDelay){
			lastFired = p.frameCount;
			firing = true;
			for(PVector g : guns){
				PVector lStart = new PVector(g.x, g.y, g.z);
				Laser l = new Laser(p, p.color(0, 255, 0), lStart, new PVector(p.mouseX, p.mouseY, 0), this);
				Main.objects.add(l);
			}
			
			//Play a shooting sound
			Main.audio = Main.minim.loadFile("sounds/XWingFire.mp3");
			Main.audio.play();
		}
	}

	@Override
	void update(){
	}

	@Override
	void render() {
		//Ship
		
		p.pushMatrix();
		p.pushStyle();
		
		p.stroke(255, 0, 0);
		p.strokeWeight(2);
		p.noFill();
		
		p.translate(pos.x, pos.y, pos.z);

		//Ship center
		p.beginShape();
		p.vertex(-30, 0, -250);
		p.vertex(-30, -25, 50);
		p.vertex(-30, 0, -250);
		p.vertex(30, 0, -250);
		p.vertex(30, -25, 50);
		p.vertex(30, 0, -250);
		p.vertex(50, 0, -200);
		p.vertex(50, 0, 0);
		p.vertex(-50, 0, 0);
		p.vertex(-50, 0, -200);
		p.vertex(-30, 0, -250);
		p.endShape();
		
		p.popStyle();
		p.popMatrix();
		
		//Create the guns
		for(PVector p : guns){
			renderGun(p);
		}

		firing = false;

	}
	
	void renderGun(PVector o){
		p.pushMatrix();
		p.pushStyle();

		p.translate(o.x, o.y, o.z);

		//Inner gun 
		p.beginShape();
		p.stroke(0, 255, 0);

		if(firing) p.fill(0, 255, 0); 
		else p.noFill();

		p.vertex(0, 5, 0);
		p.vertex(0, 5, -75);
		p.vertex(0, 0, -100);
		p.vertex(0, -5, -75);
		p.vertex(0, -5, 0);
		p.endShape();

		//Shield shape
		p.beginShape();
		p.stroke(255, 0, 0);
		p.noFill();
		p.vertex(-25, 25, 0);
		p.vertex(25, 25, 0);
		p.vertex(25, -25, 0);
		p.vertex(-25, -25, 0);
		p.vertex(-25, 25, 0);
		p.vertex(-25, 50, -25);
		p.vertex(25, 50, -25);
		p.vertex(25, 25, 0);
		p.vertex(25, 50, -25);
		p.vertex(25, 40, -25);
		p.vertex(25, 25, -10);
		p.vertex(25, -25, -10);
		p.vertex(25, -40, -25);
		p.vertex(25, -50, -25);
		p.vertex(25, -25, 0);
		p.vertex(25, -50, -25);
		p.vertex(-25, -50, -25);
		p.vertex(-25, -25, 0);
		p.vertex(-25, -50, -25);
		p.vertex(-25, -40, -25);
		p.vertex(-25, -25, -10);
		p.vertex(-25, 25, -10);
		p.vertex(-25, 40, -25);
		p.vertex(-25, 50, -25);
		p.endShape();
		
		p.popStyle();
		p.popMatrix();
	}
}
