package ch.makery.address.model;

import com.sun.corba.se.impl.orbutil.closure.Constant;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;
import javafx.util.Duration;
import org.bytedeco.javacpp.annotation.Const;

import java.awt.*;

public class simFlightDrone implements SimDrone{

    @FXML
    private RadioButton scanFarm; //scanFarm

    @FXML
    private RadioButton visit; // visit item/itemContainer

    @FXML
    private ImageView drone;

    @FXML
    private TreeView<Component> treeView;

    private int hoverTIME;
    private RotateTransition rotate = new RotateTransition();
    private PathTransition pathTransition = new PathTransition();


    public simFlightDrone() {

    }

    @Override
    public void setup(PathElement[] path, int speed, boolean deleteOnFinish) {

    }

    @Override           // speed    current X & Y position,   size Width,  size Length
    public void scanFarm(int height, ImageView drone) {
        int frameLength = Constants.FARMWIDTH;
        int frameWidth = Constants.FARMHEIGHT;
		
		Path scanpath = new Path(); 
		ArcTo arcpath = new ArcTo();		
		
		//Path of the drone so that it travels over every pixel of the farm
		//Obviously needs to be changed but good enough for now
		scanpath.getElements().add(new MoveTo(65, 65)); //Initial position (0,0) taking into consideration the drone's 50x50 size
		scanpath.getElements().add(new VLineTo(550)); //Horizontal move to the top right corner
		scanpath.getElements().add(new HLineTo(750)); //Vertical move down to bottom right corner
		scanpath.getElements().add(new VLineTo(500)); //Horizontal move left 50
		scanpath.getElements().add(new HLineTo(50)); //Vertical move back to top
		scanpath.getElements().add(new VLineTo(450)); //Horizontal move left 50
		scanpath.getElements().add(new HLineTo(750)); //Vertical move back to bottom
		scanpath.getElements().add(new VLineTo(400)); //Horizontal move left 50
		scanpath.getElements().add(new HLineTo(50)); //Vertical move back to top
		scanpath.getElements().add(new VLineTo(350)); //Horizontal move left 50
		scanpath.getElements().add(new HLineTo(750)); //Vertical move back to bottom
		scanpath.getElements().add(new VLineTo(300)); //Horizontal move left 50
		scanpath.getElements().add(new HLineTo(50)); //Vertical move back to top
		scanpath.getElements().add(new VLineTo(250)); //Horizontal move left 50
		scanpath.getElements().add(new HLineTo(750)); //Vertical move back to bottom
		scanpath.getElements().add(new VLineTo(200)); //Horizontal move left 50
		scanpath.getElements().add(new HLineTo(50)); //Vertical move back to top
		scanpath.getElements().add(new VLineTo(150)); //Horizontal move left 50
		scanpath.getElements().add(new HLineTo(750)); //Vertical move back to bottom
		scanpath.getElements().add(new VLineTo(100)); //Horizontal move left 50
		scanpath.getElements().add(new HLineTo(50)); //Vertical move back to top
		scanpath.getElements().add(new VLineTo(50)); //Horizontal move left 50
		scanpath.getElements().add(new HLineTo(750)); //Vertical move back to bottom
		scanpath.getElements().add(new HLineTo(50)); //Vertical move back to top
		scanpath.getElements().add(new HLineTo(drone.getX() + 50));
		scanpath.getElements().add(new VLineTo(drone.getY() + 50));
		PathTransition scantransition = new PathTransition();
		scantransition.setNode(drone);
		scantransition.setDuration(Duration.millis(10000));
		scantransition.setPath(scanpath); 
		scantransition.setCycleCount(1);
		scantransition.play();
		
		
		//for testing, would be nice to disable button and change it's text to "scanning" while it is running
		System.out.println("Current state: "+ scantransition.getStatus());

    }

    @Override
    public void flytoLocation(int targetX, int targetY, int targetZ, int height, ImageView drone) {
    	Path path = new Path();
    	// Moves the starting point to on screen
        path.getElements().add(new MoveTo(65, 65));
        path.getElements().add(new HLineTo(targetX));
        path.getElements().add(new VLineTo(targetY));
        path.getElements().add(new HLineTo(65));
		path.getElements().add(new VLineTo(65));

        pathTransition.setNode(drone);
        pathTransition.setDuration(Duration.millis(10000));
        pathTransition.setPath(path);
        pathTransition.play();
    }



    @Override
    public void deleteDrone(ImageView drone) {

    }
}
