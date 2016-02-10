package fpsGame;

import java.util.ArrayList;

import processing.core.*;

public class HighscoresScreen extends GameObject{

	ArrayList<String> scores;
	int active;
	int inactive;
	PVector col1;
	PVector col2;
	float textHeight;

	HighscoresScreen(PApplet p, ArrayList<String> scores) {
		super(p);
		this.scores = scores;

		col1 = new PVector(p.width / 8, p.height / 4);
		col2 = new PVector(col1.x + 3 * p.textWidth("5chars"), col1.y);
		textHeight = p.textDescent() + p.textAscent();

		active = p.color(0, 255, 0);
		inactive = p.color(255, 255, 0);
		
		GameObject back = new MenuObject(p, new PVector(p.width / 2, 5 * p.height / 6), 50, 
				"Menu", active, inactive);
		
		Main.objects.add(back);
	}

	@Override
	void update() {}

	@Override
	void render() {
		p.pushStyle();
		
		for(int i = 0; i < scores.size(); i++){
			String[] record = scores.get(i).split(":");
			//Records stored in reverse order, so we keep a "reverse" int variable
			int reverse = scores.size() - i;

			p.textAlign(PApplet.LEFT, PApplet.TOP);
			p.fill(active);
			p.text("   Player", col1.x, col1.y - textHeight);
			p.text("Score", col2.x, col2.y - textHeight);
			p.fill(inactive);
			p.text(
					reverse	+ ". " + record[0], 
					col1.x, col1.y + 
					(reverse) * textHeight
				);
			//Use another text to keep the columns consistent
			p.text(
					record[1],
					col2.x, col2.y + 
					(reverse) * textHeight
				);
		}
		p.popStyle();
	}
}
