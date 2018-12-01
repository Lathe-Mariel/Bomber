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
		item.init(container, frameX, frameY);
	}

	void fired() {
		if (item == null) {
			container.removeTile(this);
		} else {
			container.removeTile(this);
			container.addTile(item);
			//add item on container, and dissapear myself.
		}
	}

	boolean stepOn(Creature source) {
		return false;
	}

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, 40, 40, this);
	}
}
