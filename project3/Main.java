package project3;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		char menu = ' ';
		int size;
		int hashValue;
		int hashMult;
		int relPrime;
		
		System.out.println("Enter table size: ");
		size = s.nextInt();
		System.out.println("Enter initial has value: ");
		hashValue = s.nextInt();
		System.out.println("Enter has multiplier: ");
		hashMult = s.nextInt();
		System.out.println("Enter relative prime value: ");
		relPrime = s.nextInt();
		
		StringHash user = new StringHash(size, hashValue, hashMult, relPrime);
		
		while(menu != 'q' && menu != 'Q') {
			System.out.println("1. Search String\r\n"
					+ "2. Add String\r\n"
					+ "3. Remove String\r\n"
					+ "4. Display Table\r\n"
					+ "5. Resize Table\r\n"
					+ "Q. Quit\r\n"
					+ "Choice: ");
			menu = s.next().charAt(0);
			if(menu == '1') {
				System.out.println("String to search for: ");
				if(!user.contains(s.next())) {
					System.out.println("\nFALSE");
				} else {
					System.out.println("\nTRUE");
				}
					
				
			}else if(menu == '2') {
				System.out.println("String to add: ");
				user.add(s.next());
				
			}else if(menu == '3') {
				System.out.println("String to remove: ");
				user.remove(s.next());
				
			}else if(menu == '4') {
				user.displayTable();
				
			}else if(menu == '5') {
				user.resize();
			}
		}
		
		
	}

}
