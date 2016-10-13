package com.ter.sample.item.pricer;

import java.math.BigDecimal;
import java.util.List;

import com.ter.sample.item.Item;

/**
 * This price total calculator ignores null and negative prices
 *
 */
public class PriceCalc {

	public BigDecimal getTotalPrice(List<Item> items){
		
		BigDecimal totalPrice = BigDecimal.ZERO;
		
		totalPrice = items.stream()
				.filter(item -> item.getPrice()!=null)
				.filter(item -> BigDecimal.ZERO.compareTo(item.getPrice()) <= 0)
				.map(Item::getPrice)
				.reduce(BigDecimal.ZERO, (a, b)->a.add(b));
		
		return totalPrice;
	}
	
}
