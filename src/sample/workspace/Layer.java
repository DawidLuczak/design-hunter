package sample.workspace;

import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;

public class Layer {
	private String title;
	private StackPane stackPane;
	private Canvas activeContent;
	
	public Layer(String title){
		this.title = title;
		this.stackPane = new StackPane();
	}
	
	public void addContentElement(Canvas content){
		this.stackPane.getChildren().add(content);
		this.activeContent = content;
	}
	
	public void removeContentElement(){
		if (activeContent != null)
			stackPane.getChildren().remove(activeContent);
			
	}
	
	public Canvas getObject(int i){
		if (stackPane.getChildren().size() > i){
			return (Canvas) stackPane.getChildren().get(i);
		} else {
			return null;
		}
	}
	
	public String getTitle() {
		return title;
	}
	
	
	public StackPane getStackPane() {
		return stackPane;
	}
	
	public Canvas getActiveContent() {
		return activeContent;
	}
	
	public void setActiveContent(Canvas activeContent) {
		this.activeContent = activeContent;
	}
}
