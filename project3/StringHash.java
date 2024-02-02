package project3;
import java.lang.Math;

public class StringHash {
	
	private int size;
	private int initial;
	private int mult;
	private int prime;
	private String[] table;
	
	public StringHash(int x, int y, int z, int a) {
		size = x;
		initial = y;
		mult = z;
		prime = a;
		table = new String[size];
		for(int i = 0; i < table.length; i++) {
			table[i] = "<EMPTY>";
		}
	}
	
	boolean contains(String data) {
		int h1 = getH1(data);
		int h2 = (prime - (h1 % prime)) % size;
		int bucketsProbed = 0;
		int bucket = h1;
		System.out.print("Searching \"" + data + "\"");
		while(bucketsProbed < size) {
			System.out.print(" -> " + bucket);
			if(table[bucket].equals("<EMPTY>")) {
				System.out.println(" -> FAILED");
				return false;
			}
			if(table[bucket].equals(data)) {
				System.out.println();
				return true;
			}
			bucketsProbed++;
			bucket = (h1 + bucketsProbed * h2) % size;
		}
		System.out.println(" -> FAILED");
		return false;
		
	}
	
	boolean add(String data) {
		int h1 = getH1(data);
		int h2 = (prime - (h1 % prime)) % size;
		int bucketsProbed = 0;
		int bucket = h1;
		System.out.print("Adding \"" + data + "\"");
		while(bucketsProbed < size) {
			System.out.print(" -> " + bucket);
			if(table[bucket].equals("<EMPTY>") || table[bucket].equals("<REMOVED>")) {
				table[bucket] = data;
				System.out.println();
				return true;
			}
			if(table[bucket].equals(data)) {
				System.out.println(" -> FAILED");
				return false;
			}
			bucketsProbed++;
			bucket = (h1 + bucketsProbed * h2) % size;
		}
		System.out.println(" -> FAILED");
		return false;
	}
	
	boolean remove(String data) {
		int h1 = getH1(data);
		int h2 = (prime - (h1 % prime)) % size;
		int bucketsProbed = 0;
		int bucket = h1;
		System.out.print("Removing \"" + data + "\"");
		while(bucketsProbed < size) {
			System.out.print(" -> " + bucket);
			if(table[bucket].equals("<EMPTY>")) {
				System.out.println(" -> FAILED");
				return false;
			}
			if(table[bucket].equals(data)) {
				table[bucket] = "<REMOVED>"; 
				System.out.println();
				return true;
			}
			bucketsProbed++;
			bucket = (h1 + bucketsProbed * h2) % size;
		}
		System.out.println(" -> FAILED");
		return false;
	}
	
	void displayTable() {
		for (int i = 0; i < size; i ++) {
			System.out.println(" "+i + " : " + table[i]);
		}
	}
	
	void resize() {
		size = size*2;
		String[] newTable = new String[size];
		for (int i = 0; i < size; i++)
		{
			newTable[i] = "<EMPTY>";
		}
		for(int i = 0; i < table.length; i++)
		{
			int h1 = getH1(table[i]);
			int h2 = (prime - (h1 % prime)) % size;
			int bucketsProbed = 0;
			int bucket = h1;
			if(!(table[i].equals("<EMPTY>")) && !(table[i].equals("<REMOVED>"))) {
				System.out.print("Rehashing \"" + table[i] +"\"");
				while(bucketsProbed < size) {
					System.out.print(" -> " + bucket);
					if(newTable[bucket].equals("<EMPTY>")) {
						newTable[bucket] = table[i];
						System.out.println();
						break;
					}
					bucketsProbed++;
					bucket = (h1 + bucketsProbed * h2) % size;
				}
			}
		}
		table = newTable;
		
	}
	
	
	int getH1(String data) {
		int h1 = initial;
        for(int i = 0; i < data.length(); i ++) {
        	h1 = (h1 * mult) + data.charAt(i);
        }
		return Math.abs(h1 % size);
	}
	
}
