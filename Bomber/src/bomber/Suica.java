package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Suica extends Enemy {
	static private Image []suicaImageArray;

	static {
		suicaImageArray = new Image[6];
		try {
			suicaImageArray[0] = ImageIO.read(new File("suica.png"));
			suicaImageArray[1] = ImageIO.read(new File("suica.png"));
			//suicaImageArray[2] = ImageIO.read(new File("suica-dead.png"));
			suicaImageArray[3] = ImageIO.read(new File("suica-dead.png"));
//			imageArray = new Image[7];
//			imageArray[0] = ImageIO.read(new File("rock.png"));
//			imageArray[1] = ImageIO.read(new File("brick.png"));
//			imageArray[2] = ImageIO.read(new File("kusa.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	Suica(Field container, int x, int y) {
		super(container, x, y);
		// TODO 自動生成されたコンストラクター・スタブ
		alive = true;
		speed = 200;

		movingImage[0] = suicaImageArray[0];
		movingImage[1] = suicaImageArray[1];
		image = movingImage[0];
		deadImage = suicaImageArray[3];
	}

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		int direction, succession;

		while (alive) {
			direction = (int) (Math.random() * 4);
			succession = (int) (Math.random() * 3);

			//System.out.println("Cat movement->>    direction: " + direction + "    succession: " + succession);
			do {
				switch (direction) {
				case 0:
					if(!alive)break;
					moveLeft();
					break;
				case 1:
					if(!alive)break;
					moveDown();
					break;
				case 2:
					if(!alive)break;
					moveRight();
					break;
				case 3:
					if(!alive)break;
					moveUp();
					break;
				default:
					break;
				}
				try {
					Thread.sleep(speed);
				} catch (Exception e) {
					System.out.println("Suica -> run()");
					e.printStackTrace();
				}
			} while (--succession > 0);
		}
	}

	@Override
	Image getKillImage() {
		// TODO 自動生成されたメソッド・スタブ
		return suicaImageArray[2];
	}

	@Override
	void contact() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
