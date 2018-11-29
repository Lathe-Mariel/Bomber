package bomber;

abstract class BomberMan extends Creature {
//	BomberMan(Field container){
//		super(container);
//	}
	
	BomberMan(Field container, int x, int y){
		super(container, x, y);
	}
	
	@Override
	boolean stepOn(Tile source) {
		if(source instanceof Enemy) {
			kill();
		}
		return true;
	}
	
	void putOnBomb() {
		//generation of new Bombs
	}
}
