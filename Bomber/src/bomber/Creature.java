package bomber;

abstract public class Creature extends Tile {

	Creature(Field container) {
		super(container);
	}

	/**
	 * When this object contacts enemy, this method will be called by enemy(when enemy steps on) or oneself(when PC steps on).
	 */
	abstract void kill();

	void moveRight() {

	}

	void moveLeft() {

	}

	void moveUp() {

	}

	void moveDown() {

	}
}
