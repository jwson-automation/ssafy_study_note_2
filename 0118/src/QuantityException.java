
public class QuantityException extends Exception {

	public QuantityException() {
		super();
		
		System.out.println("수량이 부족합니다.");
	}
	
	public void myMethod() {
        System.out.println("Exception 처리 문들...");
    }

}
