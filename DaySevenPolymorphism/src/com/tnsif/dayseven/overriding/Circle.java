package com.tnsif.dayseven.overriding;

public class Circle extends Shape {
	
	private double radius;

	// constructor for circle
	public Circle(double radius) {
		this.radius = radius;
	}
	
	// override draw method for Circle
	@Override
	public void draw()
	{
		System.out.println("Drawing a circle with radius "+ radius);
	}
	
	@Override
	public void erase()
	{
		System.out.println("Erasing a circle with radius "+ radius);
	}
	

}
