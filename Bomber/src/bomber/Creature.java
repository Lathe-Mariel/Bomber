package bomber;

import javax.swing.SwingUtilities;

abstract public class Creature extends Tile {

	Creature(Field container) {
		super(container);
	}

	Creature(Field container, int x, int y) {
		super(container, x, y);
	}

	/**
	 * When this object contacts enemy, this method will be called by enemy(when enemy steps on) or oneself(when PC steps on).
	 */
	abstract void kill();

	abstract void contact();

	void moveRight() {
		Tile[][] tiles = container.getTileArray();
		if (tiles[frameX + 1][frameY] != null && !tiles[frameX +1][frameY].stepOn(this)) {
			return;
		}
		tiles[frameX][frameY] = null;
		setFrameX(frameX + 1);
		tiles[frameX][frameY] = this;
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(50, 0, 0, 40, 40);
			}
		});
	}

	void moveLeft() {
		Tile[][] tiles = container.getTileArray();
		if (tiles[frameX - 1][frameY] != null && !tiles[frameX -1][frameY].stepOn(this)) {
			return;
		}
		tiles[frameX][frameY] = null;
		setFrameX(frameX - 1);
		tiles[frameX][frameY] = this;
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(50, 0, 0, 40, 40);
			}
		});
	}

	void moveUp() {
		Tile[][] tiles = container.getTileArray();
		if (tiles[frameX][frameY - 1] != null && !tiles[frameX][frameY-1].stepOn(this)) {
			return;
		}
		tiles[frameX][frameY] = null;
		setFrameY(frameY - 1);
		tiles[frameX][frameY] = this;
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(50, 0, 0, 40, 40);
			}
		});
	}

	void moveDown() {
		Tile[][] tiles = container.getTileArray();
		if (tiles[frameX][frameY + 1] != null && !tiles[frameX][frameY+1].stepOn(this)) {
			return;
		}
		tiles[frameX][frameY] = null;
		setFrameY(frameY + 1);
		tiles[frameX][frameY] = this;
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.repaint(50, 0, 0, 40, 40);
			}
		});
	}
}
