package com.firstproject.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class Input implements KeyListener {

	private Handler handler;

	// control settings

	// buttons
	public int VK_LEFT = KeyEvent.VK_A;
	public int VK_UP = KeyEvent.VK_W;
	public int VK_RIGHT = KeyEvent.VK_D;
	public int VK_DOWN = KeyEvent.VK_S;

	public int VK_JUMP = KeyEvent.VK_SPACE;

	public static LinkedList<Integer> Inputs = new LinkedList<Integer>();

	// sticks
	private double MOVE_STICK;
	private double MOVE_LENGTH = 0;

	public Input(Handler handler) {
		this.handler = handler;

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int Key = e.getKeyCode();

		if (Key == VK_LEFT)
			handler.LEFT = true;
		if (Key == VK_RIGHT)
			handler.RIGHT = true;
		if (Key == VK_UP)
			handler.UP = true;
		if (Key == VK_DOWN)
			handler.DOWN = true;

		if (!Inputs.contains(Key))
			Inputs.add(Key);
		// System.out.println(Inputs);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int Key = e.getKeyCode();

		if (Key == VK_LEFT)
			handler.LEFT = false;
		if (Key == VK_RIGHT)
			handler.RIGHT = false;
		if (Key == VK_UP)
			handler.UP = false;
		if (Key == VK_DOWN)
			handler.DOWN = false;

		if (Inputs.contains(Key))
			Inputs.remove((Object) Key);
		// System.out.println(Inputs);
	}

}
