
class InversaoString{
	
	public static boolean fim(String str){
	return (str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M')? true : false;
	}


	public static String inversao(String str){
	int n = str.length();
	String result = "";
	for(int i = n-1; i >= 0; i--){
		
		result = result + str.charAt(i);
	}
	return result;
	
	}




	public static void main(String[] arg){
	
	String str = MyIO.readLine();
	while(!fim(str)){
	MyIO.println(inversao(str));
	str = MyIO.readLine();
	}
	
	}



}
