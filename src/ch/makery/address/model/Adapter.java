package ch.makery.address.model;

import javafx.scene.image.ImageView;

import java.io.IOException;


public class Adapter implements SimDrone {

    private final int convPixelToCenti = Constants.CENTIMETERS_PER_MODEL_FOOT / Constants.PIXELS_TO_ONE_MODEL_FOOT;

    public Adapter() {

    }

    @Override
    public void scanFarm(int height, ImageView drone) throws IOException, InterruptedException {
    	PhysicalTelloDrone telloDrone = new PhysicalTelloDrone();
    	
    	// set farm and scan parameters
        int farmLength = Constants.MODELHEIGHT;
        int farmWidth =  Constants.MODELWIDTH;
        int advance = farmWidth / 10;
        
        // set starting point for drone
        telloDrone.activateSDK();
        telloDrone.beginProgram();
        telloDrone.takeoff();
        
        // fly to height of tallest item (and then some)
        telloDrone.flyUpward(height + 5);

        // initiate scan of farm
        telloDrone.flyForward(farmLength);
        
        telloDrone.turnCCW(90);
        telloDrone.flyForward(farmWidth);
        
        telloDrone.hoverInPlace(2);

        telloDrone.turnCCW(90);
        telloDrone.flyForward(advance);
        
        telloDrone.turnCCW(90);
        telloDrone.flyForward(farmWidth);
        
        telloDrone.hoverInPlace(2);
        
        telloDrone.turnCW(90);
        telloDrone.flyForward(advance);
        
        telloDrone.turnCW(90);
        telloDrone.flyForward(farmWidth);
        
        telloDrone.hoverInPlace(2);

        telloDrone.turnCCW(90);
        telloDrone.flyForward(advance);
        
        telloDrone.turnCCW(90);
        telloDrone.flyForward(farmWidth);
        
        telloDrone.hoverInPlace(2);
        
        telloDrone.turnCW(90);
        telloDrone.flyForward(advance);
        
        telloDrone.turnCW(90);
        telloDrone.flyForward(farmWidth);
        
        telloDrone.hoverInPlace(2);

        telloDrone.turnCCW(90);
        telloDrone.flyForward(advance);
        
        telloDrone.turnCCW(90);
        telloDrone.flyForward(farmWidth);
        
        telloDrone.hoverInPlace(2);
        
        telloDrone.turnCW(90);
        telloDrone.flyForward(advance);
        
        telloDrone.turnCW(90);
        telloDrone.flyForward(farmWidth);
        
        telloDrone.hoverInPlace(2);

        telloDrone.turnCCW(90);
        telloDrone.flyForward(advance);
        
        telloDrone.turnCCW(90);
        telloDrone.flyForward(farmWidth);
        
        telloDrone.hoverInPlace(2);
        
        telloDrone.turnCW(90);
        telloDrone.flyForward(advance);
        
        telloDrone.turnCW(90);
        telloDrone.flyForward(farmWidth);
        
        telloDrone.hoverInPlace(2);
        
        telloDrone.turnCCW(90);
        telloDrone.flyForward(advance);
        
        telloDrone.turnCCW(90);
        telloDrone.flyForward(farmWidth);
        
        telloDrone.hoverInPlace(2);
        
        telloDrone.turnCW(90);
        telloDrone.flyForward(advance);
 
        // reset drone location
        telloDrone.turnCW(180);
        telloDrone.land();
        telloDrone.endProgram();

    }


    @Override
    public void flytoLocation(int targetX, int targetY, int targetZ, int height, ImageView drone) throws InterruptedException, IOException {
    	PhysicalTelloDrone telloDrone = new PhysicalTelloDrone();
    	
    	telloDrone.activateSDK();
        telloDrone.beginProgram();
        telloDrone.takeoff();
        
        // fly to item
        telloDrone.flyUpward(height + 5);
        telloDrone.flyLeft(targetX * convPixelToCenti);
        telloDrone.flyForward(targetY * convPixelToCenti);
        
        // visit item
        telloDrone.flyDown(height - targetZ);
        telloDrone.turnCW(180);
        telloDrone.hoverInPlace(5);
        telloDrone.turnCW(360);
        
        // reset drone location
        telloDrone.flyUpward(height - targetZ);
        telloDrone.flyLeft(targetX * convPixelToCenti);
        telloDrone.flyForward(targetY * convPixelToCenti);
        telloDrone.turnCW(180);
        telloDrone.land();

    }


    @Override
    public void deleteDrone(ImageView drone) {
    }


}

