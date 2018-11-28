package bomber;


abstract public class ItemTile extends Tile {

	ItemTile(Field container) {
		super(container);
		steppable = true;
	}

	void pickUpItem(Player player) {

	}

}
