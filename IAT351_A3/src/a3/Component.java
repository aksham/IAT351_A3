package a3;

import javax.swing.JComponent;
import javax.swing.JSlider;
import javax.swing.event.*;
import javax.swing.event.*;

public class Component extends JComponent implements ChangeListener{
	public void stateChanged(ChangeEvent e) {
        JSlider satSlider = (JSlider)e.getSource();
        if (!satSlider.getValueIsAdjusting()) {
            float satAmount = (float)satSlider.getValue();
        }    
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
