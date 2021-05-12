package sample;

import javafx.scene.control.ListCell;
import sample.workspace.Layer;

public class LayersListView extends ListCell<Layer> {
	@Override
	public void updateItem(Layer item, boolean empty) {
		super.updateItem(item, empty);
		if (item != null) {
			setGraphic(item.getViewMiniCanvas());
			setText(item.getTitle());
		}
	}
	
}
