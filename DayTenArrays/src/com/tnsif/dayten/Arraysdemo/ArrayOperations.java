package com.tnsif.dayten.Arraysdemo;

public class ArrayOperations {

	       //print int array
			static void printArray(int[] arr)
			{
				for(int i=0; i< arr.length; i++)
				{
					System.out.print(arr[i]+ "\t");
				}
				System.out.println();
			}
			
			// count no.of odd elements
			public static int getOddCount(int b[])
			{
				int count=0;
				for(int i=0; i<b.length; i++)
				{
					if(b[i] %2 != 0)
					{
						count++;
					}
				}
				return count;
			}
			
			// sum of array elements
			public static int getSum(int... n)   
			{
				int sum=0; 
				
				for(int no:n)
				{
					sum += no;   //sum = sum+no   
				}
				return sum;
			}
			
	public static void main(String[] args) {

		int a1[] = {12, 34, 21, 45, 22};
		
		ArrayOperations obj = new ArrayOperations();
		
		obj.printArray(a1);
		
		System.out.println("Sum of Array elements");
		System.out.println(getSum(a1));
		System.out.println(getSum(100, 23, 21));
		
		System.out.println("No of Odd elements");
		System.out.println(getOddCount(a1));
	}

}
