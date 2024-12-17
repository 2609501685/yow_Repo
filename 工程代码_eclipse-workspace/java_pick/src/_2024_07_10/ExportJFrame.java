package _2024_07_10;

import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ExportJFrame extends JFrame implements ActionListener,DocumentListener {

	private static final long serialVersionUID = 1L;

	private JLabel label_selectFolder = new JLabel("选择文件夹： ");
	private JLabel label_fileName = new JLabel("文件名： ");
	private JLabel label_exportTo = new JLabel("导出到： ");
	
	private JTextField textField_selectFolder = new JTextField();
	private JTextField textField_fileName = new JTextField();
	private JTextField textField_exportTo = new JTextField();
	
	private JButton button_selectFolder = new JButton("选择文件夹");
	private JLabel label_seize = new JLabel();
	private JButton button_comp = new JButton("完成");
	
	private CenterFrame centerFrame = null;
	
	public ExportJFrame(CenterFrame centerFrame) {
		this.centerFrame = centerFrame;
		init();
		this.setTitle("导出数据");
		this.setSize(600, 150);
		
		this.setLocationRelativeTo(null);
		Point pointOnScreen = this.getLocation();
		pointOnScreen.setLocation(pointOnScreen.getX() +  500, pointOnScreen.getY());
		this.setLocation(pointOnScreen);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void init() {
		this.setLayout(new GridLayout(3, 3));
		this.add(label_selectFolder);
		this.add(textField_selectFolder);
		this.add(button_selectFolder);
		
		this.add(label_fileName);
		this.add(textField_fileName);
		this.add(label_seize);
		
		this.add(label_exportTo);
		this.add(textField_exportTo);
		this.add(button_comp);
		
//		C:\Users\26095\Desktop\test.xml
		textField_exportTo.setText("C:\\Users\\26095\\Desktop\\test.xml");
		textField_exportTo.setEnabled(false);
		
		textField_selectFolder.getDocument().addDocumentListener(this);
		textField_fileName.getDocument().addDocumentListener(this);
		
		button_selectFolder.addActionListener(this);
		button_comp.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if (button == button_selectFolder) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int retVal = fileChooser.showOpenDialog(null);
			if (retVal == JFileChooser.APPROVE_OPTION) {
				File selectFile = fileChooser.getSelectedFile();
				textField_selectFolder.setText(selectFile.getAbsolutePath());
			}
	
		} else if (button == button_comp) {
			LinkedList<Item> items = centerFrame.getItems();
			boolean isSaveSuccess = SaveItemsToFile(items, textField_exportTo.getText());
			JOptionPane.showMessageDialog(null, isSaveSuccess ? "保存成功" : "保存失败");
			if (isSaveSuccess) {
				dispose();
			}
		}
	}

	private String ItemsTransToFileData(LinkedList<Item> items) {
		StringBuilder builder = new StringBuilder();
		builder.append("<items>\n");
		for (Item item : items) {
			builder.append("	<item>\n");
			builder.append("		<name>").append(item.getItemName()).append("</name>\n");
			builder.append("		<currentNum>").append(item.getCurrentNumber()).append("</currentNum>\n");
			builder.append("		<totalnum>").append(item.getTotalNumber()).append("</totalnum>\n");
			builder.append("	</item>\n");
		}
		builder.append("</items>\n");
		return builder.toString();
	}
	
	private boolean isAvailbleFilePath(String filePath) {
		
		return true;
	}
	
	private boolean SaveItemsToFile(LinkedList<Item> items, String filePath) {
		if (!isAvailbleFilePath(filePath)) {
			return false;
		}
		
		String data = ItemsTransToFileData(items);
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
			writer.write(data);
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private void mergeFilePath() {
		textField_exportTo.setText(textField_selectFolder.getText() + "\\" + textField_fileName.getText());
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		mergeFilePath();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		mergeFilePath();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		mergeFilePath();
	}
	
}
