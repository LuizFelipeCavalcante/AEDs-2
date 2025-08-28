import java.util.Scanner;

class ciframentoCesar{


        public static String ciframento(String str){
                int n = str.length();
                char a;
                String result = "";

                for(int i = 0; i < n; i++){
                if(str.charAt(i) > 31 && str.charAt(i) < 127){
                        a = (char)(str.charAt(i) + 3);
                        result = result + a;

                } else {
                        result = result + str.charAt(i);
                }

                }
                return result;
        }





        public static void main(String[] arg){
                Scanner scanner = new Scanner(System.in);
                String str = scanner.nextLine();
                while(!str.equals("FIM")){
                System.out.println(ciframento(str));
                str = scanner.nextLine();
                }


                scanner.close();
	
	}
}
