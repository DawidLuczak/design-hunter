package sample;

import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import sample.auxiliary.ChangeWorkspace;
import sample.auxiliary.ControllerEvents;
import sample.auxiliary.ControllerInterface;
import sample.item.ItemConstructor;
import sample.library.Brush;
import sample.library.Rubber;
import sample.library.event.DrawingTool;
import sample.library.geometry.draw.DrawCircle;
import sample.library.geometry.draw.DrawLine;
import sample.library.geometry.draw.DrawRectangle;
import sample.library.geometry.draw.Tool;
import sample.menu.NewFilePanel;
import sample.workspace.*;
import sample.library.FillType;
import sample.workspace.content.Layer;

public class Controller implements ControllerEvents {

/*	DECLARATIONS  */
	
	private List<Workspace> workspaceList;
	private Workspace actualWorkspace;
	
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
	
	@FXML private ToggleButton rectangleButton;
	@FXML private ToggleButton circleButton;
	@FXML private ToggleButton triangleButton;
	@FXML private ToggleButton lineButton;
	@FXML private ToggleButton brushButton;
	@FXML private ToggleButton getColorButton;
	@FXML private ToggleButton rubberButton;
	
	private DrawingTool myTool;
	
	
/*	INIT CONTROLLER  */
	
	
	@FXML
	void initialize() {
		assert rectangleButton != null : "fx:id=\"rectangleButton\" was not injected: check your FXML file 'sample.fxml'.";
		assert circleButton != null : "fx:id=\"circleButton\" was not injected: check your FXML file 'sample.fxml'.";
		assert triangleButton != null : "fx:id=\"triangleButton\" was not injected: check your FXML file 'sample.fxml'.";
		assert lineButton != null : "fx:id=\"lineButton\" was not injected: check your FXML file 'sample.fxml'.";
		assert brushButton != null : "fx:id=\"brushButton\" was not injected: check your FXML file 'sample.fxml'.";
		assert rubberButton != null : "fx:id=\"rubberButton\" was not injected: check your FXML file 'sample.fxml'.";
		
		
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
		constructFillTypesBox();
		constructOpacitySpinners();
		constructLineWidthSpinner();
	}
	
	
/*	INIT EVENTS  */
	
	
	private void initLayoutEvents(){
		menuFileNew.setOnAction(
				actionEvent ->
						new NewFilePanel(
								this
						)
		);
		menuUndo.setOnAction(
				actionEvent ->
						actualWorkspace.getWorkspaceContent().getActiveLayer().removeContentElement()
		);
		tabPanel.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeWorkspace(
						this
				)
		);
		addLayerButton.setOnAction(
				this::addLayerEvent
		);
	}
	
	
	private void initContentPropertyEvents(){
		colorPickerA.setOnAction(
				this::setColorFirstEvent
		);
		colorPickerB.setOnAction(
				this::setColorSecondaryEvent
		);
	}
	
	
	public void initShapeToolsEvents(){
		setToolButtonEvent(
				circleButton,
				Tool.CIRCLE
		);
		setToolButtonEvent(
				rectangleButton,
				Tool.RECTANGLE
		);
		setToolButtonEvent(
				lineButton,
				Tool.LINE
		);
		setToolButtonEvent(
				brushButton,
				Tool.BRUSH
		);
		setToolButtonEvent(
				rubberButton,
				Tool.RUBBER
		);
	}
	
	
/*	GETTERS & SETTERS  */
	

	public List<Workspace> getWorkspaceList() {
		return workspaceList;
	}
	
	public void setWorkspaceList(List<Workspace> workspaceList) {
		this.workspaceList = workspaceList;
	}
	
	public Workspace getActualWorkspace() {
		return actualWorkspace;
	}
	
	public void setActualWorkspace(Workspace actualWorkspace) {
		this.actualWorkspace = actualWorkspace;
	}
	
	public TabPane getTabPanel() {
		return tabPanel;
	}
	
	public ListView<Layer> getLayerList() {
		return layerList;
	}
	
	
	/*	MY SETTERS  */
	
	private void toolSwitch
			(
					Tool tool
			)
	{
		switch (tool)
		{
			case RECTANGLE ->
					myTool = new DrawRectangle(
							actualWorkspace
					);
			case CIRCLE ->
					myTool = new DrawCircle(
							actualWorkspace
					);
			case LINE ->
					myTool = new DrawLine(
							actualWorkspace
					);
			case BRUSH ->
					myTool = new Brush(
							actualWorkspace
					);
			case RUBBER ->
					myTool = new Rubber(
							actualWorkspace
					);
		}
	}
	
	
	private void setToolButtonEvent
			(
					ToggleButton button,
					Tool tool
			)
	{
		button.setOnAction(
				actionEvent ->
				{
					if (actualWorkspace != null)
					{
						if (button.isSelected())
						{
							toolSwitch(
									tool
							);
						}

					}
				}
		);
	}
	
	
	public void setWorkspaceToolsProperties
			(
					Workspace workspace
			)
	{
		setColorPickersValues(
				workspace
		);
		setOpacitySpinnersValues(
				workspace
		);
		setLineWidthSpinnerValue(
				workspace
		);
	}
	
	
	private void setColorPickersValues
			(
					Workspace workspace
			)
	{
		colorPickerA.setValue(
				workspace.getWorkspaceContent().getColorFirst()
		);
		colorPickerB.setValue(
				workspace.getWorkspaceContent().getColorSecondary()
		);
	}
	
	
	private void setOpacitySpinnersValues
			(
					Workspace workspace
			)
	{
		opacitySpinnerA.getValueFactory().setValue(
				workspace.getWorkspaceContent().getColorFirst().getOpacity()
		);
		opacitySpinnerB.getValueFactory().setValue(
				workspace.getWorkspaceContent().getColorSecondary().getOpacity()
		);
	}
	
	
	private void setLineWidthSpinnerValue
			(
					Workspace workspace
			)
	{
		strokeWidthSpinner.getValueFactory().valueProperty().setValue(
				workspace.getWorkspaceContent().getLineWidth()
		);
	}

	
/*	ITEMS CONSTRUCTORS  */
	
	
	private void constructFillTypesBox()
	{
		fillTypeBox.setItems(
				FXCollections.observableArrayList(
						FillType.FULL, FillType.STROKE, FillType.FILL
				)
		);
		
		fillTypeBox.getSelectionModel().selectedItemProperty().addListener(
				(observableValue, fillType, selectedFillType) ->
				{
					if (actualWorkspace != null && myTool != null)
					{
							actualWorkspace.getWorkspaceContent().setFillType(
									selectedFillType
							);
					}
				});
		fillTypeBox.getSelectionModel().selectFirst();
	}
	
	
	private void constructOpacitySpinners()
	{
		ItemConstructor.constructDoubleSpinner(
				opacitySpinnerA, 0, 1, 0.01
		);
		ItemConstructor.constructDoubleSpinner(
				opacitySpinnerB, 0, 1, 0.01
		);
		
		opacitySpinnerA.getValueFactory().setValue(
				1d
		);
		opacitySpinnerB.getValueFactory().setValue(
				1d
		);
		
		opacitySpinnerA.getValueFactory().valueProperty().addListener(
				(observableValue, aDouble, newValue) ->
				{
					if (actualWorkspace != null)
					{
						actualWorkspace.getWorkspaceContent().setColorFirst(
								actualWorkspace.getWorkspaceContent().getColorFirst(), newValue
						);
					}
				});
		
		opacitySpinnerB.getValueFactory().valueProperty().addListener(
				(observableValue, aDouble, newValue) ->
				{
					if (actualWorkspace != null)
					{
						actualWorkspace.getWorkspaceContent().setColorSecondary(
								actualWorkspace.getWorkspaceContent().getColorSecondary(), newValue
						);
					}
				});
	}
	
	
	private void constructLineWidthSpinner()
	{
		ItemConstructor.constructDoubleSpinner(
				strokeWidthSpinner, 0, 100, 0.1
		);
		
		strokeWidthSpinner.getValueFactory().setValue(1d);
		
		strokeWidthSpinner.getValueFactory().valueProperty().addListener(
				(observableValue, aDouble, t1) ->
				{
					if (actualWorkspace != null)
					{
						actualWorkspace.getWorkspaceContent().setLineWidth(
								t1
						);
					}
				});
	}
	
	
/*	CONTROLLER EVENT OVERRIDE METHODS */
	
	
	@Override
	public void setColorFirstEvent
			(
					ActionEvent event
			)
	{
		if (actualWorkspace != null)
		{
			actualWorkspace.getWorkspaceContent().setColorFirst(
					colorPickerA.getValue(), opacitySpinnerA.getValue()
			);
		}
	}
	
	
	@Override
	public void setColorSecondaryEvent
			(
					ActionEvent event
			)
	{
		if (actualWorkspace != null)
		{
			actualWorkspace.getWorkspaceContent().setColorSecondary(
					colorPickerB.getValue(), opacitySpinnerB.getValue()
			);
		}
	}
	
	
	@Override
	public void addLayerEvent
			(
					ActionEvent event
			)
	{
		if (actualWorkspace != null)
		{
			actualWorkspace.getWorkspaceContent().addDefaultLayer();
			layerList.refresh();
		}
	}
	
	
	
}



