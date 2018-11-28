package bomber;


public class PC extends BomberMan {

	PC(Field container) {
		super(container);
	}

	@Override
	void fired() {
		//process die
		//container.disappear(this)
	}

	/**
	 * This method will be triggered by KeyEventHandler. B means generating Bomb.
	 */
	void fireB() {

	}
	void fireL() {

	}
	void fireR() {

	}
	void fireU() {

	}
	void fireD() {

	}

	@Override
	boolean stepOn(Tile source) {
		// TODO 自動生成されたメソッド・スタブ
		return true;
	}

	@Override
	void kill() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
