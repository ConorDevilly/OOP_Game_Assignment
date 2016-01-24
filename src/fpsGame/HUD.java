package fpsGame;

import processing.core.PApplet;

public class HUD extends GameObject{

	PApplet p;
	int score;
	int shield;
	
	HUD(PApplet p){
		this.p = p;
		score = 0;
		shield = 10;
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
		p.text(shield, p.width / 2, 20, 1);
		p.text("SHIELD", p.width / 2, 20 + p.textDescent() + p.textAscent(), 1);
		
		p.popStyle();
		p.popMatrix();
		
	}
}
