package com.firstproject.main;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

public class Structure {
	Line2D.Float[] Lines;
	boolean Active;

	public Structure(Line2D.Float line) {
		this.Lines = new Line2D.Float[] { line };
	}

	public Structure(Line2D.Float[] Lines) {
		this.Lines = Lines;
	}

	public Structure(Rectangle R) {
		Lines = new Line2D.Float[] { new Line2D.Float(R.x, R.y, R.x + R.width, R.y),
				new Line2D.Float(R.x + R.width, R.y, R.x + R.width, R.y + R.height),
				new Line2D.Float(R.x + R.width, R.y + R.height, R.x, R.y + R.height),
				new Line2D.Float(R.x, R.y + R.height, R.x, R.y), };
	}

	public Structure(Line2D[] L) {
		Lines = new Line2D.Float[L.length];
		for (int i = 0; i < Lines.length; i++) {
			Lines[i] = new Line2D.Float(0.0f, 0.0f, 0.0f, 0.0f);
			Lines[i].x1 = (float) L[i].getX1();
			Lines[i].y1 = (float) L[i].getY1();
			Lines[i].x2 = (float) L[i].getX2();
			Lines[i].y2 = (float) L[i].getY2();
		}
	}

	public boolean intersectsLine(Line2D L) {
		for (int i = 0; i < Lines.length; i++)
			if (L.intersectsLine(Lines[i]))
				return true;

		return false;
	}

	public Line2D collission(Rectangle R) {
		if (R != null)
			for (int i = 0; i < Lines.length; i++)
				if (R.intersectsLine(Lines[i].getX1(), Lines[i].getY1(), Lines[i].getX2(), Lines[i].getY2()))
					return Lines[i];

		return null;
	}

	public Line2D.Float collission(Structure S) {

		for (int i = 0; i < Lines.length; i++)
			for (int j = 0; j < S.Lines.length; j++)
				if (Lines[i].intersectsLine(S.Lines[j]))
					return S.Lines[j];
		return null;
	}

	public boolean collission(Polygon P) {

		for (int i = 0; i < Lines.length; i++)
			for (int j = 1; j < P.npoints; j++)
				if (Lines[i].intersectsLine(P.xpoints[j - 1], P.ypoints[j - 1], P.xpoints[j], P.ypoints[j]))
					return true;

		return false;
	}

	public void Draw(Graphics2D g) {
		for (int i = 0; i < Lines.length; i++)
			g.draw(Lines[i]);
	}

	public Line2D[] getCollisions(Rectangle R) {
		Line2D[] result = new Line2D[Lines.length];
		int L = 0;
		for (int i = 0; i < Lines.length; i++)
			if (R.intersectsLine(Lines[i].getX1(), Lines[i].getY1(), Lines[i].getX2(), Lines[i].getY2())) {
				result[L] = Lines[i];
				L++;
			}
		if (L != 0) {
			Line2D[] r = new Line2D[L];
			for (int i = 0; i < L; i++)
				r[i] = result[i];
			return r;
		} else
			return null;
	}

}
