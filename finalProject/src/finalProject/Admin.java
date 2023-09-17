package finalProject;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Admin {
	private static int id;
	private static String pw;
	
	
	public void SetAdmin() {
		Scanner scanner = new Scanner(System.in);
		MyVendingMachine MVM = new MyVendingMachine();
		
		
		System.out.println("--------------------------");
		System.out.println("[VendingMachine]");
		System.out.println("--------------------------");
		System.out.println("관리자를 생성합니다.");
		
		System.out.print("새로운 id를 생성하세요(숫자만 생성가능)");
		while(true) {
			try {
				id = scanner.nextInt();
				break;
			}
			catch(InputMismatchException e) {
				scanner = new Scanner(System.in);
				System.out.println("아이디를 숫자로 지정하세요.");
				System.out.print("id : ");
			}
		}
		
		System.out.print("새로운 pw를 생성하세요(숫자, 영문, 대소문자 가능)");
		pw = scanner.next();
	
		
		System.out.print("새로운 관리자 id : " + id + '\t');
		System.out.print("pw : ");
		for(int i = 1; i <= pw.length(); i++) {
			System.out.print('*');
		}
		System.out.println(' ');
		System.out.println(' ');

		
		MVM.menuPrint();

		
	}
	
	public boolean checkAdmin() {
		MyVendingMachine MVM = new MyVendingMachine();
		Scanner scanner = new Scanner(System.in);
		System.out.println(' ');
		System.out.println("관리자 권한이 있어야 하는 메뉴입니다.");
		
		System.out.print("id : ");
		int inputId = 0;
		
		while(true) {
			try {
				inputId = scanner.nextInt();
				break;
			}
			catch(InputMismatchException e) {
				scanner = new Scanner(System.in);
				System.out.println("아이디는 숫자입니다.");
				System.out.print("id : ");
			}
		}
		
		System.out.print("pw : ");
		String inputPw = scanner.next();
		if(id == inputId && pw.equals(inputPw)) {
			System.out.println(' ');
			return true;
		}
		else {
			System.out.println(' ');
			System.out.println("관리자 권한이 없습니다.");
			System.out.println(' ');
			MVM.menuPrint();
			return false;
		}
		
		
	}
}
