package _2024_07_10;

import java.awt.Dimension;
import java.awt.Font;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class CenterFrame extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private static int num = 1;
	private HashMap<Integer, SubFrame> map = new HashMap<Integer, SubFrame>();
	
	private JLabel label_default = new JLabel("没有任何事项哦~");
	
	public CenterFrame() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(new BevelBorder(BevelBorder.RAISED));
		this.add(label_default);
		label_default.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
		label_default.setFont(new Font(label_default.getText(), Font.CENTER_BASELINE, 20));
		label_default.setHorizontalAlignment(JLabel.CENTER);
		label_default.setVerticalAlignment(JLabel.CENTER);
	}
	
	public LinkedList<Item> getItems() {
		LinkedList<Item> items = new LinkedList<Item>();
		for (SubFrame subFrame : map.values()) {
			items.add(subFrame.getItem());
		}
		return items;
	}
	
	public void add(Item item) {
		if (label_default.isEnabled()) {
			this.remove(label_default);
		}
		SubFrame subFrame = new SubFrame(this, item, num);
		map.put(num++, subFrame);
		this.add(subFrame);
		this.getParent().validate();
		this.getParent().repaint();
	}
	
	public void add(LinkedList<Item> items) {
		if (label_default.isEnabled()) {
			this.remove(label_default);
		}
		for (Item item : items) {
			SubFrame subFrame = new SubFrame(this, item, num);
			map.put(num++, subFrame);
			this.add(subFrame);
		}
		this.getParent().validate();
		this.getParent().repaint();
	}
	
	public void removeItem(int num) {
		SubFrame subFrame = map.getOrDefault(num, null);
		if (subFrame == null) {
			return;
		}
		map.remove(num);
		this.remove(subFrame);
		if (map.size() == 0) {
			this.add(label_default);
		}
		this.getParent().validate();
		this.getParent().repaint();
	}

}
