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
import java.awt.geom.Point2D.Double;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedHashSet;
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
	protected LinkedList<Polygon> Tetrodon	  = new LinkedList<Polygon>();
	protected LinkedList<Polygon> TetrodonNow = new LinkedList<Polygon>();
	protected LinkedList<Point2D.Double> TetrodonDirection = new LinkedList<Point2D.Double>();
	protected LinkedList<int[]> TetrodonDepth = new LinkedList<int[]>();
	protected LinkedList<int[]> TetrodonDepthNow = new LinkedList<int[]>();
	protected LinkedList<Integer> AvarageTetrodonDepth = new LinkedList<Integer>();
	protected LinkedList<Integer> AvarageTetrodonDepthNow = new LinkedList<Integer>();
	protected LinkedList<Polygon> TextureMap = new LinkedList<Polygon>();
	LinkedList<Polygon> T = new LinkedList<Polygon>();
	LinkedList<int[]> TD = new LinkedList<int[]>();
	protected Area HitArea = new Area();
	protected Polygon HitPolygon = new Polygon();
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
		this.HitArea = getArea();
		this.HitPolygon = getBounds();
		this.Tetrodon = getTertradon(0,0,0);
		this.TetrodonNow = getTertradon(direction,Z_direction,S_direction);
		this.TetrodonDepth = getTertradonDepth(0,0);
		this.AvarageTetrodonDepthNow = getAvarageTertradonDepth(Z_direction, S_direction);
		this.AvarageTetrodonDepth = getAvarageTertradonDepth(0, 0);
		this.TetrodonDepthNow= getTertradonDepth(Z_direction,S_direction);
		this.T = Game.sortOnDepth((LinkedList)Tetrodon.clone(),(LinkedList)AvarageTetrodonDepthNow.clone());
		this.TD =  Game.sortOnDepthArray((LinkedList)TetrodonDepth.clone(),(LinkedList) AvarageTetrodonDepthNow.clone());
		this.TextureMap = getTextureMap(T, TD);
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
		LinkedList<Integer> Z = new LinkedList<Integer>();
		
		
		int o=0;
		
		int[] pX = new int[SW.length];
		int[] pY = new int[SW.length];
		int[] pZ = new int[SW.length];
		double pDirection;
		
		
        for(int L=0;L<layers.length;L++) {
        	
    		int[] polX = new int[layers[L]];
    		int[] polY = new int[layers[L]];
    		int[] polZ = new int[layers[L]];
    		

    		
    		
    		Point Dpt[] = new Point[polX.length];
    		
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
				if(L!=0) 
				{
				
				Polys.add(new Polygon(new int[] {polX[i],pX[i],pX[i-1],polX[i-1]},new int[] {polY[i],pY[i],pY[i-1],polY[i-1]},4));
				Z.add(Game.Avarage_Array(new int[] {polZ[i],pZ[i],pZ[i-1],polZ[i-1]}, 4));
				}
				
		        	}	
	    
		Polys.add(new Polygon(polX,polY,polX.length));
		Z.add(Game.Avarage_Array(polZ, polZ.length));
		
		pX=polX;
		pY=polY;
		pZ=polZ;
		
			o+=layers[L];
        }
        return Polys;
	}

	public LinkedList<Polygon> getTertradon(double direction,double Z_direction,double S_direction){


		/*
		 {x-16,x+16,x+16,x-16};
		 {y-16,y-16,y+16,y+16};
		 */
		//SD[i] = Math.sqrt(x^2)
		


		
		LinkedList<Polygon> Polys = new LinkedList<Polygon>();
		LinkedList<Integer> Z = new LinkedList<Integer>();
		
		
		int o=0;
		
		int[] pX = new int[SW.length];
		int[] pY = new int[SW.length];
		int[] pZ = new int[SW.length];
		LinkedList<Point2D.Double> DIR = new LinkedList<Point2D.Double>();
		//TetrodonDirection
		
        for(int L=0;L<layers.length;L++) {
        	
    		int[] polX = new int[layers[L]];
    		int[] polY = new int[layers[L]];
    		int[] polZ = new int[layers[L]];
    		

    		
    		
    		Point Dpt[] = new Point[polX.length];
    		
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
				if(L!=0) 
				{
				
				Polys.add(new Polygon(new int[] {polX[i],pX[i],pX[i-1],polX[i-1]},new int[] {polY[i],pY[i],pY[i-1],polY[i-1]},4));
				Z.add(Game.Avarage_Array(new int[] {polZ[i],pZ[i],pZ[i-1],polZ[i-1]}, 4));
				}
				
		        	}	
	    
		Polys.add(new Polygon(polX,polY,polX.length));
		Z.add(Game.Avarage_Array(polZ, polZ.length));
		
		pX=polX;
		pY=polY;
		pZ=polZ;
		
			o+=layers[L];
        }
        return Polys;
	}

	public LinkedList<int[]> getTertradonDepth(double Z_direction,double S_direction){


		/*
		 {x-16,x+16,x+16,x-16};
		 {y-16,y-16,y+16,y+16};
		 */
		//SD[i] = Math.sqrt(x^2)
		


		
		LinkedList<int[]> Polys = new LinkedList<int[]>();
		LinkedList<Integer> Z = new LinkedList<Integer>();
		
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
				Polys.add(new int[] {polZ[i],pZ[i],pZ[i-1],polZ[i-1]});	
				}
				
		        	}	
	        	
		Polys.add(polZ);
	        	
		pZ=polZ;
		
			
		
			o+=layers[L];
        }
        

        
        return  Polys;
	}

	public LinkedList<Integer> getAvarageTertradonDepth(double Z_direction,double S_direction){


		/*
		 {x-16,x+16,x+16,x-16};
		 {y-16,y-16,y+16,y+16};
		 */
		//SD[i] = Math.sqrt(x^2)
		


		
		LinkedList<Integer> Polys = new LinkedList<Integer>();
		LinkedList<Integer> Z = new LinkedList<Integer>();
		
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
				Polys.add(Game.Avarage_Array(new int[] {polZ[i],pZ[i],pZ[i-1],polZ[i-1]}, 4));	
				}
				
		        	}	
	        	
		Polys.add(Game.Avarage_Array(polZ, polZ.length));
	        	
		pZ=polZ;
		
			
		
			o+=layers[L];
        }
        

        
        return  Polys;
	}

	public LinkedList<Polygon> getTextureMap(LinkedList<Polygon> P,LinkedList<int[]> depth){
		LinkedList<Polygon> result = new LinkedList<Polygon>();
			for(int i=0;i<P.size();i++) {
				result.add(Get3DBounds(P.get(i), depth.get(i)));
			}
		
		return result;
	}
	
	public Point2D.Double getAvarageDepthDir(Polygon P,int[] depth,Point PT){
		Point2D.Double result = new Point2D.Double();
		int X=0;
		int Y=0;
		int Z=0;
		
			for(int i=0;i<P.npoints;i++) {
				X+=P.xpoints[i];
				Y+=P.ypoints[i];
				Z+=depth[i];
			}
			result.x = Game.getAngle(new Point((X/P.npoints),(Z/P.npoints)), new Point(PT.x,0));
			result.y = Game.getAngle(new Point((Y/P.npoints),(Z/P.npoints)), new Point(PT.y,0));
		
		
		return result;
	}

	public void UpdateHitBox() {
		//LinkedList<Polygon> T = Game.sortOnDepth((LinkedList)Tetrodon.clone(),(LinkedList)AvarageTetrodonDepthNow.clone());
		//LinkedList<int[]> TD =  Game.sortOnDepthArray((LinkedList)TetrodonDepth.clone(),(LinkedList) AvarageTetrodonDepthNow.clone());
		this.AvarageTetrodonDepthNow = getAvarageTertradonDepth(Z_direction, S_direction);
	
		this.HitArea = getArea();
		this.HitPolygon = getBounds();
		
		this.TetrodonDepthNow= getTertradonDepth(Z_direction,S_direction);
		this.TetrodonNow = getTertradon(direction,Z_direction,S_direction);
		
		this.T = Game.sortOnDepth((LinkedList)Tetrodon.clone(),(LinkedList)AvarageTetrodonDepthNow.clone());
		this.TD =  Game.sortOnDepthArray((LinkedList)TetrodonDepth.clone(),(LinkedList) AvarageTetrodonDepthNow.clone());
		
		this.TextureMap = getTextureMap(T, TD);
	}
	
	public Polygon getBounds(){
        return Game.outlineTetrahedron(getTertradon());
	}
	
	public Area getArea(){   
        return  Game.getTetrahedronArea(getTertradon());
	}
	
	public Area getAllBounds(){
		
		Area A = new Area(HitPolygon);
			
			for(int i=0;i<LimpParts.size();i++) { 
				GameObject b=LimpParts.get(i);
				A.add(b.getAllBounds());
			}
		
		return new Area(A);

	}
	
	
	public void render3DImage(BufferedImage Image,Polygon P,int[] depth,Graphics2D g2d) {
		//Z
		//S
		//Direction

		//g2d.draw(R);
		//g2d.draw(P);
		//if(P.npoints>4)
		

		if(P!=null) {
		int MX = Game.Min(P.xpoints); 
		int MY = Game.Min(P.ypoints);
		AffineTransform at = AffineTransform.getTranslateInstance(MX,MY);
		//if(!P.getBounds().isEmpty())
		
	    BufferedImage I = Pseudo3D.computeImage(Image, new Point(P.xpoints[1]-MX,P.ypoints[1]-MY), new Point(P.xpoints[2]-MX,P.ypoints[2]-MY), new Point(P.xpoints[3]-MX,P.ypoints[3]-MY), new Point(P.xpoints[0]-MX,P.ypoints[0]-MY));
		//g2d.draw(new Polygon(P.xpoints,P.ypoints,P.npoints));
	    g2d.setClip(P);
		g2d.drawImage(I, at, null);
		g2d.setClip(null);
		}
		//g2d.draw(P);
	}

	
	public Point2D.Double getPolygonDirecton(Point center,Polygon P,int[] depth) {
		int   centerDepth = z;
		double[] Z_dir = new double[P.npoints];
		double[] S_dir = new double[P.npoints];
		
			for(int i=0;i<P.npoints;i++) {
				Point PT = new Point(P.xpoints[i],P.ypoints[i]);
				int   D = depth[i];
				
				
				Z_dir[i] = Game.getAngle(new Point(center.x,centerDepth), new Point(PT.x,D));
				S_dir[i] = Game.getAngle(new Point(center.y,centerDepth), new Point(PT.y,D));
				
			}
		
		
		return new Point2D.Double(
				Game.Avarage_Array(Z_dir, Z_dir.length),
				Game.Avarage_Array(S_dir, S_dir.length));
	}
	
	public Polygon TurnPolygon(int x,int y,int z,Polygon P,int[] depth,double direction, double Z_direction,double S_direction) {
		
		int[] D = depth;
		
		int[] polX = new int[P.npoints];
		int[] polY = new int[P.npoints];
		int[] polZ = new int[P.npoints];
		Point Dpt[] = new Point[polX.length];
		
		for(int i=0;i<P.npoints;i++) {
		polX[i] = P.xpoints[i];
		polY[i] = P.ypoints[i];				
		
		polZ[i]= (Game.rotatePoint(new Point(polX[i], D[i]),new Point(x,z),Z_direction)).y;
		polX[i]= (Game.rotatePoint(new Point(polX[i], D[i]),new Point(x,z),Z_direction)).x;	
		
		int SDZ=polZ[i];		
	    polZ[i]= (Game.rotatePoint(new Point(polY[i], SDZ),new Point(y,z),S_direction)).y;
		polY[i]= (Game.rotatePoint(new Point(polY[i], SDZ),new Point(y,z),S_direction)).x;				
			
		Dpt[i] = (Game.rotatePoint(new Point(polX[i], polY[i]),new Point(x,y),direction));
		polX[i] = Dpt[i].x;
		polY[i] = Dpt[i].y;	
		}
		
		return new Polygon(polX,polY,P.npoints); 
	}

	public int[] TurnPolygonDepth(int x,int y,int z,Polygon P,int[] depth,double direction, double Z_direction,double S_direction) {
		
		
		int[] polX = new int[P.npoints];
		int[] polY = new int[P.npoints];
		int[] polZ = new int[P.npoints];
		
		for(int i=0;i<P.npoints;i++) {
		polX[i] = P.xpoints[i];
		polY[i] = P.ypoints[i];				
		
		polZ[i]= (Game.rotatePoint(new Point(polX[i], depth[i]),new Point(x,z),Z_direction)).y;
		polX[i]= (Game.rotatePoint(new Point(polX[i], depth[i]),new Point(x,z),Z_direction)).x;	
		
		int SDZ=polZ[i];		
	    polZ[i]= (Game.rotatePoint(new Point(polY[i], SDZ),new Point(y,z),S_direction)).y;
		polY[i]= (Game.rotatePoint(new Point(polY[i], SDZ),new Point(y,z),S_direction)).x;				
			
		}
		
		return polZ; 
	}
	
	public Polygon TurnPolygonBack(int x,int y,int z,Polygon P,int[] depth,double direction, double Z_direction,double S_direction) {
		
		
		int[] polX = new int[P.npoints];
		int[] polY = new int[P.npoints];
		int[] polZ = new int[P.npoints];
		Point Dpt[] = new Point[polX.length];
		
		for(int i=0;i<P.npoints;i++) {
		polX[i] = P.xpoints[i];
		polY[i] = P.ypoints[i];	
		
		Dpt[i] = (Game.rotatePoint(new Point(polX[i], polY[i]),new Point(x,y),direction));
		polX[i] = Dpt[i].x;
		polY[i] = Dpt[i].y;	
		
	    polZ[i]= (Game.rotatePoint(new Point(polY[i], depth[i]),new Point(y,z),S_direction)).y;
		polY[i]= (Game.rotatePoint(new Point(polY[i], depth[i]),new Point(y,z),S_direction)).x;
		
		int SDZ=polZ[i];
		polZ[i]= (Game.rotatePoint(new Point(polX[i], SDZ),new Point(x,z),Z_direction)).y;
		polX[i]= (Game.rotatePoint(new Point(polX[i], SDZ),new Point(x,z),Z_direction)).x;	
				
			
		}
		
		return new Polygon(polX,polY,P.npoints); 
	}
	
	public int[] TurnPolygonDepthBack(int x,int y,int z,Polygon P,int[] depth,double direction, double Z_direction,double S_direction) {
		
		
		int[] polX = new int[P.npoints];
		int[] polY = new int[P.npoints];
		int[] polZ = new int[P.npoints];
		Point Dpt[] = new Point[polX.length];
		
		for(int i=0;i<P.npoints;i++) {
		polX[i] = P.xpoints[i];
		polY[i] = P.ypoints[i];	
		
		Dpt[i] = (Game.rotatePoint(new Point(polX[i], polY[i]),new Point(x,y),direction));
		polX[i] = Dpt[i].x;
		polY[i] = Dpt[i].y;	
		
	    polZ[i]= (Game.rotatePoint(new Point(polY[i], depth[i]),new Point(y,z),S_direction)).y;
		polY[i]= (Game.rotatePoint(new Point(polY[i], depth[i]),new Point(y,z),S_direction)).x;
		
		int SDZ=polZ[i];
		polZ[i]= (Game.rotatePoint(new Point(polX[i], SDZ),new Point(x,z),Z_direction)).y;
		polX[i]= (Game.rotatePoint(new Point(polX[i], SDZ),new Point(x,z),Z_direction)).x;	
				
			
		}
		
		return polZ;
	}
	
	public Polygon Get3DBounds(Polygon P,int[] depth) {
		//int D = Game.Avarage_Array(depth, depth.length);
		int[] DP = depth;
		Polygon Poly = P;
			 
		
		
		
		//System.out.println(Game.Avarage_Array(depth,depth.length));
		//Poly = TurnPolygonBack(C.x, C.y, D, Game.RectangleToPolygon(R), DP, 0, Game.Deg(-DIR1), Game.Deg(DIR2));
		//DP   = TurnPolygonDepthBack(C.x, C.y, D, Game.RectangleToPolygon(R), DP, 0,Game.Deg(-DIR1), Game.Deg(DIR2));
		
		if(P.npoints!=4)
		Poly = new Polygon();
			
		if(Poly.npoints!=0)
		return TurnPolygon(x,y,z,Poly,DP, direction,Z_direction,S_direction); else
		return new Polygon();
		

	}
	public Polygon GetMinimumBounds(Polygon P) {
		int[] D = new int[P.npoints];
		for(int i=0;i<P.npoints;i++)
		D[i] = 0;
			
		double DIR=0;
		Rectangle Rel = P.getBounds();
		Polygon result = Game.RectangleToPolygon(Rel);
		double size = Rel.getWidth()*Rel.getHeight();
			for(int i=1;i<P.npoints;i++) {
				DIR = Game.getAngle(new Point(P.xpoints[i-1],P.ypoints[i-1]), new Point(P.xpoints[i],P.ypoints[i]));
				Rectangle R = TurnPolygon(P.xpoints[i-1], P.ypoints[i-1], 0, P, D, DIR, 0, 0).getBounds();
					if(R.getWidth()*R.getHeight()<=size) {
						size = R.getWidth()*R.getHeight();
						result = TurnPolygon(P.xpoints[i-1], P.ypoints[i-1], 0, Game.RectangleToPolygon((TurnPolygon(P.xpoints[i-1], P.ypoints[i-1], 0, P, D, DIR, 0, 0).getBounds())), D, -DIR, 0, 0);
					}
					
			}
		
		return result;
	}

	public Polygon MakeFlat(Polygon P, int[] depth) {
		int[] D = depth;
		Polygon result = P;
		
		int Dmin = Game.MinIndex(depth);
		int Dmax = Game.MaxIndex(depth);
		
		Point P1 = new Point(P.xpoints[Dmin],depth[Dmin]);
		Point P2 = new Point(P.xpoints[Dmax],depth[Dmax]);
	  double Dir = Game.getAngle(P1, P2);
	  
	  //if(Dir!=0)
		  result = TurnPolygon(P.xpoints[Dmin], P.ypoints[Dmin], depth[Dmin], result, depth, 0, Dir, 0);
		
		Dmin = Game.MinIndex(depth);
		Dmax = Game.MaxIndex(depth);
		
		 P1 = new Point(P.ypoints[Dmin],depth[Dmin]);
		 P2 = new Point(P.ypoints[Dmax],depth[Dmax]);
		 Dir = Game.getAngle(P1, P2);
		 
	  //if(Dir!=0)
		  result = TurnPolygon(P.xpoints[Dmin], P.ypoints[Dmin], depth[Dmin], result, depth, 0, 0, Dir);	 
	  
		   return result;
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
			tempObject.UpdateHitBox();
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
							
							
							for(int p=0;p<tempObject.HitPolygon.xpoints.length-1;p++) {
								
								int b;
								if(p+1 > tempObject.HitPolygon.xpoints.length-1)
								b=-p;else b=1;
								
								int[] pX = {tempObject.HitPolygon.xpoints[p],
											tempObject.HitPolygon.xpoints[p+b],
											tempObject.HitPolygon.xpoints[p]+1,
											tempObject.HitPolygon.xpoints[p+b]+1};
											
								int[] pY = {tempObject.HitPolygon.ypoints[p],
											tempObject.HitPolygon.ypoints[p+b],
											tempObject.HitPolygon.ypoints[p]+1,
											tempObject.HitPolygon.ypoints[p+b]+1};
								
								
								

								double dir = Game.Deg(Game.getAngle(new Point(pX[0],pY[0]),new Point(pX[1],pY[1])));

								
								int X = (pX[0]-pX[1])/2+pX[0];
								int Y = (pY[0]-pY[1])/2+pY[0];
								Point PT = new Point(X,Y);
								double pdir = Game.Deg(Game.getAngle(PT,new Point(x,y)));
								if(Game.inDeg(pdir, dir, Game.Deg(dir+180)))
								dir  =	Game.Deg(dir-90); else
								if(Game.inDeg(pdir, Game.Deg(dir+180), dir))
								dir  =	Game.Deg(dir+90);
								
								
								
								if(testIntersection(HitArea,new Polygon(pX,pY,pX.length))) {
									CL = true;
									GameObject O=owner;
									
									Polygon P=new Polygon(pX,pY,pX.length);
									int cx = x;
									int cy = y;
									while(testIntersection(HitArea,Game.MovePolygon(P,(cx-x),(cy-y)))){	

																						
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
