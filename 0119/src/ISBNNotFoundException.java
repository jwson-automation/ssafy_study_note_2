public class ISBNNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	private String isbn;

	public ISBNNotFoundException(String isbn) {
		super("Isbn이 동일한 책이 없습니다.");
		this.isbn = isbn;
	}

	public void calc() {
		System.out.println("Exception 처리 문들...");
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

}