package ch.hsr.ifs.liquids.game;

import javax.media.opengl.GL;

import ch.hsr.ifs.liquids.common.Renderable;
import ch.hsr.ifs.liquids.common.Movable;
import ch.hsr.ifs.liquids.util.Color;
import ch.hsr.ifs.liquids.util.Vector;

public class Player implements Movable, Renderable {

	protected Vector position;
	protected Color color;
	
	public void move() {

	}

	public void render(GL gl) {
		gl.glColor3f(color.red, color.green, color.blue);

		gl.glBegin(GL.GL_POLYGON);
			gl.glVertex3f(position.x, position.y, position.z);
			gl.glVertex3f(position.x, position.y, position.z);
			gl.glVertex3f(position.x, position.y, position.z);
			gl.glVertex3f(position.x, position.y, position.z);
		gl.glEnd();
	}
	
	public void setColor(Color color) {
		this.color = color;
	}

}
