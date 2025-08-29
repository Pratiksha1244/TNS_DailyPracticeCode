package com.tnsif.dayfour.modifiers;

public class Executor {

	public static void main(String[] args) {

		// accessing same packagee class
		Base b1 = new Base();
	//	b1.methodPrivate();
		b1.methodDefault();
		b1.methodProtected();
	}

}
