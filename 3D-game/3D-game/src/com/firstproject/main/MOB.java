package com.firstproject.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class MOB extends GameObject {
	public double grv = 0;
	public double Masa = 0;
	public Line2D Grounded = null;

	public MobState MobState;
	public Skeloton Skeloton;
	public Rectangle SolidBox;

	public MOB(Position Position, double Masa, Rectangle SolidBox, Skeloton Skeloton, MobState MobState) {
		super(Position);
		this.SolidBox = SolidBox;
		this.Masa = Masa;
		this.Skeloton = Skeloton;
		this.Skeloton.update();
		this.MobState = MobState;
		this.MobState.Position = this.Position;

		// TODO Auto-generated constructor stub
	}

	@Override
	public MOB clone() {
		MOB r = new MOB(Position.clone(), Masa, (Rectangle) SolidBox.clone(), Skeloton.clone(), MobState.clone());
		if (MobState.Move != null)
			r.MobState.Move = this.MobState.Move.clone();
		return r;
	}

	public static Structure getSolid(Rectangle R) {
		return new Structure(new Line2D[] { new Line2D.Double(R.getCenterX(), R.getY(), R.getMaxX(), R.getCenterY()),
				new Line2D.Double(R.getMaxX(), R.getCenterY(), R.getCenterX(), R.getMaxY()),
				new Line2D.Double(R.getCenterX(), R.getMaxY(), R.getX(), R.getCenterY()),
				new Line2D.Double(R.getX(), R.getCenterY(), R.getCenterX(), R.getY()) });
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	public Line2D Collision(Structure S) {
		return S.collission(this.SolidBox);
	}

	public Line2D Collision(Position P, Structure S) {
		Rectangle R = (Rectangle) this.SolidBox.clone();
		R.x += P.x;
		R.y += P.y;

		return S.collission(this.SolidBox);
	}

	public Line2D Collision(Rectangle R, Structure S) {
		return S.collission(R);
	}

	public Line2D[] getCollisions(Rectangle R, Structure S) {
		return S.getCollisions(R);

	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		Skeloton.Draw(g);
	}

}
