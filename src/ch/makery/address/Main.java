package ch.makery.address;

import java.io.IOException;

import ch.makery.address.model.*;
import ch.makery.address.view.OverviewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage stage;


	
	/**
     * The data as an observable list of Components.
     */
    private ObservableList<Component> componentData = FXCollections.observableArrayList();
	
	/**
     * Returns the data as an observable list of Items. 
     * @return
     */
    public ObservableList<Component> getComponentData() {
        return componentData;
    }
    
    public Main() {
    	componentData.add(new ItemContainer("Farm"));
    }
	
	public void showFarmOverview() {
		try {
			// Load farm overview.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/FarmOverview.fxml"));
	        AnchorPane farmOverview = (AnchorPane) loader.load();
	        Scene scene = new Scene(farmOverview);
	        stage.setScene(scene);

	        // Give the controller access to the main.
	        OverviewController controller = loader.getController();
	        controller.setMain(this);
	        controller.initTreeView();
	        
	        stage.show();

	     } catch (IOException e) {
	         e.printStackTrace();
	     }
	 }

    @Override
    public void start(Stage stage) {
    	try {
	    	this.stage = stage;
	    	this.stage.setTitle("Farm Help");
	    	
	    	showFarmOverview();
	    	
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	        
    }


    
    public static void main(String[] args) {
    	launch(args);

    }
	
	public Stage getStage() {
		return stage;
	}
    
    

    
}