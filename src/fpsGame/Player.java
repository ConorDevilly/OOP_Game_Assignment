package fpsGame;

import processing.core.*;

public class Player extends GameObject{
	
	PVector eyePos;
	PVector centerPos;
	PApplet p;
	boolean[] keys;
	
	Player(PApplet p, boolean[] keys){
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
		
		forward.x = PApplet.sin(theta);
		forward.z = PApplet.cos(theta);
		forward.mult(speed);

		if (keys['A'])
		{
			eyePos.x -= forward.x;
			//As the center position is the mouse, we subtract the speed from the mouse instead
			p.mouseX -= forward.x;
		}
		if (keys['D'])
		{
			eyePos.x += forward.x;
			p.mouseX += forward.x;
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

		centerPos.y = p.mouseY;
		centerPos.x = p.mouseX;

		/*
		float xDif = p.mouseX - eyePos.x;
		if(xDif > 100)
			eyePos.x++;
		else if(xDif < 100)
			eyePos.x--;
		*/
			
	}
	
	void render(){
		p.camera(eyePos.x, eyePos.y, eyePos.z, centerPos.x, centerPos.y, centerPos.z, 0, 1, 0);

		p.pushStyle();
		p.fill(255, 0, 0);
		p.textAlign(PApplet.CENTER, PApplet.BOTTOM);
		p.text("eX", eyePos.x, eyePos.y);
		p.text("cX", centerPos.x, centerPos.y, centerPos.z);
		p.text("mouseX", p.mouseX, p.mouseY, centerPos.z);
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
