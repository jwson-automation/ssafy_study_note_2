public class ISBNNotFoundException extends Exception{
	private String isbn ;
	

	public ISBNNotFoundException(String isbn) {
		super();
		this.isbn = isbn;
		System.out.println("Isbn이 동일한 책이 없습니다.");
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