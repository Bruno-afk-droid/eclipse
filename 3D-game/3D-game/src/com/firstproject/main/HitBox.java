package com.firstproject.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class HitBox {
	public Boolean triggerd;
	String ID = "";
	Object Owner;

	Armor Armor = null;
	Damage Damage = null;

	Position PS;
	Position PD;

	// defauld
	Angle3D Angle;
	double Length;
	double Radius;

	// custom
	Circle CR1;
	Circle CR2;
	Line2D.Float L1;
	Line2D.Float L2;

	public HitBox(Position Position, Angle3D Angle, double Length, double Radius, Object Owner) {
		this.PS = Position;
		this.Angle = Angle;
		this.Radius = Radius;
		this.Length = Length;
		this.triggerd = false;

		CR1 = new Circle(PS, Radius);
		CR2 = new Circle(PD, Radius);
		L1 = new Line2D.Float(0.0f, 0.0f, 0.0f, 0.0f);
		L2 = new Line2D.Float(0.0f, 0.0f, 0.0f, 0.0f);
		update();
	}

	public void update() {

		PD = Game.LengthDir3D(Length, Angle);
		PD.plus(PS);

		CR1.Position = PS;
		CR2.Position = PD;

		L1.x1 = this.PS.x;
		L1.y1 = this.PS.y;

		L1.x2 = this.PD.x;
		L1.y2 = this.PD.y;

		L2.x1 = this.PS.x;
		L2.y1 = this.PS.y;

		L2.x2 = this.PD.x;
		L2.y2 = this.PD.y;

		Point2D.Double Ls = Game.LengthDir(Radius / 2, Math.toDegrees(Math.atan2(PD.y - PS.y, PD.x - PS.x) - 90));

		L1.x1 += Ls.x;
		L1.y1 += Ls.y;

		L1.x2 += Ls.x;
		L1.y2 += Ls.y;

		L2.x1 -= Ls.x;
		L2.y1 -= Ls.y;

		L2.x2 -= Ls.x;
		L2.y2 -= Ls.y;

	}

	@Override
	public HitBox clone() {
		HitBox result = new HitBox(new Position(PS.x, PS.y, PS.z),
				new Angle3D(Angle.X_direction, Angle.Y_direction, Angle.Z_direction), Length, Radius, Owner);
		return result;
	}

	public HitBox Intersect(HitBox S) {

		if (this.CR1.Intersect(S.CR1))
			return S;
		if (this.CR1.Intersect(S.CR2))
			return S;

		if (this.CR2.Intersect(S.CR1))
			return S;
		if (this.CR2.Intersect(S.CR2))
			return S;

		if (this.CR1.Intersect(S.L1))
			return S;
		if (this.CR1.Intersect(S.L2))
			return S;

		if (this.CR2.Intersect(S.L1))
			return S;
		if (this.CR2.Intersect(S.L2))
			return S;

		if (this.L1.intersectsLine(S.L1))
			return S;
		if (this.L1.intersectsLine(S.L2))
			return S;

		if (this.L2.intersectsLine(S.L1))
			return S;
		if (this.L2.intersectsLine(S.L2))
			return S;

		if (S.CR1.Intersect(this.L1))
			return S;
		if (S.CR2.Intersect(this.L1))
			return S;

		if (S.CR1.Intersect(this.L2))
			return S;
		if (S.CR2.Intersect(this.L2))
			return S;

		return null;
	}

	public String GetCreatorCode(String R) {
		R += "new HitBox(";
		R += PS.GetCreatorCode() + ",";
		R += Angle.GetCreatorCode() + ",";
		R += Length + ",";
		R += Radius + ",";
		R += null + ")";
		return R;
	}

	public String getDataPrint() {
		String S = "";

		S += "H[";
		S += "P[";
		S += this.PS.x + "|";
		S += this.PS.y + "|";
		S += this.PS.z;
		S += "]|";
		S += "A[";
		S += this.Angle.X_direction + "|";
		S += this.Angle.Y_direction + "|";
		S += this.Angle.Z_direction;
		S += "]|";
		S += "L[" + this.Length + "]|";
		S += "R[" + this.Radius + "]|";
		S += "O[null]";
		S += "]";

		return S;
	}

	public void Draw(Graphics2D g) {
		Color P = g.getColor();
		g.setColor(Color.LIGHT_GRAY);
		if (triggerd)
			g.setColor(Color.YELLOW);
		CR1.Draw(g);
		CR2.Draw(g);
		g.draw(L1);
		g.draw(L2);
		g.setColor(P);

	}

}
