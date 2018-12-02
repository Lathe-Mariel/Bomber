package bomber;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
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
		setBounds(10, 10, 865, 780);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel northPanel = new JPanel();
		northPanel.setPreferredSize(new Dimension(0,50));
		
		ImageIcon icon = null;
		try {
			icon = new ImageIcon(ImageIO.read(new File("neko-l.png")));
		}catch(Exception e) {e.printStackTrace( );}
		northPanel.add(new JLabel(icon));
		contentPane.add(northPanel, BorderLayout.NORTH);
		
		Field field0 = new Field();
		contentPane.add(field0, BorderLayout.CENTER);
		KeyEventHandler keyHandler = new KeyEventHandler();
		field0.setKeyListener(keyHandler);
		addKeyListener(keyHandler);

		field0.init1PC();
	}

}
