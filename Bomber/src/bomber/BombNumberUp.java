package bomber;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BombNumberUp extends ItemTile {

	static {
		try {
			staticImage = ImageIO.read(new File("bombup.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}}

	BombNumberUp() {
		super();
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
			return true;
		}
		Tile thisTile = this;
		new Thread() {
			public void run() {
		container.removeTile(thisTile);
		}}.start();

		((BomberMan) source).addBombNumber(1);
		return true;
	}

}
