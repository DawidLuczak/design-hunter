package sample.workspace.drawingtools;

import javafx.scene.canvas.GraphicsContext;
import sample.workspace.drawingtools.shapes.FillType;

public interface DrawRectangle {
	
	static void drawRectangle(GraphicsContext graphicsContext, Point pointA, Point pointB, FillType fillType) {
		
		graphicsContext.beginPath();
		graphicsContext.moveTo(pointA.getX(), pointA.getY());
		graphicsContext.lineTo(pointA.getX(), pointB.getY());
		graphicsContext.lineTo(pointB.getX(), pointB.getY());
		graphicsContext.lineTo(pointB.getX(), pointA.getY());
		graphicsContext.lineTo(pointA.getX(), pointA.getY());
		
		if (fillType == null || fillType.equals(FillType.FULL)){
			graphicsContext.fill();
			graphicsContext.stroke();
		} else if (fillType.equals(FillType.FILL)) {
			graphicsContext.fill();
		} else if (fillType.equals(FillType.STROKE)) {
			graphicsContext.stroke();
		}
	}
	
}
