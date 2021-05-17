package sample.library;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import sample.library.event.DrawingTool;
import sample.workspace.Workspace;
import sample.workspace.WorkspaceContent;

public class Rubber extends DrawingTool {
	
	
	public Rubber
			(
					Workspace workspace
			)
	{
		super();
		setMouseEvents(
				workspace
		);
	}
	
	
	private void rubber
			(
					GraphicsContext graphicsContext,
					double x, double y, double width
			)
	{
		graphicsContext.clearRect(
				x, y, width, width
		);
	}
	
	
	private void rubberDefault
			(
					WorkspaceContent content,
					double x, double y
			)
	{
		rubber(
				content.getActiveLayer().getActiveContent().getGraphicsContext2D(),
				x, y, content.getLineWidth()
		);
	}
	
	
	@Override
	protected void onMousePress
			(
					MouseEvent event, Workspace workspace
			)
	{
		mousePressed(
				event
		);
		workspace.getWorkspaceContent().getActiveLayer().getActiveContent().copyGraphicsContext(
				workspace.getWorkspaceContent()
		);
		rubberDefault(
				workspace.getWorkspaceContent(),
				event.getX(), event.getY()
		);
	}
	
	
	@Override
	protected void onMouseDrag
			(
					MouseEvent event, Workspace workspace
			)
	{
		mousePressed(
				event
		);
		rubberDefault(
				workspace.getWorkspaceContent(),
				event.getX(), event.getY()
		);
	}
}
