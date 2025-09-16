class inversaoString{

	public static boolean fim(String str)
	{return (str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M') ? false: true;}




	public static String inversao(String str, int tam){

		if(tam <= 1){
			return str.charAt(0) + "";
		}else{
			return str.charAt(tam-1)+ ""  + inversao(str, tam - 1);
		}
		
	}





	public static void main(String[] arg){
	
	String str = MyIO.readLine();
	while(fim(str)){
		MyIO.println(inversao(str, str.length()));
		str = MyIO.readLine();
	
	}
	
	
	
	
	}




}
