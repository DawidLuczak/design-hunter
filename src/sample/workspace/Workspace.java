package sample.workspace;

public class Workspace {
	private String title;
	private int height, width;
	private WorkspaceContent workspaceContent;
	
	public Workspace(String title, int width, int height){
		this.title = title;
		this.height = height;
		this.width = width;
		this.workspaceContent = new WorkspaceContent(this);
	}
	
	public WorkspaceContent getWorkspaceContent() {
		return workspaceContent;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
}
