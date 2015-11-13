package a3;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class DelUI extends Component {

	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 500;

	private Model model;
	private Component comp;

	private JFrame window = new JFrame("A3");

	private GridBagConstraints c = new GridBagConstraints();
	private JLabel placeholder;

	JMenuBar menubar = new JMenuBar();
	JMenu menu = new JMenu("File");
	JMenuItem open = new JMenuItem("Open");
	JMenuItem exit = new JMenuItem("Exit");

	JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files Only", "jpg", "png");

	JPanel panel = new JPanel();
	JLabel label = new JLabel("Saturation:");

	File imagePathName = new File("default.png");

	public DelUI(Model model, final Component comp) {
		this.model = model;
		this.comp = comp;

		BufferedImage img = Model.loadImage(imagePathName);
		final ImageIcon icon = new ImageIcon(img);
		placeholder = new JLabel(icon);

		window.setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
		window.setVisible(true);

		// Menu bar
		window.setJMenuBar(menubar);
		menubar.add(menu);
		menu.add(open);
		menu.add(exit);

		window.getContentPane().add(panel);
		panel.setLayout(new GridBagLayout());

		// Label
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 4;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(5, 5, 5, 5);
		panel.add(label, c);

		// Slider
		comp.addSliderComp();
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		panel.add(comp.slider, c);

		// Image
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		panel.add(placeholder, c);
		
		open.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Images Files (.jpg, .png)", "jpg", "png");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(chooser);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					final File imagePathName = chooser.getSelectedFile();
					placeholder.setIcon(Model.doDesat(imagePathName, 1.0f));
					
					// This is the file is opened
					comp.slider.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							JSlider satSlider = (JSlider) e.getSource();
							if (!satSlider.getValueIsAdjusting()) {
								float satAmount = (float) satSlider.getValue() / 100;

								System.out.println(satAmount);
								placeholder.setIcon(Model.doDesat(imagePathName, satAmount));
							}
						}
					});
				} else {
					System.out.println("Open cancelled");
				}

			}

		});
		
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
