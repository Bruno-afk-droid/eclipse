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
import java.util.LinkedList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.Line;

public class fore_arm extends Part {


	Random r = new Random();
	Handler handler;

	
	public fore_arm(int x, int y,int[] SW,int[] SH,int[] SD, ID id,int px,int py,int pz,GameObject owner,Handler handler) {
		super(x, y, SW, SH, SD, id,px,py,pz, owner);
		this.handler = handler;
		weight = 10;
		direction = 0;
		
		GameObject O=this;
		while(O.owner!=null) 
		O=O.owner;
		H_owner=O;
		
		int[] X = {-4,-4,4,4};
		int[] Y=  {0,18,18,0};
		
		//addPart(new tibia(x,y-16,X,Y,ID.tibia, 0,15,this,handler));
		//addPart(new tibia(x,y-16,X,Y,ID.tibia, 0,15,this,handler));
	}

	
	
	public void tick() {
		
		//dsp=Game.sign(hsp)*5;
		

		
		//if(Game.BDown)dsp=Game.sign(px);else
		//if(Game.BUp)dsp=-Game.sign(px); else

			
		direction=owner.direction;
		Z_direction=owner.Z_direction;
		S_direction=owner.S_direction;
		
		
		x = Game.clamp(x, 0, Game.WIDTH  -47);
		y = Game.clamp(y, 0, Game.HEIGHT -70);
		
		

		
		
		
		
		//owner.collision_Balance(this,owner.x);
		//direction_Balance(this,owner.x);
		
		
		//collision_Check();
		
		tickPart();
		
	}
	
	public ArrayList<GameObject> Balance_Array(GameObject TH,ArrayList<GameObject> BL) {

		
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);	
			
			if(tempObject.getId() == ID.Ground){
				
				if(collision(x,y+1,tempObject))
				BL.add(this);
					
				
				for(int j=0;j<LimpParts.size();j++) {
					GameObject O=LimpParts.get(j);
					BL=O.Balance_Array(TH,BL);
				}
				
			}
			
		}
	
	return BL;
}
	
	public boolean collision_Balance(GameObject TH,int BL) {
		
		boolean A=false;
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);	
				
				if(tempObject.getId() == ID.Ground){
				
					if(!collision(x,y,tempObject))
					while(collision(x+TH.hsp,y+TH.vsp,tempObject)) {
						A=true;
						TH.grv=0;
						TH.hsp-=Game.sign(TH.hsp);
						TH.vsp-=Game.sign(TH.vsp);	
					}
						if((TH.hsp!=0)&&(TH.vsp!=0))
						for(int j=0;j<LimpParts.size();j++) {
							GameObject O=LimpParts.get(j);
								O.collision_Balance(TH,BL);}
					}
					
				}
		return A;
			
		}	
	
	public boolean collision_Branch(GameObject TH,int BL) {
		
		boolean A=false;
		for(int i=0;i<handler.object.size();i++) {
			GameObject tempObject = handler.object.get(i);	
				
				if(tempObject.getId() == ID.Ground){
				
					if(Game.sign(x-TH.x)==Game.sign(BL))
					if(collision(x,y+1,tempObject)) 
					A=true;
					
					
						for(int j=0;j<LimpParts.size();j++) {
							GameObject O=LimpParts.get(j);
							
							if(Game.sign(O.x-BL)==Game.sign(TH.x-BL)) {
							O.collision_Check();
							O.collision_Balance(TH,BL);}
						}
					}
					
				}
		return A;
			
		}
	
	public boolean direction_Balance(GameObject TH,int BL) {
		
		Point Dpt = (Game.rotatePoint(new Point(px,py),new Point(0,0),owner.direction));
		double D=Game.Deg(Game.getAngle(new Point(0,0),new Point(Dpt.x,Dpt.y)));
		
				double A=Game.Deg(D-Game.getAngle(new Point(0,0), new Point(Dpt.x+hsp,Dpt.y+vsp)));	
				owner.dsp+=A;	
				
				return false;	
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
		
		Point Dpt = (Game.rotatePoint(new Point(px,py),new Point(0,0),owner.direction));
		g.setColor(Color.yellow);
		g2d.drawLine(x, y, owner.x+Dpt.x, owner.y+Dpt.y);
		
		renderPart(g);
		
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

}
