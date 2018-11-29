package bomber;

abstract class BomberMan extends Creature {
	BomberMan(Field container){
		super(container);
	}
	BomberMan(Field container, int x, int y){
		super(container, x, y);
	}
	
	void putOnBomb() {
		
	}
}
