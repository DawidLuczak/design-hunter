package sample.workspace.content;

import javafx.scene.Group;

public class Layer {
	
	private String title;
	private Group group;
	private Content activeContent;
	
	
	public Layer
			(
					String title
			)
	{
		this.title = title;
		this.group = new Group();
	}
	
	
	public void addContentElement
			(
					Content content
			)
	{
		this.group.getChildren().add(
				content
		);
		this.activeContent = content;
	}
	
	
	public void removeContentElement()
	{
		if (activeContent != null)
		{
			group.getChildren().remove(
					activeContent
			);
		}
	}
	
	
	public Content getObject
			(
					int i
			)
	{
		if (group.getChildren().size() > i)
		{
			return (Content) group.getChildren().get(i);
		}
		else
		{
			return null;
		}
	}
	
	
	public String getTitle()
	{
		return title;
	}
	
	
	public Group getGroup()
	{
		return group;
	}

	
	public Content getActiveContent()
	{
		return activeContent;
	}
	
	
	public void setActiveContent
			(
					Content activeContent
			)
	{
		this.activeContent = activeContent;
	}
}
