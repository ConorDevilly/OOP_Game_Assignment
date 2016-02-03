package fpsGame;

import processing.core.*;

public class HUD extends GameObject{

	float viewDist;
	Ship parent;
	
	HUD(PApplet p, Ship parent){
		super(p);
		this.parent = parent;
		viewDist = 500;
	}
	
	@Override
	void update(){}

	@Override
	void render() {
		p.pushMatrix();
		p.pushStyle();

		int shield = (int) parent.shield;
		int score = (int) parent.score;
		int wave = Main.wave;
		
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
		
		p.popStyle();
		p.popMatrix();
		
	}
}

