package com.tnsif.dayseven.overriding;

public class Square extends Shape {
	private double side;

	// constructor for square
	public Square(double side) {
		this.side = side;
	}
	
	// override draw method for square
	@Override 
	public void draw()
	{
		System.out.println("Drawing a square with side "+ side);
	}
	
	// override erase method for square
		@Override 
		public void erase()
		{
			System.out.println("Erasing a square with side "+ side);
		}
	

}
