package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

	private Handler handler;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		hsp = 5;
		vsp = 5;
	}

	public Polygon getBounds() {
		int[] polX = {x,x+32};
		int[] polY = {y,y+32};
		return new Polygon(polX,polY,2);
	}
	
	public void tick() {
		x+= hsp;
		y+= vsp;
		
		if(x <= 0 || x >= Game.WIDTH - 32) hsp *= -1;
		if(y <= 0 || y >= Game.HEIGHT - 16) vsp *= -1;
		
		handler.addObject(new Trail(x, y, ID.Trail,Color.red, 16, 16, 0.01f,handler));
		
	}

	
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
		
		g.setColor(Color.green);
		g2d.draw(getBounds());
		

	}

}
