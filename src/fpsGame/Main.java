package fpsGame;

import processing.core.*;

public class Main extends PApplet{

	/*
	public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "fpsGame.Main" });
	}
	
	public void settings(){
		  size(800, 600, P3D);
	}
	*/
	
	PVector eyePos;
	PVector centerPos;
	int squares;

	public void setup(){
		size(800, 600, P3D);
		eyePos = new PVector(width / 2, (height / 2) - 100, (height / 2) / tan(PI / 6));
		centerPos = new PVector(width / 2, height / 2, 0);
		squares = 20;
	}

	public void draw(){
		background(0);

		for(int i = 0; i < squares; i++){
			for(int j = 0; j < squares; j++){
				pushMatrix();
				float x = width/2 + i * 100 -(20 * 100/2);
				float z = j * 100 - (squares * 100/2);
			    translate(x, height / 2, z);
			    fill(255);
			    box(100); 
			    fill(0);
			    translate(0, -55, 0);
			    text("I: " + i, -40, 0);
			    text("J: " + j, 10, 0);
			    popMatrix();
			}
		 }

		update();
		camera(eyePos.x, eyePos.y, eyePos.z, centerPos.x, centerPos.y, centerPos.z, 0, 1, 0);
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

		centerPos.y = mouseY;
		
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
}
