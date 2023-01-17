package hw;

public class TV extends Product {
	private int inch;
	private String disply_type;

	public TV() {
	}

	

	public TV(int number, String name, int price, int amount, int inch, String disply_type) {
		super(number, name, price, amount);
		// TODO Auto-generated constructor stub
		this.inch = inch;
		this.disply_type = disply_type;
	}

	public int getInch() {
		return inch;
	}

	public void setInch(int inch) {
		this.inch = inch;
	}

	public String getDisply_type() {
		return disply_type;
	}

	public void setDisply_type(String disply_type) {
		this.disply_type = disply_type;
	}
	
	@Override
	public String toString() {
		return super.getNumber() + "\t|" + super.getName() + "\t|" + super.getPrice() + "\t|" + super.getAmount() + " | " + this.getInch() + "\t|" + this.getDisply_type();
	}

}
