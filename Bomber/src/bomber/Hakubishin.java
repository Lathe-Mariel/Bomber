package bomber;

import java.awt.Image;

public class Hakubishin extends Enemy {
	private BomberMan target;

	Hakubishin(Field container, int x, int y) {
		super(container, x, y);

		speed = 170;
	}

	@Override
	void kill(Creature source) {
		target = null;
		super.kill(null);
	}

	@Override
	public void run() {
		while (alive) {
			if (container.getBomberManNumber() > 0) {
				target = container.getBomberMan((int) (Math.random() * container.getBomberManNumber()));
			} else {
				target = null;
			}

			try {
				Thread.sleep(speed);
			} catch (Exception e) {
				System.out.println("Cat -> run()");
				e.printStackTrace();
			}
		}
	}

	@Override
	Image getKillImage() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	void contact() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
