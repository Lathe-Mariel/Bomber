package bomber;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Field extends JPanel {
	private Tile tileArray[][];
	private KeyEventHandler keyListener;

public Dimension getPreferredSize() {
	return new Dimension(840,680);
}

void setKeyListener(KeyEventHandler keyListener) {
	this.keyListener = keyListener;
}

	Field() {
		setLayout(null);
		tileArray = new Tile[21][17];
		for (int i = 2; i < tileArray.length-2; i++, i++) {
			for (int j = 2; j < tileArray[i].length-2; j++, j++) {
				add(new Obstacle(this,i,j));
			}
		}
		for(int i = 0; i < tileArray.length; i++) {
			add(new Obstacle(this,i,0));
			add(new Obstacle(this,i,tileArray[0].length-1));
		}
		for(int i = 0; i < tileArray[0].length; i++) {
			add(new Obstacle(this,0,i));
			add(new Obstacle(this,tileArray.length-1,i));
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

	void init1PC() {
		PC player0= new PC(this, 1,1);
		keyListener.addPlayer(player0);
		add(player0);
	}
}
