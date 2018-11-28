package bomber;


public class BrakableBlock extends Tile {
	ItemTile item;
	BrakableBlock(Field container){
		super(container);
		image = BomberUtility.generalImageArray[1];//brick
	}
	void setItem(ItemTile item) {
		this.item = item;
	}

	void fired() {

	}
}
