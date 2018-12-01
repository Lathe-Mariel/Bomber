package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cat extends Enemy {
	static private Image []catImageArray;

	static {
		catImageArray = new Image[5];
		try {
			catImageArray[0] = ImageIO.read(new File("neko-l.png"));
			catImageArray[1] = ImageIO.read(new File("neko-r.png"));
//			imageArray = new Image[7];
//			imageArray[0] = ImageIO.read(new File("rock.png"));
//			imageArray[1] = ImageIO.read(new File("brick.png"));
//			imageArray[2] = ImageIO.read(new File("kusa.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	Cat(Field container){
	//		this(container, 1,1);
	//	}

	Cat(Field container, int x, int y) {
		super(container, x, y);
		alive = true;

		movingImage[0] = catImageArray[0];
		movingImage[1] = catImageArray[1];
		image = movingImage[0];
	}

	@Override
	void fired() {
		// TODO 自動生成されたメソッド・スタブ
		//System.out.println("Cat -> fired()");
	}

	@Override
	Image getKillImage() {
		return catImageArray[2];
	}
	@Override
	void kill(Creature source) {
		// TODO 自動生成されたメソッド・スタブ
		//process of destorying myown
		alive = false;
	}

	@Override
	void contact() {
		// TODO 自動生成されたメソッド・スタブ
	}

	boolean alive;

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
					moveLeft();
					break;
				case 1:
					moveDown();
					break;
				case 2:
					moveRight();
					break;
				case 3:
					moveUp();
					break;
				default:
					break;
				}
				try {
					Thread.sleep(speed);
				} catch (Exception e) {
					System.out.println("Cat -> run()");
					e.printStackTrace();
				}
			} while (--succession > 0);
		}
	}
}
