package _2024_07_10;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

public class SubFrame extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JLabel label_num = new JLabel();
	private JLabel label_itemName = new JLabel();
	private JProgressBar progressBar_percentage = new JProgressBar();
	private JButton button_reduce = new JButton("-");
	private JLabel label_currentNum = new JLabel();
	private JLabel label_FixedSeparatorSymbol = new JLabel("/");
	private JLabel label_totalNum = new JLabel();
	private JButton button_add = new JButton("+");
	private JTextField textField_notes = new JTextField();
	private JButton button_update = new JButton("修改");
	private JButton button_remove = new JButton("删除");
	
	private JLabel label_redundance = new JLabel();
	
	private Item item;
	private int num;
	
	private CenterFrame centerFrame;
	
	public SubFrame(CenterFrame centerFrame, Item item, int num) {
		this.centerFrame = centerFrame;
		this.item = item;
		this.num = num;
		initFrame();
		initData();
	}
	
	private void initFrame() {
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalStrut(10));
		this.add(label_num);
		this.add(Box.createHorizontalStrut(10));
		this.add(label_itemName);
		this.add(Box.createHorizontalStrut(10));
		this.add(progressBar_percentage);
		this.add(Box.createHorizontalStrut(10));
		this.add(button_reduce);
		this.add(Box.createHorizontalStrut(10));
		this.add(label_currentNum);
		this.add(Box.createHorizontalStrut(5));
		this.add(label_FixedSeparatorSymbol);
		this.add(Box.createHorizontalStrut(5));
		this.add(label_totalNum);
		this.add(Box.createHorizontalStrut(10));
		this.add(button_add);
		this.add(label_redundance);
		this.add(Box.createHorizontalStrut(10));
		this.add(textField_notes);
		this.add(Box.createHorizontalStrut(10));
		this.add(button_update);
		this.add(Box.createHorizontalStrut(10));
		this.add(button_remove);

		button_reduce.addActionListener(this);
		button_add.addActionListener(this);
		button_update.addActionListener(this);
		button_remove.addActionListener(this);
	}
	
	private void initData() {
		
		this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		
		label_num.setText(String.valueOf(num));
		label_itemName.setText(item.getItemName());
		label_currentNum.setText(String.valueOf(item.getCurrentNumber()));
		label_totalNum.setText(String.valueOf(item.getTotalNumber()));
		
		label_itemName.setHorizontalAlignment(JLabel.CENTER);
		label_currentNum.setHorizontalAlignment(JTextField.CENTER);
		label_totalNum.setHorizontalAlignment(JTextField.CENTER);
		label_FixedSeparatorSymbol.setHorizontalAlignment(JTextField.CENTER);
		label_redundance.setHorizontalAlignment(JTextField.CENTER);
		textField_notes.setHorizontalAlignment(JTextField.CENTER);
		
		progressBar_percentage.setMaximum(item.getTotalNumber());
		
		int width = label_itemName.getPreferredSize().width;
		label_itemName.setPreferredSize(new Dimension(width > 150 ? width : 150, 30));
		
		width = label_totalNum.getPreferredSize().width;
		label_totalNum.setPreferredSize(new Dimension(width > 25 ? width : 25, 20));
		
		width = label_currentNum.getPreferredSize().width;
		label_currentNum.setPreferredSize(new Dimension(width > 25 ? width : 25, 20));
		
		width = label_num.getPreferredSize().width;
		label_num.setPreferredSize(new Dimension(width > 25 ? width : 25, 20));
	}
	
	public void specialAdjustments() {
		int w1 = label_num.getWidth();
		int w2 = label_itemName.getWidth();
		int w3 = progressBar_percentage.getWidth();
		int w4 = button_reduce.getWidth();
		int w5 = label_currentNum.getWidth();
		int w6 = label_FixedSeparatorSymbol.getWidth();
		int w7 = label_totalNum.getWidth();
		int w8 = button_add.getWidth();
		int w9 = button_update.getWidth();
		int w10 = button_remove.getWidth();
		
		progressBar_percentage.setVisible(false);
		button_reduce.setVisible(false);
		button_add.setVisible(false);
		button_update.setVisible(false);
		button_remove.setVisible(false);
		textField_notes.setVisible(false);

		
		label_num.setPreferredSize(new Dimension(w1, 20));
		label_num.setText("ID");
		
		label_itemName.setPreferredSize(new Dimension(w2, 20));
		label_itemName.setText("事件名称");
		
		label_currentNum.setPreferredSize(new Dimension(w3, 20));
		label_currentNum.setText("进度条");
		
		label_FixedSeparatorSymbol.setPreferredSize(new Dimension(w4 + w5 + w6 + w7 + w8, 20));
		label_FixedSeparatorSymbol.setText("当前数/总数");
		
		label_totalNum.setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
		label_totalNum.setText("备注");
		
		label_redundance.setPreferredSize(new Dimension(w9 + w10, 20));
		label_redundance.setText("操作");
	}
	
	public void updateItem(String itemName, int currentNumber, int totalNumber) {
		this.item.setValues(itemName, currentNumber, totalNumber);
		label_itemName.setText(item.getItemName());
		label_totalNum.setText(String.valueOf(item.getTotalNumber()));
		label_currentNum.setText(String.valueOf(item.getCurrentNumber()));
		
		progressBar_percentage.setMaximum(item.getTotalNumber());
		progressBar_percentage.setValue(item.getCurrentNumber());
	}
	
	public Item getItem() {
		return item;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if (button == button_remove) {
			centerFrame.removeItem(num);
			return;
		} else if (button == button_update) {
			new UpdateJPanel(this);
			return;
		} else if (button == button_add) {
			item.add();
		} else if (button == button_reduce) {
			item.reduce();
		}
		label_currentNum.setText(String.valueOf(item.getCurrentNumber()));
		progressBar_percentage.setValue(item.getCurrentNumber());
	}
	
	@Override
	public String toString() {
		return "subFrame has item: " + item.toString();
	}

}
