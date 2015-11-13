package a3;

import java.io.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Component extends JComponent{
	
	public JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 100);
	
	public void addSliderComp() {
		slider.setMinorTickSpacing(2);
		slider.setMajorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setLabelTable(slider.createStandardLabels(10));
	}
	
	public void addChListen(final JLabel placeholder, final File imagePathName) {
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider satSlider = (JSlider) e.getSource();
				if (!satSlider.getValueIsAdjusting()) {
					float satAmount = (float) satSlider.getValue() / 100;
					//System.out.println(satAmount);
					placeholder.setIcon(Model.doDesat(imagePathName, satAmount));
				}
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
