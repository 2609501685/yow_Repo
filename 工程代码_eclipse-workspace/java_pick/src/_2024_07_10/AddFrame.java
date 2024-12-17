package _2024_07_10;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

public class AddFrame extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel label_itemNum = new JLabel("事项:");
	private JLabel label_totalNum = new JLabel("总数:");
	
	private JTextField editorPane_itemNum = new JTextField();
	private JTextField editorPane_totalNum = new JTextField();
	private JButton button_comp = new JButton("完成");
	
	private JLabel label_seize = new JLabel();
	
	private CenterFrame centerFrame = null;
	
	public AddFrame(CenterFrame centerFrame) {
		this.centerFrame = centerFrame;
		init();
		this.setTitle("添加事项");
		this.setSize(300, 150);
		
		this.setLocationRelativeTo(null);
		Point pointOnScreen = this.getLocation();
		pointOnScreen.setLocation(pointOnScreen.getX() +  500, pointOnScreen.getY());
		this.setLocation(pointOnScreen);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void init() {
		this.setLayout(new GridLayout(3, 3));
		this.add(label_itemNum);
		this.add(editorPane_itemNum);
		this.add(label_totalNum);
		this.add(editorPane_totalNum);
		this.add(label_seize);
		this.add(button_comp, -1);
		
		label_itemNum.setMaximumSize(new Dimension(200, 20));
		
		((AbstractDocument)editorPane_totalNum.getDocument()).setDocumentFilter(new NumericFilter());
		
		button_comp.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if (button == button_comp) {
			String totalNum = editorPane_totalNum.getText();
			if (totalNum.equals("")) {
				return;
			}
			Long longNum = Long.valueOf(totalNum);
			if (longNum > Integer.MAX_VALUE) {
				return;
			}
			String itemNum = editorPane_itemNum.getText();
			Item item = new Item(itemNum, Integer.valueOf(totalNum));
			centerFrame.add(item);
			dispose();
		}
	}
	
}
