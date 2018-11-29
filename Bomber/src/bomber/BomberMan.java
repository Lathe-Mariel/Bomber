package bomber;

abstract class BomberMan extends Creature {
	int bombPower = 1;
	private Bomb newBomb;
	//	BomberMan(Field container){
	//		super(container);
	//	}

	BomberMan(Field container, int x, int y) {
		super(container, x, y);
	}

	@Override
	boolean stepOn(Tile source) {
		if (source instanceof Enemy) {
			kill();
		}
		return true;
	}

	void putOnBomb() {
		System.out.println("BomberMan -> putOnBomb()");
		//generation of new Bombs
		newBomb = new Bomb(container, frameX, frameY, bombPower);
		container.add(newBomb);
		new Thread(newBomb).start();
	}
	
	@Override
	boolean moveLeft() {
		if(!super.moveLeft())return false;
		Tile[][] tile = container.getTileArray();
		tile[frameX+1][frameY] = newBomb;
		newBomb = null;
		return true;
	}
	
	@Override
	boolean moveDown() {
		if(!super.moveDown())return false;
		Tile[][] tile = container.getTileArray();
		tile[frameX][frameY-1] = newBomb;
		newBomb = null;
		return true;
	}
	
	@Override
	boolean moveRight() {
		if(!super.moveRight())return false;
		Tile[][] tile = container.getTileArray();
		tile[frameX-1][frameY] = newBomb;
		newBomb = null;
		return true;
	}
	
	@Override
	boolean moveUp() {
		if(!super.moveUp())return false;
		Tile[][] tile = container.getTileArray();
		tile[frameX][frameY+1] = newBomb;
		newBomb = null;
		return true;
	}
}
