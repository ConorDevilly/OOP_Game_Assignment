package fpsGame;

import processing.core.PApplet;

public class HUD extends GameObject{

	PApplet p;
	int score;
	
	HUD(PApplet p){
		this.p = p;
		score = 0;
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
		
		p.popStyle();
		p.popMatrix();
		
	}
}
