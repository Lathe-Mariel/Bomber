package bomber;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BomberUtility {

	public static long KEYCheckIntervalTime = 30;

	static Image[] generalImageArray;

	static {
		try {
			generalImageArray = new Image[7];
			generalImageArray[0] = ImageIO.read(new File("rock.png"));
			generalImageArray[1] = ImageIO.read(new File("brick.png"));
			generalImageArray[2] = ImageIO.read(new File("kusa.png"));
			//generalImageArray[3] = ImageIO.read(new File("yellow.png"));
			//generalImageArray[4] = ImageIO.read(new File("purple.png"));
			//generalImageArray[5] = ImageIO.read(new File("gray.png"));
			//generalImageArray[6] = ImageIO.read(new File("rock.png"));
		} catch (IOException e) {
			System.out.println("Error from static block, It's Puyo images loading process");
			e.printStackTrace();
		}
	}
}
