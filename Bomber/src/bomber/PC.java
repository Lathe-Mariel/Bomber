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
			pcImageArray[4] =ImageIO.read(new File("usa-m-l.png"));
			pcImageArray[5] =ImageIO.read(new File("usa-m-r.png"));
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
		movingImage[4] = pcImageArray[4];
		movingImage[5] = pcImageArray[5];
		image = movingImage[1];
		deadImage = pcImageArray[2];
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
	void contact() {
		// TODO 自動生成されたメソッド・スタブ

	}
	
	void goRight() {
		super.moveRight();
		keyListener.nofityKeyProcessingEnd(this);
	}
	void goLeft() {
		super.moveLeft();
		keyListener.nofityKeyProcessingEnd(this);
	}
	void goUp() {
		super.moveUp();
		keyListener.nofityKeyProcessingEnd(this);
	}
	void goDown() {
		super.moveDown();
		keyListener.nofityKeyProcessingEnd(this);
	}

}
