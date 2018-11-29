package bomber;


public class BrakableBlock extends Tile {
	ItemTile item;
	BrakableBlock(Field container){
		super(container);
		image = BomberUtility.generalImageArray[3];//kusa
	}
	void setItem(ItemTile item) {
		this.item = item;
	}

	void fired() {

	}
	boolean stepOn(Tile source) {
		return false;
	}
}
