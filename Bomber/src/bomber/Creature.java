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

//	private Creature(Field container) {
//		super(container);
//	}

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
		if(moveProcess == true)return false;
		moveProcess = true;
		image = movingImage[1];
		if(!container.toRight(this)) {
repaint();
			moveProcess = false;
			return false;
		}
		setFrameX(frameX+1);
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(50, 0, 0,40, 40);
			}
		});
		try {
			Thread.sleep(speed);
		}catch(Exception e) {e.printStackTrace();}
		moveProcess = false;
		return true;
	}

	boolean moveLeft() {
		if(moveProcess == true)return false;
		moveProcess = true;
		image = movingImage[0];
		if(!container.toLeft(this)) {
			repaint();
			moveProcess = false;
			return false;
		}
		setFrameX(frameX - 1);
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(50, 0, 0, 40, 40);
			}
		});
		try {
			Thread.sleep(speed);
		}catch(Exception e) {e.printStackTrace();}
		moveProcess = false;
		return true;
	}

	boolean moveUp() {
		if(moveProcess == true)return false;
		moveProcess = true;
		if(!container.toUp(this)) {
			moveProcess = false;
			return false;
		}
		setFrameY(frameY - 1);
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(50, 0, 0, 40, 40);
			}
		});
		try {
			Thread.sleep(speed);
		}catch(Exception e) {e.printStackTrace();}
		moveProcess = false;
		return true;
	}

	boolean moveDown() {
		if(moveProcess == true)return false;
		moveProcess = true;
		if(!container.toDown(this)) {
			moveProcess = false;
			return false;
		}
		setFrameY(frameY + 1);
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(50, 0, 0, 40, 40);
			}
		});
		try {
			Thread.sleep(speed);
		}catch(Exception e) {e.printStackTrace();}
		moveProcess = false;
		return true;
	}
}
