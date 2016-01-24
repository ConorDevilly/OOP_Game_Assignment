package fpsGame;

import processing.core.PApplet;

public class HUD extends GameObject{

	PApplet p;
	int score;
	int shield;
	int wave;
	
	HUD(PApplet p){
		this.p = p;
		score = 0;
		shield = 10;
		wave = 1;
	}
	
	@Override
	void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void render() {
		p.pushMatrix();
		p.pushStyle();
		
		//Score
		p.fill(255, 0, 0);
		p.textAlign(PApplet.CENTER, PApplet.TOP);
		p.textSize(26);
		p.text("SCORE", p.width / 6, 10, 1);
		p.fill(0, 255, 0);
		p.text(score, p.width / 6, 10 + p.textAscent() + p.textDescent(), 1);
		
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
		
		//Top Left Gun
		p.beginShape();
		p.stroke(0, 0, 255);
		p.fill(0, 0, 255);
		p.vertex(45, 308, 1);
		p.vertex(85, 308, 1);
		p.vertex(90, 315, 1);
		p.vertex(85, 323, 1);
		p.vertex(45, 323, 1);
		p.endShape();

		p.beginShape();
		p.stroke(255, 0, 0);
		p.fill(255, 0, 0);
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
		p.noFill();
		p.beginShape();
		p.stroke(0, 0, 255);
		p.fill(0, 0, 255);
		p.vertex(p.width - 45, 308, 1);
		p.vertex(p.width - 85, 308, 1);
		p.vertex(p.width - 90, 315, 1);
		p.vertex(p.width - 85, 323, 1);
		p.vertex(p.width - 45, 323, 1);
		p.endShape();

		p.beginShape();
		p.stroke(255, 0, 0);
		p.fill(255, 0, 0);
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
		
		p.popStyle();
		p.popMatrix();
		
	}
}
