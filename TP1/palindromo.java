import java.util.Scanner;

class palindromo{

	public static void main(String[] arg){
		
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();						
		int n = str.length();
		String result = "";	
		while(!str.equals("FIM")){

		for(int i = 0; i < n; i++){ //
			if(str.charAt(i) == str.charAt(n - i - 1)){
				result = "SIM";	
			}
			else{
			result = "NAO";
			i = n;
			}			
		}

		MyIO.println(result);
		str = scanner.nextLine();
		n = str.length();	
		result = "";	
		
		}


		
		
	
	}





}
