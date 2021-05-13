package sample.workspace;

import javafx.scene.layout.StackPane;

public class Layer {
	private String title;
	private StackPane stackPane;
	private Content activeContent;
	
	public Layer(String title){
		this.title = title;
		this.stackPane = new StackPane();
	}
	
	public void addContentElement(Content content){
		this.stackPane.getChildren().add(content);
		this.activeContent = content;
	}
	
	public void removeContentElement(){
		if (activeContent != null)
			stackPane.getChildren().remove(activeContent);
	}
	
	public String getTitle() {
		return title;
	}
	
	
	public StackPane getStackPane() {
		return stackPane;
	}
	
	public Content getActiveContent() {
		return activeContent;
	}
	
	public void setActiveContent(Content activeContent) {
		this.activeContent = activeContent;
	}
}
