package bomber;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Cat extends Enemy {
	Cat(Field container){
		this(container, 0,0);


	}
	Cat(Field container, int x, int y){
		super(container, x ,y);

		try {
			image = ImageIO.read(new File("kusa.png"));
			}catch(IOException e) {e.printStackTrace();}
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

	@Override
	void contact() {
		// TODO 自動生成されたメソッド・スタブ

	}
}
