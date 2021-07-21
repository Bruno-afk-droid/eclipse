package com.tutorial.main;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.util.Arrays;

class Polygon3D extends Polygon {

	
	private static final long serialVersionUID = 1L;
	public int[] zpoints;
	public int index=0;
	
	public int x;
	public int y;
	public int z;
	
	public int Avg_X;
	public int Avg_Y;
	public int Avg_depth;

	
    public Polygon3D(int x,int y,int z,int[] xpoints, int[] ypoints,int[] zpoints, int npoints,int index) {
        // Fix 4489009: should throw IndexOutOfBoundsException instead
        // of OutOfMemoryError if npoints is huge and > {x,y}points.length
        if (npoints > xpoints.length || npoints > ypoints.length || npoints > zpoints.length) {
            throw new IndexOutOfBoundsException("npoints > xpoints.length || "+
                                                "npoints > ypoints.length "+
                                                "npoints > zpoints.length ");
        }
        // Fix 6191114: should throw NegativeArraySizeException with
        // negative npoints
        if (npoints < 0) {
            throw new NegativeArraySizeException("npoints < 0");
        }
        // Fix 6343431: Applet compatibility problems if arrays are not
        // exactly npoints in length
        this.npoints = npoints;
        this.xpoints = Arrays.copyOf(xpoints, npoints);
        this.ypoints = Arrays.copyOf(ypoints, npoints);
        this.zpoints = Arrays.copyOf(zpoints, npoints);
        this.x=x;
        this.y=y;
        this.z=z;
        this.index=index;
    }
    public Polygon3D(Polygon3D P) {
        this.npoints = P.npoints;
        this.xpoints = Arrays.copyOf(P.xpoints, P.npoints);
        this.ypoints = Arrays.copyOf(P.ypoints, P.npoints);
        this.zpoints = Arrays.copyOf(P.zpoints, P.npoints);
        this.x=Game.Avarage_Array(P.xpoints, P.npoints);
        this.y=Game.Avarage_Array(P.ypoints, P.npoints);
        this.z=Game.Avarage_Array(P.zpoints, P.npoints);
        
        this.Avg_X=Game.Avarage_Array(P.xpoints, P.npoints);
        this.Avg_Y=Game.Avarage_Array(P.ypoints, P.npoints);
        this.Avg_depth=Game.Avarage_Array(P.zpoints, P.npoints);
        this.index=P.index;
	}
    public Area getArea() {
    	return new Area(this);
    }
    
    public void draw(BufferedImage B,Graphics2D g) {
			//Z
			//S
			//Direction
	
			//g2d.draw(R);//
			//g2d.draw(P);
			//if(P.npoints>4)
			
	
			
			int MX = Game.Min(xpoints); 
			int MY = Game.Min(ypoints);
			AffineTransform at = AffineTransform.getTranslateInstance(MX,MY);
			//if(!P.getBounds().isEmpty())
			if(!new Area(this).isEmpty()) {		
		    BufferedImage I = Pseudo3D.computeImage(B, new Point(xpoints[1]-MX,ypoints[1]-MY), new Point(xpoints[2]-MX,ypoints[2]-MY), new Point(xpoints[3]-MX,ypoints[3]-MY), new Point(xpoints[0]-MX,ypoints[0]-MY));
		     //I = Pseudo3D.computeImage(B, new Point(0,0), new Point(50,0), new Point(50,50), new Point(0,50));
			//g2d.draw(new Polygon(P.xpoints,P.ypoints,P.npoints));
		    g.setClip(this);
		    g.drawImage(I, at, null);
		    g.setClip(null);
			}
			g.draw(this);
    }
    
	public void Turn(double direction) {
		
		
		int[] polX = new int[this.npoints];
		int[] polY = new int[this.npoints];
		Point Dpt[] = new Point[polX.length];
		
		for(int i=0;i<npoints;i++) {
		polX[i] = this.xpoints[i];
		polY[i] = this.ypoints[i];				
				
			
		Dpt[i] = (Game.rotatePoint(new Point(polX[i], polY[i]),new Point(this.x,this.y),direction));
		polX[i] = Dpt[i].x;
		polY[i] = Dpt[i].y;	
		}
		
		this.xpoints=polX;
		this.ypoints=polY;
		
	}
    
    public Polygon3D TurnPolygon3D(double Z_direction, double Y_direction,double X_direction) {

		
		int[] D = this.zpoints;
		
		int[] polX = new int[this.npoints];
		int[] polY = new int[this.npoints];
		int[] polZ = new int[this.npoints];
		Point Dpt[] = new Point[polX.length];
		
		for(int i=0;i<npoints;i++) {
		polX[i] = this.xpoints[i];
		polY[i] = this.ypoints[i];
		
		
		Point pt = (Game.rotatePoint(new Point(polX[i], D[i]),new Point(this.x,this.z),Y_direction));
		polZ[i]= pt.y;
		polX[i]= pt.x;	
		
		pt = (Game.rotatePoint(new Point(polY[i], polZ[i]),new Point(this.y,this.z),X_direction));
	    polZ[i]= pt.y;
		polY[i]= pt.x;				
			
		Dpt[i] = (Game.rotatePoint(new Point(polX[i], polY[i]),new Point(this.x,this.y),Z_direction));
		polX[i] = Dpt[i].x;
		polY[i] = Dpt[i].y;	
		}
		
		return new Polygon3D(x, y, z, polX, polY, polZ, this.npoints,this.index);
		
	}
    
    public void Turn(double Z_direction, double Y_direction,double X_direction) {

		
		int[] D = this.zpoints;
		
		int[] polX = new int[this.npoints];
		int[] polY = new int[this.npoints];
		int[] polZ = new int[this.npoints];
		Point Dpt[] = new Point[polX.length];
		
		for(int i=0;i<npoints;i++) {
		polX[i] = this.xpoints[i];
		polY[i] = this.ypoints[i];				
		
		Point pt = (Game.rotatePoint(new Point(polX[i], D[i]),new Point(this.x,this.z),Y_direction));
		polZ[i]= pt.y;
		polX[i]= pt.x;	
		
		pt = (Game.rotatePoint(new Point(polY[i], polZ[i]),new Point(this.y,this.z),X_direction));
	    polZ[i]= pt.y;
		polY[i]= pt.x;				
			
		Dpt[i] = (Game.rotatePoint(new Point(polX[i], polY[i]),new Point(this.x,this.y),Z_direction));
		polX[i] = Dpt[i].x;
		polY[i] = Dpt[i].y;	
		}
		
		this.xpoints=polX;
		this.ypoints=polY;
		this.zpoints=polZ;
		
	}

    public void update() {
        this.Avg_X=Game.Avarage_Array(xpoints, npoints);
        this.Avg_Y=Game.Avarage_Array(ypoints, npoints);
        this.Avg_depth=Game.Avarage_Array(zpoints, npoints);
    }
    
    public void Move(int x,int y) {

    	for(int i=0;i<this.npoints;i++) {
    		this.xpoints[i]+=x;
    		this.ypoints[i]+=y;
    	}
		this.x+=x;
		this.y+=y;
    }
    
    public Polygon3D MovePolygon3D(int x,int y) {
    	Polygon3D result = new Polygon3D(this);
    	
    	for(int i=0;i<this.npoints;i++) {
    		result.xpoints[i]+=(x);
    		result.ypoints[i]+=(y);
    	}
    	result.x=x;
    	result.y=y;
		return result;
    }
    
    public Polygon3D MovePolygon3D(int x,int y,int z) {

    	
    	Polygon3D result = new Polygon3D(this);
    	
    	for(int i=0;i<this.npoints;i++) {
    		result.xpoints[i]+=x;
    		result.ypoints[i]+=y;
    		result.zpoints[i]+=z;
    	}
    	result.x=x;
    	result.y=y;
    	result.z=z;
		return result;
    }

    public void Move(int x,int y,int z) {

    	for(int i=0;i<this.npoints;i++) {
    		this.xpoints[i]+=x-this.x;
    		this.ypoints[i]+=y-this.y;
    		this.zpoints[i]+=z-this.z;
    	}
		this.x=x;
		this.y=y;
		this.z=z;
    }
    
    public void set(int x,int y,int z) {

    	for(int i=0;i<this.npoints;i++) {
    		this.xpoints[i]=this.xpoints[i]+x;
    		this.ypoints[i]=this.ypoints[i]+y;
    		this.zpoints[i]=this.zpoints[i]+z;
    	}
		this.x=x;
		this.y=y;
		this.z=z;
    }
    
    public void MoveDepth(int z) {

    	for(int i=0;i<this.npoints;i++) {
    		this.zpoints[i]+=z;
    	}
    	this.z+=z;
    }
    
}
