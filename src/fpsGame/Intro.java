package fpsGame;

import processing.core.PApplet;

public class Intro extends GameObject{

	float initFrame;
	int active;
	int inactive;
	
	public Intro(PApplet p) {
		super(p);
		pos.x = p.width / 2;
		pos.y = p.height;
		
		initFrame = p.frameCount;
	}
	
	void update(){
		if(p.frameCount - initFrame < 180){
			
		}else if(pos.y > p.height / 4){
			pos.y -= 0.5;
		}
	}
	
	void render(){
		p.pushMatrix();
		p.pushStyle();

		p.textAlign(PApplet.CENTER);

		if(p.frameCount - initFrame < 180){
			p.stroke(51, 102, 255);
			p.fill(51, 102, 255);
			p.text("Not long ago, \n in this very galaxy...", pos.x, p.height / 2);
		}else if(pos.y > p.height / 4){
			p.rotateX(PApplet.PI / 5);
			p.stroke(255, 255, 0);
			p.fill(255, 255, 0);
			p.textSize(14);
			p.text("A compute science student attempted "
					+ "\n to make a first-person shooter "
					+ "\n in Processing."
					+ "\n He failed miserably."
					+ "\n But from his failure a new game arose..."
					+ "\n A game that ripped off a well known franchise so bad,"
					+ "\n he feared that Mickey Mouse's lawyers would wipe it off"
					+ "\n the face of the internet."
					+ "\n\n Its name:"
					+ "\n Random X-Wing Game"
					+ "\n\n\nPress space to play..."
					, pos.x, pos.y);
		}

		p.popStyle();
		p.popMatrix();
	}
}
