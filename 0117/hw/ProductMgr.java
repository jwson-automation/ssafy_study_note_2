package hw;

public class ProductMgr {

	private Product[] products = new Product[50];
	private int index = 0;

	public void add(TV product) {
		products[index] = product;
		index++;
	};

	public void add(Refrigerator product) {
		products[index] = product;
		index++;
	};

	public Product[] getAll() {
		return products;
	};

	public Product searchByNumber(int number) {
		int _matched = -1;
		for (int i = 0; i < index; i++) {
			if (products[i].getNumber() == number)
				_matched = i;
		}
		return products[_matched];
	}

	public Product searchByName(String name) {
		int _matched = -1;
		for (int i = 0; i < index; i++) {
			if (products[i].getName().contains(name))
				_matched = i;
		}
		return products[_matched];
	}

	public Product[] loadAllTV() {
		Product[] _matched = new Product[index];
		int tmp_index = 0;
		for (int i = 0; i < index; i++) {
			if (products[i] instanceof TV)
				_matched[tmp_index] = products[i];
			tmp_index++;
		}
		return _matched;
	}

	public Product[] loadAllRefrigerator() {
		Product[] _matched = new Product[index];
		int tmp_index = 0;
		for (int i = 0; i < index; i++) {
			if (products[i] instanceof Refrigerator)
				_matched[tmp_index] = products[i];
			tmp_index++;
		}
		return _matched;
	}

	public void numberProductRemove(int _number) {
		int remove_index = 100;
		
		for (int i = 0; i < index; i++) {
			System.out.println(products[i].getNumber());
			if (products[i].getNumber() == _number)
				remove_index = i;				
		}
		if (remove_index == 100) {
			System.out.println("해당 상품이 존재하지 않습니다.");
		} else {
			products[remove_index] = products[index-1];
			index--;
		}
	};
	
	public int getTotalPrice() {
		int _sum = 0;
		for (int i = 0; i < index; i++) {
			_sum = _sum + products[i].getPrice();
		}
		return _sum;
	}

	
	
	

}
