package sample.library;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import sample.library.event.DrawingTool;
import sample.workspace.Workspace;

public class Brush extends DrawingTool {
	
	
	public Brush
			(
					Workspace workspace
			)
	{
		super();
		setMouseEvents(
				workspace
		);
	}
	
	
	private void draw
			(
					MouseEvent event,GraphicsContext context
			)
	{
		brush(
				event, context
		);
	}
	
	
	private void brush
			(
					MouseEvent event, GraphicsContext context
			)
	{
		context.beginPath();
		context.lineTo(
				event.getX(), event.getY()
		);
		context.moveTo(
				event.getX(), event.getY()
		);
		context.stroke();
		context.closePath();
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
		draw(
				event, getContent().getGraphicsContext2D()
		);
	}
	
	@Override
	protected void onMousePress
			(
					MouseEvent event, Workspace workspace
			)
	{
		super.onMousePress(
				event, workspace
		);
	}
	
	
	@Override
	protected void onMouseReleased
			(
					MouseEvent event
			)
	{
		getContent().getGraphicsContext2D().closePath();
	}

}
