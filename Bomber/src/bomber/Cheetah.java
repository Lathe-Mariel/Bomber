package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cheetah extends Cat {
	static private Image[] cheetahImageArray;
	private BomberMan target;

	static {
		try {
			cheetahImageArray = new Image[6];
			cheetahImageArray[0] = ImageIO.read(new File("cheetah1l.png"));
			cheetahImageArray[1] = ImageIO.read(new File("cheetah1r.png"));
			cheetahImageArray[2] = ImageIO.read(new File("cheetah-win.png"));
			cheetahImageArray[3] = ImageIO.read(new File("cheetah-dead.png"));
			cheetahImageArray[4] = ImageIO.read(new File("cheetah1l.png"));
			cheetahImageArray[5] = ImageIO.read(new File("cheetah1r.png"));
			//			cheetahImageArray[2] = ImageIO.read(new File("cheetah-attacked.png"));
		} catch (IOException e) {
			System.out.println("Cheetah image reading failed");
			e.printStackTrace();
		}
	}

	Cheetah(Field container, int x, int y) {
		super(container, x, y);
		speed = 300;
		movingImage[0] = cheetahImageArray[0];
		movingImage[1] = cheetahImageArray[1];
		movingImage[4] = cheetahImageArray[4];
		movingImage[5] = cheetahImageArray[5];
		image = movingImage[0];
		deadImage = cheetahImageArray[3];
	}
@Override
void kill(Creature creature) {
	target = null;
	super.kill(null);
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
		int distance = 0;
		boolean lockon = false;
		target = null;

		while (alive) {
			if (container.getBomberManNumber() > 0) {
				target = container.getBomberMan((int) (Math.random() * container.getBomberManNumber()));
			} else {
				target = null;
			}
			while (target != null) {
				if (lockon) {
					if (distance == 0) {
						lockon = false;
						speed = 400;
					} else if (distance > 0) {
						distance--;
						if(!alive)break;
						moveDown();
					} else if (distance < 0) {
						distance++;
						if(!alive)break;
						moveUp();
					}
				} else {

					if (frameX > target.frameX) {
						moveLeft();
					} else if (frameX < target.frameX) {
						moveRight();
					} else {
						distance = target.frameY - frameY;
						lockon = true;
						speed = 30;
					}
				}

				try {
					Thread.sleep(speed);
				} catch (Exception e) {
					System.out.println("Cat -> run()");
					e.printStackTrace();
				}
			}
		}
	}
}
