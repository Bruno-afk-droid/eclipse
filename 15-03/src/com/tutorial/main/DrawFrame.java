package com.tutorial.main;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;

public class DrawFrame {
	int x;
	int y;
	int z;
	public Polygon3D Poly;
	public BufferedImage Image;
	
	public DrawFrame(Polygon3D Poly,BufferedImage Image) {
		this.Poly=Poly;
		this.x=Poly.Avg_X;
		this.y=Poly.Avg_Y;
		this.z=Poly.Avg_depth;
		
		this.Image=Image;
	}
	public void Draw(Graphics2D g) {
		//Z
		//S
		//Direction

		//g2d.draw(R);//
		//g2d.draw(P);
		//if(P.npoints>4)
		

		
		int MX = Game.Min(Poly.xpoints); 
		int MY = Game.Min(Poly.ypoints);
		AffineTransform at = AffineTransform.getTranslateInstance(MX,MY);
		//if(!P.getBounds().isEmpty())
		if(!new Area(Poly).isEmpty()) {		
	    BufferedImage I = Pseudo3D.computeImage(Image, new Point(Poly.xpoints[1]-MX,Poly.ypoints[1]-MY), new Point(Poly.xpoints[2]-MX,Poly.ypoints[2]-MY), new Point(Poly.xpoints[3]-MX,Poly.ypoints[3]-MY), new Point(Poly.xpoints[0]-MX,Poly.ypoints[0]-MY));
	     //I = Pseudo3D.computeImage(B, new Point(0,0), new Point(50,0), new Point(50,50), new Point(0,50));
		//g2d.draw(new Polygon(P.xpoints,P.ypoints,P.npoints));
	    g.setClip(Poly);
	    g.drawImage(I, at, null);
	    g.setClip(null);
		}
		g.draw(Poly);
	}
}
