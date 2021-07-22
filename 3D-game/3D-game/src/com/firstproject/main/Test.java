package com.firstproject.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Test extends MOB {

	public Animation idleAnimation = AnimationList.Idle();

	public Animation forwardWalkAnimation = AnimationList.ForwardWalk();

	public Test(Position Position, double Masa, Rectangle SolidBox, Skeloton Skeloton, MobState MobState) {
		super(Position, Masa, SolidBox, Skeloton, MobState);
		// Game.PrintWithEnters(this.Skeloton.getCreatorCode());
		this.MobState.Move = new Move(idleAnimation, (Position) null, null);
		for (int i = 0; i < this.MobState.Move.Frames.length; i++)
			this.MobState.Move.Frames[i].Position = this.Position;

		LinkedList<Integer> i = new LinkedList<Integer>();
		i.add(KeyEvent.VK_A);
		MobState.add(new Move(
				"M[F[S[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[9.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[13.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[11.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[8.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]|B[A[90.0|100.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|100.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|-80.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]]]]]]|B[A[90.0|100.0|0.0]|L[13.0]|W[10.0]|J[B[A[25.0|25.0|0.0]|L[34.0]|W[10.0]|J[B[A[20.0|135.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-15.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[13.0]|W[10.0]|J[B[A[30.0|-45.0|0.0]|L[34.0]|W[10.0]|J[B[A[-7.5|-45.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-60.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]|S[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[9.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[13.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[11.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[8.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]|B[A[90.0|100.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|100.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|-80.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]]]]]]|B[A[90.0|100.0|0.0]|L[13.0]|W[10.0]|J[B[A[25.0|25.0|0.0]|L[34.0]|W[10.0]|J[B[A[20.0|135.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-15.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[13.0]|W[10.0]|J[B[A[30.0|-45.0|0.0]|L[34.0]|W[10.0]|J[B[A[-7.5|-45.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-60.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]]|D[]|V[P[4.5454545|0.0|0.0]|P[4.5454545|0.0|0.0]]|K[0|10]|T[68]]"));

		i.clear();
		i.add(KeyEvent.VK_D);
		MobState.add(new Move(
				"M[F[S[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[9.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[13.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[11.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[8.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]|B[A[90.0|100.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|100.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|-80.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]]]]]]|B[A[90.0|100.0|0.0]|L[13.0]|W[10.0]|J[B[A[25.0|25.0|0.0]|L[34.0]|W[10.0]|J[B[A[20.0|135.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-15.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[13.0]|W[10.0]|J[B[A[30.0|-45.0|0.0]|L[34.0]|W[10.0]|J[B[A[-7.5|-45.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-60.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]|S[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[9.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[13.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[11.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[8.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]|B[A[90.0|100.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|100.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|-80.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]]]]]]|B[A[90.0|100.0|0.0]|L[13.0]|W[10.0]|J[B[A[25.0|25.0|0.0]|L[34.0]|W[10.0]|J[B[A[20.0|135.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-15.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[13.0]|W[10.0]|J[B[A[30.0|-45.0|0.0]|L[34.0]|W[10.0]|J[B[A[-7.5|-45.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-60.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]]|D[]|V[P[-4.5454545|0.0|0.0]|P[-4.5454545|0.0|0.0]]|K[0|10]|T[65]]"));
		i.clear();
		i.add(KeyEvent.VK_SPACE);
		MobState.add(new Move(
				"M[F[S[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[9.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[13.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[11.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[8.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]|B[A[90.0|100.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|100.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|-80.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|-80.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]]]]]]|B[A[90.0|100.0|0.0]|L[13.0]|W[10.0]|J[B[A[25.0|25.0|0.0]|L[34.0]|W[10.0]|J[B[A[20.0|135.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-15.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[13.0]|W[10.0]|J[B[A[30.0|-45.0|0.0]|L[34.0]|W[10.0]|J[B[A[-7.5|-45.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-60.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]|S[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[9.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[13.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[11.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[8.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]|B[A[90.0|100.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|100.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|-80.0|0.0]|L[10.0]|W[10.0]|J[B[A[44.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[131.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[207.0|-80.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]]]]]]|B[A[90.0|100.0|0.0]|L[13.0]|W[10.0]|J[B[A[25.0|25.0|0.0]|L[34.0]|W[10.0]|J[B[A[20.0|135.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-15.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[13.0]|W[10.0]|J[B[A[109.0|-45.0|0.0]|L[34.0]|W[10.0]|J[B[A[-7.5|-45.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-60.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]|S[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[9.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[13.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[11.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[8.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]|B[A[90.0|100.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|100.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|-80.0|0.0]|L[10.0]|W[10.0]|J[B[A[65.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[142.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[196.0|-80.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]]]]]]|B[A[90.0|100.0|0.0]|L[13.0]|W[10.0]|J[B[A[25.0|25.0|0.0]|L[34.0]|W[10.0]|J[B[A[20.0|135.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-15.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[13.0]|W[10.0]|J[B[A[109.0|-45.0|0.0]|L[34.0]|W[10.0]|J[B[A[98.0|-45.0|0.0]|L[30.0]|W[10.0]|J[B[A[175.0|-60.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]|S[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[9.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[13.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[11.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[8.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]|B[A[90.0|100.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|100.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|-80.0|0.0]|L[10.0]|W[10.0]|J[B[A[65.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[142.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[196.0|-80.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]]]]]]|B[A[90.0|100.0|0.0]|L[13.0]|W[10.0]|J[B[A[25.0|25.0|0.0]|L[34.0]|W[10.0]|J[B[A[20.0|135.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-15.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[13.0]|W[10.0]|J[B[A[109.0|-45.0|0.0]|L[34.0]|W[10.0]|J[B[A[98.0|-45.0|0.0]|L[30.0]|W[10.0]|J[B[A[175.0|-60.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]|S[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[9.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[13.0]|W[10.0]|J[B[A[180.0|0.0|0.0]|L[11.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[8.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[4.0]|W[10.0]|J[B[A[180.0|-90.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]|B[A[90.0|100.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|100.0|0.0]|L[10.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[16.0]|W[10.0]|J[B[A[15.0|100.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[8.0]|W[10.0]|J[B[A[90.0|-80.0|0.0]|L[10.0]|W[10.0]|J[B[A[33.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[11.0|-80.0|0.0]|L[16.0]|W[10.0]|J[B[A[360.0|-80.0|0.0]|L[4.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]]]]]]|B[A[90.0|100.0|0.0]|L[13.0]|W[10.0]|J[B[A[25.0|25.0|0.0]|L[34.0]|W[10.0]|J[B[A[20.0|135.0|0.0]|L[30.0]|W[10.0]|J[B[A[90.0|-15.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]|B[A[90.0|-80.0|0.0]|L[13.0]|W[10.0]|J[B[A[65.0|-45.0|0.0]|L[34.0]|W[10.0]|J[B[A[11.0|-45.0|0.0]|L[30.0]|W[10.0]|J[B[A[98.0|-60.0|0.0]|L[8.0]|W[10.0]|J[B[A[0.0|0.0|0.0]|L[0.0]|W[10.0]|J[]]]]]]]]]]]]]]|D[]|V[P[0.0|0.0|0.0]|P[0.0|0.0|0.0]|P[0.0|0.0|0.0]|P[0.0|0.0|0.0]|P[0.0|0.0|0.0]]|K[0|10|15|25|40]|T[32]]"));
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
		Rectangle R = this.Skeloton.getBounds();
		Rectangle Gr = (Rectangle) MobState.Move.getFrame().getBounds().clone();

		Position Min = new Position((float) (Gr.getMinX() - R.getMinX()), (float) (Gr.getMinY() - R.getMinY()), 0);
		Position Max = new Position((float) (Gr.getMaxX() - R.getMaxX()), (float) (Gr.getMaxY() - R.getMaxY()), 0);
		/*
		 * if (Game.handler.RIGHT) { Vel.x = 3; } else if (Game.handler.LEFT) { Vel.x =
		 * -3; } else { Vel.x = 0; }
		 */

		// if(Game.handler.UP)Vel.y-=2;
		// if(Game.handler.DOWN)Vel.y+=2;
		/*
		 * if (Grounded == null) { grv = Masa / 100; Vel.y += grv; } else { grv = 0;
		 * Vel.y = 0; if (Game.handler.UP) { Vel.y -= 15; Grounded = null; }
		 * 
		 * }
		 * 
		 * // System.out.println(FAM.x);
		 * 
		 * // getIntersectionPoint Position sVel = new Position(0, 0, 0); Line2D L;
		 * 
		 * if (Grounded != null) if (Math.signum(Max.y) != 1) { //
		 * SolidBox.y-=(int)(Max.y); // Position.y-=(int)(Max.y); Vel.y += 5;
		 * 
		 * }
		 * 
		 * // if (this.Collision(new Rectangle((int) (SolidBox.x + Min.x + Vel.x), //
		 * SolidBox.y - (int) Max.y, // (int) -(Min.x + Vel.x), SolidBox.height),
		 * Game.handler.Map) != null) // sVel.x = Min.x; // if (this.Collision(new
		 * Rectangle( // (int) ((SolidBox.x + Max.x + Vel.x) + SolidBox.width - (Max.x +
		 * Vel.x) + // Math.signum(Max.x)), // SolidBox.y - (int) Max.y, (int) (Max.x +
		 * Vel.x), SolidBox.height), // Game.handler.Map) != null) // sVel.x = Max.x;
		 * 
		 * // System.out.println(Min.y); // if (this.Collision( // new
		 * Rectangle(SolidBox.x, (int) (SolidBox.y + Min.y + Vel.y), SolidBox.width, //
		 * (int) -(Min.y + Vel.y)), // Game.handler.Map) != null) // sVel.y = Min.y; //
		 * if (this.Collision(new Rectangle(SolidBox.x, // (int) ((SolidBox.y + Max.y +
		 * Vel.y) + SolidBox.height - (Max.y + Vel.y) + // Math.signum(Max.y)), //
		 * SolidBox.width, (int) (Max.y + Vel.y)), Game.handler.Map) != null) // sVel.y
		 * = Max.y;
		 * 
		 * // if(this.Collision(new Rectangle((int) //
		 * (SolidBox.x+Max.x+Vel.x+SolidBox.width),SolidBox.y,-SolidBox.width/4,SolidBox
		 * .height),Game.handler.Map)!=null) // sVel.x=Max.x;
		 * 
		 * // if(this.Collision(new Rectangle((int) //
		 * (SolidBox.x-Max.x),SolidBox.y,SolidBox.width,SolidBox.height),Game.handler.
		 * Map)==null) // sVel.x=Max.x;
		 * 
		 * // System.out.println(Animation.getFVel().x);
		 * 
		 * // Vel.x += sVel.x; // Vel.y += sVel.y;
		 * 
		 * // horizontal Vel collision /* double w = Math.round(Vel.x) +
		 * Math.signum(Vel.x); this.SolidBox.x += Math.round(Vel.x) +
		 * Math.signum(Vel.x); L = this.Collision(Game.handler.Map); if (L != null) {
		 * 
		 * this.SolidBox.x -= Math.round(Vel.x); while
		 * (!this.SolidBox.intersectsLine(L)) {
		 * 
		 * this.SolidBox.x = (int) (this.SolidBox.x + Math.signum(Vel.x));
		 * this.Position.x += Math.signum(Vel.x);
		 * 
		 * if (this.SolidBox.intersectsLine(L)) L = this.Collision(Game.handler.Map); if
		 * (L == null) break; } Vel.x = 0;
		 * 
		 * } this.SolidBox.x -= w; // Vel.y+=Min.y;
		 * 
		 * // vertical Vel collision w = Math.round(Vel.y) + Math.signum(Vel.y);
		 * this.SolidBox.y += Math.round(Vel.y) + Math.signum(Vel.y); L =
		 * this.Collision(Game.handler.Map); if (L != null) {
		 * 
		 * this.SolidBox.y -= Math.round(Vel.y); while
		 * (!this.SolidBox.intersectsLine(L)) {
		 * 
		 * this.SolidBox.y = (int) (this.SolidBox.y + Math.signum(Vel.y));
		 * this.Position.y += Math.signum(Vel.y);
		 * 
		 * if (this.SolidBox.intersectsLine(L)) L = this.Collision(Game.handler.Map); if
		 * (L == null) break; } Grounded = L; Vel.y = 0; grv = 0; } else Grounded =
		 * null; this.SolidBox.y -= w;
		 * 
		 * Vel.x -= sVel.x; Vel.y -= sVel.y;
		 */

		this.Position.plus(Vel);
		this.Skeloton = MobState.Move.getFrame();
		// if(this.Skeloton.Position==null)
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
