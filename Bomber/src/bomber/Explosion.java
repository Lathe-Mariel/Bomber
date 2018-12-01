package bomber;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;

public class Explosion extends JComponent implements Runnable {
	int power;
	int frameX, frameY, cx, cy;
	int x, y, width, height;
	int fireL, fireR, fireU, fireD;
	Field container;
	Tile[][] tiles;
	Explosion var;
	boolean penetrate;

	/**
	 * This is only way to create instance of Explosion.
	 * @param power	firepower.
	 * @param x	frameX of bomb which caused fire.
	 * @param y	frameY of bomb which caused fire.
	 * @param container	The Field which has all elements of this game.
	 */
	Explosion(int power, int x, int y, Field container, boolean penetrate) {
		this.power = power;
		this.frameX = x;
		this.frameY = y;
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
		g.fillRoundRect(0, cy, width, 20, 5, 5);
		g.fillRoundRect(cx, 0, 20, height, 5, 5);

	}

	private void surveyTiles() {
		if (penetrate) {
			if (tiles[frameX][frameY] != null)
				tiles[frameX][frameY].fired();
			for (int i = frameX - 1; i >= frameX - power; i--) {
				if (tiles[i][frameY] instanceof Obstacle) {
					break;
				} else {
					if (tiles[i][frameY] != null) {
						tiles[i][frameY].fired();
					}
					fireL++;
				}
			}

			for (int i = frameX + 1; i <= frameX + power; i++) {
				if (tiles[i][frameY] instanceof Obstacle) {
					break;
				} else {
					if (tiles[i][frameY] != null) {
						tiles[i][frameY].fired();
					}
					fireR++;
				}
			}

			for (int i = frameY - 1; i >= frameY - power; i--) {
				if (tiles[frameX][i] instanceof Obstacle) {
					break;
				} else {
					if (tiles[frameX][i] != null) {
						tiles[frameX][i].fired();
					}
					fireU++;
				}
			}

			for (int i = frameY + 1; i <= frameY + power; i++) {
				if (tiles[frameX][i] instanceof Obstacle) {
					break;
				} else {
					if (tiles[frameX][i] != null) {
						tiles[frameX][i].fired();
					}
					fireD++;
				}
			}

		} else {
			if (tiles[frameX][frameY] != null)
				tiles[frameX][frameY].fired();
			for (int i = frameX - 1; i >= frameX - power; i--) {
				if (tiles[i][frameY] instanceof Obstacle) {
					break;
				} else if (tiles[i][frameY] instanceof BrakableBlock) {
					fireL++;
					tiles[i][frameY].fired();
					break;
				} else {
					if (tiles[i][frameY] != null) {
						tiles[i][frameY].fired();
					}
					fireL++;
				}
			}

			for (int i = frameX + 1; i <= frameX + power; i++) {
				if (tiles[i][frameY] instanceof Obstacle) {
					break;
				} else if (tiles[i][frameY] instanceof BrakableBlock) {
					fireR++;
					tiles[i][frameY].fired();
					break;
				} else {
					if (tiles[i][frameY] != null) {
						tiles[i][frameY].fired();
					}
					fireR++;
				}
			}

			for (int i = frameY - 1; i >= frameY - power; i--) {
				if (tiles[frameX][i] instanceof Obstacle) {
					break;
				} else if (tiles[frameX][i] instanceof BrakableBlock) {
					fireU++;
					tiles[frameX][i].fired();
					break;
				} else {
					if (tiles[frameX][i] != null) {
						tiles[frameX][i].fired();
					}
					fireU++;
				}
			}

			for (int i = frameY + 1; i <= frameY + power; i++) {
				if (tiles[frameX][i] instanceof Obstacle) {
					break;
				} else if (tiles[frameX][i] instanceof BrakableBlock) {
					fireD++;
					tiles[frameX][i].fired();
					break;
				} else {
					if (tiles[frameX][i] != null) {
						tiles[frameX][i].fired();
					}
					fireD++;
				}
			}
		}
		x = 40 * (frameX - fireL);
		width = 40 * (1 + fireL + fireR);
		y = 40 * (frameY - fireU);
		height = 40 * (1 + fireU + fireD);
		cx = fireL * 40 + 10;
		cy = fireU * 40 + 10;
		setBounds(x, y, width, height);
		repaint();
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
