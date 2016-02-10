package fpsGame;

import java.util.ArrayList;

import processing.core.*;

public class Menu extends GameObject{
	
	int active;
	int inactive;
	ArrayList<MenuObject> options;
	
	Menu(PApplet p) {
		super(p);
		pos.x = p.width / 2;
		pos.y = p.height / 4;
		
		active = p.color(0, 255, 0);
		inactive = p.color(255, 255, 0);
		
		GameObject play = new MenuObject(p, new PVector(pos.x, pos.y + 3 * (p.textDescent() + p.textAscent())), 50, 
				"Play", active, inactive);
		GameObject scores = new MenuObject(p, new PVector(pos.x, pos.y + 5 * (p.textDescent() + p.textAscent())), 50, 
				"Highscores", active, inactive);
		
		Main.objects.add(play);
		Main.objects.add(scores);
	}
	
	void update(){}
	
	void render(){
		p.pushMatrix();
		p.pushStyle();

		p.fill(inactive);
		p.textAlign(PApplet.CENTER);
		
		p.textSize(52);

		p.text("Random XWing Game", pos.x, pos.y);

		p.popStyle();
		p.popMatrix();
	}
}
