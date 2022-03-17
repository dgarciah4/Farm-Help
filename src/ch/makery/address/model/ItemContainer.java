package ch.makery.address.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class ItemContainer extends Component {
	
	private int index = 0;
	private ArrayList<Component> itemCollection = new ArrayList<Component>();


	public ItemContainer(){
		this(null);
 	}
	
	public String toString() {
		return getName();
	}
  
	public ItemContainer(String name) {
		this.name = new SimpleStringProperty(name);
	  
		// Default data
		this.price = new SimpleDoubleProperty(5000.0);
		this.LocX = new SimpleIntegerProperty(10);
		this.LocY = new SimpleIntegerProperty(10);
		this.length = new SimpleIntegerProperty(110);
		this.width = new SimpleIntegerProperty(110);
		this.height = new SimpleIntegerProperty(50);
		this.marketValue = new SimpleDoubleProperty(0.0);
	}
	
	public void addItemCont(Component itemContainer){
		itemCollection.add(itemContainer);
	}

	public void addItem(Component item){
		itemCollection.add(item);
	}

	public void deleteItemCont(Component itemContainer){
		itemCollection.remove(itemContainer);
	}
  
	public void setItemCollection(ArrayList<Component> itemCollection){
		this.itemCollection = itemCollection;
	}
  
 	public ArrayList<Component> getItemCollection(){
 		return itemCollection;
 	}

 	public void setName(String name){
 		this.name.set(name);;
 	}

 	public String getName(){
 		return name.get();
 	}

 	public void setPrice(double price){
 		this.price.set(price);;
 	}

 	public double getPrice(){
 		return price.get();
 	}

 	public void setLocX(int LocX){
 		this.LocX.set(LocX);;
 	}

 	public int getLocX(){
 		return LocX.get();
 	}

 	public void setLocY(int LocY){
 		this.LocY.set(LocY);;
 	}

 	public int getLocY(){
 		return LocY.get();
 	}

 	public void setLength(int length){
 		this.length.set(length);;
 	}

 	public int getLength(){
 		return length.get();
 	}

 	public void setWidth(int width){
 		this.width.set(width);;
 	}

 	public int getWidth(){
 		return width.get();
 	}

 	public void setHeight(int height){
 		this.height.set(height);;
 	}

 	public int getHeight(){
 		return height.get();
 	}
 	
 	public Rectangle toShape() {
		Rectangle rect = new Rectangle();
		rect.setId(getName());
		rect.setAccessibleText(getName());
		rect.setX(getLocX());
		rect.setY(getLocY());
		rect.setWidth(getWidth());
		rect.setHeight(getLength());
		rect.setStroke(Color.BLACK);
		rect.setStrokeWidth(2.0);
		rect.setFill(null);
		return rect;
	}
 	
 	public void accept(Visitor vis) {
 		  vis.visit(this);
 	  }
 	
 	public void removeShape() {
 		  
 	  }

 	@Override
 	public void deleteItem(Component itemContainer, Component item) {
 		throw new UnsupportedOperationException("This is not an item. Only delete items with Item class.");
	
 	}

	@Override
	public double getMarketValue() {
		return marketValue.get();
	}

	@Override
	public void setMarketValue(double marketValue) {
		this.marketValue.set(0.0);
		
	}
	
    public boolean hasNext() {
        if (index == itemCollection.size()) {
            index = 0;
            return false;
        }
        return index < itemCollection.size();
    }
	
	public Component next() {
        Component nextItem = this.itemCollection.get(index);
        index++;
        return nextItem;
    }

}
