package a3;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Model {

	public void doDesat(BufferedImage img, float am) {

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
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
