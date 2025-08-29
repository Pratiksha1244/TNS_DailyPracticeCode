package com.tnsif.dayfive.multilevelinheritance;

public class MultilevelInheritanceDemo {

	public static void main(String[] args) {

		LevelOneEmployee l1 = new LevelOneEmployee("Atharva", 8976543212L, 897654321256L, "Sales", 30000, 100, "Manager");

	   System.out.println(l1);
	   
	   Person p1= new Person("Gayatri", 7876543221L, 123456789098L);
	   System.out.println(p1);
	   
	   p1 = new Employee("Pranav",9087654321L, 234567898909L, "IT", 50000);
	   System.out.println(p1);
	   
	   p1= new LevelOneEmployee("Madhur",9888876543L, 98765432123L, "Account", 100000, 500,"Signing Authority" );
	   System.out.println(p1);
	   
	   
	   
	}

}
