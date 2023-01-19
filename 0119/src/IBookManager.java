
public interface IBookManager {

	abstract void add(Book book);

	abstract void remove(String isbn);

	abstract Book[] getList();

	abstract Book searchByIsbn(String isbn);

	abstract Book[] searchByTitle(String title);

	abstract Magazine[] getMagazines();

	abstract Book[] getBooks();

	abstract int getTotalPrice();

	abstract double getPriceAVG();

	abstract void sell(String isbn, int Quantity) throws Exception;

	abstract void buy(String isbn, int Quantity) throws Exception;

	abstract void saveData(String filename);

}
