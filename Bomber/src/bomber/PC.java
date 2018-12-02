package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PC extends BomberMan {
	KeyEventHandler keyListener;
	static private Image[] pcImageArray;
	
	static {
		try {
			pcImageArray = new Image[7];
			pcImageArray[0] = ImageIO.read(new File("usa-l.png"));
			pcImageArray[1] = ImageIO.read(new File("usa-r.png"));
			pcImageArray[2] = ImageIO.read(new File("dead-usa.png"));
			//			imageArray[1] = ImageIO.read(new File("brick.png"));
			//			imageArray[2] = ImageIO.read(new File("kusa.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//	PC(Field container) {
	//		this(container, 1, 1);
	//	}

	PC(Field container, int x, int y) {
		super(container, x, y);

		movingImage[0] = pcImageArray[0];
		movingImage[1] = pcImageArray[1];
		image = movingImage[1];
	}

	void setKeyListener(KeyEventHandler handler) {
		this.keyListener = handler;
	}
	@Override
	void fired() {
		super.fired();
		//process die
		//container.disappear(this)
	}

	@Override
	void kill(Creature source) {
		// TODO 自動生成されたメソッド・スタブ
		image = pcImageArray[2];
		container.repaint(50, 0, 0, 40, 40);

		super.kill(source);
	}

	@Override
	void contact() {
		// TODO 自動生成されたメソッド・スタブ

	}
	
	void goRight() {
		super.moveRight();
		keyListener.nofityKeyProcessingEnd();
	}
	void goLeft() {
		super.moveLeft();
		keyListener.nofityKeyProcessingEnd();
	}
	void goUp() {
		super.moveUp();
		keyListener.nofityKeyProcessingEnd();
	}
	void goDown() {
		super.moveDown();
		keyListener.nofityKeyProcessingEnd();
	}

}
