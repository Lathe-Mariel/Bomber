package bomber;

import java.awt.Dimension;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class TimerLable extends JLabel {
	private int m;
	private int s;
	private Timer timer;

	TimerLable() {
		m = 5;
		s = 0;
		setFont(new Font("serif", Font.BOLD, 42));
	}

	public Dimension getPreferredSize() {
		return new Dimension(300, 50);
	}

	void startTimer() {
		timer = new Timer();
			timer.schedule(new TimerTask() {
			public void run() {
				decrease1sec();
			}
		}, 0, 1000);
	}
	
	void decrease1sec() {
		if (s > 0) {
			s--;
		} else {
			if (m == 0) {
				timer.cancel();
				//timesUp();
			} else {
				s = 59;
				m--;
			}
		}
		setText(m + ":" + String.format("%02d", s));
	}
}
