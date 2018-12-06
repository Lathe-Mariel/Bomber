package bomber;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.SwingUtilities;

abstract class BomberMan extends Creature {
	private int bombPower = 1;
	private int bombNumber = 1;
	private int MAX_BOMB_NUMBER = 8;
	private int MAX_BOMB_POWER = 8;
	private Bomb newBomb;
	private int dispatchedBombNumber;
	private boolean penetrater;
	private ArrayList<ItemTile> achievement;

	BomberMan(Field container, int x, int y) {
		super(container, x, y);
		achievement = new ArrayList<ItemTile>();
	}

	void addBombNumber(int number) {
		bombNumber += number;
		bombNumber = bombNumber >= MAX_BOMB_NUMBER ? MAX_BOMB_NUMBER : bombNumber;
	}

	void addBombPower(int number) {
		bombPower += number;
		bombPower = bombPower >= MAX_BOMB_POWER ? MAX_BOMB_POWER : bombPower;
		System.out.println("bombpower: " + bombPower);
	}

	@Override
	void fired() {
		new Thread() {
			public void run() {
				kill(null);
			}
		}.start();
		System.out.println("BomberMan -> fired()");
	}

	void addItem(ItemTile item) {
		achievement.add(item);
	}

	@Override
	void kill(Creature source) {
		super.kill(source);
		if (source instanceof Cheetah) {
			PopUpWindow window = new PopUpWindow(frameX, frameY, container, ((Enemy) source).getKillImage());
			SwingUtilities.invokeLater(new Thread() {
				public void run() {
					container.add(window);
					container.setComponentZOrder(window, 0);
				}
			});
			container.revalidate();
			container.repaint();
			new Thread(window).start();
		}
		container.death(this);

		new Thread() {
			public void run() {
				throwItems();
			}
		}.start();
	}

	private void throwItems() {
		ItemTile it;
		System.out.println("Items number: " + achievement.size());
		for(Iterator<ItemTile> i = achievement.iterator(); i.hasNext(); ) {
			it = i.next();
			do {
				int depX = (int) (Math.random() * 18)+1;
				int depY = (int) (Math.random() * 15)+1;
				it.setFrameX(depX);
				it.setFrameY(depY);
			}while(!container.addTile(it));
		}
	}

	@Override
	boolean stepOn(Creature source) {
		if (source instanceof Enemy) {
			new Thread() {
				public void run() {
					kill(source);
				}
			}.start();
			return true;
		}
		if (newBomb == null)
			return true;
		return false;
	}

	private synchronized void decreaseBombNumber() {
		dispatchedBombNumber++;
	}

	synchronized void increaseBombNumber() {
		newBomb = null;
		dispatchedBombNumber--;
	}
/**
 * after doing this method, bomb has power of penetration.
 */
	void setPenetration() {
		this.penetrater = true;
	}

	/**
	 * This is the process of put on a new bomb on the field.
	 * After created new bomb, it woun't put into the container.
	 * After BomberMan moves to another place where is next to the spot where BomberMan created the bomb.
	 */
	synchronized void putOnBomb() {
		if (newBomb != null)
			return;
		if (dispatchedBombNumber >= bombNumber)
			return;
		newBomb = new Bomb(container, frameX, frameY, bombPower, this, penetrater);
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.add(newBomb);// only appearance
				container.repaint(x, y, 40, 40);
			}
		});
		new Thread(newBomb).start();
		decreaseBombNumber();
	}

	@Override
	synchronized boolean moveLeft() {
		if (!super.moveLeft())
			return false;
		if (newBomb == null)
			return true;
		container.addTile(newBomb);
		newBomb = null;
		return true;
	}

	@Override
	synchronized boolean moveDown() {
		if (!super.moveDown())
			return false;
		if (newBomb == null)
			return true;
		container.addTile(newBomb);
		newBomb = null;
		return true;
	}

	@Override
	synchronized boolean moveRight() {
		if (!super.moveRight())
			return false;
		if (newBomb == null)
			return true;
		container.addTile(newBomb);
		newBomb = null;
		return true;
	}

	@Override
	synchronized boolean moveUp() {
		if (!super.moveUp())
			return false;
		if (newBomb == null)
			return true;
		container.addTile(newBomb);
		newBomb = null;
		return true;
	}
}
