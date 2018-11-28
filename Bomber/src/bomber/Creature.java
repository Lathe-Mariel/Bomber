package bomber;


abstract public class Creature extends Tile {

	Creature(Field container) {
		super(container);
	}

	abstract void burn() ;

}
