package bomber;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BomberTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BomberTest frame = new BomberTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BomberTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 865, 760);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0, 50));

		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(new File("neko-l.png")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		northPanel.add(new JLabel(icon));

		TimerLable tb = new TimerLable();
		northPanel.add(tb);
		contentPane.add(northPanel, BorderLayout.NORTH);

		JLabel panel0 = new JLabel() {
			public boolean isOptimizedDrawingEnabled() {
				return false;
			}
		};
		contentPane.add(panel0, BorderLayout.CENTER);
		panel0.setLayout(null);
		TranceparentWindow tw = new TranceparentWindow();
		tw.setBounds(0, 0, getWidth(), getHeight() - 40);

		Field field0 = new Field();
		field0.setBounds(0, 0, getWidth() - 4, getHeight() - 44);
		panel0.add(field0);
		panel0.add(tw);
		panel0.setComponentZOrder(tw, 0);
		KeyEventHandler keyHandler = new KeyEventHandler();
		field0.setKeyListener(keyHandler);
		addKeyListener(keyHandler);

		field0.init2PC();
		
		JLabel startLabel = new JLabel("START !");
		startLabel.setFont(new Font("serif", Font.BOLD, 56));
		startLabel.setSize(240, 70);
		startLabel.setOpaque(false);

		tw.add(startLabel);
		tw.startLabel();
		
		try {
			Thread.sleep(2000);
		}catch(Exception e) {e.printStackTrace();}
		tb.startTimer();
	}
}
