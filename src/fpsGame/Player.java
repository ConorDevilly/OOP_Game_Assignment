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
	
	void update(){
		float speed = 5f;

		centerPos.y = p.mouseY;
		centerPos.x = p.mouseX;
		
		if (keys['A'])
		{
			eyePos.x -= speed;
			centerPos.x -= speed;
		}
		if (keys['D'])
		{
			eyePos.x += speed;
			centerPos.x += speed;
		}
		if (keys['W'])
		{
			eyePos.z -= speed;
			centerPos.z -= speed;
		}
		if (keys['S'])
		{
			eyePos.z += speed;
			centerPos.z += speed;
		}
	}
	
	void render(){
		p.camera(eyePos.x, eyePos.y, eyePos.z, centerPos.x, centerPos.y, centerPos.z, 0, 1, 0);
	}

}
