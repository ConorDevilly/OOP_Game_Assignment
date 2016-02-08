package fpsGame;

import java.util.ArrayList;

import processing.core.*;

public class HighscoresScreen extends GameObject{

	ArrayList<String> scores;
	int active;
	int inactive;

	HighscoresScreen(PApplet p, ArrayList<String> scores) {
		super(p);
		this.scores = scores;

		//TODO: Create super screen class
		active = p.color(0, 255, 0);
		inactive = p.color(255, 255, 0);
		
		GameObject back = new MenuObject(p, new PVector(p.width / 2, 3 * p.height / 4), 50, 
				"Menu", active, inactive);
		
		Main.objects.add(back);
	}

	@Override
	void update() {}

	@Override
	void render() {
		//TODO: Make look less shti
		p.pushStyle();
		
		p.fill(inactive);
		
		for(int i = 0; i < scores.size(); i++){
			String[] record = scores.get(i).split(":");

			p.textAlign(PApplet.LEFT, PApplet.TOP);
			p.text(
					(scores.size() - i)	+ ". " + record[0], 
					p.width / 8, p.height / 4 + 
					((scores.size() - 1) - i) * (p.textAscent() + p.textDescent())
				);
			//Use another text to keep the columns consistent
			p.text(
					record[1],
					p.width / 8 + 2 * p.textWidth("5chars"), p.height / 4 + 
					((scores.size() - 1) - i) * (p.textAscent() + p.textDescent())
				);
		}
		p.popStyle();
	}
}
