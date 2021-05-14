package sample.workspace;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import sample.workspace.drawingtools.shapes.FillType;
import sample.workspace.drawingtools.shapes.Shapes;

public class WorkspaceContent{
	private final StackPane stackPane;
	private final ObservableList<Layer> layers;
	private Layer activeLayer;
	private Shapes shape;
	private FillType fillType;
	private Color colorFirst, colorSecondary;
	private double lineWidth;
	
	WorkspaceContent(Workspace workspace){
		this.stackPane = new StackPane();
		this.stackPane.setStyle("-fx-background-color: white");
		this.layers = FXCollections.observableArrayList();
		this.colorFirst = Color.BLACK;
		this.colorSecondary = Color.BLACK;
		this.lineWidth = 1;
		addLayer(new Layer("Layer 0"));
		
	}
	
	
/*	LAYERS MANAGEMENT  */
	
	
	protected void addLayer(Layer layer){
		layers.add(layer);
		stackPane.getChildren().addAll(layer.getStackPane());
		activeLayer = layer;
		showLayer(layers.size() - 1);
	}
	
	public void addDefaultLayer(){
		Layer layer = new Layer("Layer " + layers.size());
		layers.add(layer);
		stackPane.getChildren().addAll(layer.getStackPane());
		activeLayer = layer;
		showLayer(layers.size() - 1);
	}
	
	public void showLayer(int i){
		ObservableList<Node> children = stackPane.getChildren();
		if (children.size() > 1 && i > 0 && i < children.size())
			moveLayers(i, 0);
	}
	
	private void moveLayers(int pos, int tar){
		moveLayersInStackPanel(pos, tar);
		swapLayers(pos, tar);
	}
	
	private void swapLayers(int pos, int tar){
		activeLayer = layers.get(pos);
		layers.set(pos, layers.get(tar));
		layers.set(tar, activeLayer);
		
		ObservableList<Node> children = stackPane.getChildren();
		children.remove(layers.get(pos).getStackPane());
		children.remove(activeLayer.getStackPane());
		
		if (pos < tar) {
			children.add(pos, layers.get(pos).getStackPane());
			children.add(children.size() - tar, activeLayer.getStackPane());
		} else {
			children.add(children.size() - tar, activeLayer.getStackPane());
			children.add(children.size() - pos, layers.get(pos).getStackPane());
		}
	}
	
	private void moveLayersInStackPanel(int pos, int tar){
		ObservableList<Node> children = stackPane.getChildren();
		StackPane first = (StackPane) children.get(pos);
		StackPane next = (StackPane) children.get(tar);
		children.remove(first);
		children.remove(next);
		if (pos < tar) {
			children.add(children.size() - pos, next);
			children.add(children.size() - tar, first);
		} else {
			children.add(children.size() - tar, first);
			children.add(children.size() - pos, next);
		}
	}
	
	
/*	GETTERS & SETTERS  */
	
	
	public Layer getActiveLayer() {
		return activeLayer;
	}
	
	public StackPane getStackPane() {
		return stackPane;
	}
	
	public ObservableList<Layer> getLayers() {
		return layers;
	}
	
	public Shapes getShape() {
		return shape;
	}
	
	public void setShape(Shapes shape) {
		this.shape = shape;
	}
	
	public FillType getFillType() {
		return fillType;
	}
	
	public void setFillType(FillType fillType) {
		this.fillType = fillType;
	}
	
	public Color getColorFirst() {
		return colorFirst;
	}
	
	public Color getColorSecondary() {
		return colorSecondary;
	}
	
	public double getLineWidth() {
		return lineWidth;
	}
	
	public void setColorFirst(Color colorFirst) {
		this.colorFirst = colorFirst;
	}
	
	public void setColorSecondary(Color colorSecondary) {
		this.colorSecondary = colorSecondary;
	}
	
	/*	MY SETTERS  */
	
	
	public void setColorFirst(Color colorFirst, double opacity) {
		this.colorFirst = new Color(colorFirst.getRed(), colorFirst.getGreen(), colorFirst.getBlue(), opacity);
	}
	
	public void setColorSecondary(Color colorSecondary, double opacity) {
		this.colorSecondary = new Color(colorSecondary.getRed(), colorSecondary.getGreen(), colorSecondary.getBlue(), opacity);
	}
	
	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}
}
