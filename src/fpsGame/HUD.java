package fpsGame;

import processing.core.*;

public class HUD extends GameObject{

	int score;
	int shield;
	int wave;
	float range;
	float viewDist;
	
	HUD(PApplet p){
		super(p);
		score = 0;
		shield = 10;
		wave = 1;
		viewDist = 500;
		range = 1000;
	}
	
	@Override
	void update() {
		/*
		if(Main.keys[' '] == true){
			shoot();
		}
		*/
		if(p.mousePressed) shoot();
	}

	@Override
	void render() {
		p.pushMatrix();
		p.pushStyle();
		
		//Score
		p.fill(255, 0, 0);
		p.textAlign(PApplet.CENTER, PApplet.TOP);
		p.textSize(26);
		p.text("SCORE", p.width / 6, 10, -10);
		p.fill(0, 255, 0);
		p.text(score, p.width / 6, 10 + p.textAscent() + p.textDescent(), -1);
		
		//Shield
		p.noFill();
		p.stroke(0, 255, 0);
		p.beginShape();
		p.vertex(p.width / 3, 10, 1);
		p.vertex(p.width / 3, 50, 1);
		p.vertex(p.width / 2, 10, 1);
		p.vertex(2 * p.width / 3, 50, 1);
		p.vertex(2 * p.width / 3, 10, 1);
		p.vertex(p.width / 3, 10, 1);
		p.endShape();
		p.fill(0, 255, 0);
		p.text(shield, p.width / 2, 10, 1);
		p.text("SHIELD", p.width / 2, 10 + p.textDescent() + p.textAscent(), 1);
		
		//Wave
		p.text(wave, 5 * p.width / 6, 10, 1);
		p.fill(255, 0, 0);
		p.textAlign(PApplet.LEFT, PApplet.TOP);
		p.text("WAVE", 5 * p.width / 6 + p.textWidth(Integer.toString(wave)), 10, 1);
		
		//Ship
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

		p.popStyle();
		p.popMatrix();
		
	}
	
	
	void shoot(){
		Laser l = new Laser(p, p.color(0, 255, 0), new PVector(p.width - 90, 565, 1), new PVector(p.mouseX, p.mouseY, -range));
		Main.objects.add(l);
	}
}

