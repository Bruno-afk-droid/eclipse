package com.tutorial.main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

public abstract class GameObject {

	protected BufferedImage texture;
	protected int x, y, z=0;
	protected int[] SW,SH,SD;
	protected int[] layers;
	protected ID id;
	protected int hsp, vsp;
	protected double dsp;
	protected double grv = 0; 
	protected int weight;
	protected double direction = 0;
	protected double Z_direction = 0;
	protected double S_direction = 0;
	public LinkedList<Part> LimpParts = new LinkedList<Part>();
	protected GameObject owner;
	protected GameObject H_owner;
	protected String ObjectType="Soild";

	public int px;
	public int py;
	public int pz;
	
	
	public GameObject(int x,int y,int[] SW, int[] SH,int[] SD,int[] layers, ID id,int px,int py,int pz) {
		this.x = x;
		this.y = y;
		this.SW = SW;
		this.SH = SH;
			if(SD==null) {
			this.SD = new int[SW.length];
			for(int i=0;i<SW.length;i++)
			this.SD[i]=0;
			}else
		this.SD=SD;
		if(layers==null) {
		this.layers = new int[1];
		this.layers[0]=SW.length;
		}else this.layers = layers;
		this.id = id;
		this.px=px;
		this.py=py;
		this.pz=pz;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public LinkedList<Polygon> getTertradon(){


		/*
		 {x-16,x+16,x+16,x-16};
		 {y-16,y-16,y+16,y+16};
		 */
		//SD[i] = Math.sqrt(x^2)
		


		
		LinkedList<Polygon> Polys = new LinkedList<Polygon>();
		
		
		int o=0;
		
		int[] pX = new int[SW.length];
		int[] pY = new int[SW.length];
        for(int L=0;L<layers.length;L++) {
        	
    		int[] polX = new int[layers[L]];
    		int[] polY = new int[layers[L]];
    		int[] polZ = new int[layers[L]];
    		

    		
    		
    		Point Dpt[] = new Point[polX.length];
    		
    		Polygon SP = new Polygon();
	        	for(int i=0;i<layers[L];i++) {	
					
				
				polX[i] = SW[i+o]+x;
				polY[i] = SH[i+o]+y;					
				
				polZ[i]= (Game.rotatePoint(new Point(polX[i], SD[i+o]+z),new Point(x,z),Z_direction)).y;
				polX[i]= (Game.rotatePoint(new Point(polX[i], SD[i+o]+z),new Point(x,z),Z_direction)).x;	
				
				int SDZ=polZ[i];		
			    polZ[i]= (Game.rotatePoint(new Point(polY[i], SDZ),new Point(y,z),S_direction)).y;
				polY[i]= (Game.rotatePoint(new Point(polY[i], SDZ),new Point(y,z),S_direction)).x;				
					
				Dpt[i] = (Game.rotatePoint(new Point(polX[i], polY[i]),new Point(x,y),direction));
				polX[i] = Dpt[i].x;
				polY[i] = Dpt[i].y;		
				
				if(i!=0)
				if(L!=0) {
				Polys.add(new Polygon(new int[] {polX[i],pX[i],pX[i-1],polX[i-1]},new int[] {polY[i],pY[i],pY[i-1],polY[i-1]},4));	
				}
				
		        	}	
	        	
		Polys.add(new Polygon(polX,polY,polX.length));
	        	

		pX=polX;
		pY=polY;
		
			o+=layers[L];
        }
        	
        
        return  Polys;
	}

	public LinkedList<Polygon> getTertradon(double direction,double Z_direction,double S_direction){


		/*
		 {x-16,x+16,x+16,x-16};
		 {y-16,y-16,y+16,y+16};
		 */
		//SD[i] = Math.sqrt(x^2)
		


		
		LinkedList<Polygon> Polys = new LinkedList<Polygon>();
		
		
		int o=0;
		
		int[] pX = new int[SW.length];
		int[] pY = new int[SW.length];
        for(int L=0;L<layers.length;L++) {
        	
    		int[] polX = new int[layers[L]];
    		int[] polY = new int[layers[L]];
    		int[] polZ = new int[layers[L]];
    		

    		
    		
    		Point Dpt[] = new Point[polX.length];
    		
    		Polygon SP = new Polygon();
	        	for(int i=0;i<layers[L];i++) {	
					
				
				polX[i] = SW[i+o]+x;
				polY[i] = SH[i+o]+y;					
				
				polZ[i]= (Game.rotatePoint(new Point(polX[i], SD[i+o]+z),new Point(x,z),Z_direction)).y;
				polX[i]= (Game.rotatePoint(new Point(polX[i], SD[i+o]+z),new Point(x,z),Z_direction)).x;	
				
				int SDZ=polZ[i];		
			    polZ[i]= (Game.rotatePoint(new Point(polY[i], SDZ),new Point(y,z),S_direction)).y;
				polY[i]= (Game.rotatePoint(new Point(polY[i], SDZ),new Point(y,z),S_direction)).x;				
					
				Dpt[i] = (Game.rotatePoint(new Point(polX[i], polY[i]),new Point(x,y),direction));
				polX[i] = Dpt[i].x;
				polY[i] = Dpt[i].y;		
				
				if(i!=0)
				if(L!=0) {
				Polys.add(new Polygon(new int[] {polX[i],pX[i],pX[i-1],polX[i-1]},new int[] {polY[i],pY[i],pY[i-1],polY[i-1]},4));	
				}
				
		        	}	
	        	
		Polys.add(new Polygon(polX,polY,polX.length));

		pX=polX;
		pY=polY;
		
			o+=layers[L];
        }
        	
        
        return  Polys;
	}

	public LinkedList<int[]> getTertradonDepth(double Z_direction,double S_direction){


		/*
		 {x-16,x+16,x+16,x-16};
		 {y-16,y-16,y+16,y+16};
		 */
		//SD[i] = Math.sqrt(x^2)
		


		
		LinkedList<int[]> Polys = new LinkedList<int[]>();
		
		
		int o=0;
		
		int[] pZ = new int[SW.length];
        for(int L=0;L<layers.length;L++) {
        	
    		int[] polX = new int[layers[L]];
    		int[] polY = new int[layers[L]];
    		int[] polZ = new int[layers[L]];
    		
 
	        	for(int i=0;i<layers[L];i++) {	
					
				
				polX[i] = SW[i+o];
				polY[i] = SH[i+o];					
				
				polZ[i]= (Game.rotatePoint(new Point(polX[i], SD[i+o]+z),new Point(0,z),Z_direction)).y;
				polX[i]= (Game.rotatePoint(new Point(polX[i], SD[i+o]+z),new Point(0,z),Z_direction)).x;	
				
				int SDZ=polZ[i];		
			    polZ[i]= (Game.rotatePoint(new Point(polY[i], SDZ),new Point(0,z),S_direction)).y;
				polY[i]= (Game.rotatePoint(new Point(polY[i], SDZ),new Point(0,z),S_direction)).x;				
						
				
				if(i!=0)
				if(L!=0) {
				Polys.add(new int[] {polZ[i],pZ[i],pZ[i-1],polZ[i]});	
				}
				
		        	}	
	        	
		Polys.add(polZ);
	        	
		pZ=polZ;
		
			o+=layers[L];
        }
        	
        
        return  Polys;
	}


	
	public Polygon getBounds(){
        return Game.outlineTetrahedron(getTertradon());
	}
	
	public Area getArea(){   
        return  Game.getTetrahedronArea(getTertradon());
	}
	
	public Area getAllBounds(){
		
		Area A = new Area(getBounds());
			
			for(int i=0;i<LimpParts.size();i++) { 
				GameObject b=LimpParts.get(i);
				A.add(b.getAllBounds());
			}
		
		return new Area(A);

	}
	
	public void render3DImage(BufferedImage Image,Shape P,int[] depth,Graphics2D g2d) {
		//Z
		//S
		//Direction
		Rectangle R = P.getBounds();		
		int rZ = Game.Avarage_Array(depth,depth.length);
		
		Point P1 = Game.rotatePoint(new Point(R.x, rZ+z),new Point(x,z),Z_direction);
		Point P2 = Game.rotatePoint(new Point(R.y, P1.y),new Point(y,z),S_direction);

		Point Pz = Game.rotatePoint(new Point(P1.x,P2.x), new Point (x,y), direction);
		
		AffineTransform at = AffineTransform.getTranslateInstance(Pz.x,Pz.y);
		
		Point E1 = Game.rotatePoint(new Point((int)R.getWidth()+R.x , rZ+z),new Point(x,z),Z_direction);
		Point E2 = Game.rotatePoint(new Point(R.y, E1.y),new Point(y,z),S_direction);

		Point Ez = Game.rotatePoint(new Point(E1.x,E2.x), new Point (x,y), direction);
		
		double D = Game.getAngle(Pz, Ez);
		
		Point S1 = Game.rotatePoint(new Point(R.x, rZ+z),new Point(x,z),Z_direction);
		Point S2 = Game.rotatePoint(new Point((int)R.getHeight()+R.y,S1.y),new Point(y,z),S_direction);

		Point Sz = Game.rotatePoint(new Point(S1.x,S2.x), new Point (x,y), direction);
		
		
		Point ES1 = Game.rotatePoint(new Point((int)R.getWidth()+R.y, rZ+z),new Point(x,z),Z_direction);
		Point ES2 = Game.rotatePoint(new Point((int)R.getHeight()+R.y,ES1.y),new Point(y,z),S_direction);

		Point ESz = Game.rotatePoint(new Point(ES1.x,ES2.x), new Point (x,y), direction);
		
		double distanceW = -Game.distance(Pz.x, Pz.y, Ez.x, Ez.y);
		double distanceH = -Game.distance(Pz.x, Pz.y, Sz.x, Sz.y);
		
		g2d.draw(new Line2D.Double(Pz,Ez));
		g2d.draw(new Line2D.Double(Pz,Sz));
		
		R.x=Pz.x;
		R.y=Pz.y;	
		
		at.rotate(Math.toRadians(direction-D-90));
		
		at.scale((distanceW/(double)Image.getWidth()),1);
		at.scale(1,(distanceH/(double)Image.getHeight()));
		
		
		
		//g2d.draw(R);
		
		
	}
	



	
public Area getAllArea(){
		
		Area A = getArea();
			
			for(int i=0;i<LimpParts.size();i++) { 
				GameObject b=LimpParts.get(i);
				A.add(b.getAllBounds());
			}
		
		return new Area(A);

	}
	
	public void tickPart() {
		for(int i = 0; i < LimpParts.size(); i++) {
			Part tempObject = LimpParts.get(i);
			
			tempObject.tick();
		}
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
	
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
	this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public ID getId() {
		return id;
	}
	public void setHsp(int hsp) {
		this.hsp = hsp;
	}
	public void setVsp(int vsp) {
		this.vsp = vsp;
	}
	public int getHsp() {
		return hsp;
	}
	public int getVsp() {
		return vsp;
	}
	
	

	protected abstract boolean collision_Balance(GameObject TH, int BL);



	public boolean collision_Check() {
		
		boolean CL = false;


		
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
								
								
								
								if(testIntersection(getAllArea(),new Polygon(pX,pY,pX.length))) {
									CL = true;
									GameObject O=owner;
									while(testIntersection(getAllArea(),new Polygon(pX,pY,pX.length))){	

																						
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

	public static boolean findPoints(Area area1, Line2D line1, int depth) {

	    Point p1 = new Point((int) (line1.getX2() + line1.getX1()) / 2,
	            (int) (line1.getY2() + line1.getY1()) / 2);

	    if (depth == 0) {
	        return false;
	    }

	    if (area1.contains(p1)) {
	        return true;

	    } else {
	        return findPoints(area1, new Line2D.Double(p1, line1.getP2()),
	                depth - 1)
	                || findPoints(area1, new Line2D.Double(line1.getP1(), p1),
	                        depth - 1);
	    }

	}
	
	public static boolean testIntersection(Shape shapeA, Shape shapeB) {
		   Area areaA = new Area(shapeA);
		   areaA.intersect(new Area(shapeB));
		   return !areaA.isEmpty();
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

	public abstract ArrayList<GameObject> Balance_Array(GameObject TH, ArrayList<GameObject> bL);

	public abstract boolean collision_Branch(GameObject TH, int X);

	
}
