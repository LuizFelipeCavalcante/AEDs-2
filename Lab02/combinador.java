class Combinador{

public static Strign programa(String str1, String str2){
	int x, y;
	boolean primeiro = false; 
	if(str1.length() > str2.length()){
		x = str1.length();
		y = str2.length();
		primeiro = true;
	}else{
		y = str1.length();
		x = str2.length();
	}
	String resultado = "";
	for(int i = 0; i < x; i++){
		if(primeiro == true && i+1 >= y){
		resultado = resultado + str1.charAt(i);
		}else if(primeiro == false && i+1 >= y){
		resultado = resultado + str2.charAt(i);
		}else{
		resultado = resultado + str1.charAt(i) + str2.charAt(i);
		}
	}
}




public static void main(String[] arg){
String str1 = MyIO.readLine();
String str2 = MyIO.readLine();

while(){

}

}
}
