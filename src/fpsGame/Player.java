package fpsGame;

import processing.core.*;

public class Player extends GameObject{
	
	PVector eyePos;
	PVector centerPos;
	PVector straight;
	PApplet p;
	boolean[] keys;
	
	Player(PApplet p, PVector pos, boolean[] keys){
		super(pos);
		this.p = p;
		this.keys = keys;
		eyePos = new PVector(p.width / 2, (p.height / 2) - 100, (p.height / 2) / PApplet.tan(PApplet.PI / 6));
		centerPos = new PVector(p.width / 2, p.height / 2, 0);
	}
	
	/*
	 * eyePos & mousePos = itself +/- speed * sin/cos(angle???)
	 * Value to rotate by is previousCenter - newCenter
	 * Set mouseX to newCenter?
	 * RotateY - Rotating AROUND the Y axis
	 *
	 */
	
	void update(){
		//TODO: Angles.
		//TODO: Need unit circle kind of movement
		float speed = 5f;
		straight = new PVector(eyePos.x, eyePos.y, centerPos.z);

		theta = PApplet.degrees(PApplet.acos(eyePos.dist(straight) / eyePos.dist(centerPos)));
		
		forward.x = PApplet.sin(theta);
		forward.z = PApplet.cos(theta);
		forward.mult(speed);

		//TODO: Change to apply movement
		if (keys['A'])
		{
			centerPos.x -= 15;
			centerPos.z -= 1;
			/*
			eyePos.x -= forward.x;
			//As the center position is the mouse, we subtract the speed from the mouse instead
			p.mouseX -= forward.x;
			*/
		}
		if (keys['D'])
		{
			centerPos.x += 15;
			centerPos.z -= 1;
			/*
			eyePos.x += forward.x;
			p.mouseX += forward.x;
			*/
		}
		if (keys['W'])
		{
			eyePos.z -= forward.z;
			centerPos.z -= forward.z;
		}
		if (keys['S'])
		{
			eyePos.z += forward.z;
			centerPos.z += forward.z;
		}

		//TODO: Change theta
		//centerPos.y = p.mouseY;
		//centerPos.x = p.mouseX;
		
		/*
		float xDif = p.mouseX - eyePos.x;
		if(xDif > 100)
			p.mouseX += 1000;
		else if(xDif < 100)
			p.mouseX -= 1000;
		
		p.mouseX = p.width / 2;
		*/
			
	}
	
	void render(){
		p.camera(eyePos.x, eyePos.y, eyePos.z, centerPos.x, centerPos.y, centerPos.z, 0, 1, 0);

		//DEBUG
		p.pushStyle();
		p.fill(255, 0, 0);
		p.textAlign(PApplet.CENTER, PApplet.BOTTOM);
		p.text("eX", eyePos.x, eyePos.y);
		p.text("cX", centerPos.x, centerPos.y, centerPos.z);
		p.text(theta, p.mouseX, p.mouseY + 10, centerPos.z);
		p.text("mouseX", p.mouseX, p.mouseY, centerPos.z);
		p.text("P3", eyePos.x, eyePos.y, centerPos.z);
		p.popStyle();

		/*
		p.pushMatrix();
		p.pushStyle();

		p.translate(eyePos.x, eyePos.y, eyePos.z);


		p.beginShape();

		p.fill(255, 0, 0);
		p.stroke(255, 0, 0);

		p.vertex(-25, -50, centerPos.z);
		p.vertex(25, -50, centerPos.z);
		p.vertex(25, -100, centerPos.z);
		p.vertex(-25, -100, centerPos.z);

		p.endShape(PApplet.CLOSE);
		
		p.popStyle();
		p.popMatrix();
		*/
	}

}
