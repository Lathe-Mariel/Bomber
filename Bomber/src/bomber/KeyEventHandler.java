package bomber;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;

public class KeyEventHandler implements KeyListener {
	private boolean keyState[];
	private boolean doublePressLock;
	private PC players[];

	public KeyEventHandler() {
		players = new PC[2];
		keyState = new boolean[10];

		new java.util.Timer().schedule(new TimerTask() {
			public void run() {
				keyPressHandler();
			}
		}, 500, BomberUtility.KEYCheckIntervalTime);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	private void keyPressHandler() {
		if (keyState[4]) {
			new Thread() {
				public void run() {
					if(doublePressLock)return;
					doublePressLock = true;
					players[0].putOnBomb();
				}
			}.start();
		} else if (keyState[0]) {
			new Thread() {
				public void run() {
					players[0].moveLeft();
				}
			}.start();
		} else if (keyState[1]) {
			new Thread() {
				public void run() {
					players[0].moveDown();
				}
			}.start();
		} else if (keyState[2]) {
			new Thread() {
				public void run() {
					players[0].moveRight();
				}
			}.start();
		} else if (keyState[3]) {
			new Thread() {
				public void run() {
					players[0].moveUp();
				}
			}.start();
		} else if (keyState[9]) {

		} else if (keyState[5]) {

		} else if (keyState[6]) {

		} else if (keyState[7]) {

		} else if (keyState[8]) {

		} else {
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_S:
			keyState[4] = true;
			break;
		case KeyEvent.VK_A:
			keyState[0] = true;
			break;
		case KeyEvent.VK_X:
			keyState[1] = true;
			break;
		case KeyEvent.VK_D:
			keyState[2] = true;
			break;
		case KeyEvent.VK_W:
			keyState[3] = true;
			break;
		case KeyEvent.VK_NUMPAD5:
			keyState[9] = true;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_S:
			keyState[4] = false;
			doublePressLock = false;
			break;
		case KeyEvent.VK_A:
			keyState[0] = false;
			break;
		case KeyEvent.VK_X:
			keyState[1] = false;
			break;
		case KeyEvent.VK_D:
			keyState[2] = false;
			doublePressLock = false;
			break;
		case KeyEvent.VK_W:
			keyState[3] = false;
			doublePressLock = false;
			break;
		case KeyEvent.VK_NUMPAD5:
			keyState[9] = false;
			doublePressLock = false;
			break;
		default:
			break;
		}
	}

	public boolean addPlayer(PC player) {
		if (players[0] == null) {
			players[0] = player;
			return true;
		} else if (players[1] == null) {
			players[1] = player;
			return true;
		}
		return false;
	}
}
