package task4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PointConnector {

	Logger logger = LogManager.getLogger(PointConnector.class);
	Point startPoint = new Point(0,0);
	String fileName = "coordinates.txt";

	public static void main(String[] args) {
		PointConnector pc = new PointConnector();
		if(args.length == 1){
			pc.fileName = args[0];
		}		
		pc.findPath();
		

	}

	public void findPath() {
		//read list of points from file
		File file = new File(fileName);
		ArrayList<Point> points = new ArrayList<Point>();
		try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
			String line;
			String[] coordinates = new String[2];
			while ((line = bf.readLine()) != null) {
				coordinates = line.split(",");
				points.add(new Point(coordinates[0], coordinates[1]));
			}

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
		
		Point[] pnts = points.toArray(new Point[points.size()]);
		//sort array by x first coordinate to get start point
		Arrays.sort(pnts, new XYOrderComparator());		
		startPoint = pnts[0];
		//sort array by polar coordinates regarding startPoint
		Arrays.sort(pnts,1,pnts.length,new OrientationOrderComparator());
		for(Point p : pnts){
			System.out.println("("+ p.getX() + "," + p.getY() + ")");
		}

	}
	
	/**
	 * Class for comparing Points by X coordinate
	 * 
	 */
	private class XYOrderComparator implements Comparator<Point>{
		@Override
		public int compare(Point o1, Point o2) {
			if(o1.getX()>o2.getX()){
				return 1;
			}else if(o1.getX()<o2.getX()){
				return -1;
			}else{
				return o1.getY() - o2.getY();
			}
		
		}
		
	}
	
	
	/**
	 * Class for comparing Points by polar coordinate
	 * 
	 */
	private class OrientationOrderComparator implements Comparator<Point>{
		@Override
		public int compare(Point o1, Point o2) {
			if (o1.getAngle(startPoint)>o2.getAngle(startPoint)){
				return 1;
			}else	if(o1.getAngle(startPoint)<o2.getAngle(startPoint)){
				return -1;
			
			}else{
			return o1.getY() - o2.getY();}
		}
		
	}	
	
}