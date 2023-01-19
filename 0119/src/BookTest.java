public class BookTest {

	public static void main(String[] args) throws Exception {
		// 변경 전
//		BookManagerImpl manager = new BookManagerImpl();
		// 변경 후 ( Get Instance 사용 )
		BookManagerImpl manager = BookManagerImpl.getInstance();
		MyThread thr = new MyThread();
		thr.start();

//		책 추가		
		addBook(manager);
//		전체 리스트 가져오기		
		loadBookList(manager);
//		ISBN 서치, 파라미터 추가
		searchISBN(manager, "21424");
//		Title 서치
		searchTitle(manager, "함께하면");
//		책만 가져오기
		LoadOnlyBooks(manager);
//		매거진만 가져오기
		LoadOnlyMagazine(manager);
//		도서 가격 총합
		System.out.println("도서 가격 총합 : " + manager.getTotalPrice());
		System.out.println();
//		도서 가격 평균
		System.out.println("도서 가격 평균 : " + manager.getPriceAVG());
		System.out.println();
//		도서 판매 ( 수량 부족  )
		sellTheBook(manager, "21424", 100);
//		도서 구매
		buyTheBook(manager, "21424", 10);
//		도서 판매	
		sellTheBook(manager, "21424", 10);
//		책 데이터 입력하기
		SaveToDat(manager, "book.dat");
////		책 데이터 파일 불러오기
//		LoadFromDat(manager, "tmp.dat");
////		책 데이터 파일 불러오기
//		LoadFromDat(manager, "book.dat");

	}

	private static void SaveToDat(BookManagerImpl manager, String filename) {
		// TODO Auto-generated method stub
		System.out.println("**********입력한 도서 전체 목록**********");
		manager.saveData(filename);
		System.out.println();
	}

//	private static void LoadFromDat(BookManagerImpl manager, String filename) {
//		// TODO Auto-generated method stub
//		System.out.println("**********불러온 도서 전체 목록**********");
//		manager.loadData(filename);
//		System.out.println();
//	}

	private static void buyTheBook(BookManagerImpl manager, String isbn, int _quantity) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("**********도서구매:" + isbn + "," + _quantity + "개**********");
		try {
			manager.buy(isbn, _quantity);
			System.out.println(manager.searchByIsbn(isbn));
			System.out.println();
		} catch (ISBNNotFoundException e) {
			System.out.println(e);
			System.out.println();
		}

	}

	private static void sellTheBook(BookManagerImpl manager, String isbn, int _quantity) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("**********도서판매:" + isbn + "," + _quantity + "개**********");
		try {
			manager.sell(isbn, _quantity);
			System.out.println(manager.searchByIsbn(isbn));
			System.out.println();
		} catch (ISBNNotFoundException e) {
			System.out.println(e);
			System.out.println();
		} catch (QuantityException qe) {
			System.out.println(qe);
			System.out.println();
		}

	}

	public static void addBook(BookManagerImpl manager) {
		manager.add(new Book("21424", "함께하면 더 재미있는 자바", "김자바", "삼성청년데미", 10_000, "프로그래밍", 20));
		manager.add(new Book("21425", "함께하면 더 재미없는 자바", "김자바", "성년아카데미", 20_000, "프로그래밍", 30));
		manager.add(new Book("21426", "그녀는 왜 자바를 떠났을까", "김자바", "청년아카데미", 10_000, "프로그래밍", 40));
		manager.add(new Book("21427", "자바 인문학을 공부하기위해", "김자바", "삼청아카데미", 10_000, "프로그래밍", 50));
		manager.add(new Book("21428", "나는 자바로 고백한다 3권", "김자반", "삼성아카데미", 10_000, "프로그래밍", 60));
		manager.add(new Magazine("21450", "박재범과 아이유의 모험 10월", "김재범", "ZK메거진", 30_000, "연애", 10, 31, 70));
		manager.add(new Magazine("21451", "박모범과 아재범의 이유 12월", "박유천", "K인사이트", 30_000, "철학", 10, 10, 80));
	}

	public static void loadBookList(BookManagerImpl manager) {
		System.out.println("**********도서전체목록**********");

		Book[] full = manager.getList();
		for (int i = 0; i < full.length; i++) {
			System.out.println(full[i]);
		}
		System.out.println();
	}

	public static void searchISBN(BookManagerImpl manager, String isbn) {
		System.out.println("**********도서 Isbn 검색 결과**********");
		System.out.println(manager.searchByIsbn(isbn));

		System.out.println();
	}

	public static void searchTitle(BookManagerImpl manager, String title) {
		System.out.println("**********도서 제목 포함 검색 결과**********");
		Book[] Title = manager.searchByTitle(title);

		for (int i = 0; i < Title.length; i++) {
			if (Title[i] != null)
				System.out.println(Title[i]);
		}

		System.out.println();
	}

	public static void LoadOnlyBooks(BookManagerImpl manager) {
		System.out.println("**********일반 도서 목록**********");
		Book[] book_all = manager.getBooks();

		for (int i = 0; i < book_all.length; i++) {
			if (book_all[i] instanceof Book)
				if (!(book_all[i] instanceof Magazine))
					System.out.println(book_all[i]);
		}

		System.out.println();
	}

	public static void LoadOnlyMagazine(BookManagerImpl manager) {
		System.out.println("**********매거진 목록**********");
		Book[] book_all = manager.getBooks();

		for (int i = 0; i < book_all.length; i++) {
			if (book_all[i] instanceof Magazine)
				System.out.println(book_all[i]);
		}

		System.out.println();
	}
}
