import java.util.Scanner;
class SomaDigitos{

	public static boolean fim(String s)
	{return (s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M') ? false : true;}

	public static int soma(int n){
		if(n / 10 == 0){
			return n;
		}
		else{
			return soma(n/10) + (n%10); 
		}
	}
	
	public static int converte(String str){
		int resultado = 0;
		int n = str.length();
		for(int i = 0; i < n; i++){
			resultado = (resultado*10) + (str.charAt(i)-48); 
		}
		return resultado;
	}

	public static void main(String[] args){
	Scanner scanner = new Scanner(System.in);
	String str = scanner.nextLine();

	while(fim(str)){
	System.out.println(soma(converte(str)));
	str = scanner.nextLine();
	
	}
	
	}






}
