package com.firstproject.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.SwingUtilities;

public class MouseInput implements MouseListener, MouseMotionListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Game.handler.MOUSE_Clicked = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Game.handler.MOUSE_Clicked = false;

	}

	@Override
	public void mouseEntered(MouseEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent evt) {
		evt = SwingUtilities.convertMouseEvent(evt.getComponent(), evt,
				SwingUtilities.windowForComponent(evt.getComponent()));
		Game.handler.MOUSE_Position.x = evt.getX() - 10;
		Game.handler.MOUSE_Position.y = evt.getY() - 30;

	}

	@Override
	public void mouseMoved(MouseEvent evt) {
		evt = SwingUtilities.convertMouseEvent(evt.getComponent(), evt,
				SwingUtilities.windowForComponent(evt.getComponent()));
		Game.handler.MOUSE_Position.x = evt.getX() - 10;
		Game.handler.MOUSE_Position.y = evt.getY() - 30;

	}

}
