package ch.makery.address.model;

import javafx.scene.image.ImageView;
import javafx.scene.shape.PathElement;

import java.io.IOException;

public interface SimDrone {

    public default void setup(PathElement[] path, int speed, boolean deleteOnFinish) {

    }
    public void scanFarm(int height, ImageView drone) throws IOException, InterruptedException;
    public void flytoLocation(int targetX, int targetY, int targetZ, int height, ImageView drone) throws InterruptedException, IOException;
    public void deleteDrone(ImageView drone);
}
