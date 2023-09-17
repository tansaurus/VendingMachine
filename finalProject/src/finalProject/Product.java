package finalProject;

import java.util.ArrayList;
import java.util.Scanner;

public class Product {
	Scanner scanner = new Scanner(System.in);
	MyVendingMachine MVM = new MyVendingMachine();
	String productName;
	int price;
	int stock;
	
	public Product(String productName, int price, int stock) {
		this.productName = productName;
		this.price = price;
		this.stock = stock;
	}
	
	public boolean sellProduct(int num) {
		
		if(MVM.stock < MVM.stockNumber) {
			return false;
		}else {
			return true;
		}
		
	}

	


	
}
