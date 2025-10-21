import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/*  public class Game{

    private int id;
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

    public void setId(string id){
        this.id = id;
    }
    
    public void setName(string name){
        this.name = name;
    }
    
    public void setReleaseDate(string releaseDate){
        this.releaseDate = releaseDate;
    }
    
    public void setEstimatedOwners(string estimatedOwners){
        this.estimatedOwners = estimatedOwners;
    }
    
    public void setPrice(string price){
        this.price = price;
    }
    
    public void setSupportedLanguages(string supportedLanguages){
        this.supportedLanguages = supportedLanguages;
    }
    
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
    }
}
*/

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String chave = scanner.nextLine();

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
        System.out.println(linha);
        
    }
}