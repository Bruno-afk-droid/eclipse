package com.firstproject.main;

import java.util.LinkedList;

public class Move extends Animation {
	LinkedList<Integer> TriggerdBy = new LinkedList<Integer>();
	LinkedList<HitBox[]> DisjointedHitboxes = new LinkedList<HitBox[]>();
	int inputpriority = 0;
	Position SP = new Position(0, 0, 0);

	double grv = 0;

	State[] MoveStates;

	State MoveState = new State();
	Position[] vel;

	Skeloton ActiveFrame;

	public class State {
		int index = 0;

		Move Owner;
		public boolean abolishable = true;
		Position vel = new Position(0, 0, 0);
		Damage Damage = new Damage();
		Armor Armor = new Armor();
		int SuperArmor = 0;
		int Priority = 1;

		public State(int index, Move Owner, Position vel, Damage Damage, Armor Armor, int SuperArmor, int Priority) {
			this.index = index;
			this.Owner = Owner;
			this.vel = vel;
			this.Damage = Damage;
			this.Armor = Armor;
			this.SuperArmor = SuperArmor;
			this.Priority = Priority;
		}

		public State() {

		}

		@Override
		public State clone() {
			return new State(this.index, this.Owner.clone(), this.vel.clone(), this.Damage.clone(), this.Armor.clone(),
					this.SuperArmor, this.Priority);
		}

		public Damage GetDamage(Move Move) {
			HitBox[] I = Intersection(Move);
			if (I != null) {
				if (this.Priority >= Move.MoveState.Priority) {

					Armor A = this.Armor;
					Damage D = this.Damage;

					if (I[0].Armor != null)
						A = I[0].Armor;
					if (I[1].Damage != null)
						D = I[1].Damage;

					return A.Earn_Damage(D);
				}
			}
			return null;
		}

		public HitBox[] Intersection(Move Move) {
			if (Owner == null) {
				return null;
			}
			HitBox[] r = Owner.getFrame().Intersect(Move.getFrame());
			return r;
		}

	}

	public Move(String string) {
		Animation T = this;

		String[] s = Game.ConvertToObjectArguments(string);

		String[] f = Game.ConvertToObjectArguments(s[0]);
		String[] d = Game.ConvertToObjectArguments(s[1]);
		String[] v = Game.ConvertToObjectArguments(s[2]);
		String[] k = Game.ConvertToObjectArguments(s[3]);
		String[] t = Game.ConvertToObjectArguments(s[4]);

		this.Frames = new Skeloton[Integer.parseInt(k[k.length - 1]) + 1];
		this.DisjointedHitboxes = new LinkedList<HitBox[]>();
		this.vel = new Position[v.length];
		this.KeyFrames = new LinkedList<Integer>();
		this.TriggerdBy = new LinkedList<Integer>();

		for (int i = 0; i < f.length - 1; i++)
			this.Frames[Integer.parseInt(k[i])] = new Skeloton(f[i]);

		for (int i = 0; i < d.length - 1; i++) {
			String[] h = Game.ConvertToObjectArguments(d[i]);
			this.DisjointedHitboxes.add(new HitBox[h.length]);
			for (int j = 0; j < h.length; i++) {
				String[] ar = Game.ConvertToObjectArguments(h[j]);
				String[] p = Game.ConvertToObjectArguments(ar[0]);
				String[] an = Game.ConvertToObjectArguments(ar[1]);

				this.DisjointedHitboxes.get(i)[j] = new HitBox(
						new Position(Float.parseFloat(p[0]), Float.parseFloat(p[1]), Float.parseFloat(p[2])),
						new Angle3D(Float.parseFloat(an[0]), Float.parseFloat(an[1]), Float.parseFloat(an[2])),
						Float.parseFloat(Game.ConvertToObjectArguments(ar[3])[0]),
						Float.parseFloat(Game.ConvertToObjectArguments(ar[4])[0]), this);

			}
		}

		for (int i = 0; i < k.length; i++) {
			this.KeyFrames.add(Integer.parseInt(k[i]));
		}

		this.vel = new Position[Frames.length];
		for (int i = 0; i < v.length - 2; i++) {
			String[] vel = Game.ConvertToObjectArguments(v[0]);
			for (int j = KeyFrames.get(i); j <= KeyFrames.get(i + 1); j++) {
				this.vel[j] = new Position(Float.parseFloat(vel[0]), Float.parseFloat(vel[1]),
						Float.parseFloat(vel[2]));
			}
		}

		for (int i = 0; i < t.length; i++) {
			this.TriggerdBy.add(Integer.parseInt(t[i]));
		}

		((Animation) this).FVel = new Position[Frames.length];
		((Animation) this).AutoFilter();
		// AutoFill(0,6);
		// AutoFill(6,Frames.length-1);
		// AutoFill(Frames.length/2,Frames.length-1);
		// AutoFill(0,Frames.length);

		for (int i = 0; i < Frames.length; i++) {
			((Animation) this).FVel[i] = new Position(Frames[i].Position.x, Frames[i].Position.y, Frames[i].Position.z);
		}

		this.MoveStates = new State[this.Frames.length];

		setup();

	}

	public Move() {
		super();
	}

	public Move(Animation Animation, Position[] vel, LinkedList<Integer> TriggerdBy) {
		super(Animation.Frames);
		this.KeyFrames = Animation.KeyFrames;
		this.vel = vel;
		this.TriggerdBy = TriggerdBy;
		this.ActiveFrame = this.getFrame();
		this.MoveStates = new State[this.Frames.length];
		setup();
	}

	public Move(Animation Animation, Position vel, LinkedList<Integer> TriggerdBy) {
		super(Animation.Frames);
		this.KeyFrames = Animation.KeyFrames;
		this.vel = new Position[Frames.length];
		this.TriggerdBy = TriggerdBy;
		this.ActiveFrame = this.getFrame();
		this.MoveStates = new State[this.Frames.length];

		if (vel == null) {
			vel = new Position(0, 0, 0);
		} else {
			vel.multiply((double) 1 / this.vel.length);
		}

		for (int i = 0; i < this.vel.length; i++)
			this.vel[i] = vel;

		setup();
	}

	public Move(Skeloton[] Frames, Position[] vel, LinkedList<Integer> TriggerdBy) {
		super(Frames);
		this.vel = vel;
		this.TriggerdBy = TriggerdBy;
		this.ActiveFrame = this.getFrame();
		this.MoveStates = new State[this.Frames.length];
		setup();
	}

	public Move(Skeloton[] Frames, Position[] vel, LinkedList<Integer> KeyFrames, LinkedList<Integer> TriggerdBy) {
		super(Frames, KeyFrames);
		this.vel = vel;
		this.TriggerdBy = TriggerdBy;
		this.ActiveFrame = this.getFrame();
		this.MoveStates = new State[this.Frames.length];

		if (this.vel == null) {
			this.vel = new Position[this.Frames.length];
			for (int i = 0; i < this.vel.length; i++) {
				this.vel[i] = new Position(0, 0, 0);
			}
		}

		for (int i = 0; i < this.KeyFrames.size(); i++) {
			MoveStates[this.KeyFrames.get(i)] = new State(this.KeyFrames.get(i), this, vel[i], new Damage(),
					new Armor(), 0, 1);
		}

		// setup();
	}

	public Move(Skeloton[] Frames, Position vel, LinkedList<Integer> KeyFrames, LinkedList<Integer> TriggerdBy) {
		super(Frames, KeyFrames);
		this.vel = new Position[Frames.length];

		this.TriggerdBy = TriggerdBy;
		this.ActiveFrame = this.getFrame();
		this.MoveStates = new State[this.Frames.length];

		for (int i = 0; i < this.vel.length; i++)
			this.vel[i] = vel;

		// setup();
	}

	@Override
	public Move clone() {
		Skeloton[] frames = this.Frames.clone();
		Position[] vel = this.vel.clone();
		LinkedList<Integer> key = (LinkedList<Integer>) this.KeyFrames.clone();
		LinkedList<Integer> tri = null;
		if (this.TriggerdBy != null)
			tri = (LinkedList<Integer>) this.TriggerdBy.clone();

		return new Move(frames, vel, key, tri);
	}

	public void setup() {
		if (this.vel == null) {
			this.vel = new Position[this.Frames.length];
			for (int i = 0; i < this.vel.length; i++) {
				this.vel[i] = new Position(0, 0, 0);
			}
		}

		int p = 0;
		for (int i = 0; i < this.KeyFrames.size(); i++) {
			MoveStates[this.KeyFrames.get(i)] = new State(this.KeyFrames.get(i), this, vel[i], new Damage(),
					new Armor(), 0, 0);
		}
		this.updateAll();

	}

	public void clear() {
		this.Frame = 0;
		this.update();
	}

	public Damage GetDamage(Move Move) {
		return MoveState.GetDamage(Move);
	}

	public String getDataPrint() {
		String S = "M[";

		S += "F[";
		for (int i = 0; i < this.KeyFrames.size(); i++) {
			S += this.Frames[this.KeyFrames.get(i)].getDataPrint();
			if (i < this.KeyFrames.size() - 1)
				S += "|";
		}
		S += "]|";

		S += "D[";
		for (int i = 0; i < this.DisjointedHitboxes.size(); i++) {
			S += "L[";
			for (int j = 0; j < this.DisjointedHitboxes.get(i).length; i++) {
				S += this.DisjointedHitboxes.get(i)[j].getDataPrint();
				if (i < this.DisjointedHitboxes.get(i).length) {
					S += "|";
				}
			}
			S += "]";
			if (i < this.DisjointedHitboxes.size()) {
				S += "|";
			}
		}
		S += "]|";

		S += "V[";
		for (int i = 0; i < this.MoveStates.length; i++) {
			if (this.MoveStates[i] != null) {
				S += "P[" + this.MoveStates[i].vel.x + "|";
				S += this.MoveStates[i].vel.y + "|";
				S += this.MoveStates[i].vel.z + "]";

				if (i < this.MoveStates.length - 1)
					S += "|";
			}
		}
		S += "]|";

		S += "K[";
		for (int i = 0; i < this.KeyFrames.size(); i++) {
			S += this.KeyFrames.get(i);
			if (i < this.KeyFrames.size() - 1)
				S += "|";
		}

		S += "]|";

		S += "T[";
		for (int i = 0; i < this.TriggerdBy.size(); i++) {
			S += this.TriggerdBy.get(i);

			if ((i < this.TriggerdBy.size() - 1))
				S += "|";
		}
		S += "]";

		S += "]";
		return S;
	}

	@Override
	public void update() {
		Frames[Frame].update();
		ActiveFrame = this.Frames[Frame];

		if (MoveStates[Frame] != null) {
			MoveState = MoveStates[Frame];
		}

	}

	@Override
	public void tick() {
		if (Frame < Frames.length - 1) {
			Frame++;
		} else {
			Frame = 0;

		}

		this.SP.plus(vel[Frame]);
		this.update();
	}

	public Animation getAnimation() {
		return this;
	}

}
