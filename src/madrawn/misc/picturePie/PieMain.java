package madrawn.misc.picturePie;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import madrawn.misc.picturePie.picturepiechart.PPCFactory;
import madrawn.misc.picturePie.picturepiechart.PicturePieChart;

public class PieMain {

	public static void main(String[] args) {
		if (args.length!=2) {
			System.err.println("Arguments: <FilePath> <OutputPath>");
			return;
		}
		System.out.println(args[0] + " " + args[1]);
		File inputFile, outputFile;
		
		inputFile = new File(args[0]);
		outputFile = new File(args[1]);
		
		BufferedImage bi;
		try {
			bi = ImageIO.read(inputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		@SuppressWarnings("static-access")
		PicturePieChart pi = PPCFactory
				.setUseAlpha(true)
				.setImage(bi)
				.create();
		
		BufferedImage out = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
				
		pi.drawPie(out);
		
		try {
			ImageIO.write(out, "png", outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
	}

}
