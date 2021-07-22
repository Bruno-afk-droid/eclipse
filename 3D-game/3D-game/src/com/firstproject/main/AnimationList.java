package com.firstproject.main;

import java.util.Arrays;
import java.util.LinkedList;

public class AnimationList {

	public static Animation ForwardWalk() {
		double H = 10;

		/*
		 * //Leg R new Bone(new Position(0,0,0), new Angle3D(80,80,0), 13, H, new Bone[]
		 * { new Bone(new Position(0,0,0), new Angle3D(30.5,45,0), 34, H, new Bone[] {
		 * new Bone(new Position(0,0,0), new Angle3D(-10,22.5,0), 34, H, new Bone[] {
		 * new Bone(new Position(0,0,0), new Angle3D(90,22.5,0), 8, H, new Bone[] { new
		 * Bone(new Position(0,0,0), new Angle3D(0,0,0), 0, H, new Bone[] {
		 * 
		 * }, null), }, null), }, null), }, null), }, null),
		 * 
		 * //Leg L new Bone(new Position(0,0,0), new Angle3D(80,-100,0), 13, H, new
		 * Bone[] { new Bone(new Position(0,0,0), new Angle3D(25,-90,0), 34, H, new
		 * Bone[] { new Bone(new Position(0,0,0), new Angle3D(16,-90,0), 34, H, new
		 * Bone[] { new Bone(new Position(0,0,0), new Angle3D(90,-65,0), 8, H, new
		 * Bone[] { new Bone(new Position(0,0,0), new Angle3D(0,0,0), 0, H, new Bone[] {
		 * 
		 * }, null), }, null), }, null), }, null), }, null) },null)
		 */

		Skeloton S = new Skeloton(new Position(0, 0, 0), new Angle3D(),
				new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 0), 0, H, new Bone[] {
						// torso
						new Bone(new Position(0, 0, 0), new Angle3D(180, 0, 0), 9, H, new Bone[] { new Bone(
								new Position(0, 0, 0), new Angle3D(180, 0, 0), 13, H,
								new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(180, 0, 0), 11, H, new Bone[] {

								}, null), }, null), }, null),

						// Leg R
						new Bone(new Position(0, 0, 0), new Angle3D(80, 80, 0), 13, H,
								new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(30.5, 45, 0), 34, H,
										new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(-10, 22.5, 0), 34, H,
												new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(90, 22.5, 0),
														8, H, new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(0, 0, 0), 0, H, new Bone[] {

																}, null), }, null), }, null), }, null), }, null),

						// Leg L
						new Bone(new Position(0, 0, 0), new Angle3D(80, -100, 0), 13, H,
								new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(25, -90, 0), 34, H,
										new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(16, -90, 0), 34, H,
												new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(90, -65, 0), 8,
														H, new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(0, 0, 0), 0, H, new Bone[] {

																}, null), }, null), }, null), }, null), }, null) },
						null) });

		Skeloton S1 = new Skeloton(new Position(0, 0, 0), new Angle3D(),
				new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 0), 0, H, new Bone[] {
						// torso
						new Bone(new Position(0, 0, 0), new Angle3D(180, 0, 0), 9, H, new Bone[] { new Bone(
								new Position(0, 0, 0), new Angle3D(180, 0, 0), 13, H,
								new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(180, 0, 0), 11, H, new Bone[] {

								}, null), }, null), }, null),

						// Leg L
						new Bone(new Position(0, 0, 0), new Angle3D(90, 90 - 65, 0), 13, H, new Bone[] { new Bone(
								new Position(0, 0, 0), new Angle3D(22.5, 0 - 65, 0), 34, H,
								new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(22.5, 0 - 65, 0), 34, H,
										new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(112.5, 0 - 65, 0), 8,
												H, new Bone[] { new Bone(new Position(0, 0, 0),
														new Angle3D(0, 0 - 65, 0), 0, H, new Bone[] {

														}, null), }, null), }, null), }, null), }, null),

						// Leg R
						new Bone(new Position(0, 0, 0), new Angle3D(90, -90 - 65, 0), 13, H, new Bone[] { new Bone(
								new Position(0, 0, 0), new Angle3D(-22.5, 0 - 65, 0), 34, H,
								new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(-45.0, 0 - 65, 0), 34, H,
										new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(45, 0 - 65, 0), 8, H,
												new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0 - 65, 0),
														0, H, new Bone[] {

														}, null), }, null), }, null), }, null), }, null) }, null) });

		Skeloton S2 = new Skeloton(
				new Position(0, 0, 0), new Angle3D(), new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 0),
						0, H, new Bone[] {
								// torso
								new Bone(new Position(0, 0, 0), new Angle3D(180, 0, 0), 9, H,
										new Bone[] { new Bone(
												new Position(0, 0, 0), new Angle3D(180, 0, 0), 13, H,
												new Bone[] { new Bone(
														new Position(0, 0, 0), new Angle3D(180, 0, 0), 11, H,
														new Bone[] {}, null), },
												null), },
										null),

								// Leg L
								new Bone(new Position(0, 0, 0), new Angle3D(90, 90 - 65, 0), 13, H,
										new Bone[] {
												new Bone(new Position(0, 0, 0), new Angle3D(11.25, 0 - 65, 0), 34, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(-5.5, 0 - 65, 0), 34, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(90, 0 - 65, 0), 8, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(0, 0 - 65, 0), 0, H,
																				new Bone[] {

																				}, null), }, null), }, null), },
														null), },
										null),

								// Leg R
								new Bone(new Position(0, 0, 0), new Angle3D(90, -90 - 65, 0), 13, H,
										new Bone[] {
												new Bone(new Position(0, 0, 0), new Angle3D(-32.5, 0 - 65, 0), 34, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(-45.0, 0 - 65, 0), 34, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(45, 0 - 65, 0), 8, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(0, 0 - 65, 0), 0, H,
																				new Bone[] {

																				}, null), }, null), }, null), },
														null), },
										null) },
						null) });

		Skeloton S3 = new Skeloton(
				new Position(0, 0, 0), new Angle3D(), new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 0),
						0, H, new Bone[] {
								// torso
								new Bone(new Position(0, 0, 0), new Angle3D(180, 0, 0), 9, H,
										new Bone[] { new Bone(
												new Position(0, 0, 0), new Angle3D(180, 0, 0), 13, H,
												new Bone[] { new Bone(
														new Position(0, 0, 0), new Angle3D(180, 0, 0), 11, H,
														new Bone[] {}, null), },
												null), },
										null),

								// Leg L
								new Bone(new Position(0, 0, 0), new Angle3D(90, 90 - 65, 0), 13, H,
										new Bone[] {
												new Bone(new Position(0, 0, 0), new Angle3D(0, 0 - 65, 0), 34, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(-22.5, 0 - 65, 0), 34, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(90, 0 - 65, 0), 8, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(0, 0 - 65, 0), 0, H,
																				new Bone[] {

																				}, null), }, null), }, null), },
														null), },
										null),

								// Leg R
								new Bone(new Position(0, 0, 0), new Angle3D(90, -90 - 65, 0), 13, H,
										new Bone[] {
												new Bone(new Position(0, 0, 0), new Angle3D(33.25, 0 - 65, 0), 34, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(-45.0, 0 - 65, 0), 34, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(45, 0 - 65, 0), 8, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(0, 0 - 65, 0), 0, H,
																				new Bone[] {

																				}, null), }, null), }, null), },
														null), },
										null) },
						null) });

		Skeloton S4 = new Skeloton(
				new Position(0, 0, 0), new Angle3D(), new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 0),
						0, H, new Bone[] {
								// torso
								new Bone(new Position(0, 0, 0), new Angle3D(180, 0, 0), 9, H,
										new Bone[] { new Bone(
												new Position(0, 0, 0), new Angle3D(180, 0, 0), 13, H,
												new Bone[] { new Bone(
														new Position(0, 0, 0), new Angle3D(180, 0, 0), 11, H,
														new Bone[] {}, null), },
												null), },
										null),

								// Leg L
								new Bone(new Position(0, 0, 0), new Angle3D(90, 90 - 65, 0), 13, H,
										new Bone[] {
												new Bone(new Position(0, 0, 0), new Angle3D(-22.5, 0 - 65, 0), 34, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(-45.0, 0 - 65, 0), 34, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(45, 0 - 65, 0), 8, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(0, 0 - 65, 0), 0, H,
																				new Bone[] {

																				}, null), }, null), }, null), },
														null), },
										null),

								// Leg R
								new Bone(new Position(0, 0, 0), new Angle3D(90, -90 - 65, 0), 13, H,
										new Bone[] {
												new Bone(new Position(0, 0, 0), new Angle3D(22.5, 0 - 65, 0), 34, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(22.5, 0 - 65, 0), 34, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(112.5, 0 - 65, 0), 8, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(0, 0 - 65, 0), 0, H,
																				new Bone[] {

																				}, null), }, null), }, null), },
														null), },
										null) },
						null) });

		Skeloton S5 = new Skeloton(
				new Position(0, 0, 0), new Angle3D(), new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 0),
						0, H, new Bone[] {
								// torso
								new Bone(new Position(0, 0, 0), new Angle3D(180, 0, 0), 9, H,
										new Bone[] { new Bone(
												new Position(0, 0, 0), new Angle3D(180, 0, 0), 13, H,
												new Bone[] { new Bone(
														new Position(0, 0, 0), new Angle3D(180, 0, 0), 11, H,
														new Bone[] {}, null), },
												null), },
										null),

								// Leg L
								new Bone(new Position(0, 0, 0), new Angle3D(90, 90 - 65, 0), 13, H,
										new Bone[] {
												new Bone(new Position(0, 0, 0), new Angle3D(-32.5, 0 - 65, 0), 34, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(-45.0, 0 - 65, 0), 34, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(45, 0 - 65, 0), 8, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(0, 0 - 65, 0), 0, H,
																				new Bone[] {

																				}, null), }, null), }, null), },
														null), },
										null),

								// Leg R
								new Bone(new Position(0, 0, 0), new Angle3D(90, -90 - 65, 0), 13, H,
										new Bone[] {
												new Bone(new Position(0, 0, 0), new Angle3D(11.25, 0 - 65, 0), 34, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(-5.5, 0 - 65, 0), 34, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(90, 0 - 65, 0), 8, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(0, 0 - 65, 0), 0, H,
																				new Bone[] {

																				}, null), }, null), }, null), },
														null), },
										null) },
						null) });
		Skeloton S6 = new Skeloton(
				new Position(0, 0, 0), new Angle3D(), new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 0),
						0, H, new Bone[] {
								// torso
								new Bone(new Position(0, 0, 0), new Angle3D(180, 0, 0), 9, H,
										new Bone[] { new Bone(
												new Position(0, 0, 0), new Angle3D(180, 0, 0), 13, H,
												new Bone[] { new Bone(
														new Position(0, 0, 0), new Angle3D(180, 0, 0), 11, H,
														new Bone[] {}, null), },
												null), },
										null),

								// Leg L
								new Bone(new Position(0, 0, 0), new Angle3D(90, 90 - 65, 0), 13, H,
										new Bone[] {
												new Bone(new Position(0, 0, 0), new Angle3D(33.25, 0 - 65, 0), 34, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(-45.0, 0 - 65, 0), 34, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(45, 0 - 65, 0), 8, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(0, 0 - 65, 0), 0, H,
																				new Bone[] {

																				}, null), }, null), }, null), },
														null), },
										null),

								// Leg R
								new Bone(new Position(0, 0, 0), new Angle3D(90, -90 - 65, 0), 13, H,
										new Bone[] {
												new Bone(new Position(0, 0, 0), new Angle3D(0, 0 - 65, 0), 34, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(-22.5, 0 - 65, 0), 34, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(90, 0 - 65, 0), 8, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(0, 0 - 65, 0), 0, H,
																				new Bone[] {

																				}, null), }, null), }, null), },
														null), },
										null) },
						null) });

		// Skeloton St = S1.clone();
		// S1.addAngle(new Angle3D(0,-67.5,0));
		// S2.addAngle(new Angle3D(0,-90,0));
		// S3.addAngle(new Angle3D(0,-112.5,0));
		// S4.addAngle(new Angle3D(0,-90,0));
		// S5.addAngle(new Angle3D(0,-67.5,0));
		// S6.addAngle(new Angle3D(0,-112.5,0));
		// S7.addAngle(new Angle3D(0,-67.5,0));

		// S1.addAngle(new Angle3D(0,-65,0));
		// S2.addAngle(new Angle3D(0,-65,0));
		// S3.addAngle(new Angle3D(0,-65,0));
		// S4.addAngle(new Angle3D(0,-65,0));
		// S5.addAngle(new Angle3D(0,-65,0));
		// S6.addAngle(new Angle3D(0,-65,0));

		return new Animation(new Skeloton[] {

				S5.clone(), null, null, null, null, null, null, null, null, null, null, null, null, S6.clone(), null,
				null, null, null, null, null, null, null, null, null, null, null, S1.clone(), null, null, null, null,
				null, null, null, null, null, null, null, null, S2.clone(), null, null, null, null, null, null, null,
				null, null, null, null, null, S3.clone(), null, null, null, null, null, null, null, null, null, null,
				null, null, S4.clone(), null, null, null, null, null, null, null, null, null, null, null, null,
				S5.clone(),

		});
	}

	public static Animation Idle() {
		double H = 10;
		Skeloton S1 = new Skeloton(new Position(0, 0, 0), new Angle3D(),
				new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(0, 0, 0), 0, H, new Bone[] {
						// torso
						new Bone(new Position(0, 0, 0), new Angle3D(180, 0, 0), 9, H,
								new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(180, 0, 0), 13, H, new Bone[] {
										new Bone(new Position(0, 0, 0), new Angle3D(180, 0, 0), 11, H, new Bone[] {
												// Neck
												new Bone(new Position(0, 0, 0), new Angle3D(180, -90, 0), 8, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(180, -90, 0), 4, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(180, -90, 0), 4, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(180, -90, 0), 0, H,
																				new Bone[] {

																				}, null), }, null), }, null), }, null),

												// Arm R
												new Bone(new Position(0, 0, 0), new Angle3D(90, 100, 0), 8, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(90, 100, 0), 10, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(15, 100, 0), 16, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(15, 100, 0), 16, H,
																				new Bone[] { new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(15, 100, 0), 4, H,
																						new Bone[] { new Bone(
																								new Position(0, 0, 0),
																								new Angle3D(0, 0, 0), 0,
																								H, new Bone[] {

																								}, null), }, null), },
																				null), },
																		null), },
																null), },
														null),
												// Arm L
												new Bone(new Position(0, 0, 0), new Angle3D(90, -80, 0), 8, H,
														new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(90, -80, 0), 10, H,
																new Bone[] { new Bone(new Position(0, 0, 0),
																		new Angle3D(15, -80, 0), 16, H,
																		new Bone[] { new Bone(new Position(0, 0, 0),
																				new Angle3D(15, -80, 0), 16, H,
																				new Bone[] { new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(15, -80, 0), 4, H,
																						new Bone[] { new Bone(
																								new Position(0, 0, 0),
																								new Angle3D(0, 0, 0), 0,
																								H, new Bone[] {

																								}, null), }, null), },
																				null), },
																		null), },
																null), },
														null),

										}, null), }, null), }, null),

						// Leg R
						new Bone(new Position(0, 0, 0), new Angle3D(90, 100, 0), 13, H,
								new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(25, 25, 0), 34, H,
										new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(20, 135, 0), 30, H,
												new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(90, -15, 0), 8,
														H, new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(0, 0, 0), 0, H, new Bone[] {

																}, null), }, null), }, null), }, null), }, null),

						// Leg L
						new Bone(new Position(0, 0, 0), new Angle3D(90, -80, 0), 13, H,
								new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(30, -45, 0), 34, H,
										new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(-7.5, -45, 0), 30, H,
												new Bone[] { new Bone(new Position(0, 0, 0), new Angle3D(90, -60, 0), 8,
														H, new Bone[] { new Bone(new Position(0, 0, 0),
																new Angle3D(0, 0, 0), 0, H, new Bone[] {

																}, null), }, null), }, null), }, null), }, null) },
						null) });

		S1.addAngle(new Angle3D(0, 0, 0));

		Skeloton St = S1.clone();
		St.addAngle(new Angle3D(0, 0, 0));
		return new Animation(new Skeloton[] { S1.clone(), S1.clone(), }, new LinkedList<Integer>(Arrays.asList(0, 10)));

	}

	public static Animation KickAttack() {
		return new Animation(
				new Skeloton[] {
						new Skeloton(new Position(0, 0, 0), new Angle3D(), new Bone[] { /* index: 0 */new Bone(
								new Position(0, 0, 0), new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
								new Bone[] {
										/* index: 0 */new Bone(new Position(0, 0, 0), new Angle3D(180.0, 0.0, 0.0), 9.0,
												10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(180.0, 0.0, 0.0), 13.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(180.0, 0.0, 0.0), 11.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(180.0, -90.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(180.0, -90.0, 0.0), 4.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(180.0, -90.0, 0.0),
																						4.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(
																												0, 0,
																												0),
																										new Angle3D(
																												180.0,
																												-90.0,
																												0.0),
																										0.0, 10,
																										new Bone[] {},
																										null), },
																						null), },
																				null), },
																		null),
																		/* index: 1 */new Bone(new Position(0, 0, 0),
																				new Angle3D(90.0, 100.0, 0.0), 8.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(90.0, 100.0, 0.0),
																						10.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(
																												0, 0,
																												0),
																										new Angle3D(
																												15.0,
																												100.0,
																												0.0),
																										16.0, 10,
																										new Bone[] {
																												/*
																												 * index:
																												 * 0
																												 */new Bone(
																														new Position(
																																0,
																																0,
																																0),
																														new Angle3D(
																																15.0,
																																100.0,
																																0.0),
																														16.0,
																														10,
																														new Bone[] {
																																/*
																																 * index:
																																 * 0
																																 */new Bone(
																																		new Position(
																																				0,
																																				0,
																																				0),
																																		new Angle3D(
																																				15.0,
																																				100.0,
																																				0.0),
																																		4.0,
																																		10,
																																		new Bone[] {
																																				/*
																																				 * index:
																																				 * 0
																																				 */new Bone(
																																						new Position(
																																								0,
																																								0,
																																								0),
																																						new Angle3D(
																																								0.0,
																																								0.0,
																																								0.0),
																																						0.0,
																																						10,
																																						new Bone[] {},
																																						null), },
																																		null), },
																														null), },
																										null), },
																						null), },
																				null),
																		/* index: 2 */new Bone(new Position(0, 0, 0),
																				new Angle3D(90.0, -80.0, 0.0), 8.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(90.0, -80.0, 0.0),
																						10.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(0,
																												0, 0),
																										new Angle3D(
																												15.0,
																												-80.0,
																												0.0),
																										16.0, 10,
																										new Bone[] {
																												/*
																												 * index:
																												 * 0
																												 */new Bone(
																														new Position(
																																0,
																																0,
																																0),
																														new Angle3D(
																																15.0,
																																-80.0,
																																0.0),
																														16.0,
																														10,
																														new Bone[] {
																																/*
																																 * index:
																																 * 0
																																 */new Bone(
																																		new Position(
																																				0,
																																				0,
																																				0),
																																		new Angle3D(
																																				15.0,
																																				-80.0,
																																				0.0),
																																		4.0,
																																		10,
																																		new Bone[] {
																																				/*
																																				 * index:
																																				 * 0
																																				 */new Bone(
																																						new Position(
																																								0,
																																								0,
																																								0),
																																						new Angle3D(
																																								0.0,
																																								0.0,
																																								0.0),
																																						0.0,
																																						10,
																																						new Bone[] {},
																																						null), },
																																		null), },
																														null), },
																										null), },
																						null), },
																				null), },
																null), },
														null), },
												null),
										/* index: 1 */new Bone(new Position(0, 0, 0), new Angle3D(90.0, 100.0, 0.0),
												13.0, 10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(25.0, 25.0, 0.0), 34.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(20.0, 135.0, 0.0), 30.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(90.0, -15.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
																				new Bone[] {}, null), },
																		null), },
																null), },
														null), },
												null),
										/* index: 2 */new Bone(new Position(0, 0, 0), new Angle3D(90.0, -80.0, 0.0),
												13.0, 10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(30.0, -45.0, 0.0), 34.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(-7.5, -45.0, 0.0), 30.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(90.0, -60.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
																				new Bone[] {}, null), },
																		null), },
																null), },
														null), },
												null), },
								null), }),
						new Skeloton(new Position(0, 0, 0), new Angle3D(), new Bone[] { /* index: 0 */new Bone(
								new Position(0, 0, 0), new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
								new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0), new Angle3D(180.0, 0.0, 0.0),
										9.0, 10,
										new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
												new Angle3D(180.0, 0.0, 0.0), 13.0, 10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(180.0, 0.0, 0.0), 11.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(180.0, -90.0, 0.0), 8.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(180.0, -90.0, 0.0), 4.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(180.0, -90.0, 0.0), 4.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(180.0, -90.0, 0.0),
																						0.0, 10, new Bone[] {},
																						null), },
																				null), },
																		null), },
																null),
																/* index: 1 */new Bone(new Position(0, 0, 0),
																		new Angle3D(90.0, 100.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(90.0, 100.0, 0.0), 10.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(15.0, 100.0, 0.0),
																						16.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(
																												0, 0,
																												0),
																										new Angle3D(
																												15.0,
																												100.0,
																												0.0),
																										16.0, 10,
																										new Bone[] {
																												/*
																												 * index:
																												 * 0
																												 */new Bone(
																														new Position(
																																0,
																																0,
																																0),
																														new Angle3D(
																																15.0,
																																100.0,
																																0.0),
																														4.0,
																														10,
																														new Bone[] {
																																/*
																																 * index:
																																 * 0
																																 */new Bone(
																																		new Position(
																																				0,
																																				0,
																																				0),
																																		new Angle3D(
																																				0.0,
																																				0.0,
																																				0.0),
																																		0.0,
																																		10,
																																		new Bone[] {},
																																		null), },
																														null), },
																										null), },
																						null), },
																				null), },
																		null),
																/* index: 2 */new Bone(new Position(0, 0, 0),
																		new Angle3D(90.0, -80.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(90.0, -80.0, 0.0), 10.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(44.0, -80.0, 0.0),
																						16.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(0,
																												0, 0),
																										new Angle3D(
																												131.0,
																												-80.0,
																												0.0),
																										16.0, 10,
																										new Bone[] {
																												/*
																												 * index:
																												 * 0
																												 */new Bone(
																														new Position(
																																0,
																																0,
																																0),
																														new Angle3D(
																																207.0,
																																-80.0,
																																0.0),
																														4.0,
																														10,
																														new Bone[] {
																																/*
																																 * index:
																																 * 0
																																 */new Bone(
																																		new Position(
																																				0,
																																				0,
																																				0),
																																		new Angle3D(
																																				0.0,
																																				0.0,
																																				0.0),
																																		0.0,
																																		10,
																																		new Bone[] {},
																																		null), },
																														null), },
																										null), },
																						null), },
																				null), },
																		null), },
														null), },
												null), },
										null),
										/* index: 1 */new Bone(new Position(0, 0, 0), new Angle3D(90.0, 100.0, 0.0),
												13.0, 10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(25.0, 25.0, 0.0), 34.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(20.0, 135.0, 0.0), 30.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(90.0, -15.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
																				new Bone[] {}, null), },
																		null), },
																null), },
														null), },
												null),
										/* index: 2 */new Bone(new Position(0, 0, 0), new Angle3D(90.0, -80.0, 0.0),
												13.0, 10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(109.0, -45.0, 0.0), 34.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(-7.5, -45.0, 0.0), 30.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(90.0, -60.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
																				new Bone[] {}, null), },
																		null), },
																null), },
														null), },
												null), },
								null), }),
						new Skeloton(new Position(0, 0, 0), new Angle3D(), new Bone[] { /* index: 0 */new Bone(
								new Position(0, 0, 0), new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
								new Bone[] {
										/* index: 0 */new Bone(new Position(0, 0, 0), new Angle3D(180.0, 0.0, 0.0), 9.0,
												10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(180.0, 0.0, 0.0), 13.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(180.0, 0.0, 0.0), 11.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(180.0, -90.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(180.0, -90.0, 0.0), 4.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(180.0, -90.0, 0.0),
																						4.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(
																												0, 0,
																												0),
																										new Angle3D(
																												180.0,
																												-90.0,
																												0.0),
																										0.0, 10,
																										new Bone[] {},
																										null), },
																						null), },
																				null), },
																		null),
																		/* index: 1 */new Bone(new Position(0, 0, 0),
																				new Angle3D(90.0, 100.0, 0.0), 8.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(90.0, 100.0, 0.0),
																						10.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(
																												0, 0,
																												0),
																										new Angle3D(
																												15.0,
																												100.0,
																												0.0),
																										16.0, 10,
																										new Bone[] {
																												/*
																												 * index:
																												 * 0
																												 */new Bone(
																														new Position(
																																0,
																																0,
																																0),
																														new Angle3D(
																																15.0,
																																100.0,
																																0.0),
																														16.0,
																														10,
																														new Bone[] {
																																/*
																																 * index:
																																 * 0
																																 */new Bone(
																																		new Position(
																																				0,
																																				0,
																																				0),
																																		new Angle3D(
																																				15.0,
																																				100.0,
																																				0.0),
																																		4.0,
																																		10,
																																		new Bone[] {
																																				/*
																																				 * index:
																																				 * 0
																																				 */new Bone(
																																						new Position(
																																								0,
																																								0,
																																								0),
																																						new Angle3D(
																																								0.0,
																																								0.0,
																																								0.0),
																																						0.0,
																																						10,
																																						new Bone[] {},
																																						null), },
																																		null), },
																														null), },
																										null), },
																						null), },
																				null),
																		/* index: 2 */new Bone(new Position(0, 0, 0),
																				new Angle3D(90.0, -80.0, 0.0), 8.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(90.0, -80.0, 0.0),
																						10.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(0,
																												0, 0),
																										new Angle3D(
																												65.0,
																												-80.0,
																												0.0),
																										16.0, 10,
																										new Bone[] {
																												/*
																												 * index:
																												 * 0
																												 */new Bone(
																														new Position(
																																0,
																																0,
																																0),
																														new Angle3D(
																																142.0,
																																-80.0,
																																0.0),
																														16.0,
																														10,
																														new Bone[] {
																																/*
																																 * index:
																																 * 0
																																 */new Bone(
																																		new Position(
																																				0,
																																				0,
																																				0),
																																		new Angle3D(
																																				196.0,
																																				-80.0,
																																				0.0),
																																		4.0,
																																		10,
																																		new Bone[] {
																																				/*
																																				 * index:
																																				 * 0
																																				 */new Bone(
																																						new Position(
																																								0,
																																								0,
																																								0),
																																						new Angle3D(
																																								0.0,
																																								0.0,
																																								0.0),
																																						0.0,
																																						10,
																																						new Bone[] {},
																																						null), },
																																		null), },
																														null), },
																										null), },
																						null), },
																				null), },
																null), },
														null), },
												null),
										/* index: 1 */new Bone(new Position(0, 0, 0), new Angle3D(90.0, 100.0, 0.0),
												13.0, 10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(25.0, 25.0, 0.0), 34.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(20.0, 135.0, 0.0), 30.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(90.0, -15.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
																				new Bone[] {}, null), },
																		null), },
																null), },
														null), },
												null),
										/* index: 2 */new Bone(new Position(0, 0, 0), new Angle3D(90.0, -80.0, 0.0),
												13.0, 10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(109.0, -45.0, 0.0), 34.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(98.0, -45.0, 0.0), 30.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(175.0, -60.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
																				new Bone[] {}, null), },
																		null), },
																null), },
														null), },
												null), },
								null), }),
						new Skeloton(new Position(0, 0, 0), new Angle3D(), new Bone[] { /* index: 0 */new Bone(
								new Position(0, 0, 0), new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
								new Bone[] {
										/* index: 0 */new Bone(new Position(0, 0, 0), new Angle3D(180.0, 0.0, 0.0), 9.0,
												10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(180.0, 0.0, 0.0), 13.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(180.0, 0.0, 0.0), 11.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(180.0, -90.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(180.0, -90.0, 0.0), 4.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(180.0, -90.0, 0.0),
																						4.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(
																												0, 0,
																												0),
																										new Angle3D(
																												180.0,
																												-90.0,
																												0.0),
																										0.0, 10,
																										new Bone[] {},
																										null), },
																						null), },
																				null), },
																		null),
																		/* index: 1 */new Bone(new Position(0, 0, 0),
																				new Angle3D(90.0, 100.0, 0.0), 8.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(90.0, 100.0, 0.0),
																						10.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(
																												0, 0,
																												0),
																										new Angle3D(
																												15.0,
																												100.0,
																												0.0),
																										16.0, 10,
																										new Bone[] {
																												/*
																												 * index:
																												 * 0
																												 */new Bone(
																														new Position(
																																0,
																																0,
																																0),
																														new Angle3D(
																																15.0,
																																100.0,
																																0.0),
																														16.0,
																														10,
																														new Bone[] {
																																/*
																																 * index:
																																 * 0
																																 */new Bone(
																																		new Position(
																																				0,
																																				0,
																																				0),
																																		new Angle3D(
																																				15.0,
																																				100.0,
																																				0.0),
																																		4.0,
																																		10,
																																		new Bone[] {
																																				/*
																																				 * index:
																																				 * 0
																																				 */new Bone(
																																						new Position(
																																								0,
																																								0,
																																								0),
																																						new Angle3D(
																																								0.0,
																																								0.0,
																																								0.0),
																																						0.0,
																																						10,
																																						new Bone[] {},
																																						null), },
																																		null), },
																														null), },
																										null), },
																						null), },
																				null),
																		/* index: 2 */new Bone(new Position(0, 0, 0),
																				new Angle3D(90.0, -80.0, 0.0), 8.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(90.0, -80.0, 0.0),
																						10.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(0,
																												0, 0),
																										new Angle3D(
																												65.0,
																												-80.0,
																												0.0),
																										16.0, 10,
																										new Bone[] {
																												/*
																												 * index:
																												 * 0
																												 */new Bone(
																														new Position(
																																0,
																																0,
																																0),
																														new Angle3D(
																																142.0,
																																-80.0,
																																0.0),
																														16.0,
																														10,
																														new Bone[] {
																																/*
																																 * index:
																																 * 0
																																 */new Bone(
																																		new Position(
																																				0,
																																				0,
																																				0),
																																		new Angle3D(
																																				196.0,
																																				-80.0,
																																				0.0),
																																		4.0,
																																		10,
																																		new Bone[] {
																																				/*
																																				 * index:
																																				 * 0
																																				 */new Bone(
																																						new Position(
																																								0,
																																								0,
																																								0),
																																						new Angle3D(
																																								0.0,
																																								0.0,
																																								0.0),
																																						0.0,
																																						10,
																																						new Bone[] {},
																																						null), },
																																		null), },
																														null), },
																										null), },
																						null), },
																				null), },
																null), },
														null), },
												null),
										/* index: 1 */new Bone(new Position(0, 0, 0), new Angle3D(90.0, 100.0, 0.0),
												13.0, 10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(25.0, 25.0, 0.0), 34.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(20.0, 135.0, 0.0), 30.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(90.0, -15.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
																				new Bone[] {}, null), },
																		null), },
																null), },
														null), },
												null),
										/* index: 2 */new Bone(new Position(0, 0, 0), new Angle3D(90.0, -80.0, 0.0),
												13.0, 10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(109.0, -45.0, 0.0), 34.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(98.0, -45.0, 0.0), 30.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(175.0, -60.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
																				new Bone[] {}, null), },
																		null), },
																null), },
														null), },
												null), },
								null), }),
						new Skeloton(new Position(0, 0, 0), new Angle3D(), new Bone[] { /* index: 0 */new Bone(
								new Position(0, 0, 0), new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
								new Bone[] {
										/* index: 0 */new Bone(new Position(0, 0, 0), new Angle3D(180.0, 0.0, 0.0), 9.0,
												10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(180.0, 0.0, 0.0), 13.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(180.0, 0.0, 0.0), 11.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(180.0, -90.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(180.0, -90.0, 0.0), 4.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(180.0, -90.0, 0.0),
																						4.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(
																												0, 0,
																												0),
																										new Angle3D(
																												180.0,
																												-90.0,
																												0.0),
																										0.0, 10,
																										new Bone[] {},
																										null), },
																						null), },
																				null), },
																		null),
																		/* index: 1 */new Bone(new Position(0, 0, 0),
																				new Angle3D(90.0, 100.0, 0.0), 8.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(90.0, 100.0, 0.0),
																						10.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(
																												0, 0,
																												0),
																										new Angle3D(
																												15.0,
																												100.0,
																												0.0),
																										16.0, 10,
																										new Bone[] {
																												/*
																												 * index:
																												 * 0
																												 */new Bone(
																														new Position(
																																0,
																																0,
																																0),
																														new Angle3D(
																																15.0,
																																100.0,
																																0.0),
																														16.0,
																														10,
																														new Bone[] {
																																/*
																																 * index:
																																 * 0
																																 */new Bone(
																																		new Position(
																																				0,
																																				0,
																																				0),
																																		new Angle3D(
																																				15.0,
																																				100.0,
																																				0.0),
																																		4.0,
																																		10,
																																		new Bone[] {
																																				/*
																																				 * index:
																																				 * 0
																																				 */new Bone(
																																						new Position(
																																								0,
																																								0,
																																								0),
																																						new Angle3D(
																																								0.0,
																																								0.0,
																																								0.0),
																																						0.0,
																																						10,
																																						new Bone[] {},
																																						null), },
																																		null), },
																														null), },
																										null), },
																						null), },
																				null),
																		/* index: 2 */new Bone(new Position(0, 0, 0),
																				new Angle3D(90.0, -80.0, 0.0), 8.0, 10,
																				new Bone[] { /* index: 0 */new Bone(
																						new Position(0, 0, 0),
																						new Angle3D(90.0, -80.0, 0.0),
																						10.0, 10,
																						new Bone[] {
																								/* index: 0 */new Bone(
																										new Position(0,
																												0, 0),
																										new Angle3D(
																												33.0,
																												-80.0,
																												0.0),
																										16.0, 10,
																										new Bone[] {
																												/*
																												 * index:
																												 * 0
																												 */new Bone(
																														new Position(
																																0,
																																0,
																																0),
																														new Angle3D(
																																11.0,
																																-80.0,
																																0.0),
																														16.0,
																														10,
																														new Bone[] {
																																/*
																																 * index:
																																 * 0
																																 */new Bone(
																																		new Position(
																																				0,
																																				0,
																																				0),
																																		new Angle3D(
																																				360.0,
																																				-80.0,
																																				0.0),
																																		4.0,
																																		10,
																																		new Bone[] {
																																				/*
																																				 * index:
																																				 * 0
																																				 */new Bone(
																																						new Position(
																																								0,
																																								0,
																																								0),
																																						new Angle3D(
																																								0.0,
																																								0.0,
																																								0.0),
																																						0.0,
																																						10,
																																						new Bone[] {},
																																						null), },
																																		null), },
																														null), },
																										null), },
																						null), },
																				null), },
																null), },
														null), },
												null),
										/* index: 1 */new Bone(new Position(0, 0, 0), new Angle3D(90.0, 100.0, 0.0),
												13.0, 10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(25.0, 25.0, 0.0), 34.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(20.0, 135.0, 0.0), 30.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(90.0, -15.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
																				new Bone[] {}, null), },
																		null), },
																null), },
														null), },
												null),
										/* index: 2 */new Bone(new Position(0, 0, 0), new Angle3D(90.0, -80.0, 0.0),
												13.0, 10,
												new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
														new Angle3D(65.0, -45.0, 0.0), 34.0, 10,
														new Bone[] { /* index: 0 */new Bone(new Position(0, 0, 0),
																new Angle3D(11.0, -45.0, 0.0), 30.0, 10,
																new Bone[] { /* index: 0 */new Bone(
																		new Position(0, 0, 0),
																		new Angle3D(98.0, -60.0, 0.0), 8.0, 10,
																		new Bone[] { /* index: 0 */new Bone(
																				new Position(0, 0, 0),
																				new Angle3D(0.0, 0.0, 0.0), 0.0, 10,
																				new Bone[] {}, null), },
																		null), },
																null), },
														null), },
												null), },
								null), }), },
				new LinkedList<Integer>(Arrays.asList(0, 10, 15, 25, 40)));
	}

}
