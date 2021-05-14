package sample.workspace.drawingtools;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Ellipse;
import sample.workspace.drawingtools.shapes.FillType;

public interface DrawCircle {
	
	static void circleFill(GraphicsContext graphicsContext, Ellipse ellipse){
		graphicsContext.fillOval(ellipse.getCenterX() - (ellipse.getRadiusX() / 2), ellipse.getCenterY() - (ellipse.getRadiusY() / 2), Math.sqrt(Math.PI) * ellipse.getRadiusX(), Math.sqrt(Math.PI) * ellipse.getRadiusY());
	}
	
	static void circleStroke(GraphicsContext graphicsContext, Ellipse ellipse){
		graphicsContext.strokeOval(ellipse.getCenterX() - (ellipse.getRadiusX() / 2), ellipse.getCenterY() - (ellipse.getRadiusY() / 2), Math.sqrt(Math.PI) * ellipse.getRadiusX(), Math.sqrt(Math.PI) * ellipse.getRadiusY());
	}
	
	static void drawEllipse(GraphicsContext graphicsContext, Point pointA, Point pointB, FillType fillType){
		if (fillType == null) {
			fillType = FillType.FULL;
		}
		
		Ellipse ellipse = new Ellipse(pointA.getX(), pointA.getY(), pointA.distanceX(pointB), pointA.distanceY(pointB));
		if (fillType.equals(FillType.FILL)) {
			circleFill(graphicsContext, ellipse);
		} else if (fillType.equals(FillType.STROKE)) {
			circleStroke(graphicsContext, ellipse);
		} else {
			circleFill(graphicsContext, ellipse);
			circleStroke(graphicsContext, ellipse);
		}
	}
}
