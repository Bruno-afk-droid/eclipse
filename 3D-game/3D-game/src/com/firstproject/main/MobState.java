package com.firstproject.main;

import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.LinkedList;

public class MobState {

	public Position Position;
	public Structure gridOnStructure = Handler.Map;
	public Line2D.Float gridOn;
	public float lenPose = 0.00f;

	public double grv = 0;

	public LinkedList<Move> Moves;
	public LinkedList<Move> MoveBuffer = new LinkedList<Move>();
	int inputlag = 4;
	int lag = 0;
	public Move Move;

	public MobState(Position Position, LinkedList<Move> Moves) {
		this.Position = Position;
		this.Moves = Moves;
		this.Move = Moves.get(0);
	}

	@Override
	public MobState clone() {

		return new MobState(Position.clone(), (LinkedList<Move>) Moves.clone());
	}

	public void UseMove(int i) {
		if (Moves.indexOf(Move) != i) {
			Move.clear();
			Move = Moves.get(i);
		} else {

		}
	}

	public void tick() {
		if (Move.MoveState.abolishable) {
			for (int i = 0; i < Moves.size(); i++) {
				if (Moves.get(i).TriggerdBy.size() != 0)
					if (isoninput(Moves.get(i).TriggerdBy)) {

						UseMove(i);
						break;
					} else {
						if (Move.finished()) {
							UseMove(0);

						}
					}
			}

		}
		Move.tick();

		movePosition(Move.MoveState.vel);
	}

	public void movePosition(Position vel) {
		this.Move.SP = this.Position;
		Rectangle R = this.Move.getFrame().getBounds();
		double L = (this.Move.getFrame().getBounds().getMaxY() - Position.y);

		R.x += vel.x;
		R.y += vel.y + 1;

		if (gridOn == null)
			gridOn(MOB.getSolid(R).collission(Handler.Map), this.Position);

		// gridOn = Handler.Map.Lines[0];
		if (gridOn != null) {
			float a = (float) Math.toDegrees(Math.atan2(gridOn.y2 - gridOn.y1, gridOn.x2 - gridOn.x1));

			lenPose += vel.x;
			Position.set(gridOn.x1 + lenPose, gridOn.y1, 0);
			Position.Turn(new Position(gridOn.x1, gridOn.y1, 0), new Angle3D(a - 90, 90, 0));

			Position.min(new Position(0, (int) L, 0));

			if ((lenPose > Math.hypot(gridOn.x1 - gridOn.x2, gridOn.y1 - gridOn.y2)) || (lenPose < 0))

				gridOn(MOB.getSolid(R).collission(Handler.Map), this.Position);

		} else {

			Position.plus(vel);
		}

	}

	public void gridOn(Line2D.Float l, Position P) {
		if (l != null) {
			gridOn = l;
			lenPose = P.x - l.x1;
		} else {
			gridOff();
		}
	}

	public void gridOff() {
		gridOn = null;
		lenPose = 0;
	}

	public boolean isoninput(LinkedList<Integer> input) {
		int n = input.size();
		for (int i = 0; i < Input.Inputs.size(); i++) {
			for (int j = 0; j < input.size(); j++) {
				if (Input.Inputs.get(i) == input.get(j))
					n--;
			}
		}

		return n == 0;
	}

	public void setMove(int i, Move M) {
		this.Moves.set(i, M);
		this.Move = M;
	}

	public int getIndex(Move I) {
		for (int i = 0; i < this.Moves.size(); i++) {
			if (this.Moves.get(i).TriggerdBy == I.TriggerdBy) {
				return i;
			}
		}
		return -1;
	}

	public void set(Move I, Move S) {
		for (int i = 0; i < this.Moves.size(); i++) {
			if (this.Moves.get(i).equals(I)) {
				this.Moves.set(i, S);
			}
		}
	}

	public void add(Animation A) {
		LinkedList<Integer> l = new LinkedList<Integer>();
		l.add(111 + Moves.size());
		Moves.add(new Move(A, A.FVel, l));
	}

	public void add(Move Move) {
		Moves.add(Move);
	}

	public Skeloton getFrame() {
		return Move.getFrame();
	}

	public Damage GetDamage(MobState MS) {
		return this.Move.GetDamage(MS.Move);
	}

}
