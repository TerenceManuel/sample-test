package com.ter.sample.item.impl;

import java.math.BigDecimal;

import com.ter.sample.item.Item;

/**
 * An Item cannot have a negative price
 *
 */
public class AbstractItem implements Item{

	protected final String name;
	protected final BigDecimal price;
	
	protected AbstractItem(String name, BigDecimal price){
		this.name = name;
		
		if(price == null){
			throw new UnsupportedOperationException("An item requires a price");
		}
		
		if(price != null && BigDecimal.ZERO.compareTo(price) > 0){
			throw new UnsupportedOperationException("An item cannot have a negative price");
		}
		
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractItem other = (AbstractItem) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractItem [name=" + name + ", price=" + price + "]";
	}
		
	
	
}
