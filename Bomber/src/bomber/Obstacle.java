package bomber;

import java.awt.Graphics;

public class Obstacle extends Tile {

	Obstacle(Field container) {
		super(container);

		// TODO 自動生成されたコンストラクター・スタブ
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

	public void paint(Graphics g){
		g.fill3DRect(0, 0, 40, 40, true);
	}

}
