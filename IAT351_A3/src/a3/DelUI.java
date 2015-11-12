package a3;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class DelUI {

	private static final int WINDOW_HEIGHT = 500;
	private static final int WINDOW_WIDTH = 500;
	private static final int TEXT_WIDTH = 20;

	private Model model;
	private Component comp;

	private BufferedReader fileInput;

	private JFrame window = new JFrame("A3");

	private GridBagConstraints c = new GridBagConstraints();
	private JLabel label2;

	JMenuBar menubar = new JMenuBar();
	JMenu menu = new JMenu("File");
	JMenuItem open = new JMenuItem("Open");
	JMenuItem save = new JMenuItem("Save");
	JMenuItem exit = new JMenuItem("Exit");

	JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files Only", "jpg", "png");

	JPanel panel = new JPanel();
	JLabel label = new JLabel("Saturation:");
	JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);

	File imagePathName = new File("default.png");

	public DelUI(Model model, Component comp) {
		this.model = model;
		this.comp = comp;

		BufferedImage img = Model.loadImage(imagePathName);
		final ImageIcon icon = new ImageIcon(img);
		label2 = new JLabel(icon);

		window.setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
		window.setVisible(true);

		// Menu bar
		window.setJMenuBar(menubar);
		menubar.add(menu);
		menu.add(open);
		menu.add(save);
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
		slider.setMinorTickSpacing(2);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels(10));
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;
		panel.add(slider, c);

		// Image
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 4;
		c.weightx = 1;
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		panel.add(label2, c);
		
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
					label2.setIcon(Model.doDesat(imagePathName, 1.0f));
					// This is where a real application would open the file.
					slider.addChangeListener(new ChangeListener() {
						@Override
						public void stateChanged(ChangeEvent e) {
							JSlider satSlider = (JSlider) e.getSource();
							if (!satSlider.getValueIsAdjusting()) {
								float satAmount = (float) satSlider.getValue() / 100;

								System.out.println(satAmount);
								label2.setIcon(Model.doDesat(imagePathName, satAmount));
							}
						}
					});
					save.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							JFileChooser fc = new JFileChooser();
							int sv = fc.showSaveDialog(fc);
							
							
							if (sv == JFileChooser.APPROVE_OPTION) {

								try {
									File svFile = fc.getSelectedFile();
									//
									Image imgSave = icon.getImage();
									
									BufferedImage export = new BufferedImage(imgSave.getWidth(null),imgSave.getHeight(null),BufferedImage.TYPE_INT_ARGB);
									Graphics2D g2 = export.createGraphics();
									g2.drawImage(imgSave, 0, 0, null);
									
									g2.dispose();
									ImageIO.write(export, "jpg", svFile);//new File("output"));
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							} else {
								System.out.println("Save Cancelled");
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
