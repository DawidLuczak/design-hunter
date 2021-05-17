package sample.library.geometry;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import sample.library.event.DrawingTool;
import sample.workspace.Workspace;

public class MyRectangle extends DrawingTool {
	
	
	public MyRectangle
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
		rectangle(
				getContent().getGraphicsContext2D(),
				getX(), getY(),
				event.getX(), event.getY()
		);
	}
	
	
	protected void rectangle
			(
					GraphicsContext graphicsContext,
					double ax, double ay,
					double bx, double by
			)
	{
		graphicsContext.beginPath();
		graphicsContext.moveTo(
				ax, ay
		);
		graphicsContext.lineTo(
				ax, by
		);
		graphicsContext.lineTo(
				bx, by
		);
		graphicsContext.lineTo(
				bx, ay
		);
		graphicsContext.lineTo(
				ax, ay
		);
	}
	
	
	
}
