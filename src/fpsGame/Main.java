package fpsGame;

import processing.core.*;

import java.util.ArrayList;

public class Main extends PApplet{

	/*
	public static void main(String args[]) {
	    PApplet.main(new String[] { "--present", "fpsGame.Main" });
	}
	
	public void settings(){
		  size(800, 600, P3D);
	}
	*/
	
	//TODO: Investigate Core Dumps
	
	ArrayList<GameObject> objects = new ArrayList<GameObject>();
	Player p1;
	public static boolean[] keys = new boolean[512];
	int squares;

	public void setup(){
		size(displayWidth, displayHeight, P3D);
		//size(500, 500, P3D);
		cursor(CROSS);
		p1 = new Player(this, new PVector(0, 0, 0), keys);
		objects.add(p1);
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
		
		/*
		pushMatrix();
		translate(width / 2, height / 2 + 200, 0);
		fill(153, 102, 0);
		box(500);
		popMatrix();
		*/
		
		for(GameObject o : objects){
			o.update();
			o.render();
		}
	}

	public void keyPressed()
	{
		keys[keyCode] = true;
	}

	public void keyReleased()
	{
		keys[keyCode] = false;
	}
}
