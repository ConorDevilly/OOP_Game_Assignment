package fpsGame;

import processing.core.*;

public class XWing extends Ship{
	
	XWing(PApplet p){
		super(p);
		pos = new PVector(p.width / 2, p.height / 2, 0);
	}

	XWing(PApplet p, PVector pos){
		super(p);
		this.pos = pos;
	}

	@Override
	void update() {
		// TODO Auto-generated method stub
		
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
		//p.rotateY(PApplet.HALF_PI);

		//Ship center
		p.beginShape();
		p.vertex(-50, 0, 0);
		p.vertex(-50, 0, -200);
		p.vertex(0, 0, -250);
		p.vertex(50, 0, -200);
		p.vertex(50, 0, 0);
		p.endShape();
		
		//Top Left Gun
		renderGun(new PVector(-p.width / 2, -250, 0));
		renderGun(new PVector(p.width / 2, -250, 0));
		renderGun(new PVector(-p.width / 2, 0, 0));
		renderGun(new PVector(p.width / 2, 0, 0));

		p.popStyle();
		p.popMatrix();

		/* Attemp 3d drawing
		p.vertex(-size / 6, -qsize, 0);
		p.vertex(-size / 6, -qsize, qsize);
		p.vertex(size / 6, -qsize, qsize);
		p.vertex(size / 6, -qsize, 0);
		p.vertex(-size / 6, -qsize, 0);
		
		p.vertex(-size / 6, 0, -qsize);
		p.vertex(-size / 6, 0, -(3 * qsize));
		p.vertex(size / 6, 0, -(3 * qsize));
		p.vertex(size / 6, 0, -qsize);
		p.vertex(size / 6, -qsize, 0);
		
		p.vertex(hsize, -hsize, 0);
		p.vertex(hsize, -hsize, -hsize);
		p.vertex(hsize, -hsize, qsize);
		p.vertex(hsize, -hsize, -qsize);
		p.vertex(size / 6, -qsize, -qsize);
		
		p.vertex(size / 6, -qsize, 0);
		p.vertex(-size / 6, -qsize, 0);
		p.vertex(-hsize, -hsize, 0);
		p.vertex(-hsize, -hsize, -hsize);
		p.vertex(-hsize, -hsize, qsize);
		p.vertex(-hsize, -hsize, -qsize);
		p.vertex(-size / 6, -qsize, -qsize);

		p.vertex(-size / 6, qsize, -qsize);
		p.vertex(-size / 6, qsize, 0);
		p.vertex(-hsize, hsize, 0);
		p.vertex(-hsize, hsize, -hsize);
		p.vertex(-hsize, hsize, qsize);
		p.vertex(-hsize, hsize, -qsize);
		*/

		/* 2D
		p.beginShape();
		p.strokeWeight(2);
		p.stroke(255, 0, 0);
		p.noFill();
		p.vertex(p.width / 2 - 90, p.height, 1);
		p.vertex(p.width / 2 - 50, p.height - 35, 1);
		p.vertex(p.width / 2 - 30, p.height - 50, 1);

		p.vertex(p.width / 2 + 30, p.height - 50, 1);
		p.vertex(p.width / 2 + 50, p.height - 35, 1);
		p.vertex(p.width / 2 + 90, p.height, 1);

		p.vertex(p.width / 2 + 40, p.height, 1);
		p.vertex(p.width / 2 + 30, p.height - 50, 1);
		p.vertex(p.width / 2 + 40, p.height, 1);
		p.vertex(p.width / 2 - 40, p.height, 1);
		p.vertex(p.width / 2 - 30, p.height - 50, 1);
		p.endShape();
		
		//TODO: Put gun drawing into function
		//TODO: Make guns scale with display size
		//Guns
		//Top Left Gun
		p.strokeWeight(2);
		p.noFill();

		p.beginShape();
		p.stroke(0, 0, 255);
		p.vertex(45, 308, 1);
		p.vertex(85, 308, 1);
		p.vertex(90, 315, 1);
		p.vertex(85, 323, 1);
		p.vertex(45, 323, 1);
		p.endShape();

		p.beginShape();
		p.stroke(255, 0, 0);
		p.vertex(0, 285, 1);
		p.vertex(20, 275, 1);
		p.vertex(60, 275, 1);
		p.vertex(45, 285, 1);
		p.vertex(45, 345, 1);
		p.vertex(60, 355, 1);
		p.vertex(20, 355, 1);
		p.vertex(0, 345, 1);
		p.vertex(30, 345, 1);
		p.vertex(30, 285, 1);
		p.vertex(0, 285, 1);
		p.endShape();

		p.beginShape();
		p.stroke(255, 0, 0);
		p.vertex(0, 305, 1);
		p.vertex(15, 305, 1);
		p.vertex(20, 310, 1);
		p.vertex(20, 320, 1);
		p.vertex(15, 325, 1);
		p.vertex(0, 325, 1);
		p.endShape();
		
		//Top Right Gun
		p.beginShape();
		p.stroke(0, 0, 255);
		p.vertex(p.width - 45, 308, 1);
		p.vertex(p.width - 85, 308, 1);
		p.vertex(p.width - 90, 315, 1);
		p.vertex(p.width - 85, 323, 1);
		p.vertex(p.width - 45, 323, 1);
		p.endShape();

		p.beginShape();
		p.stroke(255, 0, 0);
		p.vertex(p.width - 0, 285, 1);
		p.vertex(p.width - 20, 275, 1);
		p.vertex(p.width - 60, 275, 1);
		p.vertex(p.width - 45, 285, 1);
		p.vertex(p.width - 45, 345, 1);
		p.vertex(p.width - 60, 355, 1);
		p.vertex(p.width - 20, 355, 1);
		p.vertex(p.width - 0, 345, 1);
		p.vertex(p.width - 30, 345, 1);
		p.vertex(p.width - 30, 285, 1);
		p.vertex(p.width - 0, 285, 1);
		p.endShape();

		p.beginShape();
		p.stroke(255, 0, 0);
		p.vertex(p.width - 0, 305, 1);
		p.vertex(p.width - 15, 305, 1);
		p.vertex(p.width - 20, 310, 1);
		p.vertex(p.width - 20, 320, 1);
		p.vertex(p.width - 15, 325, 1);
		p.vertex(p.width - 0, 325, 1);
		p.endShape();
		
		//Bottom Left Gun
		p.beginShape();
		p.stroke(0, 0, 255);
		p.vertex(45, 558, 1);
		p.vertex(85, 558, 1);
		p.vertex(90, 565, 1);
		p.vertex(85, 573, 1);
		p.vertex(45, 573, 1);
		p.endShape();

		p.beginShape();
		p.stroke(255, 0, 0);
		p.vertex(0, 535, 1);
		p.vertex(20, 525, 1);
		p.vertex(60, 525, 1);
		p.vertex(45, 535, 1);
		p.vertex(45, 595, 1);
		p.vertex(60, 605, 1);
		p.vertex(20, 605, 1);
		p.vertex(0, 595, 1);
		p.vertex(30, 595, 1);
		p.vertex(30, 535, 1);
		p.vertex(0, 535, 1);
		p.endShape();

		p.beginShape();
		p.stroke(255, 0, 0);
		p.vertex(0, 555, 1);
		p.vertex(15, 555, 1);
		p.vertex(20, 560, 1);
		p.vertex(20, 570, 1);
		p.vertex(15, 575, 1);
		p.vertex(0, 575, 1);
		p.endShape();
		
		//Bottom Right Gun
		p.beginShape();
		p.stroke(0, 0, 255);
		p.vertex(p.width - 45, 558, 1);
		p.vertex(p.width - 85, 558, 1);
		p.vertex(p.width - 90, 565, 1);
		p.vertex(p.width - 85, 573, 1);
		p.vertex(p.width - 45, 573, 1);
		p.endShape();

		p.beginShape();
		p.stroke(255, 0, 0);
		p.vertex(p.width - 0, 535, 1);
		p.vertex(p.width - 20, 525, 1);
		p.vertex(p.width - 60, 525, 1);
		p.vertex(p.width - 45, 535, 1);
		p.vertex(p.width - 45, 595, 1);
		p.vertex(p.width - 60, 605, 1);
		p.vertex(p.width - 20, 605, 1);
		p.vertex(p.width - 0, 595, 1);
		p.vertex(p.width - 30, 595, 1);
		p.vertex(p.width - 30, 535, 1);
		p.vertex(p.width - 0, 535, 1);
		p.endShape();

		p.beginShape();
		p.stroke(255, 0, 0);
		p.vertex(p.width - 0, 555, 1);
		p.vertex(p.width - 15, 555, 1);
		p.vertex(p.width - 20, 560, 1);
		p.vertex(p.width - 20, 570, 1);
		p.vertex(p.width - 15, 575, 1);
		p.vertex(p.width - 0, 575, 1);
		p.endShape();
		*/
		
		
	}
	
	void renderGun(PVector o){
		p.pushMatrix();
		p.pushStyle();

		p.translate(o.x, o.y, o.z);

		p.beginShape();
		p.stroke(0, 255, 0);
		p.vertex(0, 5, 0);
		p.vertex(0, 5, -100);
		p.vertex(0, 0, -125);
		p.vertex(0, -5, -100);
		p.vertex(0, -5, 0);
		p.endShape();

		p.beginShape();
		p.stroke(255, 0, 0);
		p.vertex(-25, 25, 0);
		p.vertex(25, 25, 0);
		p.vertex(25, -25, 0);
		p.vertex(-25, -25, 0);
		p.vertex(-25, 25, 0);

		p.vertex(-25, -25, 0);
		p.vertex(-25, -50, -25);


		p.vertex(25, -50, -25);
		p.vertex(25, -25, 0);

		p.vertex(25, 25, 0);
		p.vertex(25, 50, -25);
		p.vertex(-25, 50, -25);
		p.vertex(-25, 25, 0);
		p.endShape();

		p.popStyle();
		p.popMatrix();
	}
}
