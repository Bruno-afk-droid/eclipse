package com.firstproject.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.DefaultBoundedRangeModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class AnimationEditor {

	// gamemode
	public static String[] GAMEMODES = { "DEFAULD", "DEBUG" };
	public static String GAMEMODE = "DEFAULD";
	public static boolean Editmode = false;

	// Editior
	public static LinkedList<JButton> DebugButtons = new LinkedList<JButton>();
	public static LinkedList<JSpinner> DebugSPINNERs = new LinkedList<JSpinner>();
	public static LinkedList<JSlider> DebugSLIDERs = new LinkedList<JSlider>();
	public static LinkedList<JComboBox> DebugComboBoxes = new LinkedList<JComboBox>();
	public static LinkedList<JTextArea> DebugTextAreas = new LinkedList<JTextArea>();

	public static SpinnerNumberModel FrameLimit = new SpinnerNumberModel(0, 0, 10, 1);
	public static DefaultBoundedRangeModel Sliderlimit = new DefaultBoundedRangeModel(0, 0, 0, 10);

	public static DefaultComboBoxModel<String> ComboBox = new DefaultComboBoxModel<String>();

	public static DefaultBoundedRangeModel Slider_X = new DefaultBoundedRangeModel(0, 0, -360, 360);
	public static DefaultBoundedRangeModel Slider_Y = new DefaultBoundedRangeModel(0, 0, -360, 360);

	public static SpinnerNumberModel Spinner_X = new SpinnerNumberModel(0, -360, 360, 1);
	public static SpinnerNumberModel Spinner_Y = new SpinnerNumberModel(0, -360, 360, 1);

	public static LinkedList<Integer> AnimationKeyFrames = new LinkedList<Integer>();
	public static LinkedList<Skeloton> EditiorKeyFrames = new LinkedList<Skeloton>();
	public static Skeloton[] DebugKeyFrames = new Skeloton[0];
	public static Skeloton[] DebugKeyFramesDraw = new Skeloton[0];

	public static Rectangle FrameContainer = new Rectangle(0, 0, 0, 0);

	// ANIMATION_EDITIOR
	public static MOB MOB_SELECTED = null;
	public static Position MARKER = new Position(0, 0, 0);

	public static Move MOVE_SELECTED = null;
	public static Skeloton SKELOTON_SELECTED = null;
	public static String BONE_SELECTED = "";
	public static Angle3D ANGLE_SELECTED = null;

	public static boolean ANIMATION_PLAYING = false;

	// Setup
	public static class Setup {

		// Setup.
		public static void All() {
			Setup.FrameContainer();
			Setup.EditFrames();

			JButton Change_Gamemode = new JButton("Change");
			Change_Gamemode.setBounds(140, 0, 90, 10);
			Change_Gamemode.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					CHANGE_GAMEMODE();
				}
			});
			Game.BUTTONS.add(Change_Gamemode);
			// DEBUG_BUTTONS

			// Play_Animation
			JButton Play_Animation = new JButton("Play");
			Play_Animation.setBounds(0, 200, 80, 20);
			Play_Animation.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (ANIMATION_PLAYING) {
						ANIMATION_PLAYING = false;
						Play_Animation.setText("Play");
					} else {
						ANIMATION_PLAYING = true;
						Play_Animation.setText("Pauze");
					}
				}
			});
			Game.BUTTONS.add(Play_Animation);
			DebugButtons.add(Play_Animation);

			JSpinner FrameNr = new JSpinner(FrameLimit);
			FrameNr.setBounds(80, 200, 55, 20);
			FrameNr.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {

					// if(((int)FrameNr.getValue()==0))
					// FrameNr.setValue(MOB_SELECTED.Animation.Frames.length);

					// MOB_SELECTED.Animation.Frame=(int) FrameNr.getValue();
					if (Editmode) {

						FrameLimit.setMaximum(999);

						// if(FrameLimit.get<FrameNr.getValue())

						SELECT.SKELOTON(EditiorKeyFrames.get(Game.SLIDERS.get(0).getValue()));
						if ((int) FrameNr.getValue() != -1) {

							moveEditFrame(Game.SLIDERS.get(0).getValue(), (int) FrameNr.getValue());
						}
						// SKELOTON_SELECTED.Position.plus(new Position(125,125,0));

						SKELOTON_SELECTED.update();
					} else {
						if (((int) FrameNr.getValue() == MOB_SELECTED.MobState.Move.Frames.length))
							FrameNr.setValue(MOB_SELECTED.MobState.Move.Frames.length - 1);
						// FrameLimit.setMaximum((Comparable<?>) AnimationKeyFrames.getLast());
						SELECT.SKELOTON(MOB_SELECTED.MobState.Move.Frames[(int) FrameNr.getValue()].clone());
						SKELOTON_SELECTED.Position.plus(new Position(125, 125, 0));
						SKELOTON_SELECTED.update();
					}
				}
			});

			Game.SPINNERS.add(FrameNr);
			DebugSPINNERs.add(FrameNr);

			// Slider
			JSlider FrameSlider = new JSlider(Sliderlimit);
			FrameSlider.setBounds(0, 180, 270, 20);
			FrameSlider.setMinorTickSpacing(1);
			FrameSlider.setBackground(null);
			FrameSlider.setPaintTicks(true);
			FrameSlider.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					if (Editmode != true)
						FrameNr.setValue(FrameSlider.getValue());
					if (Editmode == true) {
						Setup.EditFrames();
						FrameNr.setValue(-1);
						FrameNr.setValue(AnimationKeyFrames.get(FrameSlider.getValue()));
					}
				}
			});

			Game.SLIDERS.add(FrameSlider);
			DebugSLIDERs.add(FrameSlider);

			// Edit
			JButton Edit = new JButton("EditMode");
			Edit.setBounds(135, 200, 135, 20);
			Edit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					if (Edit.getText().equals("EditMode")) {
						if (ANIMATION_PLAYING == true)
							Play_Animation.doClick();

						Editmode = true;
						FrameSlider.setValue(MOB_SELECTED.MobState.Move.getGridKeyFrameIndex(FrameSlider.getValue()));

						Sliderlimit.setMaximum(MOB_SELECTED.MobState.Move.countNoneAutoFilledFrames() - 1);

						Edit.setText("SpectateMode");
					} else {
						if (ANIMATION_PLAYING == true)
							Play_Animation.doClick();

						Editmode = false;
						FrameSlider.setMaximum(MOB_SELECTED.MobState.Move.Frames.length);

						FrameSlider.setValue(MOB_SELECTED.MobState.Move.KeyFrames.get(FrameSlider.getValue()));

						Edit.setText("EditMode");
					}

				}
			});
			Game.BUTTONS.add(Edit);
			DebugButtons.add(Edit);

			// Skeloton_Bone_list

			JTextArea SELECTED_BONE = new JTextArea();
			SELECTED_BONE.setBounds(135, 220, 135, 20);
			SELECTED_BONE.setVisible(false);
			Game.JTEXTAREA.add(SELECTED_BONE);
			DebugTextAreas.add(SELECTED_BONE);

			JComboBox Bone_Box = new JComboBox(ComboBox);
			Bone_Box.setBounds(135, 240, 135, 20);
			Bone_Box.setVisible(false);
			Game.JCOMBOBOX.add(Bone_Box);
			DebugComboBoxes.add(Bone_Box);

			// Select_Bone
			JButton Select_Bone = new JButton("Select_Bone");
			Select_Bone.setBounds(135, 260, 135, 20);
			Select_Bone.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// System.out.println(ComboBox.getSelectedItem().getClass().getTypeName());
					if (ComboBox.getSize() != 0)
						if (ComboBox.getSelectedItem() != "") {
							BONE_SELECTED = (String) ComboBox.getSelectedItem();
							if (BONE_SELECTED != "")
								Setup.ComboBox(BONE_SELECTED);
							Game.SLIDERS.get(1)
									.setValue((int) SKELOTON_SELECTED.getBone(BONE_SELECTED).Angle.X_direction);
							Game.SLIDERS.get(2)
									.setValue((int) SKELOTON_SELECTED.getBone(BONE_SELECTED).Angle.Y_direction);
						}
					DebugTextAreas.get(0).setText(BONE_SELECTED);
				}
			});
			Game.BUTTONS.add(Select_Bone);
			DebugButtons.add(Select_Bone);

			JButton Select_PreviosBone = new JButton("PreviosBone");
			Select_PreviosBone.setBounds(135, 280, 135, 20);
			Select_PreviosBone.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// System.out.println(ComboBox.getSelectedItem().getClass().getTypeName());
					if (BONE_SELECTED.length() > 1) {
						BONE_SELECTED = BONE_SELECTED.substring(0, BONE_SELECTED.length() - 2);
						Setup.ComboBox(BONE_SELECTED);
						Game.SLIDERS.get(1).setValue((int) SKELOTON_SELECTED.getBone(BONE_SELECTED).Angle.X_direction);
						Game.SLIDERS.get(2).setValue((int) SKELOTON_SELECTED.getBone(BONE_SELECTED).Angle.Y_direction);
					} else {
						// FrameLimit.setMaximum(MOB_SELECTED.Animation.Frames.length);
						// Sliderlimit.setMaximum(MOB_SELECTED.Animation.Frames.length);
						// Sliderlimit.setValue((int) FrameLimit.getValue());
						SELECT.SKELOTON(SKELOTON_SELECTED);
						SKELOTON_SELECTED.update();
						Setup.ComboBox("");
					}
					DebugTextAreas.get(0).setText(BONE_SELECTED);

				}
			});
			Game.BUTTONS.add(Select_PreviosBone);
			DebugButtons.add(Select_PreviosBone);

			// Set_Angle

			JSpinner DEGs_X = new JSpinner(Spinner_X);
			DEGs_X.setBounds(215, 300, 55, 20);
			DEGs_X.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {

					Game.SLIDERS.get(1).setValue((int) DEGs_X.getValue());
					if (BONE_SELECTED != "") {
						SKELOTON_SELECTED.getBone(BONE_SELECTED).Angle.X_direction = (int) DEGs_X.getModel().getValue();
						SKELOTON_SELECTED.update();
						Setup.EditFrames();
					}

				}
			});

			Game.SPINNERS.add(DEGs_X);
			DebugSPINNERs.add(DEGs_X);

			JSlider DEG_X = new JSlider(Slider_X);
			DEG_X.setBounds(135, 300, 80, 20);
			DEG_X.setMinorTickSpacing(1);
			DEG_X.setBackground(null);
			DEG_X.setPaintTicks(true);
			DEG_X.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					DEGs_X.setValue(DEG_X.getValue());
				}
			});

			Game.SLIDERS.add(DEG_X);
			DebugSLIDERs.add(DEG_X);

			JSpinner DEGs_Y = new JSpinner(Spinner_Y);
			DEGs_Y.setBounds(215, 320, 55, 20);
			DEGs_Y.setBackground(null);
			DEGs_Y.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {

					Game.SLIDERS.get(2).setValue((int) DEGs_Y.getValue());
					if (BONE_SELECTED != "") {

						SKELOTON_SELECTED.getBone(BONE_SELECTED).Angle.Y_direction = (int) DEGs_Y.getModel().getValue();
						SKELOTON_SELECTED.update();
						Setup.EditFrames();
					}
				}
			});

			Game.SPINNERS.add(DEGs_Y);
			DebugSPINNERs.add(DEGs_Y);

			JSlider DEG_Y = new JSlider(Slider_Y);
			DEG_Y.setBounds(135, 320, 80, 20);
			DEG_Y.setMinorTickSpacing(1);
			DEG_Y.setBackground(null);
			DEG_Y.setPaintTicks(true);
			DEG_Y.addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					DEGs_Y.setValue(DEG_Y.getValue());
				}
			});

			Game.SLIDERS.add(DEG_Y);
			DebugSLIDERs.add(DEG_Y);

			// Add_EditiorFrame
			JButton Add_EditiorFrame = new JButton("Add_Frame");
			Add_EditiorFrame.setBounds(0, 220, 135, 20);
			Add_EditiorFrame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					add.EditiorKeyFrame(FrameSlider.getValue());

				}
			});
			Game.BUTTONS.add(Add_EditiorFrame);
			DebugButtons.add(Add_EditiorFrame);

			// Add_EditiorFrame
			JButton Remove_EditiorFrame = new JButton("Remove_Frame");
			Remove_EditiorFrame.setBounds(0, 240, 135, 20);
			Remove_EditiorFrame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					Remove.EditiorKeyFrame(FrameSlider.getValue());

				}
			});
			Game.BUTTONS.add(Remove_EditiorFrame);
			DebugButtons.add(Remove_EditiorFrame);

			// Apply_Animation
			JButton Apply_Animation = new JButton("Apply_Animation");
			Apply_Animation.setBounds(0, 260, 135, 20);
			Apply_Animation.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					Position rvel = new Position(0, 0, 0);
					int m = 1;
					for (int i = 0; i < MOB_SELECTED.MobState.Move.MoveStates.length; i++) {
						if (MOB_SELECTED.MobState.Move.MoveStates[i] != null) {
							Position P = MOB_SELECTED.MobState.Move.MoveStates[i].vel.clone();
							P.multiply(m);
							rvel.plus(P);
							m = 1;
						} else
							m++;
					}

					MOB_SELECTED.MobState.setMove(MOB_SELECTED.MobState.getIndex(MOB_SELECTED.MobState.Move),
							new Move(new Animation(DebugKeyFrames, AnimationKeyFrames), rvel,
									MOB_SELECTED.MobState.Move.TriggerdBy));
					// MOB_SELECTED.MobState.Move = new Move(MOB_SELECTED.MobState.Move, null,
					// null);

					// ((Test)object.get(0)).ForwardWalkAnimation=new Animation(DebugKeyFrames,
					// AnimationKeyFrames);
				}
			});
			Game.BUTTONS.add(Apply_Animation);
			DebugButtons.add(Apply_Animation);

			// Print_Skeloton
			JButton Print_Skeloton = new JButton("Print_Skeloton");
			Print_Skeloton.setBounds(0, 280, 135, 20);
			Print_Skeloton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(SKELOTON_SELECTED.getDataPrint());
				}
			});
			Game.BUTTONS.add(Print_Skeloton);
			DebugButtons.add(Print_Skeloton);

			// Print_Skeloton
			JButton Print_Animation = new JButton("Print_Animation");
			Print_Animation.setBounds(0, 300, 135, 20);
			Print_Animation.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(MOB_SELECTED.MobState.Move.getDataPrint());

				}
			});
			Game.BUTTONS.add(Print_Animation);
			DebugButtons.add(Print_Animation);

			Deactivate.Buttons(DebugButtons, GAMEMODE == "DEBUG");
			Deactivate.SPINNERs(DebugSPINNERs, GAMEMODE == "DEBUG");
			Deactivate.SLIDERs(DebugSLIDERs, GAMEMODE == "DEBUG");

		}

		// FrameContainer
		public static void FrameContainer() {

			for (int i = 0; i < DebugKeyFrames.length; i++) {
				if (DebugKeyFrames[i].getBounds().width > FrameContainer.width)
					FrameContainer.width = DebugKeyFrames[i].getBounds().width;
				if (DebugKeyFrames[i].getBounds().height > FrameContainer.height)
					FrameContainer.height = DebugKeyFrames[i].getBounds().height;
			}
		}

		// EditFrames
		public static void EditFrames() {
			int M = 0;
			int W = 0;
			DebugKeyFrames = new Skeloton[EditiorKeyFrames.size()];
			for (int i = 0; i < DebugKeyFrames.length; i++) {

				DebugKeyFrames[M] = EditiorKeyFrames.get(M);
				DebugKeyFrames[M].update();

				Position R = new Position(DebugKeyFrames[M].getBounds().x,
						DebugKeyFrames[M].getBounds().y + DebugKeyFrames[M].getBounds().height, 0);
				// R.plus(new Position(DebugKeyFrames[M].getBounds().width/2,
				// DebugKeyFrames[M].getBounds().height/2, 0));
				DebugKeyFrames[M].Position.min(R);
				DebugKeyFrames[M].Position.plus(new Position(W, 180, 0));
				DebugKeyFrames[M].update();
				W += DebugKeyFrames[M].getBounds().width;
				M++;
			}
			if (DebugKeyFrames.length - 1 > 0)
				Sliderlimit.setMaximum(DebugKeyFrames.length - 1);
		}

		public static void ComboBox(Skeloton s) {
			ComboBox.removeAllElements();
			for (int i = 0; i < s.Bones.length; i++)
				ComboBox.addElement(s.Bones[i].getInString(""));
		}

		public static void ComboBox(Bone b) {
			BONE_SELECTED = b.getInString(b.getInString(""));
			// System.out.println(BONE_SELECTED);
			ComboBox.removeAllElements();
			for (int i = 0; i < b.Joint.length; i++)
				ComboBox.addElement(((Bone) b.getJoint(i)).getInString(""));
		}

		public static void ComboBox(String b) {
			BONE_SELECTED = b;
			ComboBox.removeAllElements();
			for (int i = 0; i < SKELOTON_SELECTED.getBone(b).Joint.length; i++)
				if (BONE_SELECTED != "")
					ComboBox.addElement(((Bone) SKELOTON_SELECTED.getBone(b).getJoint(i)).getInString(""));
		}

	}

	// add
	public static class add {
		// addAditorKeyFrame
		public static void EditiorKeyFrame() {
			EditiorKeyFrames.add(EditiorKeyFrames.getLast());
			Setup.EditFrames();
		}

		public static void EditiorKeyFrame(int i) {

			int Frame;
			if (i != AnimationKeyFrames.size() - 1) {
				Frame = (AnimationKeyFrames.get(i) + AnimationKeyFrames.get(i + 1)) / 2;
			} else {
				Frame = AnimationKeyFrames.get(i);
				if (Frame == 0) {
					Frame += 10;
					FrameLimit.setMaximum(10);
				}

			}

			EditiorKeyFrames.add(i, EditiorKeyFrames.get(i).clone());
			AnimationKeyFrames.add(i, Frame);
			if (i < AnimationKeyFrames.size() - 2) {
				EditiorKeyFrames.get(i).add(EditiorKeyFrames.get(i).Difference(EditiorKeyFrames.get(i + 2))
						.getmultiplied(0.5).getmultipliedHitBox(0.5));
				EditiorKeyFrames.get(i).update();
			}
			// Game.SLIDERS.get(0).setValue(i++);

			reorderEditFrames();
			Setup.EditFrames();
		}

	}

	// SELECT
	public static class SELECT {

		public static void MOB(String D) {
			if (D == ">") {
				int I = Handler.getObjectIndex(MOB_SELECTED);
				if (I == Handler.object.size()) {
					MOB((MOB) Handler.object.get(0));
				} else {
					MOB((MOB) Handler.object.get(++I));
				}
			}
			if (D == "<") {
				int I = Handler.getObjectIndex(MOB_SELECTED);
				if (I == 0) {
					MOB((MOB) Handler.object.get(Handler.object.size()));
				} else {
					MOB((MOB) Handler.object.get(--I));
				}
			}

		}

		public static void MOB(MOB MOB) {
			MOB_SELECTED = MOB;
			MARKER = MOB.Position;

			ANIMATION(MOB.MobState.Move.clone());
			SKELOTON(MOB.Skeloton.clone());
			SKELOTON_SELECTED.Position.plus(new Position(50, 150, 0));
			SKELOTON_SELECTED.update();
			Sliderlimit.setMaximum(MOB_SELECTED.MobState.Move.Frames.length);
			Sliderlimit.setMinimum(0);
			Sliderlimit.setValue(0);

		}

		public static void ANIMATION(Move A) {
			MOVE_SELECTED = A;
			EditiorKeyFrames.clear();
			for (int i = 0; i < MOB_SELECTED.MobState.Move.KeyFrames.size(); i++)
				EditiorKeyFrames.add(MOVE_SELECTED.Frames[MOB_SELECTED.MobState.Move.KeyFrames.get(i)].clone());
		}

		public static void SKELOTON(Skeloton S) {
			S.update();

			if (!Editmode) {
				Position R = new Position(S.getBounds().x, S.getBounds().y, 0);
				R.plus(new Position(S.getBounds().width / 2, S.getBounds().height / 2, 0));
				Position P = S.Position;
				S.Position.min(R);
			}

			SKELOTON_SELECTED = S;
			SKELOTON_SELECTED.update();
			BONE(SKELOTON_SELECTED.Bones[0]);
			// System.out.println(SKELOTON_SELECTED.getBone("0>0"));
			// BONE_SELECTED=SKELOTON_SELECTED.getBone("0>1");
			if (!Editmode) {
				FrameLimit.setMaximum(MOB_SELECTED.MobState.Move.Frames.length);
				Sliderlimit.setMaximum(MOB_SELECTED.MobState.Move.Frames.length);
				Sliderlimit.setValue((int) FrameLimit.getValue());
				Setup.ComboBox(SKELOTON_SELECTED);
			}

		}

		public static void BONE(String B) {
			Setup.ComboBox(SKELOTON_SELECTED.getBone(B));
		}

		public static void BONE(Bone B) {
			Setup.ComboBox(B);
		}

	}

	public static class Remove {
		// removeKeyFrame
		public static void EditiorKeyFrame() {
			EditiorKeyFrames.remove(EditiorKeyFrames.getLast().clone());
			reorderEditFrames();
			Setup.EditFrames();
		}

		public static void EditiorKeyFrame(int i) {
			if (AnimationKeyFrames.size() > 2) {
				EditiorKeyFrames.remove(i);
				AnimationKeyFrames.remove(i);
				reorderEditFrames();
				Setup.EditFrames();
			}
		}

	}

	// Deactivate.
	public static class Deactivate {

		public static void Buttons(LinkedList<JButton> D, boolean B) {
			for (int i = 0; i < D.size(); i++)
				for (int j = 0; j < Game.BUTTONS.size(); j++) {
					if (D.get(i) == Game.BUTTONS.get(j)) {
						Game.BUTTONS.get(j).setVisible(B);
					}
				}
		}

		public static void SPINNERs(LinkedList<JSpinner> D, boolean B) {
			for (int i = 0; i < D.size(); i++)
				for (int j = 0; j < Game.SPINNERS.size(); j++) {
					if (D.get(i) == Game.SPINNERS.get(j)) {
						Game.SPINNERS.get(j).setVisible(B);
						if (MOB_SELECTED != null)
							Game.SPINNERS.get(0).setValue(MOB_SELECTED.MobState.Move.Frame);
					}
				}
		}

		public static void SLIDERs(LinkedList<JSlider> D, boolean B) {
			for (int i = 0; i < D.size(); i++)
				for (int j = 0; j < Game.SLIDERS.size(); j++) {
					if (D.get(i) == Game.SLIDERS.get(j)) {
						Game.SLIDERS.get(j).setVisible(B);
					}
				}
		}

		public static void COMBOBOXes(LinkedList<JComboBox> D, boolean B) {
			for (int i = 0; i < D.size(); i++)
				for (int j = 0; j < Game.JCOMBOBOX.size(); j++) {
					if (D.get(i) == Game.JCOMBOBOX.get(j)) {
						Game.JCOMBOBOX.get(j).setVisible(B);
					}
				}
		}

		public static void TEXAREAs(LinkedList<JTextArea> D, boolean B) {
			for (int i = 0; i < D.size(); i++)
				for (int j = 0; j < Game.JTEXTAREA.size(); j++) {
					if (D.get(i) == Game.JTEXTAREA.get(j)) {
						Game.JTEXTAREA.get(j).setVisible(B);
					}
				}
		}

	}

	// angle edit
	public static void updateAngleEdit() {
		Bone Bone = SKELOTON_SELECTED.getBone(BONE_SELECTED);
		if (BONE_SELECTED.length() > 0) {
			Bone.Angle.X_direction = Game.SLIDERS.get(1).getValue();
			Bone.Angle.Y_direction = Game.SLIDERS.get(2).getValue();
		}
	}

	public static void moveEditFrame(int i, int f) {

		AnimationKeyFrames.set(i, f);
		reorderEditFrames();

	}

	public JButton GetButtonByName(String S) {
		for (int i = 0; i < Game.BUTTONS.size(); i++)
			if (Game.BUTTONS.get(i).getName() == S)
				return Game.BUTTONS.get(i);
		return null;
	}

	// DEBUGGERMODE
	public static void CHANGE_GAMEMODE() {

		// if(GAMEMODE!="DEBUG") {

		EditiorKeyFrames = new LinkedList<Skeloton>();
		AnimationKeyFrames = new LinkedList<Integer>();
		int S = MOB_SELECTED.MobState.Move.KeyFrames.size();
		for (int i = 0; i < S; i++) {
			EditiorKeyFrames
					.add(MOB_SELECTED.MobState.Move.Frames[MOB_SELECTED.MobState.Move.KeyFrames.get(i)].clone());
			AnimationKeyFrames.add(MOB_SELECTED.MobState.Move.KeyFrames.get(i));
		}
		reorderEditFrames();

		if (GAMEMODE == "DEFAULD")
			GAMEMODE = "DEBUG";
		else if (GAMEMODE == "DEBUG")
			GAMEMODE = "DEFAULD";

		if (GAMEMODE == "DEBUG") {
			SELECT.SKELOTON(MOB_SELECTED.MobState.Move.getFrame().clone());
			SKELOTON_SELECTED.Position.plus(new Position(0, 50, 0));
			SKELOTON_SELECTED.update();
		} else {
			MOB_SELECTED.MobState.Move.Frame = 0;
		}

		Deactivate.Buttons(DebugButtons, GAMEMODE == "DEBUG");
		Deactivate.SPINNERs(DebugSPINNERs, GAMEMODE == "DEBUG");
		Deactivate.SLIDERs(DebugSLIDERs, GAMEMODE == "DEBUG");
		Deactivate.COMBOBOXes(DebugComboBoxes, GAMEMODE == "DEBUG");
		Deactivate.TEXAREAs(DebugTextAreas, GAMEMODE == "DEBUG");

	}

	public static void reorderEditFrames() {

		for (int i = 0; i < AnimationKeyFrames.size(); i++) {
			for (int j = i + 1; j < AnimationKeyFrames.size(); j++) {
				Skeloton tmps;
				int tmp = 0;
				if (AnimationKeyFrames.get(i) > AnimationKeyFrames.get(j)) {
					// sort
					tmp = AnimationKeyFrames.get(i);
					AnimationKeyFrames.set(i, (int) AnimationKeyFrames.get(j));
					AnimationKeyFrames.set(j, tmp);
					// clone sort
					tmps = EditiorKeyFrames.get(i);
					EditiorKeyFrames.set(i, EditiorKeyFrames.get(j));
					EditiorKeyFrames.set(j, tmps);
				}
			}

		}

		if (AnimationKeyFrames.get(0) != 0) {
			int min = AnimationKeyFrames.get(0);
			for (int i = 0; i < AnimationKeyFrames.size(); i++) {
				AnimationKeyFrames.set(i, AnimationKeyFrames.get(i) - min);
			}
		}
		Setup.EditFrames();
	}

	public void render(Graphics2D g) {
		if (GAMEMODE == "DEFAULD") {

		} else {
			if (this.Editmode) {
				g.setColor(Color.CYAN);
				for (int i = 0; i < EditiorKeyFrames.size(); i++) {
					EditiorKeyFrames.get(i).Draw(g);
				}
				g.setColor(Color.YELLOW);
				SKELOTON_SELECTED.getBone(BONE_SELECTED).Draw(g);
			} else
				SKELOTON_SELECTED.Draw(g);

			g.setColor(Color.GREEN);
			g.draw(SKELOTON_SELECTED.HitBox);

		}
	}

	public void tick() {
		if (this.ANIMATION_PLAYING) {
			if ((int) FrameLimit.getMaximum() != (int) this.DebugSPINNERs.get(0).getValue() + 1)
				this.DebugSLIDERs.get(0).setValue(this.DebugSLIDERs.get(0).getValue() + 1);
			else
				this.DebugSLIDERs.get(0).setValue(0);
		}

	}

}
