package dto;

public class Product {

	private int index;
	private String name;
	private String price;
	private String description;

	public Product(int index, String name, String price, String description) {
		super();
		this.index = index;
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
