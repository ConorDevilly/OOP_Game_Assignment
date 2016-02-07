package fpsGame;

import processing.core.*;

public class Menu extends GameObject{
	
	float initFrame;
	
	Menu(PApplet p) {
		super(p);
		pos.x = p.width / 2;
		pos.y = p.height;
		
		initFrame = p.frameCount;
	}
	
	void update(){
		if(p.frameCount - initFrame < 180){
			
		}else{
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
		}else if(pos.y > 0){
			p.rotateX(PApplet.PI / 5);
			p.stroke(255, 255, 0);
			p.fill(255, 255, 0);
			p.textSize(12);
			p.text("A compute science student attempted "
					+ "\n to make a first-person shooter in processing"
					+ "\n He failed miserably."
					+ "\n But from his failure a new game arose..."
					+ "\n A game that ripped off a well known franchise so bad,"
					+ "\n he feared that Mickey Mouse's lawyers would wipe it off"
					+ "\n the face of the internet"
					+ "\n Its name:"
					+ "\n Nameless X-Wing Game"
					, pos.x, pos.y);
		}else{
		}

		p.popStyle();
		p.popMatrix();
	}

}
