package com.firstproject.main;

import java.util.LinkedList;

public class Animation {
	int Frame = 0;
	Skeloton[] Frames;
	Position[] FVel;
	public LinkedList<Integer> KeyFrames = new LinkedList<Integer>();

	public Animation() {

	}

	public Animation(Skeloton[] Frames) {
		this.Frames = Frames;
		this.FVel = new Position[Frames.length];
		AutoFilter();
		// AutoFill(0,6);
		// AutoFill(6,Frames.length-1);
		// AutoFill(Frames.length/2,Frames.length-1);
		// AutoFill(0,Frames.length);

		for (int i = 0; i < Frames.length; i++) {
			FVel[i] = new Position(Frames[i].Position.x, Frames[i].Position.y, Frames[i].Position.z);
		}
		updateAll();
	}

	public Animation(Skeloton[] Frames, LinkedList<Integer> KeyFrames) {
		this.Frames = new Skeloton[KeyFrames.getLast() + 1];
		this.KeyFrames = KeyFrames;

		for (int i = 0; i < KeyFrames.size(); i++) {
			this.Frames[KeyFrames.get(i)] = Frames[i];
		}

		this.FVel = new Position[this.Frames.length];
		AutoFilter();

		// AutoFill(0,6);
		// AutoFill(6,Frames.length-1);
		// AutoFill(Frames.length/2,Frames.length-1);
		// AutoFill(0,Frames.length);

		for (int i = 0; i < this.Frames.length; i++) {
			FVel[i] = new Position(this.Frames[i].Position.x, this.Frames[i].Position.y, this.Frames[i].Position.z);
		}
	}

	@Override
	public Animation clone() {
		Animation c = new Animation(Frames.clone(), (LinkedList<Integer>) KeyFrames.clone());
		return c;
	}

	public void tick() {
		if (Frame < Frames.length - 1)
			Frame++;
		else
			Frame = 0;
		this.update();
	}

	public void tickBack() {
		if (Frame > 0)
			Frame--;
		else
			Frame = Frames.length - 1;
		this.update();
	}

	public Skeloton getFrame() {

		return Frames[Frame];
	}

	public boolean finished() {
		return Frame == Frames.length - 1;
	}

	public Position getFVel() {
		return FVel[Frame];
	}

	public void AutoFilter() {
		boolean a = KeyFrames.size() == 0;
		if (a)
			KeyFrames.add(0);
		for (int i = 0; i < Frames.length; i++) {
			if (Frames[i] == null) {
				int I = i - 1;
				int J = i;
				while (Frames[J] == null)
					J++;
				AutoFill(I, J);
				if (a)
					KeyFrames.add(J);
			}
		}
	}

	public int countNoneAutoFilledFrames() {
		int result = 0;
		for (int i = 0; i < Frames.length; i++)
			if (Frames[i].String != "AutoFilled")
				result++;

		return result;

	}

	public int getGridKeyFrameIndex(int F) {

		int cp = this.Frames.length;
		int r = 0;
		int[] cpa = new int[this.KeyFrames.size()];
		for (int i = 0; i < this.KeyFrames.size(); i++)
			cpa[i] = Math.abs(this.KeyFrames.get(i) - F);

		for (int i = 0; i < this.KeyFrames.size(); i++) {
			if (cp > cpa[i]) {
				cp = cpa[i];
				r = i;
			}
		}

		return r;

	}

	public void AutoFill(int F1, int F2) {

		// Skeloton vel=Frames[F1].Difference(Frames[F2]);
		// vel=vel.getmultiplied(1/(F2-F1));
		// Skeloton N = new Skeloton(vel);
		double M = 1 / (double) (F2 - F1);
		Skeloton vel = Frames[F1].Difference(Frames[F2]).getmultiplied(M).clone();
		Skeloton N = Frames[F1].clone();

		for (int i = F1; i < F2; i++) {
			// N=N.plusAngle(vel.clone());
			// Skeloton N = vel.getmultiplied(i);
			Frames[i] = N.plus(vel.getmultiplied(i - F1));
			Frames[i].String = "AutoFilled";
			// N=N.plus(vel);
		}
		Frames[F1].String = "KeyFrame";
		Frames[F2].String = "KeyFrame";
	}

	/*
	 * Skeloton vel=new Skeloton(Frames[F1]); vel.min(Frames[F2]);
	 * vel=vel.getmultiplied(1/(F2-F1)); Skeloton N = new Skeloton(Frames[F1]);
	 * 
	 * for(int i=F1;i<F2;i++) { Frames[i]=new Skeloton(N); N.add(vel);
	 */

	public void Fill(int F1, int F2, Skeloton S) {
		for (int i = F1; i < F2; i++)
			Frames[i] = S.clone();
	}

	public String getCreatorCode() {
		int FN = 0;
		String R = "";
		update();
		R += "new Animation(";

		R += "new Skeloton[]{";
		for (int i = 0; i < this.KeyFrames.size(); i++) {
			R += this.Frames[this.KeyFrames.get(i)].getCreatorCode() + ",";
		}
		R += "},new LinkedList<Integer>(Arrays.asList(";
		for (int i = 0; i < this.KeyFrames.size(); i++) {
			R += this.KeyFrames.get(i);
			if (i < this.KeyFrames.size() - 1)
				R += ",";
		}
		R += "))";

		R += ")";
		return R;

	}

	public void update() {
		// for(int i=0;i<Frames.length;i++)
		Frames[Frame].update();
	}

	public void updateAll() {
		for (int i = 0; i < this.Frames.length; i++) {
			Frames[i].update();
		}
	}

}
