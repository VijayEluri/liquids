package ch.hsr.ifs.liquids.devices;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;

import ch.hsr.ifs.liquids.util.Vector;

public final class Keyboard extends Device {

	private static final int STEP = 3;
	private static final int MOTION_INTERVAL = 10;

	private final AWTEventListener eventListener;
	private final Thread motionThread;

	private final char[] keys;

	private boolean[] arePressed = { false, false, false, false };
	private boolean isUpdating = false;

	public Keyboard(Vector position, char up, char down, char right, char left) {
		super(position);

		eventListener = createEventListener();
		motionThread = createMotionThread();

		keys = new char[] { up, down, right, left };
	}

	private AWTEventListener createEventListener() {
		return new AWTEventListener() {

			public final void eventDispatched(final AWTEvent event) {
				char key = ((KeyEvent) event).getKeyChar();
				if(event.getID() == KeyEvent.KEY_RELEASED)
					update(key, false);
				else
					update(key, true);
			}

		};
	}

	private final void update(final char key, final boolean isPressed) {
		if (key == keys[0])
			arePressed[0] = isPressed;
		else if (key == keys[1])
			arePressed[1] = isPressed;
		else if (key == keys[2])
			arePressed[2] = isPressed;
		else if (key == keys[3])
			arePressed[3] = isPressed;
	}

	private Thread createMotionThread() {
		return new Thread() {

			@Override
			public void run() {
				while (isUpdating) {
					updatePosition();

					try {
						sleep(MOTION_INTERVAL);
					} catch (Exception e) {
						continue;
					}
				}
			}

		};
	}

	private final void updatePosition() {
		if (arePressed[0])
			setY(getY() + STEP);

		if (arePressed[1])
			setY(getY() - STEP);

		if (arePressed[2])
			setX(getX() + STEP);

		if (arePressed[3])
			setX(getX() - STEP);
	}

	public void plug() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		toolkit.addAWTEventListener(eventListener, AWTEvent.KEY_EVENT_MASK);

		isUpdating = true;
		motionThread.start();
	}

	public void unplug() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		toolkit.removeAWTEventListener(eventListener);

		isUpdating = false;
	}

}
