package com.firstproject.main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Circle {
	Position Position;
	double Radius;
	
	public Circle(Position Position,double Radius) {
		this.Position=Position;
		this.Radius=Radius;
	}
	
	public boolean Intersect(Circle C){
	return this.Radius<=Math.sqrt((C.Position.y - Position.y) * (C.Position.y - Position.y) + (C.Position.x - Position.x) * (C.Position.x - Position.x));
	}
	
	public boolean Intersect(Line2D.Float L){
		Rectangle R = new Rectangle((int)Position.x-(int)Radius/2,(int)Position.y-(int)Radius/2,(int)Radius/2,(int)Radius/2);
		
		return R.intersectsLine(L);
		
	}
	
	public Position LSegsIntersectionPoint(Position ps1, Position pe1, Position ps2, Position pe2)
			{
			    // Get A,B of first line - points : ps1 to pe1
			    float A1 = pe1.y-ps1.y;
			    float B1 = ps1.x-pe1.x;
			    // Get A,B of second line - points : ps2 to pe2
			    float A2 = pe2.y-ps2.y;
			    float B2 = ps2.x-pe2.x;

			    // Get delta and check if the lines are parallel
			    float delta = A1*B2 - A2*B1;
			    if(delta == 0) return null;

			    // Get C of first and second lines
			    float C2 = A2*ps2.x+B2*ps2.y;
			    float C1 = A1*ps1.x+B1*ps1.y;
			    //invert delta to make division cheaper
			    float invdelta = 1/delta;
			    // now return the Vector2 intersection point
			    return new Position( (B2*C1 - B1*C2)*invdelta, (A1*C2 - A2*C1)*invdelta ,0);
			}
	
	public void Draw(Graphics2D g) {
		g.drawOval((int)Position.x-(int)Radius/2, (int)Position.y-(int)Radius/2,(int)Radius,(int)Radius);
	}
	
}


