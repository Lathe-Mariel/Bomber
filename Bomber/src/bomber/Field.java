package bomber;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Field extends JPanel {
	private Tile tileArray[][];
	private KeyEventHandler keyListener;
	boolean tileArrayLocker;

	public Dimension getPreferredSize() {
		return new Dimension(840, 680);
	}

	void setKeyListener(KeyEventHandler keyListener) {
		this.keyListener = keyListener;
	}

	Field() {
		setLayout(null);
		tileArray = new Tile[21][17];
		for (int i = 2; i < tileArray.length - 2; i++, i++) {
			for (int j = 2; j < tileArray[i].length - 2; j++, j++) {
				add(new Obstacle(this, i, j));
			}
		}
		for (int i = 0; i < tileArray.length; i++) {
			add(new Obstacle(this, i, 0));
			add(new Obstacle(this, i, tileArray[0].length - 1));
		}
		for (int i = 1; i < tileArray[0].length - 1; i++) {
			add(new Obstacle(this, 0, i));
			add(new Obstacle(this, tileArray.length - 1, i));
		}
	}

	boolean add(Tile newTile) {
		super.add(newTile);
		if (tileArray[newTile.frameX][newTile.frameY] == null) {
			tileArray[newTile.frameX][newTile.frameY] = newTile;
			return true;
		}
		return false;

	}

	void disappear(BrakableBlock tile) {
		tileArray[tile.frameX][tile.frameY] = null;
		remove(tile);
	}

	synchronized Tile[][] getTileArray() {
		return tileArray;
	}

	synchronized boolean toRight(Creature source) {
		int x = source.frameX + 1;
		int y = source.frameY;
		if (tileArray[x][y] == null || tileArray[x][y].stepOn(source)) {
			tileArray[x - 1][y] = null;
			tileArray[x][y] = source;
			return true;
		} else {
			return false;
		}
	}
	
	synchronized boolean toLeft(Creature source) {
		int x = source.frameX - 1;
		int y = source.frameY;
		if (tileArray[x][y] == null || tileArray[x][y].stepOn(source)) {
			tileArray[x + 1][y] = null;
			tileArray[x][y] = source;
			return true;
		} else {
			return false;
		}
	}

	synchronized boolean toUp(Creature source) {
		int x = source.frameX;
		int y = source.frameY -1;
		if (tileArray[x][y] == null || tileArray[x][y].stepOn(source)) {
			tileArray[x][y+1] = null;
			tileArray[x][y] = source;
			return true;
		} else {
			return false;
		}
	}
	
	synchronized boolean toDown(Creature source) {
		int x = source.frameX;
		int y = source.frameY +1;
		if (tileArray[x][y] == null || tileArray[x][y].stepOn(source)) {
			tileArray[x][y-1] = null;
			tileArray[x][y] = source;
			return true;
		} else {
			return false;
		}
	}
	
	void init1PC() {
		PC player0 = new PC(this, 1, 1);
		keyListener.addPlayer(player0);
		add(player0);
		Cat enemy0 = new Cat(this, 19,15);
		add(enemy0);
		new Thread(enemy0).start();
	}
}
