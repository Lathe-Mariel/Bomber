package bomber;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

public abstract class Tile extends Component {
	Image image;
	int frameX, frameY;
	Field container;

	Tile(Field container){
		this.container = container;
	}

	abstract void fired();

	abstract boolean stepOn(Tile source);

	public void paint(Graphics g) {
		g.drawImage(image, 0,0, this);
	}
}
