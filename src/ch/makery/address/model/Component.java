package ch.makery.address.model;

import java.util.ArrayList;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.shape.Rectangle;

public abstract class Component {
	
	public StringProperty name;
	public DoubleProperty price;
	public IntegerProperty LocX;
	public IntegerProperty LocY;
	public IntegerProperty length;
	public IntegerProperty width;
	public IntegerProperty height;
	public ArrayList<Component> itemCollection = new ArrayList<Component>();
	public DoubleProperty marketValue;
	
	public abstract void setName(String name);
	public abstract String getName();
	public abstract void setPrice(double price);
	public abstract double getPrice();
	public abstract void setLocX(int LocX);
	public abstract int getLocX();
	public abstract void setLocY(int LocY);
	public abstract int getLocY();
	public abstract void setLength(int length);
	public abstract int getLength();
	public abstract void setWidth(int width);
	public abstract int getWidth();
	public abstract void setHeight(int height);
	public abstract int getHeight();
	public abstract void addItemCont(Component itemContainer);
	public abstract void addItem(Component item);
    public abstract void deleteItemCont(Component itemContainer);
    public abstract void deleteItem(Component itemContainer, Component item);
    public abstract Rectangle toShape();
    public abstract void removeShape();
    public abstract void accept(Visitor vis);
    public abstract void setItemCollection(ArrayList<Component> itemCollection);
    public abstract ArrayList<Component> getItemCollection();
    public abstract double getMarketValue();
    public abstract void setMarketValue(double marketValue);
    public abstract Component next();
    public abstract boolean hasNext();

}

