package _2024_07_10;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

public class UpdateJPanel extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel label_itemNum = new JLabel("事项名称:");
	private JLabel label_currentNum = new JLabel("当前数:");
	private JLabel label_totalNum = new JLabel("总数:");
	
	private JTextField textField_itemNum = new JTextField();
	private JTextField textField_currentNum = new JTextField();
	private JTextField textField_totalNum = new JTextField();
	
	private JLabel label_seize = new JLabel();
	private JButton button_comp = new JButton("完成");
	
	private SubFrame subFrame = null;
	
	public UpdateJPanel(SubFrame subFrame) {
		this.subFrame = subFrame;
		init();
		this.setTitle("修改事项");
		this.setSize(600, 150);
		
		this.setLocationRelativeTo(null);
		Point pointOnScreen = this.getLocation();
		pointOnScreen.setLocation(pointOnScreen.getX() +  500, pointOnScreen.getY());
		this.setLocation(pointOnScreen);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void init() {
		this.setLayout(new GridLayout(4, 2, 5, 5));
		this.add(label_itemNum);
		this.add(textField_itemNum);
		this.add(label_currentNum);
		this.add(textField_currentNum);
		this.add(label_totalNum);
		this.add(textField_totalNum);
		this.add(label_seize);
		this.add(button_comp);
		
		((AbstractDocument)textField_totalNum.getDocument()).setDocumentFilter(new NumericFilter());
		((AbstractDocument)textField_currentNum.getDocument()).setDocumentFilter(new NumericFilter());
		
		textField_itemNum.setText(subFrame.getItem().getItemName());
		textField_currentNum.setText(Integer.valueOf(subFrame.getItem().getCurrentNumber()).toString());
		textField_totalNum.setText(Integer.valueOf(subFrame.getItem().getTotalNumber()).toString());
		
		button_comp.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if (button == button_comp) {
			String totalNum = textField_totalNum.getText();
			String currentNum = textField_currentNum.getText();
			if (totalNum.equals("") || currentNum.equals("")) {
				return;
			}
			long longTotalNum = Long.valueOf(totalNum);
			if (longTotalNum > Integer.MAX_VALUE) {
				return;
			}
			long longCurrentNum = Long.valueOf(currentNum);
			if (longCurrentNum > Integer.MAX_VALUE) {
				return;
			}
			String itemNum = textField_itemNum.getText();
			subFrame.updateItem(itemNum, (int)(longTotalNum < longCurrentNum ? longTotalNum : longCurrentNum), (int)longTotalNum);
			
			dispose();
		}
	}

}
