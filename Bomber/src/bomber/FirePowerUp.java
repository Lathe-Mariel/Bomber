package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FirePowerUp extends ItemTile {
	private int power;
	private static Image[] bombImage;

	static {
		try {
			bombImage = new Image[3];
			bombImage[0] = ImageIO.read(new File("ninjin1.png"));
			bombImage[2] = ImageIO.read(new File("ninjin3.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	FirePowerUp(int power) {
		super();
		this.power = power;
		image = bombImage[power - 1];
		// TODO 自動生成されたコンストラクター・スタブ
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

		((BomberMan) source).addBombPower(power);
		((BomberMan) source).addItem(this);
		return true;
	}

}
