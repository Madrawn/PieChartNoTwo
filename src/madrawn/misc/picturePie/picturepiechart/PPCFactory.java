package madrawn.misc.picturePie.picturepiechart;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class PPCFactory {
	static BufferedImage image = null;
	static Color colorToIgnore = Color.WHITE;
	static boolean useAlpha = true;
	
	public static PPCFactory setColorToIgnore(Color c){
		colorToIgnore = c;
		return new PPCFactory();
	}
	
	public static PPCFactory setUseAlpha(boolean b){
		useAlpha = b;
		return new PPCFactory();
	}
	
	public static PPCFactory setImage(BufferedImage bi){
		image = bi;
		return new PPCFactory();
	}
	
	public static PicturePieChart create(){
		
		return new PicturePieChart(image, colorToIgnore, useAlpha);
	}
}
