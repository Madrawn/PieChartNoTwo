package madrawn.misc.picturePie.picturepiechart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PieChart {
	ArrayList<Slice> slices = new ArrayList<>();
	
	public void drawPie(BufferedImage img){
		double total = 0.0;
		
		Graphics2D gp = (Graphics2D) img.getGraphics();
		for (Iterator<Slice> iterator = slices.iterator(); iterator.hasNext();) {
			Slice slice = (Slice) iterator.next();
			total += slice.value;
			
		}
		double curValue = 0.0;
		int startAngle = 0;
		
		startAngle = 0;
		for (int i = 0; i < slices.size(); i++) {
			//startAngle = (int) Math.round((curValue * 360 / total));
			int arcAngle = (int) Math.round((slices.get(i).value * 360 / total));
			gp.setColor(slices.get(i).color);
			//Correct for the rounding errors
			//Last color might therefore be over represented by, i think,
			//at max 1/720
			if (startAngle + arcAngle == 359) {
				arcAngle++;
			}
			gp.fillArc(0, 0, img.getWidth(), img.getHeight(), startAngle, arcAngle);
			curValue += slices.get(i).value;
			startAngle += arcAngle;
		}
		
		gp.setColor(Color.BLACK);
		gp.setStroke(new BasicStroke(6));
		gp.draw(new Ellipse2D.Double(3, 3, img.getWidth()-6, img.getHeight()-6));
		
	}
	
	void addSlice(Slice s){
		slices.add(s);
	}
	
	void clearSlices(){
		slices.clear();
	}
	
	List<Slice> getSlices(){
		
		return slices;	
	}
	
	
	

}
