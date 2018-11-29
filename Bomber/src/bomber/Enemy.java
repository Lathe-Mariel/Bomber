package bomber;

abstract public class Enemy extends Creature implements Runnable {
//	Enemy(Field container){
//		super(container);
//	}

	Enemy(Field container, int x, int y){
		super(container, x, y);
	}

	@Override
	boolean stepOn(Tile source) {
		if (source instanceof BomberMan) {
			((Creature) source).kill();
		}
		return true;
	}

}
