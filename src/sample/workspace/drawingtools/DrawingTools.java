package sample.workspace.drawingtools;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import sample.workspace.Workspace;
import sample.workspace.WorkspaceContent;
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
	
	static void draw(WorkspaceContent workspaceContent, Point pointA, Point pointB){
		Canvas content = workspaceContent.getActiveLayer().getActiveContent();
		
		if (workspaceContent.getShape() != null) {
			if (workspaceContent.getShape().equals(Shapes.RECTANGLE)) {
				drawRectangle(content.getGraphicsContext2D(), pointA, pointB, workspaceContent.getFillType());
			} else if (workspaceContent.getShape().equals(Shapes.CIRCLE)){
				drawEllipse(content.getGraphicsContext2D(), pointA, pointB, workspaceContent.getFillType());
			} else if (workspaceContent.getShape().equals(Shapes.TRIANGLE)) {
				drawTriangle(content.getGraphicsContext2D(), pointA, pointA.distance(pointB), pointA.angle(pointB), workspaceContent.getFillType());
			} else if (workspaceContent.getShape().equals(Shapes.LINE)) {
				drawLine(content.getGraphicsContext2D(), pointA, pointB);
			}
		}
	}
}
