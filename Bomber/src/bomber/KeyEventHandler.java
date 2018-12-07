package bomber;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;

public class KeyEventHandler implements KeyListener {
	private boolean keyState[];
	private boolean doublePressLock;
	private boolean doublePressLockP2;
	private PC players[];
	private boolean keyInputProcessing;
	private boolean keyInputProcessing2;

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

	void nofityKeyProcessingEnd(PC pc) {
		if (pc == players[0])
			keyInputProcessing = false;
		if (pc == players[1])
			keyInputProcessing2 = false;
	}

	private void keyPressHandler() {
		if (keyState[4]) {
			if (!doublePressLock) {
				doublePressLock = true;
			new Thread() {
				public void run() {
					players[0].putOnBomb();
				}
			}.start();}
		} else if (keyState[9]) {
			if (!doublePressLockP2) {
				doublePressLockP2 = true;
			new Thread() {
				public void run() {
					players[1].putOnBomb();
				}
			}.start();
		}}

		if (!keyInputProcessing) {
		if (keyState[0]) {
			keyInputProcessing = true;
			new Thread() {
				public void run() {
					players[0].goLeft();
				}
			}.start();
		} else if (keyState[1]) {
			keyInputProcessing = true;
			new Thread() {
				public void run() {
					players[0].goDown();
				}
			}.start();
		} else if (keyState[2]) {
			keyInputProcessing = true;
			new Thread() {
				public void run() {
					players[0].goRight();
				}
			}.start();
		} else if (keyState[3]) {
			keyInputProcessing = true;
			new Thread() {
				public void run() {
					players[0].goUp();
				}
			}.start();
		}}
		if (!keyInputProcessing2 == true) {
		if (keyState[5]) {
			keyInputProcessing2 = true;
			new Thread() {
				public void run() {
					players[1].goLeft();
				}
			}.start();
		} else if (keyState[6]) {
			keyInputProcessing2 = true;
			new Thread() {
				public void run() {
					players[1].goDown();
				}
			}.start();
		} else if (keyState[7]) {
			keyInputProcessing2 = true;
			new Thread() {
				public void run() {
					players[1].goRight();
				}
			}.start();
		} else if (keyState[8]) {
			keyInputProcessing2 = true;
			new Thread() {
				public void run() {
					players[1].goUp();
				}
			}.start();
		} else {
		}}
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
		case KeyEvent.VK_NUMPAD4:
			keyState[5] = true;
			break;
		case KeyEvent.VK_NUMPAD2:
			keyState[6] = true;
			break;
		case KeyEvent.VK_NUMPAD6:
			keyState[7] = true;
			break;
		case KeyEvent.VK_NUMPAD8:
			keyState[8] = true;
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
		case KeyEvent.VK_NUMPAD4:
			keyState[5] = false;
			break;
		case KeyEvent.VK_NUMPAD2:
			keyState[6] = false;
			break;
		case KeyEvent.VK_NUMPAD6:
			keyState[7] = false;
			break;
		case KeyEvent.VK_NUMPAD8:
			keyState[8] = false;
			break;
		case KeyEvent.VK_NUMPAD5:
			keyState[9] = false;
			doublePressLockP2 = false;
			break;
		default:
			break;
		}
	}

	public boolean addPlayer(PC player) {
		if (players[0] == null) {
			players[0] = player;
			player.setKeyListener(this);
			return true;
		} else if (players[1] == null) {
			players[1] = player;
			player.setKeyListener(this);
			return true;
		}
		return false;
	}

	synchronized public void removePlayer(PC player) {
		for (int i = 0; i < players.length; i++) {
			if (players[i] == player)
				players[i] = null;
		}
	}
}
