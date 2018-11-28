package bomber;


abstract public class ItemTile extends Tile {

	ItemTile(Field container) {
		super(container);
	}

	void pickUpItem(PC player) {

	}

	boolean stepOn(Tile source) {
		pickUpItem((PC)source);
		return true;
	}

}
