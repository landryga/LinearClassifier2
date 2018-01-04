package Samples;

public class Sample {

	int red;
	int blue;
	int green;
	String class_helper;
	
	public Sample(String class_helper, int red, int blue, int green) {
		super();
		this.class_helper = class_helper;
		this.red = red;
		this.blue = blue;
		this.green = green;
	}

	public String getClass_helper() {
		return class_helper;
	}

	public void setClass_helper(String class_helper) {
		this.class_helper = class_helper;
	}

	public float getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public float getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public float getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}
	
}
