package _2024_07_10;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/* 限制 JTextField 只能填数字 */
public class NumericFilter extends DocumentFilter {
	@Override
    public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
        throws BadLocationException {
        fb.insertString(offset, text.replaceAll("[^0-9]", ""), attr);  // 只允许数字
    }
 
    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
        throws BadLocationException {
        fb.replace(offset, length, text.replaceAll("[^0-9]", ""), attrs);  // 只允许数字
    }
}
