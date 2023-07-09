package hust.soict.globalict.aims.screen;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddItemToStoreScreen {
	protected AimsScreen aims;
	
	public AddItemToStoreScreen() {
	}
	
	public AddItemToStoreScreen(AimsScreen aims) {
		this.aims = aims;
	}
	
	public JLabel label(String text) {
		JLabel label = new JLabel(text);
		return label;
	}
	
	public JTextField tfInput() {
		JTextField tf = new JTextField();
		return tf;
	}

	public JButton addItemBtn() {
		JButton addBtn = new JButton("Add to store");
		return addBtn;
	}
}
