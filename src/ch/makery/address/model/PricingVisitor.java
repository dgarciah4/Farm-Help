package ch.makery.address.model;

import java.util.ArrayList;

public class PricingVisitor implements Visitor {
	
	private double total;
	
	public PricingVisitor() {
		total = 0;
	}
	
	public double value() {
		return total;
	}

	@Override
	public void visit(Component comp) {
		if (comp.getItemCollection() != null) {
			total += comp.getPrice();
			ArrayList<Component> list = comp.getItemCollection();
			for (Component c:list) {
				if (c.getItemCollection().isEmpty())
				total += c.getPrice();
				else
					visit(c);				
			}
		}
		else {
			total += comp.getPrice();
		}
		
	}

}
