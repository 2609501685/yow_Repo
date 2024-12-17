package _2024_07_10;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AddJPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JButton button_import = new JButton("导入");
	private JButton button_export = new JButton("导出");
	private JButton button_add = new JButton("添加");
	
	private CenterFrame centerFrame = null;
	
	public AddJPanel(CenterFrame centerFrame) {
		this.centerFrame = centerFrame;
		init();
	}
	
	private void init() {
		this.setLayout(new FlowLayout());
		this.add(button_import);
		this.add(button_export);
		this.add(button_add);
		
		button_import.addActionListener(this);
		button_export.addActionListener(this);
		button_add.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if (button == button_add) {
			new AddFrame(centerFrame);
		} else if (button == button_import) {
			new ImportJFrame(centerFrame);
		} else if (button == button_export) {
			new ExportJFrame(centerFrame);
		}
	}
	
}
