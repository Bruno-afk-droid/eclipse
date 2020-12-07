package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	public static int HEALTH = 100;
	public static float GRAVITY = 0.0007f;
	public static int KEY_LEFT = 0;
	public static int KEY_RIGHT = 0;
	public static int KEY_UP = 0;
	public static int KEY_DOWN = 0;
	
	public void tick() {
		HEALTH = Game.clamp(HEALTH, 0, 100);
		
	}
	
	public void render(Graphics g) {

	}
	
}
