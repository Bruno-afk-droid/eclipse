package com.firstproject.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Collections;
import java.util.LinkedList;

public class Grid extends Button {

	boolean interactible = false;
	Button selectedButton;
	Button lastSelectedButton;
	Button doubleClickedSelectedButton;

	LinkedList<Button> Buttons = new LinkedList<Button>();
	LinkedList<Object> Objects = new LinkedList<Object>();
	LinkedList<Rectangle> Spaces = new LinkedList<Rectangle>();

	public Grid(Position position, Rectangle border, boolean interactible) {
		super(position, border, "", null);
		this.interactible = interactible;
		this.selectedFilledColor = Color.BLACK;
		this.selectedBorderColor = Color.WHITE;
		setup();
	}

	public Grid(Position position, Rectangle border, Object O, Pressed PressedTrigger) {
		super(position, border, O, PressedTrigger);
		setup();
	}

	public Grid(Position position, Skeloton s, int frame) {
		super(position, s.getBounds(), s, null);

		Grid T = this;
		this.add(new Button(new Position(0, 0, 0), new Rectangle(T.border.width - 20, 0, 20, 10), "+", () -> {
		}));
		this.Buttons.get(0).index = 0;
		T.Buttons.get(0).Pressed = () -> {
			System.out.println(getThis());
			T.owner.add(index + 1, this.clone());
			T.owner.reorder();
		};
		this.Buttons.get(0).selectedBorderColor = Color.RED;

		this.add(new Button(new Position(0, 0, 0), new Rectangle(T.border.width - 20, 0, 20, 20), "X", () -> {
		}));
		T.Buttons.get(1).index = 1;
		T.Buttons.get(1).Pressed = () -> {
			T.owner.remove(index);
			T.owner.reorder();
		};

		this.add(new Button(new Position(0, 0, 0), new Rectangle((T.border.width / 2) + 10, -10, 20, 10), frame, () -> {
		}));

		this.add(new Button(new Position(0, 0, 0), new Rectangle((T.border.width / 2) - 10, -10, 10, 10), "<", () -> {
			this.Buttons.get(2).holding = (int) this.Buttons.get(2).holding - 1;
		}));

		this.add(
				new Button(new Position(0, 0, 0), new Rectangle((this.border.width / 2) + 10, -10, 10, 10), ">", () -> {
					this.Buttons.get(2).holding = (int) this.Buttons.get(2).holding + 1;
				}));

		T.Buttons.get(2).index = 2;

		this.Pressed = () -> {
			if (this.selectedButton == null)
				if (this.doubleClicked) {
					this.add(new Button(Handler.MOUSE_Position.clone(), new Rectangle(35, 35), holding, () -> {
					}));
				}
		};
		setup();
	}

	@Override
	public Grid getThis() {
		return this;
	}

	@Override
	public Grid clone() {
		return new Grid(new Position(0, 0, 0), ((Skeloton) this.holding).clone(), ((int) this.Buttons.get(2).holding));
	}

	@Override
	public void move(Position position) {
		this.position.set(position);
		this.update();
		this.reorder();
	}

	@Override
	public void setup() {

		if (this.Pressed == null)
			this.Pressed = () -> {

				if (interactible)
					if (lastSelectedButton != null) {
						lastSelectedButton.doubleClicked = (lastSelectedButton == selectedButton);

						if (lastSelectedButton.doubleClicked) {
							doubleClickedSelectedButton = lastSelectedButton;
							System.out.println("double clicked");
						} else {
							doubleClickedSelectedButton = null;
						}
					}

				if (this.holding.getClass() == Skeloton.class) {

				}
			};

		this.Holded = () -> {
			if (interactible)
				if (selectedButton != null)
					if (!this.doubleClicked) {

						selectedButton.move(Handler.MOUSE_Position);

						for (int i = 0; i < this.Spaces.size(); i++) {
							if (this.Spaces.get(i).contains(selectedButton.position.getPointFloat())) {
								this.moveButton(selectedButton, this.Spaces.get(i));

							}
						}

						selectedButton.update();
						selectedButton.isTriggerd(Handler.MOUSE_Position, Handler.MOUSE_Clicked);
					}
		};

		this.Released = () -> {
			if (selectedButton != null) {
				if (selectedButton.Released != null) {
					selectedButton.Released.trigger();
				}
				selectedButton.selected = false;
				selectedButton.position.x = 0;
				selectedButton.update();
				lastSelectedButton = selectedButton;
				selectedButton = null;
			}

			gridposition = this.position.clone();
			this.reorder();

		};
	}

	public void selectButton() {

	}

	public void setAll(LinkedList<Button> B) {
		this.Buttons.clear();
		this.Spaces.clear();
		for (int i = 0; i < B.size(); i++)
			this.add(B.get(i));
	}

	public void add(Button B) {
		Buttons.add(B);
		Objects.add(B.holding);
		Buttons.getLast().owner = this;
		Buttons.getLast().position.y = this.position.y;
		Buttons.getLast().update();
		Buttons.getLast().index = Buttons.size() - 1;

		Spaces.add(B.border);
		reorder();

		float L = this.border.x;
		for (int i = 0; i < this.Spaces.size(); i++) {
			L += this.Spaces.get(i).width;
		}

		this.border.width = (int) L;
		if (this.border.height < Spaces.getLast().height)
			this.border.height = Spaces.getLast().height;

		// Spaces.getLast().x = (int) L;
		reorder();
	}

	public void add(int index, Button B) {
		Buttons.add(index, B);
		Objects.add(B.holding);
		B.owner = this;
		B.position.y = this.position.y;
		B.update();
		B.index = index;

		Spaces.add(index, B.border);
		reorder();

		float L = this.border.x;
		for (int i = 0; i < this.Spaces.size(); i++) {
			L += this.Spaces.get(i).width;
		}

		this.border.width = (int) L;
		if (this.border.height < Spaces.getLast().height)
			this.border.height = Spaces.getLast().height;

		Spaces.getLast().x = (int) L;
		reorder();
	}

	public void remove(Button B) {

		this.border.width -= B.border.width;
		// B.owner = null;
		Objects.remove(B.holding);
		Buttons.remove(B);
		this.Spaces.remove(B.border);
		this.reorder();
	}

	public void remove(int i) {

		this.border.width -= this.Buttons.get(i).border.width;
		Objects.remove(Buttons.get(i).holding);
		Spaces.remove(Buttons.get(i).border);
		Buttons.remove(i);

		this.reorder();
	}

	public void switchButton(Button B, Rectangle R) {
		int i = Buttons.indexOf(B);
		int j = Spaces.indexOf(R);
		Collections.swap(Buttons, i, j);
	}

	public void moveButton(Button B, Rectangle R) {
		int i = Buttons.indexOf(B);
		int j = Spaces.indexOf(R);
		int l = Objects.indexOf(B.holding);

		// int h = (int) ((Grid) B).Buttons.get(2).holding;
		// ((Grid) B).Buttons.get(2).holding = ((Grid)
		// Buttons.get(j)).Buttons.get(2).holding;
		// ((Grid) Buttons.get(j)).Buttons.get(2).holding = h;

		Buttons.remove(B);
		Buttons.add(j, B);
		Objects.set(i, B.holding);
	}

	public void reorder() {
		float L = 0;
		LinkedList<Integer> IN = new LinkedList<Integer>();

		for (int i = 0; i < this.Buttons.size(); i++) {
			if (this.Buttons.get(i).getClass().equals(Grid.class))
				IN.add((Integer) ((Grid) this.Buttons.get(i)).Buttons.get(2).holding);
		}
		Collections.sort(IN);

		for (int i = 0; i < this.Buttons.size(); i++) {

			Button B = this.Buttons.get(i);

			B.index = i;
			if (B.getClass().equals(Grid.class)) {
				if (IN.size() != 0) {
					((Grid) B).Buttons.get(2).holding = IN.getFirst();
					IN.removeFirst();
				}
				B.position.x = this.border.x + L + (B.border.width / 2);
				B.position.y = (float) (this.border.y + Math.floor(B.border.height / 2));
			} else {

				B.position.x = this.border.x + (B.border.width / 2);
				B.position.y = this.border.y + (B.border.height / 2);
				B.position.plus(B.gridposition);
			}

			B.update();

			if (B.getClass() == Grid.class)
				((Grid) B).reorder();

			L += B.border.width;

		}

		for (int i = 0; i < this.Buttons.size(); i++) {
			this.Spaces.get(i).width = this.Buttons.get(i).border.width;
			this.Spaces.get(i).x = this.Buttons.get(i).border.x;
		}
		if (this.border.width < L)
			this.border.width = (int) L;
	}

	@Override
	public void tick() {

		if (this.holding.getClass().equals(Skeloton.class)) {
			((Skeloton) holding).Bones[0].PS = this.position;
			((Skeloton) holding).update();
		}
		if (this.holding.getClass().equals(Skeloton.class)) {

			Position sp = ((Skeloton) holding).Position;

			sp.set(this.position);
			((Skeloton) holding).update();
			sp.x += (this.border.x - ((Skeloton) holding).getBounds().x);
			sp.y += (this.border.y - ((Skeloton) holding).getBounds().y);

			((Skeloton) holding).update();
			this.border = ((Skeloton) holding).getBounds();
		}

		for (int i = 0; i < this.Buttons.size(); i++) {
			this.Buttons.get(i).tick();
		}
		if (this.doubleClicked)
			this.filledColor = Color.YELLOW;
		else
			this.filledColor = Color.BLACK;

	}

	@Override
	public boolean isTriggerd(Position position, String triggerd) {

		selected = this.border.contains(position.getPointFloat());

		for (int i = 0; i < this.Buttons.size(); i++) {
			if (this.Buttons.get(i).isTriggerd(position, Handler.MOUSE_Clicked)) {
				if (this.selectedButton == null) {
					this.selectedButton = this.Buttons.get(i);
				}

			}
			if (i < this.Buttons.size())
				if (this.Buttons.get(i).selected)
					this.selected = false;
		}
		if ((this.selected) || (this.selectedButton != null)) {

			switch (triggerd) {
			case "Pressed":
				if (Pressed != null)
					Pressed.trigger();
				break;
			case "Holded":
				if (Holded != null)
					Holded.trigger();
				break;
			case "Released":
				if (Released != null)
					Released.trigger();
				break;
			}

		}

		return (triggerd != "Released") && (this.selected);
	}

	@Override
	public void render(Graphics2D g) {

		if (filledColor != Color.YELLOW)
			if (selected)
				filledColor = Color.WHITE;

		g.setColor(filledColor);
		g.fill(border);

		if (selected) {
			g.setColor(selectedBorderColor);
		} else {
			g.setColor(borderColor);
		}
		g.draw(border);

		if (selected) {
			g.setColor(selectedTextColor);
		} else {
			g.setColor(textColor);
		}
		if (holding.getClass().equals("".getClass()))
			g.drawString((String) holding, this.position.x, this.position.y);
		if (holding.getClass().equals((Skeloton.class))) {
			((Skeloton) holding).Draw(g);
		}
		if (holding.getClass().equals(int.class)) {
			g.drawString((String) holding, this.border.x, this.border.y + this.border.height);
		}

		for (int i = 0; i < this.Buttons.size(); i++)
			this.Buttons.get(i).render(g);
		g.setColor(Color.YELLOW);
		if (this.selectedButton != null) {
			g.draw(this.Spaces.get(this.Buttons.indexOf(selectedButton)));
			if (selectedButton.selected)
				this.selectedButton.render(g);

		}

	}

}
