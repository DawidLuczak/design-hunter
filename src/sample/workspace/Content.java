package sample.workspace;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class Content extends Canvas {
	
	
	public Content(Workspace workspace) {
		setWidth(workspace.getWidth());
		setHeight(workspace.getHeight());
		ContentInterface.setGraphicsContent(this, workspace);
		
	}
	
}
