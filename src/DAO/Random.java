package DAO;

public class Random {
	
	public static int soluong(int count) {
		
		if (count == 0) return 1; else
		{
			int dem=0;
		int m= count;
		while (m>0) {
			dem++;
			m=m/10; 
		}
		return dem;
		
	}
	}
	public static String createStringRandom(String character,int count) {
		String number ="";
		String st;
		for (int i=1;i<=3-soluong(count);i++) {
			number=number+'0';
		}
		
		number = number+ String.valueOf(count);
		st = character+number;
	
		return st;
        		
		
		
	}
	
	
	public static String createStringRandomService(int count) {
		String character="HDDV";
		String number ="";
		String st;
		for (int i=1;i<=3-soluong(count);i++) {
			number=number+'0';
		}
		
		number = number+ String.valueOf(count);
		st = character+number;
	
		return st;
        		
		
		
	}
	
	



}
