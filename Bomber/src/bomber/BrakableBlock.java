package bomber;

import java.awt.Graphics;

public class BrakableBlock extends Tile {
	ItemTile item;

	BrakableBlock(Field container, int depX, int depY) {
		super(container, depX, depY);

		image = BomberUtility.generalImageArray[2];//kusa
	}

	void setItem(ItemTile item) {
		this.item = item;
	}

	void fired() {
		if (item == null) {
			container.removeTile(this);
		} else {
			//add item on container, and dissapear myself.
		}
	}

	boolean stepOn(Tile source) {
		return false;
	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, 40, 40, this);
	}
}
