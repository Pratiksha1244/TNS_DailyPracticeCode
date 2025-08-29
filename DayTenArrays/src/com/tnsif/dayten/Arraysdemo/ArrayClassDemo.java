package com.tnsif.dayten.Arraysdemo;

import java.util.Arrays;

public class ArrayClassDemo {

	public static void main(String[] args) {
        // array declaration
		int intArr[];
		
		//array instantiation
		intArr = new int[7];
		
		int intArr1[] = {10, 15, 20, 35, 18, 14, 22};
		
		System.out.println(intArr1[0]);
		
		// Printing array elements
		for(int i=0; i<intArr1.length; i++)
		{
			System.out.print(intArr1[i]+ ",");
		}
		
		System.out.println();
		// To print the array elements in one line
		System.out.println("Array is : "+ Arrays.toString(intArr1));
		
		Arrays.sort(intArr1);
		
		System.out.println("---------------After Sorting-------------");
		System.out.println("Array is : "+ Arrays.toString(intArr1));
		
		int intKey = 22;
		
		// Array must be stored in ascending order for the binary search
		System.out.println(intKey+ "found at index = "+ Arrays.binarySearch(intArr1, intKey));
		System.out.println(intKey+ "found at index = "+ Arrays.binarySearch(intArr1, 1, 3, intKey));
		
		// get second array
		int intArr2[] = {10,15,20};
		System.out.println("Second Array: "+Arrays.toString(intArr2));
		
		// To Compare both arrays
		
		if(Arrays.equals(intArr1, intArr2))
		{
			System.out.println("Both arrays are equal");
		}
		else
		{
			System.out.println("Arrays are not equal");
		}
		
		// length of array
		System.out.println("Length of Array :"+ intArr1.length);
		
		// creates a new array with a specified length
		System.out.println("\n New Arrays by copyOf : \n");
		int intArr3[] = Arrays.copyOf(intArr1, 10);
		System.out.println(Arrays.toString(intArr3));
		
		System.out.println("\n New Arrays by copyOfRange:");
		//To copy the array into an array of new length
		
		int intArr4[] = Arrays.copyOfRange(intArr1, 2, 6);
		System.out.println(Arrays.toString(intArr4));
		
		// To fill the arrays with key 
		
		Arrays.fill(intArr1, intKey);
		System.out.println(Arrays.toString(intArr1));
	}

}
