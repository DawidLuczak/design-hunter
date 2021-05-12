package sample.workspace;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;

import java.util.concurrent.atomic.AtomicReference;

public class Layer {
	private String title;
	private Canvas canvas, viewMiniCanvas;
	private GraphicsContext graphicsContext, viewMiniGraphicsContext;
	
	Layer(String title, double width, double height){
		this.title = title;
		this.canvas = new Canvas(width, height);
		this.graphicsContext = canvas.getGraphicsContext2D();
		this.viewMiniCanvas = new Canvas(20, 20);
		this.viewMiniGraphicsContext = viewMiniCanvas.getGraphicsContext2D();
	}
	
	protected void setFill(Color color){
		graphicsContext.setFill(color);
	}
	
	protected void setStroke(Color color){
		graphicsContext.setStroke(color);
	}
	
	public String getTitle() {
		return title;
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}
	
	public Canvas getViewMiniCanvas() {
		return viewMiniCanvas;
	}
	
	public GraphicsContext getViewMiniGraphicsContext() {
		return viewMiniGraphicsContext;
	}
	
}
