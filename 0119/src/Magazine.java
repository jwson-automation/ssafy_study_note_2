public class Magazine extends Book {

	private static final long serialVersionUID = 1L;
	private int year;
	private int month;

	public Magazine() {
	}

	public Magazine(String isbn, String title, String author, String publisher, int price, String desc, int quantity,
			int month, int year) {
		super(isbn, title, author, publisher, price, desc, quantity);
		this.year = year;
		this.month = month;
		// TODO Auto-generated constructor stub
	}

//	게터 세터
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return super.getIsbn() + "\t | " + super.getTitle() + "\t |" + super.getAuthor() + "\t |" + super.getPublisher()
				+ "\t |" + super.getPrice() + "\t |" + super.getDesc() + "\t |" + this.year + "\t |" + this.month
				+ "\t | " + super.getQuantity();
	};

}
