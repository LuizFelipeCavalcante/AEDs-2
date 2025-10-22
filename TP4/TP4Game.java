import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

 class Game{

    public int id;
    private String name;
    private String releaseDate;
    private int estimatedOwners;
    private float price;
    private String[] supportedLanguages;
    private int metacriticScore;
    private float userScore;
    private int achievements;
    private String[] publishers;
    private String[] developers;
    private String[] categories;
    private String[] genres;
    private String[] tags;

    public Game(){};

    public void setId(String id){
        this.id = Integer.parseInt(id);
    }
    
    public void setName(String name){
        this.name = name;
    }
   
    public void setReleaseDate(String releaseDate){
        String str = "";
        str = removeAspas(releaseDate);
        String mes = "";
        for(int i = 0; i < 3; i++){
            mes = mes + str.charAt(i);
        }
        switch (mes) {
            case "Jan": 
            mes = "01";
            break;
            case "Feb":
            mes = "02";
            break;
            case "Mar":
            mes = "03";
            break;
            case "Apr":
            mes = "04";
            break;
            case "May":
            mes = "05";
            break;
            case "Jun":
            mes = "06";
            break;
            case "Jul":
            mes = "07";
            break;
            case "Aug":
            mes = "08";
            break;
            case "Sep":
            mes = "09";
            break;
            case "Oct":
            mes = "10";
            break;
            case "Nov":
            mes = "11";
            break;
            case "Dec":
            mes = "12"; 
            break;
            default:
            mes = "01";
                break;
        }

        String ano = "";
        String dia = "";
        if(str.charAt(5) != ','){
            dia = "" + str.charAt(4) + str.charAt(5);
            for(int i = 8; i <= 11; i++){
                ano = ano + str.charAt(i);
            }
        }else{
            dia = "0" + str.charAt(4);
            for(int i = 7; i <= 10; i++){
                ano = ano + str.charAt(i);
            }
        }

        releaseDate = "";
        releaseDate = dia + "/" + mes + "/" + ano;
        this.releaseDate = releaseDate;
        }
        
    
     
    public void setEstimatedOwners(String estimatedOwners){
        String resultado = "";
        for(int i = 0; i < estimatedOwners.length(); i++){
            if(estimatedOwners.charAt(i) > 47 && estimatedOwners.charAt(i) < 58){
                resultado = resultado + estimatedOwners.charAt(i);
            }
        }
        
        this.estimatedOwners = Integer.parseInt(resultado);
    }

    
    public void setPrice(String price){
        this.price = Float.parseFloat(price);
    }


     
    public void setSupportedLanguages(String supportedLanguages){
        String str = removeAspas(supportedLanguages);
        supportedLanguages = removeColchete(str);
        str = removeAspasSimples(supportedLanguages);
        for(int i =  0; i < separadorVirgula(str).length; i++){
            this.supportedLanguages[i] = separadorVirgula(str)[i];
        }
    }

    /*
    public void setMetacriticScore(string metacriticScore){
        this.metacriticScore = metacriticScore;
    }
    
    public void setUserScore(string userScore){
        this.userScore = userScore;
    }
    
    public void setAchievement(string achievements){
        this.achievements = achievements;
    }
    
    public void setId(string id){
        this.id = id;
    }
    
    public void setId(string id){
        this.id = id;
    }
    
    public void setId(string id){
        this.id = id;
    }
    
    public void setId(string id){
        this.id = id;
    }
    
    public void setId(string id){
        this.id = id;
    }
    
    public void setId(string id){
        this.id = id;
    }
    
    public void setId(string id){
        this.id = id;
    } */

    public void imprimir(){
        System.out.println(String.valueOf(this.id) + " "+ this.name + " " + this.releaseDate + " " + String.valueOf(this.estimatedOwners)
        + " " + String.valueOf(this.price) + " " + imprimirArray(this.supportedLanguages));




    }

    private String imprimirArray(String[] str){
        String resultado = "";
        for(int i = 0; i < str.length; i++){
            resultado = resultado + str[i];
        }
        return resultado;
    }

    private String removeAspas(String str){
        String resultado = "";
         for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != '"'){
                resultado = resultado + str.charAt(i) + " ";
            }
        }
        return resultado;
    }

    private String removeAspasSimples(String str){
        String resultado = "";
         for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != '\''){
                resultado = resultado + str.charAt(i);
            }
        }
        return resultado;
    }

    private String removeColchete(String str){
        String resultado = "";
         for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != '[' || str.charAt(i) != ']'){
                resultado = resultado + str.charAt(i);
            }
        }
        return resultado;
    }

    private String[] separadorVirgula(String str){
        int tam = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ','){
                tam ++;
            }
        }

        String[] resultado = new String[tam-1];
        int j = 0;

        for(int i = 0; j < str.length(); i++){
            resultado[i] = "";

        while((j < str.length()) && (str.charAt(j) != ',')){
            resultado[i] = resultado[i] + str.charAt(j);
            j++;
        }
        j++;
    
        }
        return resultado;
    }
}

public class TP4Game{
    public static String lerId(String linha){
        int i = 0;
        String resultado = "";
        while(linha.charAt(i) != ','){
            resultado = resultado + linha.charAt(i);
            i++;
        }
        return resultado;
    }
    
    public static String[] separador(String linha){
        String[] resultado = new String[14];
        int aspas = 0;
        int j = 0;
        for(int i = 0; i < 14; i++){
            resultado[i] = "";

        while((j < linha.length()) && (linha.charAt(j) != ',' || aspas == 1)){
            
            if(linha.charAt(j) == '"'){
                aspas ++;
                if(aspas >= 2){aspas = 0;}
            }
            resultado[i] = resultado[i] + linha.charAt(j);
            j++;
        }
        j++;
    
        }
        return resultado;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String chave = "1172470"; //scanner.nextLine();

        File file = new File("games.csv");
        Scanner scanearArquivo = null;

        try{
        scanearArquivo = new Scanner(file);
       } catch(FileNotFoundException e){
            System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
            e.printStackTrace();
            return;
       }

       String linha = scanearArquivo.nextLine();

       while(!(lerId(linha).equals(chave))){
        linha = scanearArquivo.nextLine();
       }
    //    System.out.println(linha); // so pra teste
       Game game = new Game();
        game.setId(separador(linha)[0]);
        game.setName(separador(linha)[1]);
        game.setReleaseDate(separador(linha)[2]);
        game.setEstimatedOwners(separador(linha)[3]);
        game.setPrice(separador(linha)[4]);
        System.out.println(separador(linha)[5]);
       game.imprimir();
        
    }
}