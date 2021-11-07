package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpeedUp extends ItemTile {
	private static Image staticImage;

	static {
		try {
			staticImage = ImageIO.read(new File("speed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	SpeedUp(){
		image = staticImage;
	}



	@Override
	boolean stepOn(Creature source) {
		// TODO 自動生成されたメソッド・スタブ
		if(source instanceof Enemy) {
			return false;
		}
		Tile thisTile = this;
		new Thread() {
			public void run() {
		container.removeTile(thisTile);
		}}.start();

		((BomberMan) source).speedUp();
		((BomberMan) source).addItem(this);
		return true;
	}
	@Override
	void fired() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
