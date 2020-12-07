package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.LinkedList;



public abstract class Part extends GameObject{

	protected LinkedList<Part> LimpParts = new LinkedList<Part>();
	protected GameObject H_owner;
	
	public Part(int x,int y,int[] SW, int[] SH,int[] SD,int[] layers, ID id,int px,int py,int pz,GameObject owner) {
		super(x,y,SW,SH,SD,layers,id, py, py, pz);
		this.px = px;
		this.py = py;
		this.pz = pz;
		this.owner = owner;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void tickPart() {
		for(int i = 0; i < LimpParts.size(); i++) {
			Part tempObject = LimpParts.get(i);
			
			tempObject.tick();
		}
	}
	
	public Area getAllBounds(){
		
		
		Point Zpt = (Game.rotatePoint(new Point(px,pz),new Point(0,0),owner.Z_direction));
		Point Spt = (Game.rotatePoint(new Point(py, Zpt.y),new Point(0,0),owner.S_direction));
		Point Dpt = (Game.rotatePoint(new Point(Zpt.x,Spt.x),new Point(0,0),owner.direction));	
		
		
		x=owner.x+Dpt.x;
		y=owner.y+Dpt.y;
		z=owner.z+Spt.y;
		
		Area A = new Area(getBounds());

			for(int i=0;i<LimpParts.size();i++) { 
				GameObject b=LimpParts.get(i);
				A.add(b.getAllBounds());
			}
			
		return new Area(A);

	}
	
	public Area getAllArea(){
		
		Area A = getArea();
			
			for(int i=0;i<LimpParts.size();i++) { 
				GameObject b=LimpParts.get(i);
				A.add(b.getAllArea());
			}
		
		return new Area(A);

	}
	
	public void renderPart(Graphics g) {
		for(int i = 0;i < LimpParts.size(); i++) {
		Part tempObject = LimpParts.get(i);
		
		tempObject.render(g);
		}
	}
	
	public void addPart(Part LimpParts) {
		this.LimpParts.add(LimpParts);
	}
	
	public void removePart(Part LimpParts) {
		this.LimpParts.remove(LimpParts);
	}
	
	
    public boolean collision_Check() {
		
		boolean CL = false;
		Point Zpt = (Game.rotatePoint(new Point(px,pz),new Point(0,0),owner.Z_direction));
		Point Spt = (Game.rotatePoint(new Point(py, Zpt.y),new Point(0,0),owner.S_direction));
		Point Dpt = (Game.rotatePoint(new Point(Zpt.x,Spt.x),new Point(0,0),owner.direction));	
		
		
		x=owner.x+Dpt.x;
		y=owner.y+Dpt.y;
		z=owner.z+Spt.y;
		
		for(int i = 0; i < Game.handler.object.size(); i++) { 
			GameObject tempObject = Game.handler.object.get(i);
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
								
								
								
								if(testIntersection(getAllBounds(),new Polygon(pX,pY,pX.length))) {
									CL = true;
									GameObject O=owner;
									int T=0;
									while(testIntersection(getAllBounds(),new Polygon(pX,pY,pX.length))){	
									if(T>1000)
									System.out.println("test");
																						
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
		}


	
	public boolean collision(int x,int y,GameObject tempObject) {
 		
		
		
		
		int X = this.x;
		int Y = this.y;
		this.x = x;
		this.y = y;
		
		if (testIntersection(getAllBounds(),tempObject.getAllBounds())) {
		this.x = X;
		this.y = Y;
		return true;
		}else {
		this.x = X;
		this.y = Y;
		return false;
		}
		
		}
	
	
}
