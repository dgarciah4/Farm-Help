package ch.makery.address.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ch.makery.address.Main;
import ch.makery.address.model.Adapter;
import ch.makery.address.model.Component;
import ch.makery.address.model.Item;
import ch.makery.address.model.ItemContainer;
import ch.makery.address.model.MarketValueVisitor;
import ch.makery.address.model.PhysicalTelloDrone;
import ch.makery.address.model.PricingVisitor;
import ch.makery.address.model.simFlightDrone;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

public class OverviewController implements Initializable {

	private Window dialogStage;

    @FXML
    private TextField LocX;

    @FXML
    private TextField LocY;

    @FXML
    private Button addContainer;

    @FXML
    private Button addItem;
    
    @FXML
    private Button delete;
    
    @FXML
    private Button addDrone;

    @FXML
    private ImageView drone;

    @FXML
    private Button droneLaunch; // button for simulation

	@FXML
	private Button droneFly; // button used for IRL drone

    @FXML
    private TextField height;

    @FXML
    private TextField length;

    @FXML
    private TextField name;

    @FXML
    private TextField price;

    @FXML
    private RadioButton scanFarm;

    @FXML
    private TreeView<Component> treeView;

    @FXML
    private RadioButton visit;

    @FXML
    private AnchorPane visualization;
    
    @FXML
    private Button update;
    
    @FXML
    private TextField marketValue;

    @FXML
    private TextField marketValueUpdate;
    
    @FXML
    private Label purchasePrice;

    @FXML
    private TextField width;
    
    private ItemContainer containerCheck = new ItemContainer();
    private Main main;
    private Component root = new ItemContainer("Farm");
    private TreeItem<Component> rootItem = new TreeItem<Component>(root);
    private static OverviewController INSTANCE;
    private ArrayList<Component> components;
    
    public static OverviewController getInstance() {
    	if (INSTANCE == null) {
    		INSTANCE = new OverviewController();
    	}
    	return INSTANCE;
    }
    
    public OverviewController() {
    	
    }
    
    public void initTreeView() {
    	rootItem.setExpanded(true);
    	treeView.setRoot(rootItem);
    	treeView.getSelectionModel().select(rootItem);
    }
    
    public TreeView<Component> getTreeView() {
    	return treeView;
    }
    
    public void setTreeView(TreeView<Component> treeView) {
    	this.treeView = treeView;
    }

    @FXML
    private void ContainerAdd(ActionEvent event) {
    	if (notEmpty()) {
    		if (selectStatus()) {
        		if (isContForCont())
        		{
	    	    	Component itemContainer = new ItemContainer();
	    	    	itemContainer.setName(name.getText());
	    	    	itemContainer.setPrice(Double.parseDouble(price.getText()));
	    	    	itemContainer.setLocX(Integer.parseInt(LocX.getText()));
	    	    	itemContainer.setLocY(Integer.parseInt(LocY.getText()));
	    	    	itemContainer.setLength(Integer.parseInt(length.getText()));
	    	    	itemContainer.setWidth(Integer.parseInt(width.getText()));
	    	    	itemContainer.setHeight(Integer.parseInt(height.getText()));
	    	    	
	    	    	components.add(itemContainer);
	    	    	treeView.getSelectionModel().getSelectedItem().getValue().addItemCont(itemContainer);
	    	    	
	    	    	updateTree();
        		}
    		}
	    	
    	}
    }
    
    @FXML
	private void addDrone(ActionEvent event) {
    	if (selectStatus()) {
    		drone.setVisible(true);
    		
    		int index = treeView.getSelectionModel().getSelectedIndex();
    		Component commandCenter = new ItemContainer("Command Center");
    		Component droneItem = new Item("Drone");
    		
    		droneItem.setHeight(0);
    		droneItem.setLength(0);
    		droneItem.setWidth(0);
    		droneItem.setLocX((int)drone.getX());
    		droneItem.setLocY((int)drone.getY());
    		
    		commandCenter.addItem(droneItem);
    		components.add(commandCenter);
    		
    		updateTree();
    		
    		MultipleSelectionModel<TreeItem<Component>> msm = treeView.getSelectionModel();
    		msm.select(index);
    	}
	
	}
    
    private void drawComponent(Component comp) {
		Text text = new Text(comp.getLocX(), comp.getLocY() - 5, comp.getName());
		text.setAccessibleText(comp.getName());
		visualization.getChildren().add(text);
    	visualization.getChildren().add(comp.toShape());
    }

    @FXML
    private void ItemAdd(ActionEvent event) {
    	if (notEmpty()) {
    		if (selectStatus()) {
    			if(selectParentValue()) {
    				int index = treeView.getSelectionModel().getSelectedIndex();
    				
			    	Component item = new Item();
			    	item.setName(name.getText());
			    	item.setPrice(Double.parseDouble(price.getText()));
			    	item.setLocX(Integer.parseInt(LocX.getText()));
			    	item.setLocY(Integer.parseInt(LocY.getText()));
			    	item.setLength(Integer.parseInt(length.getText()));
			    	item.setWidth(Integer.parseInt(width.getText()));
			    	item.setHeight(Integer.parseInt(height.getText()));
			    	item.setMarketValue(Double.parseDouble(marketValue.getText()));
			    	
			    	if (treeView.getSelectionModel().getSelectedItem().getValue() == root)
			    		components.add(item);
			    	
			    	treeView.getSelectionModel().getSelectedItem().getValue().addItem(item);
			        
			    	updateTree();
			    	
			    	MultipleSelectionModel<TreeItem<Component>> msm = treeView.getSelectionModel();
					msm.select(index);
    			}
    		}
    	}
    }
    
    private void updateTree() {
		treeView.getRoot().getChildren().clear();
		visualization.getChildren().clear();
		components.forEach(component -> {
			addComponentsToTree(component, treeView.getRoot());
			drawComponents(component, treeView.getRoot());
		});
	}
    
    private void drawComponents(Component comp, TreeItem<Component> parent) {
    	TreeItem<Component> newTreeItem = new TreeItem<Component>(comp);
    	if (comp.getName().equals("Drone")) {
			drone.setX(comp.getLocX());
			drone.setY(comp.getLocY());
			visualization.getChildren().add(drone);
			}
		else {
			drawComponent(comp);
		}
    	
		while (comp.hasNext()) {
			drawComponents(comp.next(), newTreeItem);
		}
    }
    
    private void addComponentsToTree(Component comp, TreeItem<Component> parent) {
    	TreeItem<Component> newTreeItem = new TreeItem<Component>(comp);
		parent.getChildren().add(newTreeItem);
		while (comp.hasNext()) {
			addComponentsToTree(comp.next(), newTreeItem);
		}
    }
    
    private boolean notEmpty() {
	   if(name.getText().isEmpty()|| price.getText().isEmpty() 
			|| LocX.getText().isEmpty() || LocY.getText().isEmpty()
			|| length.getText().isEmpty() || width.getText().isEmpty()
			|| height.getText().isEmpty())
		{
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("ERROR");
			alert.setHeaderText("Please fill out every field");
			alert.showAndWait();
		}
		
		return true;
	}

    /**
     *  HLineTo, VLineTo, LineTo Guide
     *  class take coordinate positions and move to those positions
     *  node does not move in steps but to coordinate point
     */

    @FXML
    private void launchSim(ActionEvent event) throws IOException { // action for simulation drone launch
    	simFlightDrone droneSim = new simFlightDrone();
    	if (selectRadioButton() && scanFarm.isSelected()) {
    		
    		droneSim.scanFarm(getMaxHeight(), drone);
    		
    	}
    	
    	else if (visit.isSelected()) {
    		
    		droneSim.flytoLocation(treeView.getSelectionModel().getSelectedItem().getValue().getLocX(), 
    				treeView.getSelectionModel().getSelectedItem().getValue().getLocY(), 0, getMaxHeight(), drone);
    		
    	}
        

    }
    
	@FXML
	private void flyDrone(ActionEvent event) throws IOException, InterruptedException { // action for IRL drone launch
		Adapter adapter = new Adapter();
		
		if (selectRadioButton() && scanFarm.isSelected()) {
			
    		adapter.scanFarm(getMaxHeight(), drone);
    		
    	}
    	
    	else if (visit.isSelected()) {
    		
    		try {
				adapter.flytoLocation(treeView.getSelectionModel().getSelectedItem().getValue().getLocX(), 
						treeView.getSelectionModel().getSelectedItem().getValue().getLocY(), 
						treeView.getSelectionModel().getSelectedItem().getValue().getHeight(), getMaxHeight(), drone);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}

	}
	
	public int[] addHeightsToList() {
		int n = components.size();
		int count = 0;
		int heights[] = new int[n + 1];
		
		for (Component c: components) {
			heights[count] = c.getHeight();
			count ++;
		}
		
		return heights;
	}
	
	public int getMaxHeight() {
		int heights[] = addHeightsToList();
		
		int n = heights.length;
		for (int i = 1; i < n; i++) {
			int key = heights[i];
			int j = i - 1;
			
			while (j >= 0 && heights[j] > key) {
				heights[j + 1] = heights[j];
				j = j - 1;
			}
			heights[j + 1] = key;
		}
		
		return heights[n - 1];
	}
    
    public void setMain(Main main) {
    	this.main = main;
    }
    
    public void showComponent(Component component) {
    	name.setText(component.getName());
    	price.setText(Double.toString(component.getPrice()));
    	LocX.setText(Integer.toString(component.getLocX()));
    	LocY.setText(Integer.toString(component.getLocY()));
    	length.setText(Integer.toString(component.getLength()));
    	width.setText(Integer.toString(component.getWidth()));
    	height.setText(Integer.toString(component.getHeight()));
    	marketValue.setText(Double.toString(component.getMarketValue()));
    	
    	Component c = treeView.getSelectionModel().getSelectedItem().getValue();
    	PricingVisitor visP = new PricingVisitor();
    	MarketValueVisitor visMV = new MarketValueVisitor();
    	c.accept(visP);
    	c.accept(visMV);
    	
    	marketValueUpdate.setText(Double.toString(visMV.value()));
    	purchasePrice.setText(Double.toString(visP.value()));
    	
    }
    
    @FXML
    void scanFarmOn(ActionEvent event) {
    	visit.setSelected(false);
    	scanFarm.setSelected(true);
    }

    @FXML
    void visitOn(ActionEvent event) {
    	scanFarm.setSelected(false);
    	visit.setSelected(true);
    }
    
    public boolean isContForCont() {
    	if(treeView.getSelectionModel().getSelectedItem().getValue().getClass().equals(containerCheck.getClass()))
    	{
    		return true;
    	}
    	else {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("ERROR");
			alert.setHeaderText("Container must be added to a container.");
			alert.showAndWait();
		
		return false;
    	}
    }
    
    public boolean selectParentValue() {
    	Component container = new ItemContainer();
    	if (treeView.getSelectionModel().getSelectedItem().getValue().getClass().equals(container.getClass()))
    	{
			return true;
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("ERROR");
			alert.setHeaderText("Please select a Container.");
			alert.showAndWait();
		
		return false;
    	}
    }
    
    public boolean selectRadioButton() {
    	if (scanFarm.isSelected() || visit.isSelected())
    	{
			return true;
    	} else {
    		Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("ERROR");
			alert.setHeaderText("Please select an action for the drone.");
			alert.showAndWait();
		
		return false;
    	}
    }
    
    @FXML
    void handleUpdate(ActionEvent event) {
    	treeView.getSelectionModel().getSelectedItem().getValue().setMarketValue(Double.parseDouble(marketValueUpdate.getText()));
    }
    
    public boolean selectStatus() {
    	if (treeView.getSelectionModel().getSelectedItem() == null)
    	{
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("ERROR");
			alert.setHeaderText("Please select a parent item.");
			alert.showAndWait();
			
			return false;
		}
    	else
    		return true;
    }
    
    @FXML
    void handleDelete(ActionEvent event) {
    	if (selectStatus()) {
    		Component componentToRemove = treeView.getSelectionModel().getSelectedItem().getValue();
    		if (componentToRemove == null || String.valueOf(componentToRemove) == "Farm") return;
    		TreeItem<Component> item = treeView.getSelectionModel().getSelectedItem();
    		TreeItem<Component> itemParent = item.getParent();
    		Component componentParent = itemParent.getValue();

    		if (String.valueOf(itemParent.getValue()) == "Farm") {
    			components.remove(componentToRemove);
    		} else {
    			itemParent.getValue().deleteItem(componentParent, componentToRemove);
    		}

    		updateTree();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		components = new ArrayList<Component>();
		
		treeView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showComponent(newValue.getValue()));
	}

}

