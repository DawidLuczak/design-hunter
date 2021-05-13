package sample.workspace;


public interface ContentInterface {
	
	static void setGraphicsContent(Content content, Workspace workspace){
		content.getGraphicsContext2D().setFill(workspace.getWorkspaceContent().getColorFirst());
		content.getGraphicsContext2D().setStroke(workspace.getWorkspaceContent().getColorSecondary());
		content.getGraphicsContext2D().setLineWidth(workspace.getWorkspaceContent().getLineWidth());
	}
}
