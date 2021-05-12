package sample.workspace.drawingtools;

import javafx.scene.canvas.GraphicsContext;
import sample.workspace.drawingtools.shapes.FillType;
import sample.workspace.drawingtools.shapes.Shapes;
import static sample.workspace.drawingtools.DrawRectangle.drawRectangle;
import static sample.workspace.drawingtools.DrawCircle.drawEllipse;
import static sample.workspace.drawingtools.DrawTriangle.drawTriangle;
import static sample.workspace.drawingtools.DrawLine.drawLine;

public interface DrawingTools extends DrawCircle, DrawRectangle, DrawTriangle, DrawLine, Brush {
	
	static void draw(GraphicsContext graphicsContext, Shapes shape, FillType fillType, Point pointA, Point pointB){
		if (shape != null) {
			if (shape.equals(Shapes.RECTANGLE)) {
				drawRectangle(graphicsContext, pointA, pointB, fillType);
			} else if (shape.equals(Shapes.CIRCLE)){
				drawEllipse(graphicsContext, pointA, pointB, fillType);
			} else if (shape.equals(Shapes.TRIANGLE)) {
				drawTriangle(graphicsContext, pointA, pointA.distance(pointB), pointA.angle(pointB), fillType);
			} else if (shape.equals(Shapes.LINE)) {
				drawLine(graphicsContext, pointA, pointB);
			}
		}
	}
}
