package com.firstproject.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.lang.reflect.Field;
import java.util.LinkedList;

public class Bone extends Fragment {

	public String ID = "Active";
	public Angle3D Angle;
	public double Length;
	public HitBox HitBox;
	public Position PS;
	public Position PE;

	public Bone(String string) {

		String[] S = Game.ConvertToObjectArguments(string);
		String[] A = Game.ConvertToObjectArguments(S[0]);
		String[] L = Game.ConvertToObjectArguments(S[1]);
		String[] R = Game.ConvertToObjectArguments(S[2]);
		String[] J = Game.ConvertToObjectArguments(S[3]);

		this.ID = "";
		this.Angle = new Angle3D(Double.parseDouble(A[0]), Double.parseDouble(A[1]), Double.parseDouble(A[2]));
		this.Length = Double.parseDouble(L[0]);
		this.PS = new Position(0, 0, 0);

		if (J != null) {
			this.Joint = new Bone[J.length - 1];
			for (int i = 0; i < J.length - 1; i++) {
				// System.out.println(J[i]);
				if (J[i] != "") {
					conect(i, new Bone(J[i]));
					this.Joint[i].Owner = this;
				} else {
					this.Joint = null;
					break;
				}
			}
		}
		this.HitBox = new HitBox(this.PS, this.Angle, this.Length, Float.parseFloat(R[0]), this);
		update();
	}

	public Bone(Position PS, Angle3D Angle, double Length, double width, Bone[] Joint, String ID) {
		super(Joint);

		this.ID = ID;
		this.Angle = Angle;
		this.Length = Length;
		this.PS = PS;
		// System.out.println(n);
		// for(int i=0;i<n;i++)
		this.HitBox = new HitBox(this.PS, this.Angle, this.Length, width, this);

		update();
		// System.out.println(": "+this.getInString(""));

	}

	public Bone(Position PS, Angle3D Angle, double Length, HitBox HitBox, Bone[] Joint) {
		super(Joint);

		this.Angle = Angle;
		this.Length = Length;
		this.PS = PS;
		// System.out.println(n);
		// for(int i=0;i<n;i++)
		this.HitBox = HitBox;
		this.HitBox.PS = this.PS;

		update();
		// System.out.println(": "+this.getInString(""));

	}

	public Bone(Bone Owner, Angle3D Angle, double Length, double width, Bone[] Joint, String ID) {
		super(Joint, Owner);

		this.Angle = Angle;
		this.Length = Length;
		this.ID = ID;
		this.PS = Owner.PE;
		int n = (int) Math.floor(Length / width) + 1;
		// System.out.println(n);
		// for(int i=0;i<n;i++)
		this.HitBox = new HitBox(this.PS, this.Angle, this.Length, width, this);

		update();

	}

	public Bone(Bone B) {
		super(B.Joint, B.Owner);

		this.Angle = B.Angle;
		this.Length = B.Length;
		this.ID = B.ID;
		// for(int i=0;i<B.HitBox.size();i++)
		this.HitBox = B.HitBox.clone();

		update();
	}

	public Bone() {

	}

	@Override
	public Bone clone() {
		Bone result = new Bone();
		result.PS = new Position(this.PS.x, this.PS.y, this.PS.z);
		result.Angle = new Angle3D(this.Angle.X_direction, this.Angle.Y_direction, this.Angle.Z_direction);
		result.Length = this.Length;
		result.Owner = this.Owner;

		result.Joint = this.Joint.clone();
		// result.Joint=new Fragment[this.Joint.length];
		for (int i = 0; i < this.Joint.length; i++) {
			result.Joint[i] = ((Bone) this.Joint[i]).clone();
			result.Joint[i].Owner = result;
		}
		// for(int i=0;i<this.HitBox.size();i++) {
		result.HitBox = this.HitBox.clone();
		result.HitBox.Owner = result;

		return result;

	}

	public boolean isInPositionCollision(Position P) {
		this.update();
		return new Line2D.Double(this.PS.x, this.PS.y, this.PE.x, this.PE.y).contains(P.x, P.y);
	}

	public Bone getBone(String ST) {
		// ST.split(" ", 2)[0];
		// Integer.parseInt
		// System.out.println(ST);

		if (ST.length() == 0) {
			return this;
		}
		if (ST.length() == 1) {
			return (Bone) Joint[Integer.parseInt(ST)];
		}

		int i = 0;
		while (i < ST.length() && !Character.isDigit(ST.charAt(i)))
			i++;
		int j = i;
		while (j < ST.length() && Character.isDigit(ST.charAt(j)))
			j++;
		int r = Integer.parseInt(ST.substring(i, j)); // might be an off-by-1 here
		// System.out.println(r);
		// ST.substring(j);
		if (j + 1 < ST.length())
			ST = ST.substring(j + 1);

		// System.out.println(ST);
		// if(r<Bones.length)

		if (Joint.length != 0) {
			return ((Bone) Joint[r]).getBone(ST);
		}
		return null;

	}

	public String getInString(String S) {

		if (Owner != null) {
			if (S.length() != 0)
				S = Owner.getIndex(this) + ">" + S;
			else
				S = Owner.getIndex(this) + S;
			return ((Bone) Owner).getInString(S);

		}

		return S;

	}

	public LinkedList<HitBox> getAllHitBoxes(LinkedList<HitBox> H) {
		if (this.HitBox.Radius != 0)
			H.add(this.HitBox);
		for (int i = 0; i < this.Joint.length; i++)
			H = ((Bone) this.Joint[i]).getAllHitBoxes(H);
		return H;
	}

	public void mergeAngle(Bone B) {
		if (B != null) {
			this.Angle.addAngle(B.Angle);

			for (int i = 0; i < Joint.length; i++) {
				((Bone) Joint[i]).mergeAngle(((Bone) B.Joint[i]));
			}
		}
	}

	public void addAngle(Bone B) {
		Angle.addAngle(B.Angle);
		// this.Length+=B.Length;
		for (int i = 0; i < Joint.length; i++) {
			((Bone) Joint[i]).addAngle(((Bone) B.Joint[i]));
		}
	}

	public void plusPosition(Position P) {
		this.PS.plus(P);
		this.update();
	}

	public void addAngle(Angle3D Angle) {
		this.Angle.addAngle(Angle);
		// this.Length+=B.Length;
		for (int i = 0; i < Joint.length; i++) {
			((Bone) Joint[i]).addAngle(Angle);
		}
	}

	public void minAngle(Bone B) {
		this.Angle.minAngle(B.Angle);
		this.Length -= B.Length;
		for (int i = 0; i < Joint.length; i++)
			((Bone) Joint[i]).minAngle(((Bone) B.Joint[i]));

	}

	public void minHitBox(Bone B) {
		// for(int i=0;i<this.HitBox.size();i++)
		this.HitBox = new HitBox(this.PS, this.Angle, Length, this.HitBox.Radius - B.HitBox.Radius, this);

		this.Length -= B.Length;
		for (int i = 0; i < Joint.length; i++)
			((Bone) Joint[i]).minHitBox(((Bone) B.Joint[i]));

	}

	public void addAsPresentage(Bone B, double D) {
		this.Angle.Z_direction += D;
		for (int i = 0; i < Joint.length; i++)
			((Bone) Joint[i]).addAsPresentage(((Bone) B.Joint[i]), D);

	}

	public Bone Difference(Bone B) {
		Bone result = this.clone();
		result.Angle = result.Angle.Difference(B.Angle);
		// for(int i=0;i<this.HitBox.size();i++)
		result.HitBox = new HitBox(result.PS, result.Angle, result.Length, B.HitBox.Radius - result.HitBox.Radius,
				result);

		for (int i = 0; i < Joint.length; i++) {
			result.Joint[i] = ((Bone) result.Joint[i]).Difference((Bone) B.Joint[i]);
		}

		return result;
	}

	public Bone multiplyAngle(double M) {
		Bone result = this.clone();
		result.Angle = result.Angle.getmultiplied(M);
		// result.HitBox=new HitBox(result.PS, result.Angle, result.Length,
		// result.HitBox.Radius*M);

		for (int i = 0; i < result.Joint.length; i++)
			result.Joint[i] = ((Bone) result.Joint[i]).multiplyAngle(M);

		return result;
	}

	public Bone multiplyHitBox(double M) {
		Bone result = this.clone();
		// for(int i=0;i<result.HitBox.size();i++)
		result.HitBox = new HitBox(result.PS, result.Angle, result.Length, result.HitBox.Radius * M, this);

		for (int i = 0; i < result.Joint.length; i++)
			result.Joint[i] = ((Bone) result.Joint[i]).multiplyHitBox(M);

		return result;
	}

	public Bone plusAngle(Bone bone) {
		Bone result = this.clone();
		result.Angle = result.Angle.plus(bone.Angle);
		// result.HitBox=new HitBox(result.PS, result.Angle, result.Length,
		// result.HitBox.Radius+bone.HitBox.Radius);

		for (int i = 0; i < Joint.length; i++)
			result.Joint[i] = ((Bone) result.Joint[i]).plusAngle((Bone) bone.Joint[i]);
		return result;
	}

	public Bone plusHitBox(Bone bone) {
		Bone result = this.clone();
		// for(int i=0;i<result.HitBox.size();i++)
		result.HitBox = new HitBox(result.PS, result.Angle, result.Length, result.HitBox.Radius + bone.HitBox.Radius,
				this);

		for (int i = 0; i < Joint.length; i++)
			result.Joint[i] = ((Bone) result.Joint[i]).plusHitBox((Bone) bone.Joint[i]);
		return result;
	}

	public Position getHighest(Position V) {

		// for(int i=0;i<HitBox.size();i++) {
		if (V.x < PE.x + HitBox.Radius / 2)
			V.x = (float) (PE.x + HitBox.Radius / 2);
		if (V.y < PE.y + HitBox.Radius / 2)
			V.y = (float) (PE.y + HitBox.Radius / 2);
		// }

		for (int i = 0; i < Joint.length; i++)
			V = ((Bone) Joint[i]).getHighest(V);
		return V;
	}

	public Position getLowest(Position V) {

		// for(int i=0;i<HitBox.size();i++) {
		if (V.x > PE.x - HitBox.Radius / 2)
			V.x = (float) (PE.x - HitBox.Radius / 2);
		if (V.y > PE.y - HitBox.Radius / 2)
			V.y = (float) (PE.y - HitBox.Radius / 2);
		// }

		for (int i = 0; i < Joint.length; i++)
			V = ((Bone) Joint[i]).getLowest(V);
		return V;
	}

	public Bone SearchBoneByID(String ID) {
		if (this.ID == ID)
			return this;
		for (int i = 0; i < Joint.length; i++) {
			Bone J = (Bone) Joint[i];
			Bone B = J.SearchBoneByID(ID);
			if (B.ID == ID)
				return B;
		}

		return null;
	}

	public void update() {
		Bone Owner = (Bone) this.Owner;

		// Angle.Z_direction++;

		if (Owner != null)
			PS = Owner.PE;
		PE = new Position((int) Length, 0, 0);
		if (Angle != null) {
			PE.Turn(new Position(0, 0, 0), Angle);

		}
		if (PS != null)
			PE.union(PS);

		HitBox.PS = this.PS;
		HitBox.Angle = this.Angle;
		HitBox.update();

		for (int i = 0; i < Joint.length; i++)
			((Bone) Joint[i]).update();
	}

	public void setBonesValue(String valuename, Object value)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

		Field field;
		field = HitBox.class.getField(valuename);
		field.set(this.HitBox, value);

		for (int i = 0; i < this.Joint.length; i++) {
			((Bone) this.Joint[i]).setBonesValue(valuename, value);
		}
	}

	public void addPosition(Position P) {

		PS.plus(P);

		for (int i = 0; i < Joint.length; i++)
			((Bone) Joint[i]).addPosition(P);

	}

	public void Draw(Graphics2D g) {
		// if(Owner!=null)g.setColor(Color.blue);else g.setColor(Color.red);

		// for(int i=0;i<HitBox.size();i++)
		HitBox.Draw(g);

		Line2D L = new Line2D.Double((int) PS.x, (int) PS.y, (int) PE.x, (int) PE.y);
		g.draw(L);
		g.drawOval((int) PS.x - 3, (int) PS.y - 3, 6, 6);

		// g.drawRect(PS.x-5, PS.y-5, 5, 7);
		for (int i = 0; i < Joint.length; i++) {
			Bone D = (Bone) Joint[i];
			if (g.getColor() == Color.YELLOW)
				g.setColor(Color.GREEN);
			D.Draw(g);
		}

	}

	public String GetCreatorCode(String C) {
		String R = "";
		R += C;
		R += "new Bone(";
		R += this.PS.GetCreatorCode() + ", ";
		R += this.Angle.GetCreatorCode() + ", ";
		R += this.Length + ", ";
		R += "10,";
		R += "new Bone[] {";
		for (int i = 0; i < this.Joint.length; i++)
			R += ((Bone) this.Joint[i]).GetCreatorCode("/*index: " + i + "*/") + ",";
		R += "}, null)";

		return R;
	}

	public String getDataPrint() {
		String S = "B[";
		S += "A[";
		S += this.Angle.X_direction + "|";
		S += this.Angle.Y_direction + "|";
		S += this.Angle.Z_direction;
		S += "]|";

		S += "L[" + this.Length + "]|";
		S += "W[" + this.HitBox.Radius + "]|";

		S += "J[";
		for (int i = 0; i < this.Joint.length; i++) {
			S += ((Bone) this.Joint[i]).getDataPrint();
			if (i < this.Joint.length - 1)
				S += "|";
		}
		S += "]";
		S += "]";

		return S;
	}

	public Bone getBoneInContact(Position p) {
		if (this.isInPositionCollision(p))
			return this;
		for (int i = 0; i < this.Joint.length; i++)
			if (((Bone) this.Joint[i]).getBoneInContact(p) != null)
				return ((Bone) this.Joint[i]).getBoneInContact(p);
		return null;
	}

	public HitBox[] Intersect(HitBox hitBox) {
		HitBox[] r = null;

		HitBox t = this.HitBox.Intersect(hitBox);
		if (t != null) {
			this.HitBox.triggerd = true;
			r = new HitBox[] { this.HitBox, t };
		} else {
			this.HitBox.triggerd = false;
		}

		for (int i = 0; i < this.Joint.length; i++) {
			HitBox[] H = ((Bone) this.Joint[i]).Intersect(hitBox);
			if (H != null)
				r = H;
		}

		return r;
	}

	public HitBox Intersect(Bone bone) {
		return this.HitBox.Intersect(bone.HitBox);
	}

}
