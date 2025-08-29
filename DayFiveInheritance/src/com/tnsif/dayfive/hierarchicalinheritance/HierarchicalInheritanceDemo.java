package com.tnsif.dayfive.hierarchicalinheritance;

class Vehicle
{
	int modelNo;
	String modelName;
	
	public void speed()
	{
		System.out.println("Vehicle Class Method");
	}
}

class Car extends Vehicle
{
	public void FuelType()
	{
		System.out.println("Car class method FuelType");
	}
}

class RacingCar extends Car
{
	public void F1()
	{
		System.out.println("RacingCar class method");
	}
}

class Bike extends Vehicle
{
	public void BreakType()
	{
		System.out.println("Bike Class Method BreakType");
	}
}
public class HierarchicalInheritanceDemo {

	public static void main(String[] args) {

	Bike b = new Bike();
	b.BreakType();
	b.speed();
	
	Car c = new Car();
	c.FuelType();
	c.speed();
	}

}
