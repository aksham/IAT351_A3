package a3;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.event.*;
import javax.swing.event.*;

public class Component extends JComponent{
	
	public JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
	
	public void addSliderComp() {
		slider.setMinorTickSpacing(2);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels(10));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
