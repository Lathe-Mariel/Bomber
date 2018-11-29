package bomber;

public class Bomb extends Tile {
	int power;

	Bomb(Field container) {
		super(container);
		power = 1;
		// TODO 自動生成されたコンストラクター・スタブ
	}

	void increasePower(int increasePower) {
		power += increasePower;
	}

	void explode() {
		// exploding process
	}

	@Override
	void fired() {
		explode();
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	boolean stepOn(Tile source) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
}
