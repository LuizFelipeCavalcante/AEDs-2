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
        supportedLanguages = removeEspacoEntreVirgulas(str);
        String[] partes = separadorVirgula(supportedLanguages);

        int tam = partes.length;
        this.supportedLanguages = new String[tam];

        for(int i =  0; i < tam; i++){
            this.supportedLanguages[i] = partes[i];
        }
    }

    
    public void setMetacriticScore(String metacriticScore){
        this.metacriticScore = Integer.parseInt(metacriticScore);
    }

    
    public void setUserScore(String userScore){
        if(userScore.equals("") || userScore.equals("tbd")){
            this.userScore = -1.00f;
        }else{
             this.userScore = Float.parseFloat(userScore);
        }
    }

   
    public void setAchievement(String achievements){
        if(achievements.equals("")){
            this.achievements = 0;
        }
        this.achievements = Integer.parseInt(achievements);
    }

     
    // public void setPublisher(String publishers){
    //     this.publishers = publishers;
    // }
    
    // public void setDevelopers(String developers){
    //     this.developers = developers;
    // }
    
    // public void setCategories(String categories){
    //     this.categories = categories;
    // }
    
    // public void setGenres(String genres){
    //     this.genres = genres;
    // }
    
    // public void setTags(String tags){
    //     this.tags = tags;
    // }
    

    public void imprimir(){
        System.out.println(String.valueOf(this.id) + " "+ this.name + " " + this.releaseDate + " " + String.valueOf(this.estimatedOwners)
        + " " + String.valueOf(this.price) + " " + imprimirArray(this.supportedLanguages) + " " + String.valueOf(this.metacriticScore)
        + " " + String.valueOf(this.userScore) + " " + String.valueOf(this.achievements));




    }

    private String imprimirArray(String[] str){
        String resultado = "";
        for(int i = 0; i < str.length; i++){
            resultado = resultado + str[i] + " ";
        }
        return resultado;
    }

    private String removeAspas(String str){
        String resultado = "";
         for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) != '"'){
                resultado = resultado + str.charAt(i);
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
            if(!(str.charAt(i) == '[' || str.charAt(i) == ']')){
                resultado = resultado + str.charAt(i);
            }
        }
        return resultado;
    }

    private String removeEspacoEntreVirgulas(String str){
         String resultado = "";
         int virgula = 0;
         for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == ','){
                virgula ++;
                resultado = resultado + str.charAt(i);
            }else if(virgula == 1 && str.charAt(i) == ' '){
                virgula = 0;
            }else{
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

        String[] resultado = new String[tam+1];
        int j = 0;

        for(int i = 0; i < resultado.length; i++){
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
        String chave = "40800"; //scanner.nextLine();

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
       System.out.println(linha); // so pra teste
       Game game = new Game();
        game.setId(separador(linha)[0]);
        game.setName(separador(linha)[1]);
        game.setReleaseDate(separador(linha)[2]);
        game.setEstimatedOwners(separador(linha)[3]);
        game.setPrice(separador(linha)[4]);
        game.setSupportedLanguages(separador(linha)[5]);
        game.setMetacriticScore(separador(linha)[6]);
        game.setUserScore(separador(linha)[7]);
        game.setAchievement(separador(linha)[8]);
        game.setPublisher(separador(linha)[9]);
        game.setDevelopers(separador(linha)[10]);
        game.setCategories(separador(linha)[11]);
        game.setGenres(separador(linha)[12]);
        game.setTags(separador(linha)[13]);

        // System.out.println(separador(linha)[9]);
       game.imprimir();
        
    }
}