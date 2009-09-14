package ch.hsr.ifs.liquids.controller.events;

import java.awt.AWTEvent;

import ch.hsr.ifs.liquids.controller.Event;
import ch.hsr.ifs.liquids.controller.devices.Mouse.MouseButton;
import ch.hsr.ifs.liquids.util.Vector;

public class MouseEvent extends Event {

	public static MouseEvent createMouseEvent(AWTEvent event) {
		java.awt.event.MouseEvent mouseEvent = (java.awt.event.MouseEvent) event;

		int x = mouseEvent.getX();
		int y = mouseEvent.getY();
		Vector position = new Vector(x, y);

		int id = mouseEvent.getButton();
		MouseButton button = MouseButton.getByID(id);
		button.invertIsPressed();

		return new MouseEvent(position, button);
	}

	private Vector position;
	private MouseButton changedButton;

	public MouseEvent(Vector position, MouseButton changedButton) {
		this.position = position;
		this.changedButton = changedButton;
	}

	public Vector getPosition() {
		return position;
	}

	public MouseButton getChangedButton() {
		return changedButton;
	}

}
