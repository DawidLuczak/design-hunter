package sample.library.event;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class MouseLocation {
	
	private Point2D point;
	
	
	protected MouseLocation()
	{
		point = new Point2D(0,0);
	}
	
	
	protected void mousePressed
			(
					MouseEvent event
			)
	{
		this.point = new Point2D(
				event.getX(), event.getY()
		);
	}
	
	
	public double getX()
	{
		return point.getX();
	}
	
	
	public double getY()
	{
		return point.getY();
	}
	
}
