package xml;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class TestMain {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
//		XMLParse xmlParse = new XMLParse("src/xml/xmlText.xml");
//		System.out.println(xmlParse.getItems());
		
//		JFileChooser fileChooser = new JFileChooser();
//		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
//		int retVal = fileChooser.showOpenDialog(null);
//		if (retVal == JFileChooser.APPROVE_OPTION) {
//			File selectFile = fileChooser.getSelectedFile();
//			System.out.println("selectFile: " + selectFile.getAbsolutePath());
//		}
		
		JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(".")); // 设置当前目录为当前目录
 
        // 显示打开文件对话框
        int result = fileChooser.showOpenDialog(null);
 
        // 检查用户是否点击了打开按钮
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("No file was selected.");
        }
		
	}

}
