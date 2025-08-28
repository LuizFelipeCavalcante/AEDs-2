import java.util.Scanner;
import java.util.Random;

class alteracaoAleatoria{


        public static String alteracao(String str){
                int n = str.length();
                char a, b;
                String result = "";
                 Random gerador = new Random();
                gerador.setSeed(4);
		a = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
		b = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
                
		for(int i = 0; i < n; i++){
                        if(str.charAt(i) == a){
                        result = result + b;
			}else{
			result = result + str.charAt(i);
			}
                }
                return result;
        }





        public static void main(String[] arg){
	
		
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                while(!str.equals("FIM")){
                System.out.println(alteracao(str));
                str = scanner.nextLine();
                }


                scanner.close();



        }




}
