package bomber;

import javax.swing.SwingUtilities;

abstract public class Creature extends Tile {
	boolean moveProcess;
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
	}

	/**
	 * When this object contacts enemy, this method will be called by enemy(when enemy steps on) or oneself(when PC steps on).
	 */
	abstract void kill();

	abstract void contact();

	void moveRight() {
		if(moveProcess == true)return;
		moveProcess = true;
		if(!container.toRight(this)) {
			moveProcess = false;
			return;
		}
		setFrameX(frameX+1);
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(50, 0, 0, 40, 40);
			}
		});
		try {
			Thread.sleep(speed);
		}catch(Exception e) {e.printStackTrace();}
		moveProcess = false;
	}

	void moveLeft() {
		if(moveProcess == true)return;
		moveProcess = true;
		if(!container.toLeft(this)) {
			moveProcess = false;
			return;
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
	}

	void moveUp() {
		if(moveProcess == true)return;
		moveProcess = true;
		if(!container.toUp(this)) {
			moveProcess = false;
			return;
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
	}

	void moveDown() {
		if(moveProcess == true)return;
		moveProcess = true;
		if(!container.toDown(this)) {
			moveProcess = false;
			return;
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
	}
}
