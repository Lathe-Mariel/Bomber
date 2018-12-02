package bomber;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Field extends JPanel {
	private Tile tileArray[][];
	private KeyEventHandler keyListener;
	boolean tileArrayLocker;
	private ArrayList<PC> bomberMans;

	public Dimension getPreferredSize() {
		return new Dimension(840, 680);
	}

	void setKeyListener(KeyEventHandler keyListener) {
		this.keyListener = keyListener;
	}
	public boolean isOptimizedDrawingEnabled() {
		return false;
	}

	Field() {
		setLayout(null);
		tileArray = new Tile[21][17];
		for (int i = 2; i < tileArray.length - 2; i++, i++) {
			for (int j = 2; j < tileArray[i].length - 2; j++, j++) {
				addTile(new Obstacle(this, i, j));
			}
		}
		for (int i = 0; i < tileArray.length; i++) {
			addTile(new Obstacle(this, i, 0));
			addTile(new Obstacle(this, i, tileArray[0].length - 1));
		}
		for (int i = 1; i < tileArray[0].length - 1; i++) {
			addTile(new Obstacle(this, 0, i));
			addTile(new Obstacle(this, tileArray.length - 1, i));
		}
		bomberMans = new ArrayList<PC>();
	}

	synchronized boolean addTile(Tile newTile) {
		if (tileArray[newTile.frameX][newTile.frameY] == null) {
			tileArray[newTile.frameX][newTile.frameY] = newTile;
			if (!SwingUtilities.isEventDispatchThread()) {
				try {
					SwingUtilities.invokeLater(new Thread() {
						public void run() {
							add(newTile);
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				add(newTile);
			}
			//System.out.println("addTile");
			repaint(100, newTile.x, newTile.y, 40, 40);
			return true;
		}else {
		System.out.println("frameX: " + newTile.frameX + "    frameY: " + newTile.frameY + "  not null");
		System.out.println(tileArray[newTile.frameX][newTile.frameY].getClass());
		return false;}
	}

	synchronized void removeTile(Tile source) {
		if(source == null)return;
		try {
			if(!SwingUtilities.isEventDispatchThread()) {
			SwingUtilities.invokeAndWait(new Thread() {
				public void run() {
					remove(source);
				}
			});}else {
				remove(source);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		tileArray[source.frameX][source.frameY] = null;
		repaint(100,source.x, source.y,40,40);
	}

	//	void disappear(BrakableBlock tile) {
	//		tileArray[tile.frameX][tile.frameY] = null;
	//		remove(tile);
	//	}

	Tile[][] getTileArray() {
		return tileArray;
	}

	synchronized boolean toRight(Creature source) {
		int x = source.frameX + 1;
		int y = source.frameY;
		if (tileArray[x][y] == null || tileArray[x][y].stepOn(source)) {
			tileArray[x - 1][y] = null;
			tileArray[x][y] = source;
			source.setFrameX(x);
			return true;
		} else {
			return false;
		}
	}

	synchronized boolean toLeft(Creature source) {
		int x = source.frameX - 1;
		int y = source.frameY;
		if (tileArray[x][y] == null || tileArray[x][y].stepOn(source)) {
			tileArray[x + 1][y] = null;
			tileArray[x][y] = source;
			source.setFrameX(x);
			return true;
		} else {
			return false;
		}
	}

	synchronized boolean toUp(Creature source) {
		int x = source.frameX;
		int y = source.frameY - 1;
		if (tileArray[x][y] == null || tileArray[x][y].stepOn(source)) {
			tileArray[x][y + 1] = null;
			tileArray[x][y] = source;
			source.setFrameY(y);
			return true;
		} else {
			return false;
		}
	}

	synchronized boolean toDown(Creature source) {
		int x = source.frameX;
		int y = source.frameY + 1;
		if (tileArray[x][y] == null || tileArray[x][y].stepOn(source)) {
			tileArray[x][y - 1] = null;
			tileArray[x][y] = source;
			source.setFrameY(y);
			return true;
		} else {
			return false;
		}
	}

	void init1PC() {
		deployBricks(85, 5, 7, 1);
		bomberMans.add(new PC(this, 1, 1));
		keyListener.addPlayer((PC) bomberMans.get(0));
		addTile(bomberMans.get(0));
		Cat enemy0 = new Cat(this, 19, 15);
		addTile(enemy0);
		new Thread(enemy0).start();
		Cheetah enemy1 = new Cheetah(this, 19, 1);
		addTile(enemy1);
		new Thread(enemy1).start();
	}

	void deployBricks(int number, int fireUp, int bombNumberUp, int FirePowerUp3) {
		ArrayList<BrakableBlock> bricks = new ArrayList<BrakableBlock>();
		for (int i = 0; i < number; i++) {
			int depX = (int) (Math.random() * (tileArray.length-3))+1;
			int depY = (int) (Math.random() * (tileArray[0].length-3))+1;
			while (tileArray[depX][depY] != null) {
				depX++;
				if (depX >= tileArray.length - 1) {
					depX = 1;
					depY = depY >= tileArray[0].length ? 1 : depY + 1;
				}
			}
			BrakableBlock block = new BrakableBlock(this, depX, depY);
			bricks.add(block);
			if(fireUp > 0) {
				block.setItem(new FirePowerUp(1));
				fireUp--;
				continue;
			}
			if(bombNumberUp > 0) {
				block.setItem(new BombNumberUp());
				bombNumberUp--;
				continue;
			}
			if(FirePowerUp3 > 0) {
				block.setItem(new FirePowerUp(3));
				FirePowerUp3--;
				continue;
			}
		}

		for (int i = 0; i < bricks.size(); i++) {
			addTile(bricks.get(i));
		}
		removeTile(tileArray[1][1]);
		removeTile(tileArray[2][1]);
		removeTile(tileArray[1][2]);
		removeTile(tileArray[19][15]);
	}

	void death(Creature creature) {
	if(creature instanceof PC) {
		PC pc=null;
		for(Iterator<PC> i = bomberMans.iterator(); i.hasNext();) {
			pc = i.next();
			if(pc == creature) break;
			pc = null;
		}
		if(bomberMans != null)
		bomberMans.remove(pc);
	}
	removeTile(creature);
	}

	BomberMan getBomberMan(int index) {
		return bomberMans.get(index);
	}

	int getBomberManNumber() {
		return bomberMans.size();
	}
}
