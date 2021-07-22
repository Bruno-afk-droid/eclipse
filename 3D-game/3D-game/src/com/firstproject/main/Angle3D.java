package com.firstproject.main;

public class Angle3D{
	public float X_direction= 0.0f;
	public float Y_direction= 0.0f;
	public float Z_direction= 0.0f;
	
	public Angle3D(double X_direction, double Y_direction,double Z_direction) {

		this.X_direction=(float) X_direction;
		this.Y_direction=(float) Y_direction;
		this.Z_direction=(float) Z_direction;
	}
	
	public Angle3D(Angle3D Angle) {
		
		this.X_direction=Angle.X_direction;
		this.Y_direction=Angle.Y_direction;
		this.Z_direction=Angle.Z_direction;
	}
	
	public Angle3D() {

	}	
	
	public Angle3D avg() {
		return new Angle3D(Math.floor(this.X_direction),Math.floor(this.Y_direction),Math.floor(this.Z_direction));
	}
	
	public String GetCreatorCode() {
		return "new Angle3D("+this.X_direction+","+this.Y_direction+","+this.Z_direction+")";
	}
	
	public Angle3D plus(Angle3D Angle) {
		Angle.addAngle(this);
		return Angle;
	}
	
	public Angle3D Difference(Angle3D Angle) {
		Angle3D result=new Angle3D();
		result.X_direction=distanceDifference(this.X_direction,Angle.X_direction);
		result.Y_direction=distanceDifference(this.Y_direction,Angle.Y_direction);
		result.Z_direction=distanceDifference(this.Z_direction,Angle.Z_direction);
		return result;
	}
	
	public static float distanceDifference(float alpha, float beta) {
		
		return (beta - alpha);	
	}
	
	public void Deg() {
		this.X_direction = Deg(this.X_direction);
		this.Y_direction = Deg(this.Y_direction);
		this.Z_direction = Deg(this.Z_direction);
	}
	
	public float Deg(float d) {
		while(d>360)d-=360;
		while(d<0)d+=360;
		return d;
	}
	
    public static double distance(double alpha, double beta) {
    	double phi = (beta - alpha) % 360;       // This is either the distance or 360 - distance
    	double distance = phi > 180 ? 360 - phi : phi;
        return distance;
    }
	
	public Angle3D Difference(Angle3D Angle, double D) {
		Angle3D result=new Angle3D();
		result.X_direction=(Angle.X_direction-this.X_direction);
		result.Y_direction=(Angle.Y_direction-this.Y_direction);
		result.Z_direction=(Angle.Z_direction-this.Z_direction);
		
		result = result.getmultiplied(1/D);
		//result.addAngle(this);
		
		return result;
	}
	
	
	
	public void minAngle(Angle3D Angle) {
		this.X_direction-=Angle.X_direction;
		this.Y_direction-=Angle.Y_direction;
		this.Z_direction-=Angle.Z_direction; 
	}
	
	public void addAngle(Angle3D Angle) {
		this.X_direction+=Angle.X_direction;
		this.Y_direction+=Angle.Y_direction;
		this.Z_direction+=Angle.Z_direction; 
	}
	
	
	public Angle3D getmultiplied(double M) {
		Angle3D result = new Angle3D(X_direction,Y_direction,Z_direction);
		result.X_direction*=M;
		result.Y_direction*=M;
		result.Z_direction*=M;
		
		return result;
	}

	public void multiplyAngle(double M) {

		this.X_direction*=M;
		this.Y_direction*=M;
		this.Z_direction*=M;
		
	}

	
}
