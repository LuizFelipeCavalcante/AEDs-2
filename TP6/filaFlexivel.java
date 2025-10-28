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

     
    public void setPublisher(String publishers){
        String str = removeAspas(publishers);

        
        String[] partes = separadorVirgula(str);

        this.publishers = new String[partes.length];
        for(int i = 0; i < partes.length; i++){
            
            this.publishers[i] = partes[i];
            // System.out.println(this.publishers[i] + ".");
        }
        
    }
    
    public void setDevelopers(String developers){
         String str = removeAspas(developers);


        String[] partes = separadorVirgula(str);

        this.developers = new String[partes.length];
        for(int i = 0; i < partes.length; i++){

            this.developers[i] = partes[i];

        }
    }
    
    public void setCategories(String categories){
        String str = removeAspas(categories);
        categories = removeColchete(str);
        str = removeAspasSimples(categories);
        categories = removeEspacoEntreVirgulas(str);
        String[] partes = separadorVirgula(categories);

        int tam = partes.length;
        this.categories = new String[tam];

        for(int i =  0; i < tam; i++){
            this.categories[i] = partes[i];
        }
    }
    
    public void setGenres(String genres){
        String str = removeAspas(genres);
        genres = removeColchete(str);
        str = removeAspasSimples(genres);
        genres = removeEspacoEntreVirgulas(str);
        String[] partes = separadorVirgula(genres);

        int tam = partes.length;
        this.genres = new String[tam];

        for(int i =  0; i < tam; i++){
            this.genres[i] = partes[i];
        }
    }
    
    public void setTags(String tags){
        String str = removeAspas(tags);
        tags = removeColchete(str);
        str = removeAspasSimples(tags);
        tags = removeEspacoEntreVirgulas(str);
        String[] partes = separadorVirgula(tags);

        int tam = partes.length;
        this.tags = new String[tam];

        for(int i =  0; i < tam; i++){
            this.tags[i] = partes[i];
        }
    }
    

    public void imprimir(){
        String e = " ## ";
        System.out.println("=> "+ String.valueOf(this.id) + e + this.name + e + this.releaseDate + e + String.valueOf(this.estimatedOwners)
        + e + String.valueOf(this.price) + e + "[" + imprimirArrayVirgula(this.supportedLanguages) + "]" + e + String.valueOf(this.metacriticScore)
        + e + String.valueOf(this.userScore) + e + String.valueOf(this.achievements) + e + "["+ imprimirArray(this.publishers)+"]"
        + e + "["+ imprimirArray(this.developers) + "]"+ e +"[" +imprimirArrayVirgula(this.categories) +"]" +e + "["+imprimirArray(this.genres) + "]"+e + "[" + imprimirArrayVirgula(this.tags)+"]");
    }

    private String imprimirArray(String[] str){
        String resultado = "";
        for(int i = 0; i < str.length; i++){
            if(i == str.length-1){resultado = resultado + str[i];}
            else{resultado = resultado + str[i] + " ";}
        }
        return resultado;
    }

    private String imprimirArrayVirgula(String[] str){
        String resultado = "";
        for(int i = 0; i < str.length; i++){
            if(i == str.length-1){resultado = resultado + str[i];}
            else{resultado = resultado + str[i] + ", ";}
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

class Celula{
    public Game elemento;
    public Celula prox;

    public Celula(){
        this.elemento = 0;
        this.prox = null;
    }

    public Celula(Game elemento){
        this.elemento = elemento;
        this.prox = null;
    }
}

class Lista{
    private Celula primeiro, ultimo;

    public Lista(){
        ultimo = primeiro = new Celula();
    }

    public void inserirFim(Game x){
        ultimo.prox = new Celula(x);
        ultimo = ultimo.prox;
    }

    // public void inserirInicio(int x){
    //     Celula temp = new Celula(x);
    //     temp.prox = primeiro.prox;
    //     primeiro.prox = temp;
    //     if(primeiro == ultimo){
    //         ultimo = temp;
    //     }
    //     temp = null;
    // }

    // public int removerFim(){
    //     if(primeiro == ultimo){
    //         return 0;
    //     }else{
    //         Celula i;
    //     for(i = primeiro; i.prox != ultimo; i = i.prox);
    //         int elemento = ultimo.elemento;
    //         ultimo = i;
    //         i = ultimo.prox = null;
        
    //         return elemento;
    //     }
    // }

    public int removerInicio(){
        if(ultimo == primeiro){
            return 0;
        }else{
            Celula tmp = primeiro.prox;
            primeiro.prox = tmp.prox;
            int resultado = tmp.elemento;
            tmp.prox = null;
            tmp = null;
            return resultado ;
        }
    }

    // public int inserir(int x, int pos){
    //     int tamanho = tamanho();
    //     if(pos < 0 || pos > tamanho){
    //        return 0;
    //     }else if(pos == 0){
    //         inserirInicio(x);
    //     }else if(pos == tamanho){
    //         inserirFim(x);
    //     }else{
    //         Celula ponteiro = primeiro.prox;
    //         for(int i = 0; i < pos; i++){ponteiro = ponteiro.prox;}
    //         Celula tmp = new Celula(x);
    //         tmp.prox = ponteiro.prox;
    //         ponteiro.prox = tmp;
    //         tmp = ponteiro = null;
    //     } 
    // }

    public void mostrar(){
        for(Celula i = primeiro.prox; i != null; i = i.prox){
            System.out.println(ponteiro.elemento + " ");  
        }
    }



}

public class filaFlexivel{
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
        String chave = "866800"; //scanner.nextLine();

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

        game.imprimir();
        
    }
}