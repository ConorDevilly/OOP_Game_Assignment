package fpsGame;

import processing.core.PApplet;

public class RecordScoreScreen extends GameObject{
	
	String name;
	float score;

	RecordScoreScreen(PApplet p, float score) {
		super(p);
		this.score = score;
		name = "";
	}

	@Override
	void update() {
		
	}

	@Override
	void render() {
		p.pushMatrix();
		p.pushStyle();

		p.textAlign(PApplet.CENTER);
		p.text("Your Score: " + score, p.width / 2, p.height / 2);
		p.text("Name: " + name, p.width / 2, p.height / 2 + p.textAscent() + p.textDescent());
		
		p.popStyle();
		p.popMatrix();
	}

}
