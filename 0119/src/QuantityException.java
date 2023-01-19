
public class QuantityException extends Exception {

	private static final long serialVersionUID = 1L;

	public QuantityException() {
		super("수량이 부족합니다.");
	}

	public void myMethod() {
		System.out.println("Exception 처리 문들...");
	}

}
