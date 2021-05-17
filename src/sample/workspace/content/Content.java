package sample.workspace.content;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import sample.workspace.Workspace;
import sample.library.FillType;
import sample.workspace.WorkspaceContent;

public class Content extends Canvas {
	
	private String title;
	
	
	public Content(){
		super();
	}
	
	
	public Content
			(
					double width, double height
			)
	{
		super(
				width, height
		);
	}
	
	
	public Content
			(
					Workspace workspace
			)
	{
		super(
				workspace.getWidth(), workspace.getHeight()
		);
		getGraphicsContext2D().setLineWidth(
				workspace.getWorkspaceContent().getLineWidth()
		);
		FillType.setFillTypeSwitch(
				workspace.getWorkspaceContent(), getGraphicsContext2D()
		);
		workspace.getWorkspaceContent().getActiveLayer().addContentElement(
				this
		);
	}
	
	
	public void copyGraphicsContext
			(
					GraphicsContext context
			)
	{
		getGraphicsContext2D().setFill(
				context.getFill()
		);
		getGraphicsContext2D().setStroke(
				context.getStroke()
		);
		getGraphicsContext2D().setLineWidth(
				context.getLineWidth()
		);
	}
	
	
	public void copyGraphicsContext
			(
					WorkspaceContent content
			)
	{
		getGraphicsContext2D().setFill(
				content.getColorFirst()
		);
		getGraphicsContext2D().setStroke(
				content.getColorSecondary()
		);
		getGraphicsContext2D().setLineWidth(
				content.getLineWidth()
		);
	}
	
	
	public void setLayout
			(
					double x, double y
			)
	{
		setLayoutX(x);
		setLayoutY(y);
	}
	
}
