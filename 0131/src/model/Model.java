package model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sun.org.apache.xml.internal.security.Init;

import dto.Product;

public class Model {

	// Book정보 가져오기
	public static List<Product> list = new ArrayList<Product>();
	
	public static void init() {
		list.add(new Product(1, "냉장고", "25000", "좋은냉장고다"));
		list.add(new Product(2, "자판기", "25000", "좋은자판기다"));
		list.add(new Product(3, "데스크탑", "25000", "좋은데스크탑이다"));
		list.add(new Product(4, "노트북", "25000", "노트북이다"));
	}

	public static void insertProduct(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Product insertedProduct = new Product(list.size()+1, request.getParameter("name"), request.getParameter("price"),
				request.getParameter("description"));
				list.add(insertedProduct);
				request.setAttribute("_product", insertedProduct);
	}
	
	public static void updateProduct(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		int index = 0;
		String name = request.getParameter("name");
		
		for (Product prods : list) {
			if (prods.getName().equals(name)) {
				index = prods.getIndex();
				break;
			}
		}
		list.set(index-1, new Product(index, request.getParameter("name"), request.getParameter("price"),
				request.getParameter("description")));
		
		request.setAttribute("_product", list.get(index-1));
	}
	
	public static void deleteProduct(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int index = 0;
		
		String name = request.getParameter("name");
		System.out.println(name);
		
		for (Product prods : list) {
			if (prods.getName().equals(name)) {
				index = prods.getIndex();
				list.remove(index-1);
				System.out.println();
				break;
			}
		}
		
	}
	
	public static void selectProductAll(HttpServletRequest request) {
		// TODO Auto-generated method stub
				request.setAttribute("_product", list);
	}
	
	

	public static Product getProductByName(HttpServletRequest request) {
		Product prod = null;
		String name = request.getParameter("name");
		for (Product prods : list) {
			if (prods.getName().equals(name)) {
				prod = prods;
				break;
			}
		}
		
		request.setAttribute("_product", prod);
		
		return prod;
	}
	
	
}
