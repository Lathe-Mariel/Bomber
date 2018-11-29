package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cheetah extends Cat {
	static private Image[] cheetahImageArray;
	
	static {
		try {
			System.out.println("Cheetah image reading failed");
			cheetahImageArray = new Image[2];
			cheetahImageArray[0] = ImageIO.read(new File("cheetah1l.png"));
			cheetahImageArray[1] = ImageIO.read(new File("cheetah1r.png"));
			//			cheetahImageArray[2] = ImageIO.read(new File("cheetah-attacked.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	Cheetah(Field container, int x, int y) {
		super(container, x, y);
		speed = 400;

		// TODO 自動生成されたコンストラクター・スタブ
		image = cheetahImageArray[0];
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
				image = cheetahImageArray[0];
				moveLeft();
			} else if (frameX < target.frameX) {
				image = cheetahImageArray[1];
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
