package sample.library.event;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import sample.workspace.Workspace;
import sample.workspace.content.Content;

public class DrawingTool extends MouseLocation{
	
	private Content content;
	
	
	protected DrawingTool()
	{
		super();
	}
	
	
	protected void setMouseEvents
			(
					Workspace workspace
			)
	{
		workspace.getWorkspaceContent().getStackPane().setOnMousePressed(
				event ->
						onMousePress(
								event, workspace
						)
		);
		workspace.getWorkspaceContent().getStackPane().setOnMouseDragged(
				event ->
						onMouseDrag(
								event, workspace
						)
		);
		workspace.getWorkspaceContent().getStackPane().setOnMouseReleased(
				this::onMouseReleased
		);
	}

	
	protected void onMousePress
			(
					MouseEvent event, Workspace workspace
			)
	{
		mousePressed(
				event
		);
		content = new Content(
				workspace
		);
	}
	
	
	protected void onMouseDrag
			(
					MouseEvent event, Workspace workspace
			)
	{
		content.resize(
				Math.abs(
						getX() - event.getX()
				),
				Math.abs(
						getY() - event.getY()
				)
		);
	}
	
	
	protected void onMouseReleased
			(
					MouseEvent event
			)
	{ }
	
	
	protected void drawShape
			(
					GraphicsContext context,
					double width, double height
			)
	{
		context.clearRect(
				0, 0,
				width, height
		);
		context.fill();
		context.stroke();
		context.closePath();
	}
	
	
	public Content getContent()
	{
		return content;
	}
	
}
