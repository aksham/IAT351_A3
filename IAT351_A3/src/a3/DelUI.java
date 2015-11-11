package a3;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

public class DelUI {

	private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 500;
    private static final int TEXT_WIDTH = 20;
    
    private BufferedReader fileInput;
    
    private JFrame window = new JFrame("A3");
    
    JMenuBar menubar = new JMenuBar();
    JMenu menu = new JMenu("File");
    JMenuItem open = new JMenuItem("Open");
    JMenuItem save = new JMenuItem("Save");
    JMenuItem exit = new JMenuItem("Exit");
    
    JFileChooser chooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files Only", "jpg", "png");
    
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Saturation:");
    JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    
    public DelUI() {
    	try{
    		BufferedImage img = ImageIO.read(new File("default.jpg"));
    	    ImageIcon icon = new ImageIcon(img);
    	    JLabel label2 = new JLabel(icon);
    		
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
	        GridBagConstraints c = new GridBagConstraints();
	        
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
        
    	}catch (IOException e) {
			e.printStackTrace();
		}
        
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
            public void run () {
                // Make and display the window.
                new DelUI();
            }
        });

	}

}
