package chapter2;

import javax.swing.JButton;

/**
 * �����ڲ���/Lambda���õľֲ�����������final?
 * @author skywalker
 *
 */
public class LocalVariable {
	
	public static void main(String[] args) {
		String name = "skywalker";
		//name = "str";
		JButton button = new JButton();
		button.addActionListener(event -> System.out.println(name));
	}
	
}
