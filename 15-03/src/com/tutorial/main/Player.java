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

public class Player extends GameObject {
	
	
	
	Random r = new Random();
	Handler handler;
	//int x,int y,int[] SW, int[] SH, ID id,int px,int py
	public Player(int x, int y,int[] SW,int[] SH,int[] SD,int[] layers, ID id,int px,int py,int pz,Handler handler) {
		super(x, y, SW, SH, SD, layers, id, px, py, pz);
		this.handler = handler;
		weight = 100;

		int[] X = {-8,-12,-10  ,0   ,10   ,12  ,8 ,0};
		int[] Y=  {10,0+8,-12+8,-8+8,-12+8,0+8 ,10,16};
		
		int[] XL = {0,0,12,12,6};
		int[] YL=  {-6,6,3,-2,-4};
		
		int[] XR = {-6,-12,-12,0,0};
		int[] YR=  {-4,-2,3,6,-6};
		
		int[] XN = {7,-7,-5,-4,4,5};
		int[] YN=  {0,0,-2,-7,-7,-2};
		
		/*
		 		int[] X = {-8,-12,-10,0  ,10 ,12,8,0};
		int[] Y=  {10,0+8  ,-12+8,-8+8,-12+8,0+8 ,10,16};
		
		int[] XL = {0,0,12,12,6};
		int[] YL=  {-6,6,3,-2,-4};
		
		int[] XR = {-6,-12,-12,0,0};
		int[] YR=  {-4,-2,3,6,-6};
		
		int[] XN = {7,-7,-5,-4,4,5};
		int[] YN=  {0,0,-2,-7,-7,-2};
		 */
		
		//addPart(new Hip(x,y-8,X,Y,null,null, ID.Hip, 0,11,0,this,handler));
		//addPart(new shoulder(x,y-8,XR,YR,null,ID.shoulder, -16,-10,0,this,handler));
		//addPart(new shoulder(x,y-8,XL,YL,null,ID.shoulder,  16,-10,0,this,handler));
		//addPart(new neck(x,y-8,XN,YN,null,ID.neck, 0,-20,0,this,handler));
		//addPart(new Hip(x,y-8,new int[] {0,64,64,0},new int[] {-8,-8,8,8},ID.Hip,-20,0,this,handler));
		//addPart(new Limb(x,y-8,new int[] {0,64,64,0},new int[] {-8,-8,8,8},ID.Limb, -20,-31,this,handler));
		//addPart(new Limb(x,y-8,new int[] {0,64,64,0},new int[] {-8,-8,8,8},ID.Limb,  20,-31,this,handler));		
		
	}

	public void tick() {	
		
		//if(Game.BLeft == true)hsp=-5;else 
		//if(Game.BRight == true)hsp=5;else hsp=0;
				
		grv +=HUD.GRAVITY*(weight/50);
		if(vsp > 10)
		grv = 0;
		///vsp += grv;
					
		direction=Game.Deg(direction+dsp);
		Z_direction=Game.Deg(Z_direction);
		
		//direction++;
		if(Game.BDown == true)
		S_direction--;
		if(Game.BUp == true)
		S_direction++;
		
		if(Game.BLeft == true)
		Z_direction++;
		if(Game.BRight == true)
		Z_direction--;
		
		
		dsp=0;

		y+=vsp;
		x+=hsp;
		
		//collision_Balance(this,0);
		
		collision_Check();
			
		tickPart();	
		

	}
	
	/*public boolean collision_Check() {
		
	boolean CL = false;

	

	
	for(int i = 0; i < handler.object.size(); i++) { 
		GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Ground) {		
						
						
						for(int p=0;p<tempObject.getBounds().xpoints.length-1;p++) {
							
							int b;
							if(p+1 > tempObject.getBounds().xpoints.length-1)
							b=-p;else b=1;
							
							int[] pX = {tempObject.getBounds().xpoints[p],
										tempObject.getBounds().xpoints[p+b],
										tempObject.getBounds().xpoints[p]+1,
										tempObject.getBounds().xpoints[p+b]+1};
										
							int[] pY = {tempObject.getBounds().ypoints[p],
										tempObject.getBounds().ypoints[p+b],
										tempObject.getBounds().ypoints[p]+1,
										tempObject.getBounds().ypoints[p+b]+1};
							
							
							

							double dir = Game.Deg(Game.getAngle(new Point(pX[0],pY[0]),new Point(pX[1],pY[1])));

							
							int X = (pX[0]-pX[1])/2+pX[0];
							int Y = (pY[0]-pY[1])/2+pY[0];
							Point PT = new Point(X,Y);
							double pdir = Game.Deg(Game.getAngle(PT,new Point(x,y)));
							if(Game.inDeg(pdir, dir, Game.Deg(dir+180)))
							dir  =	Game.Deg(dir-90); else
							if(Game.inDeg(pdir, Game.Deg(dir+180), dir))
							dir  =	Game.Deg(dir+90);
							
							
							
							if(testIntersection(getBounds(),new Polygon(pX,pY,pX.length))) {
								CL = true;
								GameObject O=owner;
								while(testIntersection(getBounds(),new Polygon(pX,pY,pX.length))){	
																					
										if(Game.inDeg(dir, 315, 45)) {
											y--;	
											if(Y>0)Y--;
											if((Game.sign(vsp)==1)) {vsp = 0; grv=0;}	
											 O=owner;
											while(O!=null)
											{
												if((Game.sign(O.vsp)==1))O.vsp = 0;	
											O.y--;
											O=O.owner;}
											}else
										if(Game.inDeg(dir, 135, 225)) {
											
											y++;
											if(Y>0)Y++;
											if((Game.sign(vsp)==-1))vsp = 0;	
											O=owner;
											while(O!=null)
												{
													if((Game.sign(O.vsp)==-1))O.vsp = 0;	
												O.y++;
												O=O.owner;}
										}
										if(Game.inDeg(dir, 45, 135)) {
											
											x--;	
											if(X>0)X--;
											if((Game.sign(hsp)==1))hsp = 0;
											 O=owner;
												while(O!=null)
												{
													if((Game.sign(O.hsp)==1))O.hsp = 0;	
												O.x--;
												O=O.owner;}
											}else
										if(Game.inDeg(dir, 225, 315)) {
											
											x++;
											if(X<0)X++;
											if((Game.sign(hsp)==-1))hsp = 0;
											 O=owner;
												while(O!=null)
												{
													if((Game.sign(O.hsp)==-1))O.hsp = 0;	
												O.x++;
												O=O.owner;}
										}
										
									}
								
								}
							
						}
						

					
					if(collision(x,y+1,tempObject)||collision(x,y,tempObject)) {
							grv = 0;
						}else if((int)grv == 0)
					if (!collision(x,y+1,tempObject)){ 
						grv = 1;
					}
					
			}			

		}
	return CL;
	}*/
	

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
							
							if(!O.collision_Check());
							A=O.collision_Branch(TH,BL);
						}
					}
					
				}
		return A;
			
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
					
					for(int j=0;j<LimpParts.size();j++) {
						GameObject O=LimpParts.get(j);
					if(!O.collision(x,y,tempObject))
					while(O.collision(x+TH.hsp,y+TH.vsp,tempObject)) {
						A=true;
						TH.grv=0;
						TH.hsp-=Game.sign(TH.hsp);
						TH.vsp-=Game.sign(TH.vsp);	
					}}
				}
					
				}
		return A;
			
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
		
		//renderPart(g);
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


	
}
