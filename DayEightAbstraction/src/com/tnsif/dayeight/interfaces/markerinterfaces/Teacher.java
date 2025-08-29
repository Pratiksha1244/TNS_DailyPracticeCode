package com.tnsif.dayeight.interfaces.markerinterfaces;

public class Teacher implements Registrable, Data{
	
	private int id;
	private String name;
	
	public Teacher() {
		super();
	}

	public Teacher(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + "]";
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
