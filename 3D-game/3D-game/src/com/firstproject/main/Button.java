package com.firstproject.main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Button {

	public int index;
	public Position position;
	public Position gridposition = new Position(0, 0, 0);
	public Rectangle border = new Rectangle();
	public Grid owner = null;
	public boolean solid = false;
	public boolean doubleClicked = false;

	public Object holding = "";

	public interface Pressed {
		void trigger();
	}

	public interface Holded {
		void trigger();
	}

	public interface Released {
		void trigger();
	}

	public Pressed Pressed;

	public Holded Holded;

	public Released Released;

	public Color textColor = Color.DARK_GRAY;
	public Color filledColor = Color.BLACK;
	public Color borderColor = Color.WHITE;

	public Color selectedTextColor = Color.DARK_GRAY;
	public Color selectedFilledColor = Color.WHITE;
	public Color selectedBorderColor = Color.BLACK;

	public boolean selected = false;
	public boolean triggerd = false;

	public Button(Position position, Rectangle border, Object holding, Pressed PressedTrigger) {
		this.position = position;
		this.gridposition = new Position(border.x, border.y, 0);
		this.border = border;
		this.holding = holding;
		this.Pressed = PressedTrigger;
		this.update();
		setup();
	}

	public Button(Position position, Rectangle border, Object holding, Pressed PressedTrigger,
			Released ReleaseTrigger) {
		this.position = position;
		this.gridposition = new Position(border.x, border.y, 0);
		this.border = border;
		this.holding = holding;
		this.Pressed = PressedTrigger;
		this.Released = ReleaseTrigger;
		this.update();
		setup();
	}

	public Button(Position position, Rectangle border, Object holding, Pressed PressedTrigger, Holded HoldedTrigger,
			Released ReleaseTrigger) {
		this.position = position;
		this.gridposition = new Position(border.x, border.y, 0);
		this.border = border;
		this.holding = holding;
		this.Pressed = PressedTrigger;
		this.Holded = HoldedTrigger;
		this.Released = ReleaseTrigger;
		this.update();
		setup();
	}

	@Override
	public Button clone() {
		Button r = new Button(position.clone(), new Rectangle(border.x, border.y, border.width, border.height),
				(holding), Pressed, Holded, Released);
		if (this.owner != null) {
			// r.position = this.position.clone();

		}
		r.update();
		return r;
	}

	public void setup() {
		if (this.holding.getClass().equals(Skeloton.class)) {
			((Skeloton) holding).update();
			this.border = ((Skeloton) holding).getBounds();
		}
		if (owner != null) {
			owner.reorder();
		}

	}

	public void tick() {
		this.update();
		if (this.holding.getClass().equals(Skeloton.class)) {

			Position sp = ((Skeloton) holding).Position;

			sp.set(this.position);
			((Skeloton) holding).update();
			sp.x += (this.border.x - ((Skeloton) holding).getBounds().x);
			sp.y += (this.border.y - ((Skeloton) holding).getBounds().y);

			((Skeloton) holding).update();
			this.border = ((Skeloton) holding).getBounds();
		}
	}

	public Button getThis() {
		return this;
	}

	public boolean isTriggerd(Position position, String triggerd) {

		selected = this.border.contains(position.getPointFloat());

		if ((this.selected)) {

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

		/*
		 * 
		 * if (triggerd) { if (!this.triggerd) { Pressed.trigger(); } else { if
		 * (this.Holded != null) Holded.trigger(); } } else { if (this.triggerd) { if
		 * (this.Released != null) Released.trigger(); } }
		 * 
		 */

		return (triggerd != "Released") && (this.selected);
	}

	public void move(Position position) {
		this.position.set(position);
		this.update();
	}

	public void update() {

		this.border.x = (int) (this.position.x - (this.border.width / 2));
		this.border.y = (int) (this.position.y - (this.border.height / 2));
	}

	public void render(Graphics2D g) {
		if (selected) {
			g.setColor(selectedFilledColor);
		} else {
			g.setColor(filledColor);
		}
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
		// if (holding.getClass().equals(int.class)) {

		// }
		if (holding.getClass().equals((Skeloton.class))) {
			((Skeloton) holding).Draw(g);
		} else {
			if (holding.equals(int.class))
				g.drawString((holding).toString() + owner.Buttons.indexOf(this), this.border.x,
						this.border.y + this.border.height);
			else
				g.drawString(holding.toString(), this.border.x, this.border.y + this.border.height);
		}

	}

}
