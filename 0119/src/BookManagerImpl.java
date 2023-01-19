import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class BookManagerImpl implements IBookManager {
	final int MAX_SIZE = 100; // constants

//	private Book[] books = new Book[100];

	private ArrayList<Book> books = new ArrayList<Book>();
	private Magazine[] magazines = new Magazine[100];
	private int size = 0;
	// 0118 get instance 추가해서 (객체 1개만 유지 싱글톤)
	private static BookManagerImpl manager;

//	private void loadData(String filename) {
//		File target = new File("resource" + File.separator + filename);
//		try {
//			// 객체 로딩
//			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(target));
//			Object readed = ois.readObject();
//			List<Book> list = (List<Book>) readed;
//			for (Book book : list) {
//				System.out.println(book);
//			}
//
//			ois.close();
//
//		} catch (EOFException t) {
//			System.out.println("등록된 도서가 없습니다.");
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//
//	}

	public void saveData(String filename) {
		File target = new File("resource" + File.separator + filename);
		Book tempBook1 = new Book("22000", "안한다면 더 재미있는 자바", "데이터", "집에가고싶다", 10_000, "프로그래밍", 20);
		Book tempBook2 = new Book("21000", "집에가면 더 재미있는 자바", "데이터", "집에가고싶어", 10_000, "프로그래밍", 20);
		List<Book> list = new ArrayList();
		list.add(tempBook1);
		list.add(tempBook2);

		try {
			// 객체 저장
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(target));
			oos.writeObject(list);
			for (Book book : list) {
				System.out.println(book);
			}

			oos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void buy(String isbn, int quantity) throws Exception {
		int _index = MAX_SIZE;
		for (int i = 0; i < books.size(); i++) {
			if (isbn.equals(books.get(i).getIsbn())) {
				_index = i;
			}
		}
		if (_index == MAX_SIZE) {
			throw new ISBNNotFoundException("ISBN 매칭값이 존재하지 않습니다.");
		} else {
			int beforeQuanity = books.get(_index).getQuantity();
			books.get(_index).setQuantity(beforeQuanity + quantity);
		}

	}

//	
	public void sell(String isbn, int quantity) throws Exception {
		int _index = MAX_SIZE;
		for (int i = 0; i < books.size(); i++) {
			if (isbn.equals(books.get(i).getIsbn())) {
				_index = i;
			}
		}
		if (_index == MAX_SIZE) {
			throw new ISBNNotFoundException("ISBN 매칭값이 존재하지 않습니다.");
		} else {
			int beforeQuanity = books.get(_index).getQuantity();
			if (beforeQuanity < quantity) {
				throw new QuantityException();
			} else {
				books.get(_index).setQuantity(beforeQuanity - quantity);
			}
		}

	}

	// 0118 get instance 추가해서 (객체 1개만 유지 싱글톤)
	public static BookManagerImpl getInstance() {
		if (manager == null) {
			manager = new BookManagerImpl();
		}
		return manager;
	}

	// 리스트 형태로 사용할 수 있게 만들기
	public void add(Book book) {
		books.add(book);
		size++;
	};

//	코드리뷰 후 삭제
//	public void add(Magazine book) {
//		books[size] = book;
//		size++;
//	};

	// 리스트 형태로 사용할 수 있게 만들기
	// 예외처리 추가하기 ( 코드리뷰 )
	public void remove(String Isbn) {
		int _index = MAX_SIZE;

		for (int i = 0; i < books.size(); i++) {
			if (Isbn.equals(books.get(i).getIsbn())) {
				_index = i;
			}
		}
		if (_index == MAX_SIZE) {
			System.out.println("");
			System.out.println("Isbn이 동일한 책이 없습니다.");
			System.out.println("");
		} else {
			books.remove(_index);
		}
	};

	public Book[] getList() {
		Book[] _matched = new Book[size];
		int tmp_index = 0;
		for (int i = 0; i < size; i++) {
			_matched[tmp_index] = books.get(i);
			tmp_index++;
		}
		return _matched;
	};

	public Book searchByIsbn(String isbn) {
		int _matched = MAX_SIZE;

		for (int i = 0; i < size; i++) {
			if (isbn.equals(books.get(i).getIsbn())) {
				_matched = i;
			}
		}
		if (_matched == MAX_SIZE) {
			System.out.println("");
			System.out.println("Isbn이 동일한 책이 없습니다.");
			System.out.println("");
		}

		return books.get(_matched);
	}

	// Contains를 반대로 넣어서 예외처리를 하고 싶었으나,, 모르겠음
	public Book[] searchByTitle(String title) {
		Book[] _matched = new Book[size];
		int tmp_index = 0;
		for (int i = 0; i < size; i++) {
			if (books.get(i).getTitle().contains(title))
				_matched[tmp_index] = books.get(i);
			tmp_index++;
		}

		return _matched;
	}

//	코드리뷰 -> instanceof 사용
	public Book[] getBooks() {
		Book[] _matched = new Book[size];
		int tmp_index = 0;
		for (int i = 0; i < size; i++) {
			if (books.get(i) instanceof Book)
				_matched[tmp_index] = books.get(i);
			tmp_index++;
		}
		return _matched;
	}

//	코드리뷰 -> instanceof 사용
	public Magazine[] getMagazines() {
		Magazine[] _matched = new Magazine[size];
		int tmp_index = 0;
		for (int i = 0; i < size; i++) {
			if (magazines[i] instanceof Magazine)
				_matched[tmp_index] = magazines[i];
			tmp_index++;
		}
		return _matched;
	}

	public int getTotalPrice() {
		int _sum = 0;
		for (int i = 0; i < size; i++) {
			_sum = _sum + books.get(i).getPrice();
		}
		return _sum;
	}

	public double getPriceAVG() {
		double _avg = getTotalPrice() / size;
		return _avg;
	}

}
