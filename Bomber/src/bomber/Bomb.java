package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

public class Bomb extends Tile implements Runnable {
	int power;
	/**
	 * bomb may have two state, one is that can't penetrate bricks, and the other is that can penetrate bricks.
	 */
	boolean penetrate = false;
	static private Image[] bombImageArray;

	static {
		try {
			bombImageArray = new Image[2];
			bombImageArray[1] = ImageIO.read(new File("bomb1.png"));
			bombImageArray[0] = ImageIO.read(new File("bomb0.png"));
			//			imageArray[2] = ImageIO.read(new File("cheetah-attacked.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	Bomb(Field container) {
	//		super(container);
	//		power = 1;
	//		// TODO 自動生成されたコンストラクター・スタブ
	//	}
	Bomb(Field container, int x, int y, int power) {
		super(container, x, y);
		this.power = power;
		// TODO 自動生成されたコンストラクター・スタブ
		image = bombImageArray[1];
	}

	void explode() {
		// exploding process
		System.out.println("bomb explosion");
		
		Explosion exp = new Explosion(power, frameX, frameY, container);
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.add(exp);
				container.setComponentZOrder(exp, 0);
			}
		});
		container.revalidate();
		container.repaint();
		new Thread(exp).start();
		container.removeTile(this);
	}

	@Override
	void fired() {
		explode();
		// TODO 自動生成されたメソッド・スタブ

		//process destory(killing thread is essential, because this thread is still alive)
	}

	@Override
	boolean stepOn(Tile source) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		int count = 0;
		do {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				System.out.println("Bomb -> run()");
				e.printStackTrace();
			}
			image = bombImageArray[count % 2];
			repaint();
		} while (count++ < 4);
		explode();
	}
}
