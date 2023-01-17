package hw;

public class ProductTest {
	public static void main(String[] args) {
		ProductMgr manager = new ProductMgr();
		
		addData(manager);
		loadProductList(manager);
		LoadProductNumber(manager, 0001);
		LoadProductName(manager,"Samsung");
		LoadOnlyTV(manager);
		LoadOnlyRef(manager);
		deleteProduct(manager, 4);
		loadProductList(manager);
		LoadTotalPrice(manager);
	}
	
	
	public static void addData(ProductMgr manager) {
		// 티비 입력
		manager.add(new TV(0001, "SamsumgTV", 10_000, 31, 32, "LED"));
		manager.add(new TV(0002, "LGTVKorea", 20_000, 32, 32, "QLED"));
		manager.add(new TV(0003, "ShaomiTV", 30_000, 12, 64, "SuperLED"));
		manager.add(new TV(0004, "KoreaTV", 40_000, 53, 64, "SuperLED"));
		manager.add(new TV(0005, "JapanTV", 50_000, 11, 64, "SuperLED"));
		// 냉장고 입력
		manager.add(new Refrigerator(0100, "Samsung Fre", 100_000, 52, 50));
		manager.add(new Refrigerator(0101, "Samsung Fre2", 100_000, 52, 350));
		manager.add(new Refrigerator(0102, "LG Friends", 450_000, 22, 540));
		manager.add(new Refrigerator(0103, "Freezer Korea", 400_000, 51, 1000));
		manager.add(new Refrigerator(0104, "Co.Co.RFG", 500_000, 23, 120));		
	}
	
	public static void loadProductList(ProductMgr manager) {
		System.out.println("**********전체재고목록**********");

		Product[] full = manager.getAll();
		for (int i = 0; i < full.length; i++) {
			if (full[i] == null)
				break;
			System.out.println(full[i]);
		}
		System.out.println();
	}
	
	public static void LoadProductNumber(ProductMgr manager, int _number) {
		System.out.println("**********상품번호검색**********");
		System.out.println(manager.searchByNumber(_number));
		System.out.println();
	}
	
	public static void LoadProductName(ProductMgr manager, String name) {
		System.out.println("**********도서 제목 검색 결과**********");
		Product Title = manager.searchByName(name);
		System.out.println(Title);
		System.out.println();
	}
	
	public static void LoadOnlyTV(ProductMgr manager) {
		System.out.println("**********TV 목록**********");
		Product[] TV_all = manager.loadAllTV();

		for (int i = 0; i < TV_all.length; i++) {
			if (TV_all[i] != null)
				System.out.println(TV_all[i]);
		}

		System.out.println();
	}
	
	public static void LoadOnlyRef(ProductMgr manager) {
		System.out.println("**********냉장고 목록**********");
		Product[] Ref_all = manager.loadAllRefrigerator();

		for (int i = 0; i < Ref_all.length; i++) {
			if (Ref_all[i] != null)
				System.out.println(Ref_all[i]);
		}
		System.out.println();
	}
	
	public static void deleteProduct(ProductMgr manager, int _number) {
		manager.numberProductRemove(_number);
	}
	
	public static void LoadTotalPrice(ProductMgr manager) {
		System.out.println("전체 상품 가격 : " + manager.getTotalPrice());
	}






}
