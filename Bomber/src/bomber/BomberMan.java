package bomber;

import javax.swing.SwingUtilities;

abstract class BomberMan extends Creature {
	private int bombPower = 1;
	private int bombNumber = 1;
	private int MAX_BOMB_NUMBER = 9;
	private int MAX_BOMB_POWER = 9;
	private Bomb newBomb;
	//	BomberMan(Field container){
	//		super(container);
	//	}

	BomberMan(Field container, int x, int y) {
		super(container, x, y);
	}

	void addBombNumber(int number) {
		bombNumber += number;
		bombNumber = bombNumber >= MAX_BOMB_NUMBER ? 9 : bombNumber;
	}

	void addBombPower(int number) {
		bombPower += number;
		bombPower = bombPower >= MAX_BOMB_NUMBER ? 9 : bombNumber;
	}

	@Override
	void fired() {
		System.out.println("BomberMan -> fired()");
	}

	@Override
	void kill(Creature source) {
		if (source instanceof Cheetah) {
			PopUpWindow window = new PopUpWindow(frameX, frameY-30, container, ((Enemy) source).getKillImage());
			SwingUtilities.invokeLater(new Thread() {
				public void run() {
					container.add(window);
					container.setComponentZOrder(window, 0);
				}
			});
			container.revalidate();
			container.repaint();
			new Thread(window).start();
			System.out.println("killed bomberman");
		}
	}

	@Override
	boolean stepOn(Tile source) {
		if (source instanceof Enemy) {
			System.out.println(0);
			kill((Creature) source);
			System.out.println(1);
			return true;
		}
		if (newBomb == null)
			return true;
		return false;
	}

	void putOnBomb() {
		//System.out.println("BomberMan -> putOnBomb()");
		if (newBomb != null)
			return;
		newBomb = new Bomb(container, frameX, frameY, bombPower);
		new Thread(newBomb).start();
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
