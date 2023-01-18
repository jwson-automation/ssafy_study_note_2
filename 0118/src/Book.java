
public class Book extends Object {

//	변수설정
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private int price;
	private String desc;
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book() {
		super();
	};

	public Book(String isbn, String title, String author, String publisher, int price, String desc, int quantity) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.desc = desc;
		this.quantity = quantity;
	}

//	게터세터
	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

//	투스트링

	@Override
	public String toString() {
		return this.getIsbn() + "\t | " + this.getTitle() + "\t | " + this.getAuthor() + "\t | " + this.getPublisher() + "\t | "
				+ this.getPrice() + "\t | " + this.getDesc() + "\t | " + this.quantity + "\t | " ;
	}

// 코드 리뷰 후 삭제, abstract 제거하고 instance of 로 변경
// Abstract
//	int getMonth() {
//		return -1;
//	}

}
