package bomber;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

public abstract class Tile extends Component {
	//Image imageArray[];
	Image image;
	int frameX, frameY;
	/**
	 * Cordinate on Field which is described by dot.
	 */
	int x, y;
	Field container;

	Tile(Field container) {
		this.container = container;
		setSize(40, 40);
	}

	Tile(Field container, int x, int y) {
		this(container);
		this.frameX = x;
		this.x = x * 40;
		this.y = y * 40;
		this.frameY = y;
		setBounds(this.x, this.y, 40, 40);
	}

	void setFrameX(int x) {
		frameX = x;
		this.x = frameX * 40;
		setLocation(this.x, y);
	}

	void setFrameY(int y) {
		frameY = y;
		this.y = frameY * 40;
		setLocation(x, this.y);
	}

	abstract void fired();
/**
 * This method has two functions.
 * One is return value which refer to steppable.
 * And the other is function which is called by Creature who can step on this tile.
 * @param source	Source calls this method.
 * If some functios is needed to triger by source who steps on this tile, you can describe the function in this method.
 * @return	This boolean value stands for steppable or not.
 */
	abstract boolean stepOn(Tile source);

	public void paint(Graphics g) {
		g.drawImage(image, 0, 0, 40, 40, this);
	}
}
