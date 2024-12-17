package _2024_07_10;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import xml.XMLParse;

public class ImportJFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel label_filapath = new JLabel("选择文件： ");
	private JTextField textField_filepath = new JTextField();
	private JButton button_selectFile = new JButton("选择文件");
	private JLabel label_seize_1 = new JLabel();
	private JLabel label_seize_2 = new JLabel();
	private JButton button_comp = new JButton("导入");
	
	private CenterFrame centerFrame = null;
	
	public ImportJFrame(CenterFrame centerFrame) {
		this.centerFrame = centerFrame;
		init();
		this.setTitle("导入数据");
		this.setSize(600, 150);
		
		this.setLocationRelativeTo(null);
		Point pointOnScreen = this.getLocation();
		pointOnScreen.setLocation(pointOnScreen.getX() +  500, pointOnScreen.getY());
		this.setLocation(pointOnScreen);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void init() {
		this.setLayout(new GridLayout(2, 3));
		this.add(label_filapath);
		this.add(textField_filepath);
		this.add(button_selectFile);
		
		this.add(label_seize_1);
		this.add(label_seize_2);
		this.add(button_comp);
		
		textField_filepath.setText("C:/Users/26095/eclipse-workspace/java_pick/bin/xml/xmlText.xml");
		
		button_selectFile.addActionListener(this);
		button_comp.addActionListener(this);
	}
	
	private void Import() {
		String filePath = textField_filepath.getText();
		XMLParse xmlParse = null;
		try {
			xmlParse = new XMLParse(filePath);
		} catch (ParserConfigurationException | SAXException | IOException e1) {
			e1.printStackTrace();
		}
		if (xmlParse != null) {
			centerFrame.add(xmlParse.getItems());
			dispose();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if (button == button_comp) {
			Import();
		} else if (button == button_selectFile) {
			JFileChooser fileChooser = new JFileChooser();
	        fileChooser.setCurrentDirectory(new File("."));
	        int result = fileChooser.showOpenDialog(null);
	        if (result == JFileChooser.APPROVE_OPTION) {
	            File selectedFile = fileChooser.getSelectedFile();
	            textField_filepath.setText(selectedFile.getAbsolutePath());
	        }
		}
		
	}

}
