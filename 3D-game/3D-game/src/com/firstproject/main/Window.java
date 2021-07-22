package com.firstproject.main;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Window extends Canvas{

	private static final long serialVersionUID = 2251249791872570777L;
	public JFrame frame;
	public Window(int width, int height, String title, Game game) {
		
		frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		Container content = frame.getContentPane();
		
		
		//frame.getContentPane().add(game.panel);
		for(int i=0;i<game.BUTTONS.size();i++)
		frame.add(game.BUTTONS.get(i));
		for(int i=0;i<game.SPINNERS.size();i++)
		frame.add(game.SPINNERS.get(i));
		for(int i=0;i<game.SLIDERS.size();i++)
		frame.add(game.SLIDERS.get(i));
		for(int i=0;i<game.JCOMBOBOX.size();i++) 
		frame.add(game.JCOMBOBOX.get(i));
		for(int i=0;i<game.JTEXTAREA.size();i++)
		frame.add(game.JTEXTAREA.get(i));
		
		frame.add(game);
		
		
		
		
		
		frame.setVisible(true);
		game.start();
	}
	
}
