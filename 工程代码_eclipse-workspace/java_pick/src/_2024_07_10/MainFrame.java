package _2024_07_10;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private CenterFrame centerFrame = new CenterFrame();
	private JScrollPane scrollPane = new JScrollPane(centerFrame);
	private AddJPanel addJPanel = new AddJPanel(centerFrame);
	private SubFrame frame_massage = new SubFrame(null, new Item("", 0), 0);
	
	public MainFrame() {
		init();
		this.setTitle("事项记录");
		this.setResizable(true);
		this.setSize(800, 900);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
		frame_massage.specialAdjustments();
	}
	
	private void init() {
		this.setLayout(new BorderLayout(10, 3));
		this.add(addJPanel, BorderLayout.SOUTH);
		this.add(scrollPane,BorderLayout.CENTER);
		this.add(frame_massage, BorderLayout.NORTH);
	}

}
