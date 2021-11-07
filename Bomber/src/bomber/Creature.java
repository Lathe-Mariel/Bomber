package bomber;

import java.awt.Component;
import java.awt.Image;

import javax.swing.SwingUtilities;

abstract public class Creature extends Tile {
	boolean moveProcess;
	Image movingImage[];
	Image deadImage;
	boolean alive;

	/**
	 * Moting interval time.
	 */
	int speed;

	private Creature(Field container) {
		super(container);
	}

	private Creature() {
	}

	Creature(Field container, int x, int y) {
		super(container, x, y);
		speed = 200;
		moveProcess = false;
		movingImage = new Image[6];
		alive = true;
		deadImage = image;
	}

	/**
	 * When this object contacts enemy, this method will be called by enemy(when enemy steps on) or oneself(when PC steps on enemy).
	 */
	synchronized void kill(Creature source) {
		alive = false;
		container.death(this);
		image = deadImage;
		Component c = this;
		new Thread() {
			public void run() {
				SwingUtilities.invokeLater(new Thread() {
					public void run() {
						container.add(c);
					}
				});
				repaint();
				try {
					Thread.sleep(1800);
				}catch(Exception e ) {e.printStackTrace();}
				SwingUtilities.invokeLater(new Thread() {
					public void run() {
						container.remove(c);
					}
				});
				container.repaint(300,x,y,40,40);
			}
		}.start();
		
		System.out.println(this.getClass() + "  killed");
	}

	abstract void contact();

	synchronized boolean moveRight() {
		image = movingImage[1];
		if (!container.toRight(this)) {
			repaint();
			return false;
		}
		image = movingImage[5];
		setLocation(x-20,y);
//		SwingUtilities.invokeLater(new Thread() {
//			public void run() {
//				setLocation(x-20,y);
//				//container.repaint(100, 0, 0, 40, 40);
//			}
//		});
		try {Thread.sleep(speed);
		}catch(Exception e) {e.printStackTrace();}
		image = movingImage[1];
		setLocation(x,y);
		try {
			Thread.sleep(speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	synchronized boolean moveLeft() {
		image = movingImage[0];
		if (!container.toLeft(this)) {
			repaint();
			return false;
		}
		image = movingImage[4];
		setLocation(x+20,y);
		try {Thread.sleep(speed);
		}catch(Exception e) {e.printStackTrace();}
		image = movingImage[0];
		setLocation(x,y);
//		SwingUtilities.invokeLater(new Thread() {
//			public void run() {
//				container.repaint(100, 0, 0, 40, 40);
//			}
//		});
		try {
			Thread.sleep(speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	synchronized boolean moveUp() {
		if (!container.toUp(this)) {
			return false;
		}
		setLocation(x,y+20);
		try {Thread.sleep(speed);
		}catch(Exception e) {e.printStackTrace();}
		setLocation(x,y);
//		SwingUtilities.invokeLater(new Thread() {
//			public void run() {
//				container.repaint(100, 0, 0, 40, 40);
//			}
//		});
		try {
			Thread.sleep(speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	synchronized boolean moveDown() {
		if (!container.toDown(this)) {
			return false;
		}
		setLocation(x,y-20);
		try {Thread.sleep(speed);
		}catch(Exception e) {e.printStackTrace();}
		setLocation(x,y);
//		SwingUtilities.invokeLater(new Thread() {
//			public void run() {
//				container.repaint(100, 0, 0, 40, 40);
//			}
//		});
		try {
			Thread.sleep(speed);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
}
