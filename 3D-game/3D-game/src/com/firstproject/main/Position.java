package com.firstproject.main;

import java.awt.geom.Point2D;

public class Position {
	float x = 0.0f;
	float y = 0.0f;
	float z = 0.0f;

	public Position(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Position(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Position(Position P) {
		this.x = P.x;
		this.y = P.y;
		this.z = P.z;
	}

	public Position(Point2D.Float P) {
		this.x = P.x;
		this.y = P.y;
		this.z = 0;
	}

	public Position(float[][] point) {
		this.x = point[0][0];
		this.y = point[0][1];
		this.z = point[0][2];
	}

	public Position(double x, double y, double z) {
		this.x = (float) x;
		this.y = (float) y;
		this.z = (float) z;
	}

	public float[][] PositionToFloat() {
		return new float[][] { { this.x }, { this.y }, { this.z } };
	}

	@Override
	public Position clone() {
		return new Position(this.x, this.y, this.z);
	}

	public String GetCreatorCode() {
		return "new Position(0,0,0)";
	}

	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void swap(Position p) {
		float x = p.x;
		float y = p.y;
		float z = p.z;
		p.set(this);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void union(Position P) {
		this.x += P.x;
		this.y += P.y;
		this.z += P.z;
	}

	public Point2D.Double getPointDouble() {
		return new Point2D.Double(this.x, this.y);
	}

	public Point2D.Float getPointFloat() {
		return new Point2D.Float(this.x, this.y);
	}

	/*
	 * public void Turn(Position P,Angle3D angle) { Point2D.Double P1 =
	 * Game.Turn(new Point2D.Double(this.x,this.z), new Point2D.Double(P.x,P.z),
	 * angle.Y_direction); Point2D.Double P2 = Game.Turn(new
	 * Point2D.Double(this.y,P1.y), new Point2D.Double(P.y,P.z), angle.X_direction);
	 * Point2D.Double P3 = Game.Turn(new Point2D.Double(P1.x,P2.x), new
	 * Point2D.Double(P.x,P.y), angle.Z_direction); this.x=P3.x; this.y=P3.y;
	 * this.z=P2.y; }
	 */
	/*
	 * public void Turn(Position P,Angle3D angle) { Point2D.Double P1;
	 * 
	 * 
	 * 
	 * P1 = (Game.Turn(new Point2D.Double(this.x, this.z),new
	 * Point2D.Double(P.x,P.z),angle.Y_direction)); this.z= P1.y; this.x= P1.x;
	 * 
	 * P1 = (Game.Turn(new Point2D.Double(this.x, this.z),new
	 * Point2D.Double(P.x,P.z),angle.X_direction)); this.z= P1.y; this.y= P1.x;
	 * 
	 * P1 = (Game.Turn(new Point2D.Double(this.x, this.y),new
	 * Point2D.Double(P.x,P.y),angle.Z_direction)); this.x = P1.x; this.y = P1.y;
	 * 
	 * 
	 * }
	 */

	public void Turn(Position P, Angle3D angle) {
		float[][] projection = new float[][] { { 1, 0, 0 }, { 0, 1, 0 } };
		float[][] rotationZ = { { (float) Math.cos(Math.toRadians(90)), (float) -Math.sin(Math.toRadians(90)), 0 },
				{ (float) Math.sin(Math.toRadians(90)), (float) Math.cos(Math.toRadians(90)), 0 }, { 0, 0, 1 } };
		float[][] rotationX = { { 1, 0, 0 },
				{ 0, (float) Math.cos(Math.toRadians(angle.X_direction)),
						(float) -Math.sin(Math.toRadians(angle.X_direction)) },
				{ 0, (float) Math.sin(Math.toRadians(angle.X_direction)),
						(float) Math.cos(Math.toRadians(angle.X_direction)) } };
		float[][] rotationY = {
				{ (float) Math.cos(Math.toRadians(angle.Y_direction)), 0,
						(float) -Math.sin(Math.toRadians(angle.Y_direction)) },
				{ 0, 1, 0 }, { (float) Math.sin(Math.toRadians(angle.Y_direction)), 0,
						(float) Math.cos(Math.toRadians(angle.Y_direction)) } };

		this.x -= P.x;
		this.y -= P.y;
		this.z -= P.z;

		Position projected2d = Game.matmul(projection, this.clone());
		Position rotated = Game.matmul(rotationZ, projected2d);
		rotated = Game.matmul(rotationX, rotated);
		rotated = Game.matmul(rotationY, rotated);

		this.x = rotated.x + P.x;
		this.y = rotated.y + P.y;
		this.z = rotated.z + P.z;
	}

	/*
	 * polX[i] = SW[i+o]+x; polY[i] = SH[i+o]+y;
	 * 
	 * polZ[i]= (Game.rotatePoint(new Point(polX[i], SD[i+o]+z),new
	 * Point(x,z),Z_direction)).y; polX[i]= (Game.rotatePoint(new Point(polX[i],
	 * SD[i+o]+z),new Point(x,z),Z_direction)).x;
	 * 
	 * int SDZ=polZ[i]; polZ[i]= (Game.rotatePoint(new Point(polY[i], SDZ),new
	 * Point(y,z),S_direction)).y; polY[i]= (Game.rotatePoint(new Point(polY[i],
	 * SDZ),new Point(y,z),S_direction)).x;
	 * 
	 * Dpt[i] = (Game.rotatePoint(new Point(polX[i], polY[i]),new
	 * Point(x,y),direction)); polX[i] = Dpt[i].x; polY[i] = Dpt[i].y;
	 */

	public void multiply(double D) {
		this.x *= D;
		this.y *= D;
		this.z *= D;

	}

	public void plus(Position P) {
		this.x += P.x;
		this.y += P.y;
		this.z += P.z;
	}

	public void min(Position P) {
		this.x -= P.x;
		this.y -= P.y;
		this.z -= P.z;
	}

	public void set(Position p) {
		this.x = p.x;
		this.y = p.y;
		this.z = p.z;
	}

}
