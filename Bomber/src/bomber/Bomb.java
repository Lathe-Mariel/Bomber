package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;

public class Bomb extends Tile implements Runnable {
	BomberMan owner;
	int power;
	private boolean isFired;
	Bomb thisObject;

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
	//	}
	Bomb(Field container, int x, int y, int power, BomberMan owner, boolean penetrate) {
		super(container, x, y);
		this.owner = owner;
		this.power = power;
		this.penetrate = penetrate;
		image = bombImageArray[1];
	}

	synchronized void explode() {
		if (isFired)
			return;
		isFired = true;
		if (owner.getX() != getX() || owner.getY() != getY()) {
			container.removeTile(this);
		} else {
			thisObject = this;
		}

		Explosion exp = new Explosion(power, frameX, frameY, container, penetrate);
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.add(exp);
				container.setComponentZOrder(exp, 0);
				if(thisObject != null)container.remove(thisObject);
			}
		});
		//container.revalidate();
		new Thread(exp).start();
		container.repaint(30);
		owner.increaseBombNumber();
	}

	@Override
	void fired() {
		if (isFired)
			return;
		new Thread() {
			public void run() {
				explode();
			}
		}.start();

		//process destory(killing thread is essential, because this thread is still alive)
	}

	@Override
	boolean stepOn(Creature source) {
		return false;
	}

	@Override
	public void run() {
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
