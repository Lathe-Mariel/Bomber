package bomber;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class TranceparentWindow extends JPanel {

	TranceparentWindow() {
		setLayout(null);
		setOpaque(false);
	}

	public boolean isOpaque() {
		return false;
	}

	public boolean isOptimizedDrawingEnabled() {
		return false;
	}

	public void startLabel() {
		new Thread() {
			public void run() {
				for (int i = 0; i < 7; i++) {
					setVisible(true);
					try {
						Thread.sleep(550);
						setVisible(false);
						Thread.sleep(300);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	public void add(JComponent component) {
		super.add(component);
		int x = (getWidth() - component.getWidth()) / 2;
		int y = (getHeight() - component.getHeight()) / 2 -30;
		component.setLocation(x, y);
	}
}
