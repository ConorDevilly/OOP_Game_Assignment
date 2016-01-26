package fpsGame;

import processing.core.*;
import java.util.ArrayList;

public class Space extends GameObject{
	
	int numStars;
	float speed;
	ArrayList<PVector> stars;
	
	Space(PApplet p){
		super(p);
		stars = new ArrayList<PVector>();
		speed = 3;

		numStars = (int) p.random(100, 500);
		for(int i = 0; i < numStars; i++){
			genStar();
		}
	}
	
	
	void genStar(){
		PVector star = new PVector();
		star.x = p.random(0, p.width);
		star.y = p.random(0, p.height);
		star.z = p.random(-1000, 0); //Z Max????
		stars.add(star);
	}

	@Override
	void update() {
		for(int i = 0; i < stars.size(); i++){
			PVector s = stars.get(i);
			s.z += speed;
			if(s.z > -1){
				genStar();
				stars.remove(s);
			}
		}
	}

	@Override
	void render() {
		for(PVector s : stars){
			p.pushMatrix();
			p.pushStyle();
			
			p.fill(255);
			p.stroke(255);
			p.translate(s.x, s.y, s.z);
			p.sphereDetail(3);
			p.sphere(2);
			
			p.popStyle();
			p.popMatrix();
		}
	}
}
