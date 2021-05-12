package sample.workspace.drawingtools.shapes;

public enum Shapes {
	
	TRIANGLE(false),
	RECTANGLE(false),
	CIRCLE(false),
	LINE(false),
	BRUSH(true);
	
	boolean shouldEventOnDrag;
	
	Shapes(boolean shouldEventOnDrag){
		this.shouldEventOnDrag = shouldEventOnDrag;
	}

	
}
