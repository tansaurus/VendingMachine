package finalProject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyVendingMachine implements VendingMachineInterface{
	private int profit = 0;
	public ArrayList<Product> productList = new ArrayList<Product>(10);
	int price;
	int stock;
	int stockNumber = 0;
	int whatMenu;
	String productName;
	Admin manager = new Admin();
	Scanner scanner = new Scanner(System.in);
	public int menuNumber;
	int totalProductNumber = 0;
	
	
	
	@Override
	public void setProduct() {
		// TODO Auto-generated method stub
		System.out.print("몇 개의 제품을 입력하시겠습니까?");
		
		try {
			menuNumber = scanner.nextInt();
		}catch(InputMismatchException e) {
			System.out.print("문자를 입력하셨습니다. 숫자를 입력하세요.");
			scanner = new Scanner(System.in);
			menuNumber = scanner.nextInt();
			
		}
		System.out.println(' ');
		for(int i = 1; i <= menuNumber; i++) {
			System.out.println("********"+ i + "번째 제품********");
			System.out.print("제품 이름 : ");
			productName = scanner.next();
			System.out.print("제품 가격 : ");
			try {
				price = scanner.nextInt();
			}catch(InputMismatchException e) {
				System.out.print("문자를 입력하셨습니다. 숫자를 입력하세요.");
				scanner = new Scanner(System.in);
				price = scanner.nextInt();
				
			}
			System.out.print("제품 수량 : ");
			try {
				stock = scanner.nextInt();
			}catch(InputMismatchException e) {
				System.out.print("문자를 입력하셨습니다. 숫자를 입력하세요.");
				scanner = new Scanner(System.in);
				stock = scanner.nextInt();
				
			}
			System.out.println(' ');
			productList.add(new Product(productName, price, stock));
			}
			
			
		}
	

	@Override
	public int menuPrint() {
		// TODO Auto-generated method stub
		
		System.out.println("1. 제품 구매");
		System.out.println("2. 관리자");
		System.out.println("3. 종료");
		System.out.print("--> ");
		
		int totalMenu = scanner.nextInt();
		if(totalMenu == 1) {
			if(productList.isEmpty() == true) {
				System.out.println(' ');
				System.out.println("등록된 상품이 없습니다.");
				System.out.println(' ');
				menuPrint();
			}else {
				buying();
				menuPrint();
			}
			
		}
		else if(totalMenu == 2) {
			if(manager.checkAdmin()==true) {
				adminMenu();
			}			
		}
		else if(totalMenu == 3) {
			System.exit(0);
		}
		return 0;
	}

	@Override
	public int calSum(int selNo, int num) {
		// TODO Auto-generated method stub
		
		System.out.println("현재 총 매출액 : " + profit);
		System.out.println(' ');
		
		return profit;
	}
	
	public int buying() {
		System.out.println(' ');
		System.out.println("구매할 제품의 번호를 입력하세요.");
		for(int i = 0; i < productList.size(); i++) {
			System.out.println(i + 1 + ". " + 
						productList.get(i).productName + "\t" + productList.get(i).price + "원"+ "\t" + productList.get(i).stock + "개");
		}
		System.out.print("--> ");
		int productNumber = scanner.nextInt();
		System.out.print("수량 : ");
		int buyingNumber = scanner.nextInt();
		
		if(buyingNumber == 0) {
			System.out.println("최소 1개 이상 입력해주세요.");
			buying();
			
		}else {
			if(productList.get(productNumber - 1).stock < buyingNumber) {
			System.out.println(productList.get(productNumber - 1).productName + "을/를 " + buyingNumber + "개 선택하셨습니다.");
			System.out.println("재고가 부족합니다.");
			System.out.println(' ');
			menuPrint();
		}
		else if(productList.get(productNumber - 1).stock == buyingNumber){
			System.out.println(productList.get(productNumber - 1).productName + "을/를 " + buyingNumber + "개 선택하셨습니다.");
			System.out.println("총 금액 : " + productList.get(productNumber - 1).price * buyingNumber +"원");
			System.out.println(' ');
			profit += productList.get(productNumber - 1).price * buyingNumber;
			productList.remove(productNumber - 1);
			if(productList.size() == 0) {
				System.out.println("모든 상품의 재고가 없습니다.");
			}
		}
		else {
			System.out.println(productList.get(productNumber - 1).productName + "을/를 " + buyingNumber + "개 선택하셨습니다.");
			System.out.println("총 금액 : " + productList.get(productNumber - 1).price * buyingNumber +"원");
			System.out.println(' ');
			productList.get(productNumber - 1).stock -= buyingNumber;
			profit += productList.get(productNumber-1).price * buyingNumber;
		}
		
	}
		
		
		
		return profit;
		
	}

	@Override
	public void adminMenu() {
		// TODO Auto-generated method stub
		System.out.println("1. 제품 등록");
		System.out.println("2. 매출 확인");
		System.out.println("3. 전체 제품정보 확인");
		System.out.println("4. 재고 확인");
		System.out.println("5. 재고 추가");
		System.out.println("6. 이전 메뉴");
		System.out.print("--> ");
		while(true) {
			try {
				whatMenu = scanner.nextInt();
				break;
			}
			catch(InputMismatchException e) {
				scanner = new Scanner(System.in);
				System.out.println("문자를 입력하셨습니다.");
				System.out.println(' ');
				adminMenu();
			}
		}
		System.out.println(' ');
		

		if(whatMenu == 1) {
			setProduct();
			adminMenu();
		}
		else if(whatMenu == 2) {
			int selNo = 0;
			calSum(selNo, price);
			adminMenu();
		}
		else if(whatMenu == 3) {
			showInfo();
			adminMenu();
		}
		else if(whatMenu == 4) {
			checkStock(productName);
			adminMenu();
		}
		else if(whatMenu == 5) {
			int theNbOfPrct = 0;
			addStock(productName, theNbOfPrct);
			adminMenu();
		}
		else if(whatMenu == 6) {
			menuPrint();
		}
			
	}
		
		
		

		


	public void showInfo() {
		int infoStock = 0;
		System.out.println(' ');
		System.out.println("#########################");
		for(int i = 0; i < productList.size(); i++) {
			System.out.println("제품 이름 : " + productList.get(i).productName);
			System.out.println("제품 가격 : " + productList.get(i).price + "원");
			System.out.println("제품 재고 : " + productList.get(i).stock + "개");
			System.out.println(" ");
			infoStock++;
		}
		
		System.out.println("총 " + infoStock + "개의 제품이 있습니다");
		System.out.println("#########################");
		System.out.println(' ');
	}

	@Override
	public void checkStock(String name) {
		// TODO Auto-generated method stub
		
		System.out.print("어떤 상품의 재고를 확인할까요?");
		String checkName = scanner.next();
		boolean check = false;
		
		for(int i = 0; i < productList.size(); i++) {
			if(checkName.equals((String)(productList.get(i).productName))) {
				System.out.println(checkName + " 재고 : " + productList.get(i).stock + "개");
				System.out.println(' ');
				check = true;
			
			}
		}
		if(check == false) {
			System.out.println("상품 이름이 틀렸습니다. 다시 입력해주세요");
		}
		
	}
		
	@Override
	public void addStock(String name, int theNbOfPrct) {
		// TODO Auto-generated method stub
		boolean check = false;
		System.out.print("어떤 상품의 재고를 추가할까요?(제품 이름)");
		name = scanner.next();
		System.out.print("몇 개 추가할까요?");
		theNbOfPrct = scanner.nextInt();
		for(int i = 0; i < productList.size(); i++) {
			if(name.equals((String)(productList.get(i).productName)) ) {
				totalProductNumber = productList.get(i).stock + theNbOfPrct;
				productList.get(i).stock += theNbOfPrct;
				System.out.println(name + " 상품 총 재고 : " + totalProductNumber + "개");
				System.out.println(' ');
				check = true;
			}
		}
		
		if(check == false) {
			System.out.println("없는 상품입니다.");
			System.out.println(' ');
		}
			

		
	}
	
	
	
}
