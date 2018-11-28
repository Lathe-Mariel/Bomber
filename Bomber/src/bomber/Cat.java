package bomber;

public class Cat extends Enemy {
	Cat(Field container){
		super(container);
	}

	@Override
	void fired() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	boolean stepOn(Tile source) {
		if(source instanceof PC) {
			((Creature)source).kill();
		}
		return true;
	}

	@Override
	void kill() {
		// TODO 自動生成されたメソッド・スタブ

	}
}
