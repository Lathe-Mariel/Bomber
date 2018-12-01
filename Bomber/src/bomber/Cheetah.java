package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cheetah extends Cat {
	static private Image[] cheetahImageArray;

	static {
		try {
			cheetahImageArray = new Image[3];
			cheetahImageArray[0] = ImageIO.read(new File("cheetah1l.png"));
			cheetahImageArray[1] = ImageIO.read(new File("cheetah1r.png"));
			cheetahImageArray[2] = ImageIO.read(new File("cheetah-win.png"));
			//			cheetahImageArray[2] = ImageIO.read(new File("cheetah-attacked.png"));
		} catch (IOException e) {
			System.out.println("Cheetah image reading failed");
			e.printStackTrace();
		}
	}

	Cheetah(Field container, int x, int y) {
		super(container, x, y);
		speed = 400;

		// TODO 自動生成されたコンストラクター・スタブ
		movingImage[0] = cheetahImageArray[0];
		movingImage[1] = cheetahImageArray[1];
		image = movingImage[0];
	}

	@Override
	Image getKillImage() {
		return cheetahImageArray[2];
	}
	@Override
	boolean stepOn(Creature source) {
		boolean result = super.stepOn(source);

		return result;
	}
	@Override
	public void run() {
		int distance =0;
		boolean lockon = false;

		while (alive) {
			if(lockon) {
				if(distance == 0) {
					lockon = false;
					speed = 400;
				}else if(distance > 0) {
					distance--;
					moveDown();
				}else if(distance < 0){
					distance++;
					moveUp();
				}
			}else {
			BomberMan target = container.getBomberMan((int) (Math.random() * container.getBomberManNumber()));
			if (frameX > target.frameX) {
				moveLeft();
			} else if (frameX < target.frameX) {
				moveRight();
			} else {
				distance = target.frameY - frameY;
				lockon = true;
				speed = 30;
			}}

			try {
				Thread.sleep(speed);
			} catch (Exception e) {
				System.out.println("Cat -> run()");
				e.printStackTrace();
			}
		}
	}
}
