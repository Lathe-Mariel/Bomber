package bomber;

public class NPC extends BomberMan implements Runnable{

NPC(Field container, int x, int y) {
		super(container, x, y);
		// TODO 自動生成されたコンストラクター・スタブ
	}


//	NPC(Field container) {
//		super(container);
//	}

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ

	}


	@Override
	void fired() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	boolean stepOn(Tile source) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	void kill() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	void contact() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
