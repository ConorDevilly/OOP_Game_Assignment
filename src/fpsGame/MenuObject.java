package fpsGame;

import processing.core.*;

public class MenuObject extends GameObject{
	
	String text;
	boolean active;
	int activeCol;
	int inactiveCol;
	float xSize;
	float ySize;

	MenuObject(PApplet p, PVector pos, float size, String text, int activeCol, int inactiveCol){
		super(p);
		this.pos = pos;
		this.text = text;
		this.activeCol = activeCol;
		this.inactiveCol = inactiveCol;
		
		xSize = p.textWidth(text) / 2;
		ySize = p.textDescent() + p.textAscent();
	}

	@Override
	void update() {
		if(p.mouseX > pos.x - xSize && p.mouseX < pos.x + xSize
				&& p.mouseY < pos.y + ySize && p.mouseY > pos.y - ySize){
			active = true;
		}else{
			active = false;
		}
	}

	@Override
	void render() {
		p.pushMatrix();
		p.pushStyle();

		if(active){
			p.fill(activeCol);
		}else{
			p.fill(inactiveCol);
		}
		
		p.textSize(48);
		p.textAlign(PApplet.CENTER);
		p.text(text, pos.x, pos.y);

		p.popStyle();
		p.popMatrix();
	}
}
