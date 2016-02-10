package fpsGame;

import processing.core.*;

public class TieInterceptor extends TieFighter{

	TieInterceptor(PApplet p, PVector pos) {
		super(p, pos);
	}
	
	@Override
	void renderWing(PVector location){
		p.noFill();
		p.stroke(0, 255, 0);

		p.beginShape();

		//Dying animation
		if(dying){
			p.translate(location.x + 100 * rot, location.y, location.z);
			p.rotateX(rot);
			p.rotateY(-rot);
		}else{
			p.translate(location.x, location.y, location.z);
		}
		p.vertex(0, 0, 0);
		p.vertex(0, -qsize, 0);
		p.vertex(0, -qsize, -hsize);
		p.vertex(0, -hsize, -qsize);
		p.vertex(0, -hsize, hsize);
		p.vertex(0, hsize, hsize);
		p.vertex(0, hsize, -qsize);
		p.vertex(0, qsize, -hsize);
		p.vertex(0, -qsize, 0);
		p.endShape();
		p.translate(-location.x, -location.y, -location.z);
	}

}
