package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;

public class Ground extends GameObject {


	Random r = new Random();
	Handler handler;
	
	
	public Ground(int x, int y,int[] SW,int[] SH,int[] SD,int[] layers, ID id,int px,int py, int pz,Handler handler) {
		super(x, y, SW, SH, SD, layers, id, py, py, pz);
		this.handler = handler;
		weight = 100;
	}

	public Polygon getBounds() {
		
		int[] polX = new int[SW.length];
		int[] polY = new int[SH.length];
		for(int i=0;i<polX.length;i++) {
			polX[i] = SW[i]+x;
			polY[i] = SH[i]+y;
		}
		
		
		Point Dpt[] = new Point[polX.length];
		
		for(int i=0;i<polX.length;i++) {
		Dpt[i] = (Game.rotatePoint(new Point(polX[i], polY[i]),new Point(x,y),direction));
		polX[i] = Dpt[i].x;
		polY[i] = Dpt[i].y;
		}
		return new Polygon(polX,polY,polX.length);

	}
	
	public void tick() {
		
		HitArea = getArea();
		HitPolygon = getBounds();
		
		TetrodonDepthNow= getTertradonDepth(Z_direction,S_direction);
		TetrodonNow = getTertradon(direction,Z_direction,S_direction);
			//if(Game.BUp)direction++;
			//if(Game.BDown)direction--;

	}
	

	public  boolean collision(int x,int y,GameObject tempObject) {
		 		
		
		
		
			int X = this.x;
			int Y = this.y;
			this.x = x;
			this.y = y;
			
			if (testIntersection(getBounds(),tempObject.getBounds())) {
			this.x = X;
			this.y = Y;
			return true;
			}else {
			this.x = X;
			this.y = Y;
			return false;
			}
			
			}
			
		
	

	public void render(Graphics g) {
		
		//BufferedImage Skin = LoadImage("blok.png");
		
		//AffineTransform at = AffineTransform.getTranslateInstance(x-16,y-16);
		//at.rotate(Math.toRadians(direction+=hsp), Skin.getWidth()/2, Skin.getHeight()/2); 
		Graphics2D g2d = (Graphics2D) g;
		
		//g2d.drawImage(Skin, at, null);
		
		g.setColor(Color.red);
		g2d.draw(getBounds());
		
		
		}
	
	BufferedImage LoadImage(String FileName) 
	{
		BufferedImage img = null;
		
		try {
		img = ImageIO.read(getClass().getResourceAsStream("/"+FileName));
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return img;
	}
	
	public static boolean testIntersection(Shape shapeA, Shape shapeB) {
		   Area areaA = new Area(shapeA);
		   areaA.intersect(new Area(shapeB));
		   return !areaA.isEmpty();
		}

	@Override
	protected boolean collision_Balance(GameObject TH, int BL) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<GameObject> Balance_Array(GameObject TH, ArrayList<GameObject> bL) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean collision_Branch(GameObject TH, int X) {
		// TODO Auto-generated method stub
		return false;
	}


}
