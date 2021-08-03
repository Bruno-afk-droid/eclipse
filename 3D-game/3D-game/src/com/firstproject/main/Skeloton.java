package com.firstproject.main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Skeloton implements Cloneable {
	Position Position;
	Angle3D Angle;
	Bone[] Bones;
	Rectangle HitBox;
	String String = "";

	public Skeloton(String string) {

		this.Position = new Position(0, 0, 0);
		String[] S = Game.ConvertToObjectArguments(string);
		this.Bones = new Bone[S.length - 1];
		for (int i = 0; i < S.length - 1; i++) {
			this.Bones[i] = new Bone(S[i]);
		}
		this.HitBox = getBounds();
		update();
	}

	public Skeloton(Position Position, Angle3D Angle, Bone[] Bones) {
		this.Position = Position;
		this.Bones = Bones;
		this.HitBox = getBounds();
		this.HitBox = new Rectangle(0, 0, 0, 0);
		update();

	}

	public Skeloton(Position Position, Angle3D Angle, Bone[] Bones, String S) {
		this.Position = Position;
		this.Bones = Bones;
		this.String = S;
		update();
	}

	public Skeloton(Skeloton S) {
		this.Position = new Position(S.Position);
		this.Bones = S.Bones.clone();
	}

	@Override
	public Skeloton clone() {
		Bone[] Bones = new Bone[this.Bones.length];
		for (int i = 0; i < this.Bones.length; i++)
			Bones[i] = this.Bones[i].clone();
		return new Skeloton(new Position(Position.x, Position.y, Position.z), new Angle3D(), Bones, null);
	}

	public Bone getBoneInContact(Position p) {
		for (int i = 0; i < this.Bones.length; i++)
			if (this.Bones[i].getBoneInContact(p) != null)
				return this.Bones[i].getBoneInContact(p);
		return null;
	}

	public Position getHighest(Position V) {

		for (int i = 0; i < Bones.length; i++)
			V = Bones[i].getHighest(V);
		return V;
	}

	public Position getLowest(Position V) {

		for (int i = 0; i < Bones.length; i++)
			V = Bones[i].getLowest(V);
		return V;
	}

	public Rectangle getBounds() {
		Position S = getLowest(new Position(Position.x, Position.y, Position.z));
		Position E = getHighest(new Position(Position.x, Position.y, Position.z));
		return new Rectangle((int) S.x, (int) S.y, (int) E.x - (int) S.x, (int) E.y - (int) S.y);
	}

	public void Draw(Graphics2D g) {
		for (int i = 0; i < Bones.length; i++) {
			Bones[i].Draw(g);
		}
	}

	public void tick() {
		update();
	}

	public int getIndex(Bone Bone) {
		for (int i = 0; i < Bones.length; i++)
			if (Bones[i] == Bone)
				return i;
		return -1;
	}

	public Bone getBone(String ST) {
		return Bones[0].getBone(ST);
	}

	public void mergeAngle(Skeloton S) {
		for (int i = 0; i < Bones.length; i++) {
			Bones[i].mergeAngle((S.Bones[i]));
		}
	}

	public void addAsPresentage(Skeloton S, double D) {
		for (int i = 0; i < Bones.length; i++)
			Bones[i].addAsPresentage(S.Bones[i], D);

	}

	public Skeloton Difference(Skeloton S) {
		Skeloton result = this.clone();
		result.Position = new Position(S.Position);
		result.Position.min(this.Position);

		for (int i = 0; i < Bones.length; i++) {
			result.Bones[i] = result.Bones[i].Difference(S.Bones[i]);
			result.Bones[i].PS = result.Position;
		}
		result.update();
		return result;
	}

	public void addAngle(Angle3D Angle) {

		for (int i = 0; i < this.Bones.length; i++) {
			this.Bones[i].addAngle(Angle);
		}
		update();
	}

	public void add(Skeloton S) {
		// this.Position.plus(S.Position);
		for (int i = 0; i < S.Bones.length; i++)
			Bones[i].addAngle(S.Bones[i]);
		update();
	}

	public void min(Skeloton S) {
		this.Position.min(S.Position);
		for (int i = 0; i < S.Bones.length; i++)
			Bones[i].minAngle(S.Bones[i]);
		update();
	}

	public void minPos(Position P) {
		this.Position = P;
		update();
	}

	public void minHitBox(Skeloton S) {
		// this.Position.min(S.Position);
		for (int i = 0; i < S.Bones.length; i++)
			Bones[i].minHitBox(S.Bones[i]);
		update();
	}

	public void setBonesValue(String valuename, Object value) {

		try {
			this.Bones[0].setBonesValue(valuename, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HitBox[] Intersect(HitBox HitBox) {
		return this.Bones[0].Intersect(HitBox);
	}

	public HitBox[] Intersect(Skeloton Skeloton) {
		HitBox[] r = null;
		LinkedList<HitBox> H = null;
		if (this.getBounds().intersects(Skeloton.getBounds())) {
			H = this.getAllHitBoxes();

			for (int i = 0; i < H.size(); i++) {
				r = this.Bones[0].Intersect(H.get(i));
				if (r != null)
					break;
			}
		} else {
			this.setBonesValue("triggerd", false);
		}

		return r;
	}

	public String getCreatorCode() {
		String R = "";
		update();
		R += "new Skeloton(";
		R += this.Position.GetCreatorCode() + ",";

		if (this.Angle != null) {
			R += this.Angle.GetCreatorCode() + ",";
		} else {
			R += "new Angle3D(),";
		}

		R += "new Bone[] {";

		for (int i = 0; i < this.Bones.length; i++)
			R += this.Bones[i].GetCreatorCode("/*index: " + i + "*/") + ",";

		R += "})";
		return R;
	}

	public String getDataPrint() {
		String S = "";
		S += "S[";
		for (int i = 0; i < this.Bones.length; i++) {
			S += this.Bones[i].getDataPrint();
			if (i < this.Bones.length - 1)
				S += "|";
		}
		S += "]";
		return S;
	}

	public void update() {
		for (int i = 0; i < Bones.length; i++)
			if (Bones[i] != null) {
				Bones[i].PS = this.Position;
				Bones[i].update();
			}
		this.HitBox = getBounds();
	}

	public Skeloton getmultiplied(double M) {
		Skeloton result = this.clone();
		result.Position.multiply(M);

		for (int i = 0; i < Bones.length; i++) {
			result.Bones[i] = result.Bones[i].multiplyAngle(M);
			result.Bones[i].PS = result.Position;
		}
		result.update();

		return result;
	}

	public Skeloton getmultipliedHitBox(double M) {
		Skeloton result = this.clone();

		for (int i = 0; i < Bones.length; i++) {
			result.Bones[i] = result.Bones[i].multiplyHitBox(M);
			result.Bones[i].PS = result.Position;
		}
		result.update();

		return result;
	}

	public LinkedList<HitBox> getAllHitBoxes() {
		LinkedList<HitBox> H = new LinkedList<HitBox>();
		for (int i = 0; i < Bones.length; i++)
			H = Bones[i].getAllHitBoxes(H);

		return H;
	}

	public Skeloton plus(Skeloton S) {
		Skeloton result = this.clone();
		result.Position.plus(S.Position);

		for (int i = 0; i < Bones.length; i++) {
			result.Bones[i] = result.Bones[i].plusAngle(S.Bones[i]);
			result.Bones[i].PS = result.Position;
		}

		result.update();
		return result;
	}

	public Skeloton plusHitBox(Skeloton S) {
		Skeloton result = this.clone();

		for (int i = 0; i < Bones.length; i++) {
			result.Bones[i] = result.Bones[i].plusHitBox(S.Bones[i]);
			// result.Bones[i].PS=result.Position;
		}

		result.update();
		return result;
	}

	public Skeloton plusAngle(Skeloton S) {
		Skeloton result = this.clone();
		// result.Position.plus(S.Position);

		for (int i = 0; i < Bones.length; i++)
			result.Bones[i] = result.Bones[i].plusAngle(S.Bones[i]);
		result.update();
		return result;
	}

}
