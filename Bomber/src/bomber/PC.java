package bomber;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PC extends BomberMan {

	PC(Field container) {
		this(container, 1, 1);
	}

	PC(Field container, int x, int y) {
		super(container, x, y);
		try {
			image = ImageIO.read(new File("usa.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	void fired() {
		//process die
		//container.disappear(this)
	}


	@Override
	boolean stepOn(Tile source) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	void kill() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	void contact() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
