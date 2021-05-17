package sample.library;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sample.workspace.WorkspaceContent;

public enum FillType {
	
	STROKE, FILL, FULL;
	

	
	public static void setFillTypeSwitch
			(
					WorkspaceContent content, GraphicsContext context
			)
	{
		switch (
				content.getFillType()
		)
		{
			case FILL -> {
				context.setStroke(
						Color.TRANSPARENT
				);
				context.setFill(
						content.getColorFirst()
				);
			}
			case STROKE -> {
				context.setFill(
						Color.TRANSPARENT
				);
				context.setStroke(
						content.getColorSecondary()
				);
			}
			default -> {
				context.setFill(
						content.getColorFirst()
				);
				context.setStroke(
						content.getColorSecondary()
				);
			}
		}
	}
	
}
