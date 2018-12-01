package bomber;

import javax.swing.SwingUtilities;

abstract class BomberMan extends Creature {
	private int bombPower = 1;
	private int bombNumber = 1;
	private int MAX_BOMB_NUMBER = 8;
	private int MAX_BOMB_POWER = 8;
	private Bomb newBomb;
	private int dispatchedBombNumber;
	private boolean penetrater;



	BomberMan(Field container, int x, int y) {
		super(container, x, y);
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
		System.out.println("BomberMan -> fired()");
	}

	@Override
	void kill(Creature source) {
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
	}

	@Override
	boolean stepOn(Creature source) {
		if (source instanceof Enemy) {
			kill((Creature) source);
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
		dispatchedBombNumber--;
	}

	void putOnBomb() {
		//System.out.println("BomberMan -> putOnBomb()");
		if (newBomb != null)
			return;
		if (dispatchedBombNumber >= bombNumber)
			return;
		newBomb = new Bomb(container, frameX, frameY, bombPower, this, penetrater);
		new Thread(newBomb).start();
		decreaseBombNumber();
		SwingUtilities.invokeLater(new Thread() {
			public void run() {
				container.add(newBomb);
			}
		});
	}

	@Override
	boolean moveLeft() {
		if (!super.moveLeft())
			return false;
		//		Tile[][] tile = container.getTileArray();
		//		tile[frameX+1][frameY] = newBomb;
		if (newBomb == null)
			return true;
		container.addTile(newBomb);
		newBomb = null;
		return true;
	}

	@Override
	boolean moveDown() {
		if (!super.moveDown())
			return false;
		//		Tile[][] tile = container.getTileArray();
		//		tile[frameX][frameY-1] = newBomb;
		if (newBomb == null)
			return true;
		container.addTile(newBomb);
		newBomb = null;
		return true;
	}

	@Override
	boolean moveRight() {
		if (!super.moveRight())
			return false;
		//		Tile[][] tile = container.getTileArray();
		//		tile[frameX-1][frameY] = newBomb;
		if (newBomb == null)
			return true;
		container.addTile(newBomb);
		newBomb = null;
		return true;
	}

	@Override
	boolean moveUp() {
		if (!super.moveUp())
			return false;
		//		Tile[][] tile = container.getTileArray();
		//		tile[frameX][frameY+1] = newBomb;
		if (newBomb == null)
			return true;
		container.addTile(newBomb);
		newBomb = null;
		return true;
	}
}
