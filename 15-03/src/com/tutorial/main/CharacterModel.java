package com.tutorial.main;

import java.awt.Graphics2D;
import java.util.LinkedList;


public class CharacterModel {
	protected Skeloton Skeloton;
	protected int x;
	protected int y;
	protected int z;
	
	protected double Z_direction;
	protected double Y_direction;
	protected double X_direction;
	
	public CharacterModel(Skeloton Skeloton){
		this.Skeloton=Skeloton;
	}
	
	public void Draw(Graphics2D g) {
		Skeloton.Draw(g);
	}
	
	
}
