package com.firstproject.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.LinkedList;

public class Handler {

	// actions

	public static boolean LEFT;
	public static boolean RIGHT;
	public static boolean UP;
	public static boolean DOWN;

	public static boolean JUMP;
	public static double MOVE_ANGLE;
	public static double MOVE_ANGLE_LENGTH = 0;

	public static Position MOUSE_Position = new Position(0, 0, 0);
	public static boolean MOUSE_Clicked = false;

	public static int getObjectIndex(GameObject O) {
		for (int i = 0; i < object.size(); i++)
			if (object.get(i) == O)
				return i;
		return 0;
	}

	public static LinkedList<Button> Buttons = new LinkedList<Button>();
	public static AnimationEditor AnimationEditor = new AnimationEditor();
	public static LinkedList<GameObject> object = new LinkedList<GameObject>();
	public static Structure Map = new Structure(new Line2D.Float[] { new Line2D.Float(0.0f, 400.0f, 640.0f, 400.0f), });

	public static void tick() {

		if (AnimationEditor.GAMEMODE == "DEFAULD") {

			for (int i = 0; i < object.size(); i++) {
				GameObject tempObject = object.get(i);
				tempObject.tick();
			}
		} else {
			AnimationEditor.tick();
		}

		// DEBUGGERMODE
		boolean B = false;
		for (int i = 0; i < Buttons.size(); i++) {
			if (!B)
				B = Buttons.get(i).isTriggerd(MOUSE_Position, MOUSE_Clicked);
			Buttons.get(i).tick();
		}
	}

	public void render(Graphics g) {

		for (int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render((Graphics2D) g);
		}
		g.setColor(Color.GREEN);
		Map.Draw((Graphics2D) g);

		g.drawString("FPS: " + Game.FPS_now, 0, 20);
		// DEBUGGERMODE
		AnimationEditor.render((Graphics2D) g);
		for (int i = 0; i < Buttons.size(); i++) {
			Buttons.get(i).render((Graphics2D) g);
		}
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

}
