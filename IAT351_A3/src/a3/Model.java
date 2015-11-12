package a3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.*;

public class Model {

	public static BufferedImage loadImage(File imagePathName) {
		BufferedImage img = null;

		try {
			img = ImageIO.read(imagePathName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}

	public static ImageIcon doDesat(File imagePathName, float am) {
		BufferedImage img = loadImage(imagePathName);

		for (int i = 0; i < img.getWidth(); i++) {
			for (int j = 0; j < img.getHeight(); j++) {
				Color imgRGB = new Color(img.getRGB(i, j));
				float[] hsb = Color.RGBtoHSB(imgRGB.getRed(), imgRGB.getGreen(), imgRGB.getBlue(), null);

				hsb[1] = hsb[1] * am;

				int imgHSB2RGB = Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]);
				Color desatP = new Color(imgHSB2RGB);
				int finalrgb = desatP.getRGB();

				img.setRGB(i, j, finalrgb);
			}
		}
		ImageIcon imgc = new ImageIcon(img);
		return imgc;
	}

	public void svImg() {
		JFileChooser fc = new JFileChooser();
		int sv = fc.showSaveDialog(fc);
		if (sv == JFileChooser.APPROVE_OPTION) {
			File svFile = fc.getSelectedFile();
			// This is where a real application would save the file.
		} else {
			System.out.println("Save Cancelled");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
