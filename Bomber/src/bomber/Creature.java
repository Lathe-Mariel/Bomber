package bomber;

import java.awt.Image;

import javax.swing.SwingUtilities;

abstract public class Creature extends Tile {
	boolean moveProcess;
	Image movingImage[];

	/**
	 * Moting interval time.
	 */
	int speed;

	private Creature(Field container) {
		super(container);
	}

	private Creature() {
	}

	Creature(Field container, int x, int y) {
		super(container, x, y);
		speed = 200;
		moveProcess = false;
		movingImage = new Image[5];
	}

	/**
	 * When this object contacts enemy, this method will be called by enemy(when enemy steps on) or oneself(when PC steps on).
	 */
	abstract void kill(Creature source);

	abstract void contact();

	boolean moveRight() {
		image = movingImage[1];
		if (!container.toRight(this)) {
			repaint();
			return false;
		}
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(100, 0, 0, 40, 40);
			}
		});
		try {
			Thread.sleep(speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	boolean moveLeft() {
		image = movingImage[0];
		if (!container.toLeft(this)) {
			repaint();
			return false;
		}
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(100, 0, 0, 40, 40);
			}
		});
		try {
			Thread.sleep(speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	boolean moveUp() {
		if (!container.toUp(this)) {
			return false;
		}
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(100, 0, 0, 40, 40);
			}
		});
		try {
			Thread.sleep(speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	boolean moveDown() {
		if (!container.toDown(this)) {
			return false;
		}
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(100, 0, 0, 40, 40);
			}
		});
		try {
			Thread.sleep(speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
