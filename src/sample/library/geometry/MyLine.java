package sample.library.geometry;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import sample.library.event.DrawingTool;
import sample.workspace.Workspace;

public class MyLine extends DrawingTool {
	
	
	public MyLine
			(
					Workspace workspace
			)
	{
		super();
		setMouseEvents(
				workspace
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
		line(
				getContent().getGraphicsContext2D(),
				event.getX(), event.getY(
				
				));
	}
	
	
	@Override
	protected void onMouseReleased
			(
					MouseEvent event
			)
	{
		super.onMouseReleased(
				event
		);
		getContent().getGraphicsContext2D().closePath();
	}
	
	
	private void line
			(
					GraphicsContext context,
					double x, double y
			)
	{
		context.beginPath();
		context.clearRect(
				0, 0,
				getContent().getWidth(), getContent().getHeight()
		);
		context.moveTo(
				getX(), getY()
		);
		context.lineTo(
				x, y
		);
	}
	
}
