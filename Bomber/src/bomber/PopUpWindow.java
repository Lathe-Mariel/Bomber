package bomber;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;


public class PopUpWindow extends JComponent implements Runnable{
	int frameX, frameY;
	int x, y, width, height;
	Field container;
	Tile[][] tiles;
	PopUpWindow var;
	Image image;

	PopUpWindow(int x, int y, Field container, Image image) {
		width = 80;
		height = 120;
		this.frameX = x;
		this.frameY = y;
		this.x = x * 40-15;
		this.y = y * 40-40;
		setLocation(this.x, this.y);
		setSize(width, height);
		this.container = container;
		this.image = image;
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
		float alpha = 0.7f; 
		Graphics2D g2 = (Graphics2D)g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
		g.drawImage(image, 0, 0, width, height, this);
	}

	@Override
	public void run() {
		// TODO 自動生成されたメソッド・スタブ
		try {
			Thread.sleep(1200);
		} catch (Exception e) {
			e.printStackTrace();
		}

		var = this;
		try {
			SwingUtilities.invokeLater(new Thread() {
				public void run() {
					container.remove(var);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		container.repaint(500, x, y, width, height);
	}
}
