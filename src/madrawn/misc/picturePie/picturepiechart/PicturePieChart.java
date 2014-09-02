package madrawn.misc.picturePie.picturepiechart;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class PicturePieChart extends PieChart {

	BufferedImage image;
	Color colorToIgnore;
	boolean useAlpha;
	
	public PicturePieChart(BufferedImage image, Color colorToIgnore,
			boolean useAlpha) {
		this.image = image;
		this.colorToIgnore = colorToIgnore;
		this.useAlpha = useAlpha;
		Map<Color, Integer> map = new HashMap<>();
		
		for (int x = 0; x < image.getWidth(); x++) {
			
			for (int y = 0; y < image.getHeight(); y++) {
				Color c = new Color(image.getRGB(x, y), useAlpha);
				if (useAlpha) {
					if (c.getAlpha() == 0) {
						continue;
					};
					
				}
				//TODO Maybe some code to ignore a color someday
				
				if (map.containsKey(c)) {
					map.put(c, map.get(c) + 1);
					
				} else {
					map.put(c, 1);
				}
				
				
				
			}
			
		}
		
        for (Entry<Color, Integer> entry : map.entrySet()) {
            Color c = entry.getKey();
            Integer i = entry.getValue();
            
            addSlice(new Slice(i, c));
            
        }
		
		
	}

	public void drawPie(BufferedImage img){
		super.drawPie(img);
		drawImageOnTop(img);
		
		
	}

	private void drawImageOnTop(BufferedImage img) {
		int imgWidth = img.getWidth();
		int imgHeigt = img.getHeight();
		int otherWidth = image.getWidth();
		int otherHeigth = image.getHeight();
		
		//I want the analysed Image to be 1/3 of the pie
		int targetWidth = imgWidth / 2;
		int targetHeigth = imgHeigt / 2;
		double scaleWidth = targetWidth / otherWidth;
		double scaleHeight = targetHeigth / otherHeigth;
		
		BufferedImage after ;
		AffineTransform at = new AffineTransform();
		at.scale(scaleWidth, scaleHeight);
		AffineTransformOp scaleOp = 
				   new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		after = scaleOp.filter(image, null);
		
		Graphics2D gp = (Graphics2D) img.getGraphics();
		
		int destX = (imgWidth / 2) - (after.getWidth() / 2);
		int destY = (imgHeigt / 2) - ( after.getHeight() / 2);
		
		
		gp.drawImage(after, destX, destY, null);
		
		
	}
}
