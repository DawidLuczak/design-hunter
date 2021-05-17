package sample.library.geometry;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import sample.library.event.DrawingTool;
import sample.workspace.Workspace;


public class MyCircle extends DrawingTool {
	
	
	public MyCircle
			(
					Workspace workspace
			)
	{
		super();
		setMouseEvents(
				workspace
		);
	}
	
	private void circle
			(
					GraphicsContext context,
					double ax, double ay,
					double rx, double ry,
					double angle, double startingAngle
			)
	{
		double minX = Math.min(ax, rx);
		double minY = Math.min(ay, ry);
		
		context.beginPath();
		context.moveTo(
				minX, minY
		);
		getContent().getGraphicsContext2D().arc(
				minX, minY,
				Math.abs(rx - ax),
				Math.abs(ry - ay),
				startingAngle,
				angle
		);
	}
	
	
	@Override
	protected void onMouseDrag
			(
					MouseEvent event, Workspace workspace
			)
	{
		super.onMouseDrag(
				event, workspace
		);
		circle(
				getContent().getGraphicsContext2D(),
				getX(), getY(),
				event.getX(), event.getY(),
				360d, 0d
		);
	}
	
	
}
