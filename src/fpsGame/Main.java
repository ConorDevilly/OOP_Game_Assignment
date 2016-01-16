package fpsGame;

import processing.core.*;

public class Main extends PApplet{

	public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "fpsGame.Main" });
	}
	
	public void settings(){
		  size(500, 500, P3D);
	}
	
	PVector camPos;
	public void setup(){
		camPos = new PVector(width / 2, height / 2, 0);
	}

	public void draw(){
		update();
		background(0);
		//Mouse movement for this seems right
		//camera(camPos.x, camPos.y, camPos.z, mouseX, mouseY, 0, 0, 1, 0);
		camera(camPos.x, camPos.y, camPos.z, mouseX, mouseY, 0, 0, 1, 0);
		translate(width/2, height/2, -100);
		stroke(255);
		noFill();
		box(200);
	}
	
	public static boolean[] keys = new boolean[512];

	public void keyPressed()
	{
		keys[keyCode] = true;
	}

	public void keyReleased()
	{
		keys[keyCode] = false;
	}

	public void update(){
		float speed = 5f;
		if (keys['A'])
		{
			camPos.x -= speed;
		}
		if (keys['D'])
		{
			camPos.x += speed;
		}
		if (keys['W'])
		{
			camPos.z -= speed;
		}
		if (keys['S'])
		{
			camPos.z += speed;
		}
	}
}
