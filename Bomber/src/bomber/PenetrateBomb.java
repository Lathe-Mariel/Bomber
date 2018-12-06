package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PenetrateBomb extends ItemTile {
	private static Image staticImage;

	static {
		try {
			staticImage = ImageIO.read(new File("penetratebomb.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	PenetrateBomb(){
		image = staticImage;
	}

	@Override
	void fired() {
		// TODO 自動生成されたメソッド・スタブ

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

		((BomberMan) source).setPenetration();
		((BomberMan) source).addItem(this);
		System.out.println("setPenetration()");
		return true;
	}
}
