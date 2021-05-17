package sample.library.geometry.draw;

import javafx.scene.input.MouseEvent;
import sample.library.geometry.MyLine;
import sample.workspace.Workspace;

public class DrawLine extends MyLine {
	
	
	public DrawLine
			(
					Workspace workspace
			)
	{
		super(
				workspace
		);
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
		
		getContent().getGraphicsContext2D().stroke();
	}
}
