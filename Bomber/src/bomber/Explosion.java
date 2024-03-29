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
	int hand_in_number1, hand_in_number2, hand_in_number3, hand_in_number4;

	/**
	 * This is only way to create instance of Explosion.
	 * @param power	firepower.
	 * @param x	frameX of bomb which caused fire.
	 * @param y	frameY of bomb which caused fire.
	 * @param container	The Field which has all elements of this game.
	 * @param penetrate	Wheather a bomb have penetrate power or not.
	 */
	Explosion(int power, int x, int y, Field container, boolean penetrate) {
		this.power = power;
		this.frameX = x;
		this.frameY = y;
		this.container = container;
		tiles = container.getTileArray();
		this.penetrate = penetrate;
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
		g.setColor(new Color(255, 0, 0, 150));
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
						hand_in_number1 = i;
						Thread t = new Thread() {
							public void run() {
								tiles[hand_in_number1][frameY].fired();
							}
						};
						t.start();
						try {
						t.join(150);
						}catch(Exception e) {e.printStackTrace();}
					}
					fireL++;
				}
			}

			for (int i = frameX + 1; i <= frameX + power; i++) {
				if (tiles[i][frameY] instanceof Obstacle) {
					break;
				} else {
					if (tiles[i][frameY] != null) {
						hand_in_number2 = i;
						Thread t = new Thread() {
							public void run() {
								tiles[hand_in_number2][frameY].fired();
							}
						};
						t.start();
						try {
							t.join();
						}catch(Exception e) {e.printStackTrace();}
					}
				fireR++;}
			}

			for (int i = frameY - 1; i >= frameY - power; i--) {
				if (tiles[frameX][i] instanceof Obstacle) {
					break;
				} else {
					if (tiles[frameX][i] != null) {
						hand_in_number3 = i;
						Thread t = new Thread() {
							public void run() {
								tiles[frameX][hand_in_number3].fired();
							}
						};
						t.start();
						try {
							t.join();
						}catch(Exception e) {e.printStackTrace();}
					}
					fireU++;
				}
			}

			for (int i = frameY + 1; i <= frameY + power; i++) {
				if (tiles[frameX][i] instanceof Obstacle) {
					break;
				} else {
					if (tiles[frameX][i] != null) {
						hand_in_number4 = i;
						Thread t = new Thread() {
							public void run() {
								tiles[frameX][hand_in_number4].fired();
							}
						};
						t.start();
						try {
							t.join();
						}catch(Exception e) {e.printStackTrace();}
					}
					fireD++;
				}
			}

		} else {
			if (tiles[frameX][frameY] !=null) {
				tiles[frameX][frameY].fired();
			System.out.println("fire");
			}
			for (int i = frameX - 1; i >= frameX - power; i--) {
				if (tiles[i][frameY] instanceof Obstacle) {
					break;
				} else if (tiles[i][frameY] instanceof BrakableBlock) {
					fireL++;
					tiles[i][frameY].fired();
					break;
				} else {
					if (tiles[i][frameY] != null) {
						hand_in_number1 = i;
						new Thread() {
							public void run() {
								tiles[hand_in_number1][frameY].fired();
							}
						}.start();
						//System.out.println("fire");
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
						hand_in_number2 = i;
						new Thread() {
							public void run() {
								tiles[hand_in_number2][frameY].fired();
							}
						}.start();
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
						hand_in_number3 = i;
						new Thread() {
							public void run() {
								tiles[frameX][hand_in_number3].fired();
							}
						}.start();
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
						hand_in_number4 = i;
						new Thread() {
							public void run() {
								tiles[frameX][hand_in_number4].fired();
							}
						}.start();
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
		surveyTiles();
		try {
			Thread.sleep(600);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//var = this;
		try {
			SwingUtilities.invokeAndWait(new Thread() {
				public void run() {
					container.remove(Explosion.this);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		container.repaint(300, this.x, this.y, width, height);
	}
}
