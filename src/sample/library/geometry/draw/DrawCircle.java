package sample.library.geometry.draw;

import javafx.scene.input.MouseEvent;
import sample.library.geometry.MyCircle;
import sample.workspace.Workspace;

public class DrawCircle extends MyCircle {
	
	
	public DrawCircle
			(
					Workspace workspace
			)
	{
		super(
				workspace
		);
		setMouseEvents(workspace);
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
		drawShape(
				getContent().getGraphicsContext2D(),
				workspace.getWidth(), workspace.getHeight()
		);
	}
	
	
}
