package bomber;

import java.awt.Image;

abstract public class Enemy extends Creature implements Runnable {
//	Enemy(Field container){
//		super(container);
//	}

	Enemy(Field container, int x, int y){
		super(container, x, y);
	}

	@Override
	boolean stepOn(Creature source) {
		if (source instanceof BomberMan) {
			((Creature) source).kill((Creature)source);
		}
		return true;
	}
	
	abstract Image getKillImage() ;
	
	
	@Override
	void fired() {
		
		kill(null);
	}

}
