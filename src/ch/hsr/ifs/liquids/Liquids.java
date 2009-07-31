package ch.hsr.ifs.liquids;

import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import ch.hsr.ifs.liquids.controller.Engine;
import ch.hsr.ifs.liquids.screens.Screen;
import ch.hsr.ifs.liquids.screens.StartUpScreen;

public class Liquids {

	private Engine engine;

	private Frame frame;
	private Screen screen;

	public Liquids() {
		initialzeEngine();
		initializeFrame();
	}

	private void initialzeEngine() {
		engine = new Engine();
	}

	private void initializeFrame() {
		frame = new JFrame();

		configureFrame();

		addScreen(new StartUpScreen());
		addWindowListener();

		frame.setVisible(true);
	}

	private void configureFrame() {
		frame.setUndecorated(true);
		frame.setAlwaysOnTop(true);

		setFullscreen();
	}

	private void setFullscreen() {
		GraphicsDevice device = frame.getGraphicsConfiguration().getDevice();
		device.setFullScreenWindow(frame);
	}

	private void addWindowListener() {
		frame.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
				removeScreen();
				System.exit(0);
			}

		});
	}

	private void addScreen(Screen newScreen) {
		frame.add(newScreen);
		engine.setScreen(newScreen);
		newScreen.open();
		screen = newScreen;
	}

	private void removeScreen() {
		if (screen == null) {
			return;
		}

		screen.close();
		frame.remove(screen);
		screen = null;
	}

	public void changeScreen(Screen newScreen) {
		frame.setVisible(false);

		removeScreen();
		addScreen(newScreen);

		frame.setVisible(true);
	}

	public Screen getScreen() {
		return screen;
	}

	public Frame getFrame() {
		return frame;
	}

	public static void main(String[] args) {
		new Liquids();
	}

}