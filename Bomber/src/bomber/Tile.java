package bomber;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;

public class Tile extends Component {
	Image image;
	int frameX, frameY;
	boolean steppable;
	Field container;
	
	Tile(Field container){
		steppable = false;
		this.container = container;
	}
	
	void fired() {
		
	}
	
	public void paint(Graphics g) {
		g.drawImage(image, 0,0, this);
	}
}
