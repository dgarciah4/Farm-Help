package ch.makery.address.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class Item extends Component{
	
  private SimpleDoubleProperty marketValue;
  
  public Item() {
	  this(null);
  }
  

  public Item(String name)
  {
	  this.name = new SimpleStringProperty(name);
	  
	  // Default data
	  this.price = new SimpleDoubleProperty(500.0);
	  this.LocX = new SimpleIntegerProperty(500);
	  this.LocY = new SimpleIntegerProperty(500);
	  this.length = new SimpleIntegerProperty(50);
	  this.width = new SimpleIntegerProperty(50);
	  this.height = new SimpleIntegerProperty(50);
	  this.marketValue = new SimpleDoubleProperty(500.0);
  }
  
  public String toString() {
		return getName();
	}

  public void setName(String name){
    this.name.set(name);
  }

  public String getName(){
    return name.get();
  }

  public void setPrice(double price){
    this.price.set(price);
  }

  public double getPrice(){
    return price.get();
  }

  public void setLocX(int LocX){
    this.LocX.set(LocX);
  }

  public int getLocX(){
    return LocX.get();
  }

  public void setLocY(int LocY){
    this.LocY.set(LocY);
  }

  public int getLocY(){
    return LocY.get();
  }

  public void setLength(int length){
    this.length.set(length);
  }

  public int getLength(){
    return length.get();
  }

  public void setWidth(int width){
    this.width.set(width);
  }

  public int getWidth(){
    return width.get();
  }

  public void setHeight(int height){
    this.height.set(height);
  }

  public int getHeight(){
    return height.get();
  }
  
  public void setMarketValue(double marketValue) {
	  this.marketValue.set(marketValue);
  }
  
  public double getMarketValue() {
	  return marketValue.get();
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
  
  public void setItemCollection(ArrayList<Component> itemCollection){
		this.itemCollection = null;
	}

	public ArrayList<Component> getItemCollection(){
		return itemCollection;
	}


@Override
public void addItemCont(Component itemContainer) {
	throw new UnsupportedOperationException("This is not an item container. Only add item containers"
			+ " with ItemContainer class.");
	
}


@Override
public void addItem(Component item) {
	throw new UnsupportedOperationException("This is not an item container. Only delete items with "
			+ "ItemContainer class.");
	
}


@Override
public void deleteItemCont(Component itemContainer) {
	throw new UnsupportedOperationException("This is not an item container. Only delete item containers"
			+ " with ItemContainer class.");
	
}


@Override
public void deleteItem(Component itemContainer, Component item) {
	itemContainer.getItemCollection().remove(item);
	
}


@Override
public Component next() {
	// TODO Auto-generated method stub
	return null;
}


@Override
public boolean hasNext() {
	// TODO Auto-generated method stub
	return false;
}
  

}
