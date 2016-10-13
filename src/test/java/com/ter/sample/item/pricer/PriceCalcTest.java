package com.ter.sample.item.pricer;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.ter.sample.item.Item;
import com.ter.sample.item.impl.Apple;
import com.ter.sample.item.impl.Orange;

public class PriceCalcTest {

	private PriceCalc priceCalc;
	
	@Before
	public void setup(){
		priceCalc = new PriceCalc();
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testItemCreationWithNull(){
		new Apple(null);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void testItemCreationWithNegPrice(){
		new Apple(new BigDecimal("-1"));
	}
	
	@Test
	public void testItemWithZeroPriceNoException(){
		new Apple(BigDecimal.ZERO);
	}
	
	@Test
	public void testEmptyList(){
		BigDecimal totalPrice = priceCalc.getTotalPrice(new ArrayList<>());
		Assert.assertEquals(BigDecimal.ZERO, totalPrice);
	}
	
	@Test
	public void testNullPriceSingle(){
		Item apple = new TestItem(null);		
		BigDecimal totalPrice = priceCalc.getTotalPrice(ImmutableList.of(apple));
		Assert.assertEquals(BigDecimal.ZERO, totalPrice);
	}
	
	@Test
	public void testNullPriceMultiple(){
		Item apple = new TestItem(null);
		Item orange = new TestItem(null);
		BigDecimal totalPrice = priceCalc.getTotalPrice(
											ImmutableList.of(apple, orange));
		Assert.assertEquals(BigDecimal.ZERO, totalPrice);
	}
	
	@Test
	public void testNullAndNotNullPriceMultiple(){
		Item apple = new TestItem(null);
		Orange orange = new Orange(new BigDecimal("10"));
		BigDecimal totalPrice = priceCalc.getTotalPrice(
											ImmutableList.of(apple, orange));
		Assert.assertEquals(new BigDecimal("10"), totalPrice);
	}
	
	@Test
	public void testSingleItem(){
		Orange orange = new Orange(new BigDecimal("10.965"));
		BigDecimal totalPrice = priceCalc.getTotalPrice(
											ImmutableList.of(orange));
		Assert.assertEquals(new BigDecimal("10.965"), totalPrice);
	}
	
	@Test
	public void testMultipleItemWithNegAndNullPrice(){
		Item apple = new TestItem(new BigDecimal("-2"));
		Item mango = new TestItem(null);
		Orange orange = new Orange(new BigDecimal("10.965"));
		BigDecimal totalPrice = priceCalc.getTotalPrice(
											ImmutableList.of(orange, apple, mango));
		Assert.assertEquals(new BigDecimal("10.965"), totalPrice);
	}
	
	@Test
	public void testMultipleItems(){
		Apple apple = new Apple(new BigDecimal("5.5"));
		Orange orange1 = new Orange(new BigDecimal("6.758"));
		Orange orange2 = new Orange(new BigDecimal("8.647"));
		BigDecimal totalPrice = priceCalc.getTotalPrice(
											ImmutableList.of(apple, orange1, orange2));
		Assert.assertEquals(new BigDecimal("20.905"), totalPrice);
	}
	
	private static class TestItem implements Item{

		private BigDecimal price;
		
		TestItem(BigDecimal price){
			this.price = price;
		}
		
		@Override
		public String getName() {
			return "TestItem";
		}

		@Override
		public BigDecimal getPrice() {
			return price;
		}
		
	}
	
}
