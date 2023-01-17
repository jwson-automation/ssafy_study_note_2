package hw;

public class Refrigerator extends Product {
	private int volume;

	public Refrigerator() {
	
	}


	public Refrigerator(int number, String name, int price, int amount, int volume) {
		super(number, name, price, amount);
		// TODO Auto-generated constructor stub
		this.volume = volume;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	@Override
	public String toString() {
		return super.getNumber() + "\t|" + super.getName() + "\t|" + super.getPrice() + "\t|" + super.getAmount()
				+ "\t|" + this.getVolume();
	}

}
