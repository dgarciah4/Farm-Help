package ch.makery.address.model;

import java.util.ArrayList;

public class MarketValueVisitor implements Visitor {
	
	private double total;
	
	public MarketValueVisitor() {
		total = 0;
	}
	
	public double value() {
		return total;
	}

	@Override
	public void visit(Component comp) {
		total += comp.getMarketValue();
		ArrayList<Component> list = comp.getItemCollection();
		for (Component c:list) {
			if (c.getItemCollection().isEmpty())
			total += c.getMarketValue();
			else
				visit(c);
			
		}	
	}

}
