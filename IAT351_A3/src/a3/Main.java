package a3;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Model model = new Model();
				Component comp = new Component();
				DelUI delui = new DelUI(model, comp);
			}
		});
	}

}
