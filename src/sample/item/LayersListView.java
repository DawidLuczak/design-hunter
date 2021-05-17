package sample.item;

import javafx.scene.control.ListCell;
import sample.workspace.content.Layer;

public class LayersListView extends ListCell<Layer> {
	
	
	@Override
	public void updateItem
			(
					Layer item,
					boolean empty
			)
	{
		super.updateItem(
				item, empty
		);
		
		if (item != null)
		{
			setText(
					item.getTitle()
			);
		}
	}
}
