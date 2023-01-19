import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class MyThread extends Thread {

	int i;

	@Override
	public void run() {
//		while (true) {
		File target = new File("resource" + File.separator + "book.dat");
		try {
//			join();

			// 객체 로딩
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(target));
			Object readed = ois.readObject();
			List<Book> list = (List<Book>) readed;
			System.out.println("**********불러온 도서 전체 목록**********");
			for (Book book : list) {
				System.out.println(book);
			}

			ois.close();
			System.out.println();

		} catch (EOFException t) {
			System.out.println("등록된 도서가 없습니다.");
		} catch (Exception e) {
			System.out.println(e);
		}
//		 if (i >= 100)
//             break;
//     }

	}

	@Override
	public synchronized void start() {
		// TODO Auto-generated method stub
		super.start();
	}

}
