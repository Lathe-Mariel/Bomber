package bomber;

import javax.swing.JPanel;

public class Field extends JPanel {
	Tile tileArray[][];

	Field(){
		tileArray = new Tile[20][16];
	}

	void disappear(BrakableBlock tile) {
		tileArray[tile.frameX][tile.frameY] = null;
		remove(tile);
	}
}
