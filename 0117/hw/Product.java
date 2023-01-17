package hw;

public class Product {
	private int number; // unique
	private String name; // manufacturing
	private int price;
	private int amount;

	public Product() {
		super();
	};

	

	public Product(int number, String name, int price, int amount) {
		super();
		this.number = number;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}



	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String time) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return this.getNumber() + "\t|" + this.getName() + "\t|" + this.getPrice() + "\t|" + this.getAmount();
	}

}
