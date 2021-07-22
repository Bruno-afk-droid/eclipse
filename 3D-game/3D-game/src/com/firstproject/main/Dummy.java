package com.firstproject.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Dummy extends MOB {

	public Animation idleAnimation = AnimationList.Idle();

	public Animation forwardWalkAnimation = AnimationList.ForwardWalk();

	public Dummy(Position Position, double Masa, Rectangle SolidBox, Skeloton Skeloton, MobState MobState) {
		super(Position, Masa, SolidBox, Skeloton, MobState);
		// Game.PrintWithEnters(this.Skeloton.getCreatorCode());
		this.MobState.Move = new Move(idleAnimation, (Position) null, null);
		for (int i = 0; i < this.MobState.Move.Frames.length; i++)
			this.MobState.Move.Frames[i].Position = this.Position;

		LinkedList<Integer> i = new LinkedList<Integer>();

	}

	public void changeAnimation(Move A) {

		if (this.MobState.Move != A) {
			this.MobState.Move.Frame = 0;
			A.update();
		}
		this.MobState.Move = A;

	}

	@Override
	public void tick() {
		this.MobState.tick();

		// this.MobState.Move.tick();
		Skeloton S = MobState.Move.getFrame();
		S.update();

		this.Skeloton.Position = this.Position;
		this.Skeloton.update();
		this.SolidBox = Skeloton.getBounds();
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.BLUE);
		Skeloton.Draw(g);

		if (this.SolidBox != null) {
			g.setColor(Color.RED);
			g.draw(this.SolidBox);
		}
	}

}
