class VerificacaoAnagrama{

	
	
	public static boolean fim(String str){
        return (str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M')? true : false;
        
	
	}

	public static boolean verificacao(String str1, String str2){
	int n = str1.length();
	int m = str2.length();
	int resultado = 0;

	if(n != m){return false;}else{

	int[] letras = new int[26];

	for(int i = 0; i < n; i++){

		if(str1.charAt(i) >= 'A' && str1.charAt(i) <= 'Z'){
		letras[str1.charAt(i) + 32 - 'a'] += 1;
		}

		else{
		letras[str1.charAt(i) - 'a'] += 1;
		}
	}

	for(int i = 0; i < n; i++){

                if(str2.charAt(i) >= 'A' && str2.charAt(i) <= 'Z'){
                letras[str2.charAt(i) + 32 - 'a'] -= 1;
		}

		else{
                letras[str2.charAt(i) - 'a'] -= 1;
                }
        }
	for(int i = 0; i < 26; i++){
	if(letras[i] != 0){resultado ++;}
	}

	return (resultado > 0)? false : true;
		
	}
}


public static void main(String[] arg){

String str3 = MyIO.readLine();

while(!fim(str3)){
String str1 = "";
String str2 = "";
int n = str3.length();
boolean primeira = true;

for(int i = 0; i < n; i++){
	if(str3.charAt(i) == '-'){
		primeira = false;
	}else if(str3.charAt(i) == ' '){}
	else if(primeira == true){
		str1 = str1 + str3.charAt(i);	
	}else{str2 = str2 + str3.charAt(i);}

}

MyIO.println(verificacao(str1, str2) == true ? "SIM" : "NÃO");
str3 = MyIO.readLine();

}
}












}
