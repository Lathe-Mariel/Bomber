package bomber;

import java.awt.Image;

abstract public class ItemTile extends Tile {
	static Image staticImage;

	ItemTile(){
super();
	}

	void init(Field container, int x, int y){
		this.container = container;
		frameX = x;
		frameY = y;
		this.x = frameX*40;
		this.y = frameY*40;
		setBounds(this.x, this.y, 40,40);
	}
boolean stepOn(Creature source) {
	return true;
}

}
