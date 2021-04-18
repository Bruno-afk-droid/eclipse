package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Handler {

	


	
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	public static List<DrawFrame> Render = new ArrayList<DrawFrame>();
	double direction = 0;
	

	
	
	public void tick() {
		Render.clear();	
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
			tempObject.UpdateHitBox();
		}
		Game.test.Turn(0, direction++, -45);
		
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Game.test.draw(g2d);
		
		for(int i=0;i<Render.size();i++) 
		Render.get(i).Draw(g2d);
		
	}
	
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
}
