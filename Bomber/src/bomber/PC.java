package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PC extends BomberMan {

	//	PC(Field container) {
	//		this(container, 1, 1);
	//	}

	PC(Field container, int x, int y) {
		super(container, x, y);
		try {
			image = ImageIO.read(new File("usa.png"));

			imageArray = new Image[7];
			imageArray[0] = ImageIO.read(new File("dead-usa.png"));
			//			imageArray[1] = ImageIO.read(new File("brick.png"));
			//			imageArray[2] = ImageIO.read(new File("kusa.png"));
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
	void kill() {
		// TODO 自動生成されたメソッド・スタブ
		image = imageArray[0];
		container.repaint(50, 0, 0, 40, 40);
	}

	@Override
	void contact() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
