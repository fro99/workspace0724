package com.kh.review.oop4;

public class TriangleController {
	private Shape s;

	
	public double calcArea(double height, double width) {
		this.s = new Shape(2, height, width);
		return (s.getWidth()* s.getHeight()/2);
		
	}
	
	public void printColor(String color) {
		s.setColor(color);
	}
	 public 
}
