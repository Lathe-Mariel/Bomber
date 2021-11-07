package bomber;

import java.awt.Color;
import java.awt.Graphics;

public class Obstacle extends Tile {
	private Obstacle() {
	}

	private Obstacle(Field container) {
		super(container);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	Obstacle(Field container, int x, int y) {
		super(container, x, y);
	}

	@Override
	void fired() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	boolean stepOn(Creature source) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	public void paint(Graphics g) {
		g.setColor(Color.gray);
		g.fill3DRect(0, 0, 40, 40, true);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(1, 1, 1,38);
		g.drawLine(1, 1, 38, 1);
		g.setColor(Color.DARK_GRAY);
		g.drawLine(1, 38, 38,38);
		g.drawLine(38, 1, 38, 38);
	}

}
