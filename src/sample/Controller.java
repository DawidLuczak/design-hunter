package sample;

import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import sample.menu.NewFilePanel;
import sample.workspace.Layer;
import sample.workspace.MouseDraftsmanEvents;
import sample.workspace.Workspace;
import sample.workspace.drawingtools.shapes.FillType;
import sample.workspace.drawingtools.shapes.ShapeInterface;
import sample.workspace.drawingtools.shapes.Shapes;

public class Controller implements ControllerEvents{

/*	DECLARATIONS  */
	
	private List<Workspace> workspaceList;
	private Workspace activeWorkspace;
	private MouseDraftsmanEvents mouseDraftsmanEvents;
	
	@FXML private ResourceBundle resources;
	@FXML private URL location;
	
	@FXML private Font x1;
	@FXML private Color x2;
	@FXML private Font x3;
	@FXML private Color x4;
	
	@FXML private ColorPicker colorPickerA, colorPickerB;
	@FXML private Spinner<Double> opacitySpinnerA, opacitySpinnerB;
	@FXML private Spinner<Double> strokeWidthSpinner;
	
	@FXML private AnchorPane content;
	
	@FXML private MenuItem menuFileNew, menuUndo;
	
	@FXML private TabPane tabPanel;
	
	@FXML private ListView<Layer> layerList;
	@FXML private Button addLayerButton;
	@FXML private ChoiceBox<FillType> fillTypeBox;
	
	@FXML private Circle circleIcon;
	@FXML private Rectangle rectangleIcon;
	@FXML private Polygon triangleIcon;
	@FXML private Line lineIcon;
	@FXML private CubicCurve brushIcon;
	@FXML private ToggleButton getColorButton;
	
	@FXML
	private Layer lay;
	
	
/*	INIT CONTROLLER  */
	
	
	@FXML
	void initialize() {
		assert circleIcon != null : "fx:id=\"circleIcon\" was not injected: check your FXML file 'sample.fxml'.";
		assert rectangleIcon != null : "fx:id=\"rectangleIcon\" was not injected: check your FXML file 'sample.fxml'.";
		assert triangleIcon != null : "fx:id=\"triangleIcon\" was not injected: check your FXML file 'sample.fxml'.";
		assert lineIcon != null : "fx:id=\"lineIcon\" was not injected: check your FXML file 'sample.fxml'.";
		assert brushIcon != null : "fx:id=\"brushIcon\" was not injected: check your FXML file 'sample.fxml'.";
		
		assert colorPickerA != null : "fx:id=\"color1\" was not injected: check your FXML file 'sample.fxml'.";
		assert colorPickerB != null : "fx:id=\"color2\" was not injected: check your FXML file 'sample.fxml'.";
		assert opacitySpinnerA != null : "fx:id=\"opacitySpinnerA\" was not injected: check your FXML file 'sample.fxml'.";
		assert opacitySpinnerB != null : "fx:id=\"opacitySpinnerB\" was not injected: check your FXML file 'sample.fxml'.";
		assert strokeWidthSpinner != null : "fx:id=\"strokeWidthSpinner\" was not injected: check your FXML file 'sample.fxml'.";
		
		assert getColorButton != null : "fx:id=\"getColorButton\" was not injected: check your FXML file 'sample.fxml'.";
		
		assert content != null : "fx:id=\"content\" was not injected: check your FXML file 'sample.fxml'.";
		assert x1 != null : "fx:id=\"x1\" was not injected: check your FXML file 'sample.fxml'.";
		assert x2 != null : "fx:id=\"x2\" was not injected: check your FXML file 'sample.fxml'.";
		assert x3 != null : "fx:id=\"x3\" was not injected: check your FXML file 'sample.fxml'.";
		assert x4 != null : "fx:id=\"x4\" was not injected: check your FXML file 'sample.fxml'.";
		assert menuFileNew != null : "fx:id=\"menuFileNew\" was not injected: check your FXML file 'sample.fxml'.";
		assert menuUndo != null : "fx:id=\"menuUndo\" was not injected: check your FXML file 'sample.fxml'.";
		assert tabPanel != null : "fx:id=\"tabPanel\" was not injected: check your FXML file 'sample.fxml'.";
		assert layerList != null : "fx:id=\"layerList\" was not injected: check your FXML file 'sample.fxml'.";
		assert addLayerButton != null : "fx:id=\"addLayerButton\" was not injected: check your FXML file 'sample.fxml'.";
		assert fillTypeBox != null : "fx:id=\"fillTypeBox\" was not injected: check your FXML file 'sample.fxml'.";
		
		ControllerInterface.layerViewList(this);
		initLayoutEvents();
		initContentPropertyEvents();
		initShapeToolsEvents();
		constructFillTypesBox();
		constructOpacitySpinners();
		constructLineWidthSpinner();
	}
	
	
/*	INIT EVENTS  */
	
	
	private void initLayoutEvents(){
		menuFileNew.setOnAction(actionEvent -> new NewFilePanel(this));
		menuUndo.setOnAction(actionEvent -> {
			activeWorkspace.getWorkspaceContent().getActiveLayer().removeContentElement();
		});
		tabPanel.getSelectionModel().selectedIndexProperty().addListener(new ChangeWorkspace(this));
		addLayerButton.setOnAction(this::addLayerEvent);
	}
	
	private void initContentPropertyEvents(){
		colorPickerA.setOnAction(this::setColorFirstEvent);
		colorPickerB.setOnAction(this::setColorSecondaryEvent);
	}
	
	private void initShapeToolsEvents(){
		rectangleIcon.setOnMouseClicked(event -> ShapeInterface.setShape(this, Shapes.RECTANGLE));
		circleIcon.setOnMouseClicked(event -> ShapeInterface.setShape(this, Shapes.CIRCLE));
		triangleIcon.setOnMouseClicked(event -> ShapeInterface.setShape(this, Shapes.TRIANGLE));
		lineIcon.setOnMouseClicked(event -> ShapeInterface.setShape(this, Shapes.LINE));
		brushIcon.setOnMouseClicked(event -> ShapeInterface.setShape(this, Shapes.BRUSH));
		getColorButton.setOnAction(actionEvent -> {
			if (getColorButton.isSelected()) {
				mouseDraftsmanEvents.getColorsMouseListeners();
			}
			else
				mouseDraftsmanEvents.addClickingListeners();
		});
	}
	
	
/*	GETTERS & SETTERS  */
	
	
	public void setMouseEvents(MouseDraftsmanEvents mousePositionEvents) {
		this.mouseDraftsmanEvents = mousePositionEvents;
	}
	
	public List<Workspace> getWorkspaceList() {
		return workspaceList;
	}
	
	public void setWorkspaceList(List<Workspace> workspaceList) {
		this.workspaceList = workspaceList;
	}
	
	public Workspace getActiveWorkspace() {
		return activeWorkspace;
	}
	
	public void setActiveWorkspace(Workspace activeWorkspace) {
		this.activeWorkspace = activeWorkspace;
	}
	
	public TabPane getTabPanel() {
		return tabPanel;
	}
	
	public ListView<Layer> getLayerList() {
		return layerList;
	}
	
	public MouseDraftsmanEvents getMouseDraftsmanEvents() {
		return mouseDraftsmanEvents;
	}
	
	/*	MY SETTERS  */
	
	public void setWorkspaceToolsProperties(Workspace workspace){
		setColorPickersValues(workspace);
		setOpacitySpinnersValues(workspace);
		setLineWidthSpinnerValue(workspace);
	}
	
	private void setColorPickersValues(Workspace workspace){
		colorPickerA.setValue(workspace.getWorkspaceContent().getColorFirst());
		colorPickerB.setValue(workspace.getWorkspaceContent().getColorSecondary());
	}
	
	private void setOpacitySpinnersValues(Workspace workspace){
		opacitySpinnerA.getValueFactory().setValue(workspace.getWorkspaceContent().getColorFirst().getOpacity());
		opacitySpinnerB.getValueFactory().setValue(workspace.getWorkspaceContent().getColorSecondary().getOpacity());
	}
	
	private void setLineWidthSpinnerValue(Workspace workspace){
		strokeWidthSpinner.getValueFactory().valueProperty().setValue(workspace.getWorkspaceContent().getLineWidth());
	}
	
/*	ITEMS CONSTRUCTORS  */
	
	
	private void constructFillTypesBox(){
		fillTypeBox.setItems(FXCollections.observableArrayList(FillType.FULL, FillType.STROKE, FillType.FILL));
		fillTypeBox.getSelectionModel().selectedItemProperty().addListener((observableValue, fillType, selectedFillType) -> {
			if (activeWorkspace != null)
				activeWorkspace.getWorkspaceContent().setFillType(selectedFillType);
		});
		fillTypeBox.getSelectionModel().selectFirst();
	}
	
	private void constructOpacitySpinners(){
		ControllerItemConstructor.constructDoubleSpinner(opacitySpinnerA, 0, 1, 0.01);
		ControllerItemConstructor.constructDoubleSpinner(opacitySpinnerB, 0, 1, 0.01);
		
		opacitySpinnerA.getValueFactory().setValue(1d);
		opacitySpinnerB.getValueFactory().setValue(1d);
		
		opacitySpinnerA.getValueFactory().valueProperty().addListener((observableValue, aDouble, t1) -> {
			if (activeWorkspace != null)
				activeWorkspace.getWorkspaceContent().setColorFirst(activeWorkspace.getWorkspaceContent().getColorFirst(), t1);
		});
		opacitySpinnerB.getValueFactory().valueProperty().addListener((observableValue, aDouble, t1) -> {
			if (activeWorkspace != null)
				activeWorkspace.getWorkspaceContent().setColorSecondary(activeWorkspace.getWorkspaceContent().getColorSecondary(), t1);
		});
	}
	
	private void constructLineWidthSpinner(){
		ControllerItemConstructor.constructDoubleSpinner(strokeWidthSpinner, 0, 100, 0.1);
		
		strokeWidthSpinner.getValueFactory().setValue(1d);
		
		strokeWidthSpinner.getValueFactory().valueProperty().addListener((observableValue, aDouble, t1) -> {
			if (activeWorkspace != null)
				activeWorkspace.getWorkspaceContent().setLineWidth(t1);
		});
	}
	
	
/*	CONTROLLER EVENT OVERRIDE METHODS */
	
	
	@Override
	public void setColorFirstEvent(ActionEvent event) {
		if (activeWorkspace != null) {
			activeWorkspace.getWorkspaceContent().setColorFirst(colorPickerA.getValue(), opacitySpinnerA.getValue());
		}
	}
	
	@Override
	public void setColorSecondaryEvent(ActionEvent event) {
		if (activeWorkspace != null) {
			activeWorkspace.getWorkspaceContent().setColorSecondary(colorPickerB.getValue(), opacitySpinnerB.getValue());
		}
	}
	
	@Override
	public void addLayerEvent(ActionEvent event) {
		if (activeWorkspace != null) {
			activeWorkspace.getWorkspaceContent().addDefaultLayer();
			layerList.refresh();
		}
	}
	
	
	
}



