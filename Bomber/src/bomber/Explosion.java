package bomber;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class Explosion extends JComponent implements Runnable {
	int power;
	int frameX, frameY, x2, y2, center;
	int x, y, width, height;
	Field container;
	Tile[][] tiles;
	Explosion var;

	/**
	 * This is only way to create instance of Explosion.
	 * @param power	firepower.
	 * @param x	frameX of bomb which caused fire.
	 * @param y	frameY of bomb which caused fire.
	 * @param container	The Field which has all elements of this game.
	 */
	Explosion(int power, int x, int y, Field container) {
		this.power = power;
		this.frameX = x;
		this.frameY = y;
		System.out.println("frameX: " + frameX + "    frameY: " + frameY);
		this.x = x * 40 - 40 * power;
		this.y = y * 40 - 40 * power;
		x2 = x * 40 + 40 * (power + 1);
		y2 = y * 40 + 40 * (power + 1);
		width = x2 - this.x;
		height = y2 - this.y;
		setLocation(this.x, this.y);
		setSize(width, height);
		center = power * 40 + 20;
		this.container = container;
		tiles = container.getTileArray();
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public Dimension getSize() {
		return new Dimension(width, height);
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public boolean isOpaque() {
		return false;
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(255, 0, 0, 180));
		g.fillRect(0, center - 10, x2, 20);
		g.fillRect(center - 10, 0, 20, y2);
	}

	private void surveyTiles() {
		int i1 = frameX - power;
		int j1 = frameY - power;
		int i2 = frameX + power;
		int j2 = frameY + power;
		i1 = i1 < 1 ? 1 : i1;
		j1 = j1 < 1 ? 1 : j1;
		i2 = i2 > 19 ? 19 : i2;
		j2 = j2 > 15 ? 15 : j2;
		for (; i1 <= i2; i1++) {
			if (tiles[i1][frameY] == null)
				continue;
			tiles[i1][frameY].fired();
		}
		for (; j1 <= j2; j1++) {
			if (tiles[frameX][j1] == null)
				continue;
			tiles[frameX][j1].fired();
		}
	}

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		surveyTiles();
		try {
			Thread.sleep(600);
		} catch (Exception e) {
			e.printStackTrace();
		}

		var = this;
		try {
			SwingUtilities.invokeAndWait(new Thread() {
				public void run() {
					container.remove(var);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		container.repaint(100, this.x, this.y, width, height);
	}
}
