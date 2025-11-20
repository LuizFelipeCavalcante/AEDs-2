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
        public int getId(){
            return this.id;
        }
        
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
    
        public void setReleaseDate(String releaseDate){
            if(releaseDate == null || releaseDate.equals("")) {
                this.releaseDate = "01/01/0000";
                return;
            }
            String str = removeAspas(releaseDate);
            if(str.length() < 7) { 
                this.releaseDate = "01/01/0000";
                return;
            }

            String mes = "";
            for(int i = 0; i < 3 && i < str.length(); i++){
                mes += str.charAt(i);
            }
            switch(mes){
                case "Jan": mes="01"; break;
                case "Feb": mes="02"; break;
                case "Mar": mes="03"; break;
                case "Apr": mes="04"; break;
                case "May": mes="05"; break;
                case "Jun": mes="06"; break;
                case "Jul": mes="07"; break;
                case "Aug": mes="08"; break;
                case "Sep": mes="09"; break;
                case "Oct": mes="10"; break;
                case "Nov": mes="11"; break;
                case "Dec": mes="12"; break;
                default: mes="01";
            }

            String dia="", ano="";
            if(str.length() > 5 && str.charAt(5) != ','){
                if(str.length() >= 12){
                    dia = "" + str.charAt(4) + str.charAt(5);
                    for(int i = 8; i <= 11; i++) ano += str.charAt(i);
                } else {
                    dia="01"; ano="0000";
                }
            } else {
                if(str.length() >= 11){
                    dia = "0" + str.charAt(4);
                    for(int i = 7; i <= 10; i++) ano += str.charAt(i);
                } else {
                    dia="01"; ano="0000";
                }
            }
            this.releaseDate = dia + "/" + mes + "/" + ano;
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

        public void imprimirVarios(int i){
        String e = " ## ";
        System.out.println("["+ String.valueOf(i) +"]"+" => "+ String.valueOf(this.id) + e + this.name + e + this.releaseDate + e + String.valueOf(this.estimatedOwners)
        + e + String.valueOf(this.price) + e + "[" + imprimirArrayVirgula(this.supportedLanguages) + "]" + e + String.valueOf(this.metacriticScore)
        + e + String.valueOf(this.userScore) + e + String.valueOf(this.achievements) + e + "["+ imprimirArray(this.publishers)+"]"
        + e + "["+ imprimirArray(this.developers) + "]"+ e +"[" +imprimirArrayVirgula(this.categories) +"]" +e + "["+imprimirArray(this.genres) + "]"+e + "[" + imprimirArrayVirgula(this.tags)+"] ##");
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

class NoName{
    String elemento;
    NoName esq, dir;
    NoName(){}
}
class No{
    int elemento;
    No esq, dir;
    NoName ponteiro;
    No(){};
    No(String game){this.elemento = game; ponteiro = esq = dir = null;}
}

class Arvore{
    No raiz;

    Arvore(int elemento){this.raiz = new No(elemento);}
    Arvore(){}
    private No inserirRec(int elemento, No raiz){
        if(raiz == null || elemento == raiz.elemento){
            return new No(elemento);
        }
        if(elemento > raiz.elemento)
            raiz.dir = inserirRec(elemento, raiz.dir);
        if(elemento < raiz.elemento)
            raiz.esq = inserirRec(elemento, raiz.esq);
        return raiz;
    }

    public void inserir(int elemento){
        this.raiz = inserirRec(elemento, this.raiz);
    }

    private String pesquisarRec(int elemento, No raiz ){
        if(raiz == null)
            return " NAO";
        if(elemento == raiz.elemento){
            return " SIM";
        }
        if(elemento > raiz.elemento){
            return " dir" + pesquisarRec(elemento, raiz.dir);
        }else{
            return " esq" + pesquisarRec(elemento, raiz.esq);
        }
    }

    public String pesquisar(int elemento){
        return "raiz " + pesquisarRec(elemento, this.raiz);
    }

    private void mostrarRec(No raiz){
        if(raiz == null){
            return;
        }
        mostrarRec(raiz.esq);
        System.out.print(" " + raiz.elemento);
        mostrarRec(raiz.dir);
    }

    public void mostrar(){
        mostrarRec(this.raiz);
    }
}



public class arvoreDeArvores {
    
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


    public static Game buscarPorId(String id){
        File file = new File("games.csv");
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
            return null;
        }

        String line = scanner.nextLine();

        while(!separador(line)[0].equals(id)){
            line = scanner.nextLine();
        }

        String[] dados = separador(line);
        Game game = new Game();
        game.setId(dados[0]);
        game.setName(dados[1]);
       
        scanner.close();
        return game;
    }

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();
        Arvore arvore = new Arvore();

        while(!str.equals("FIM")){
            Game game = new Game();
            game = buscarPorId(str);
            arvore.inserir(game.getName());
            str = scanner.nextLine();
        }

        str = scanner.nextLine();
        while(!str.equals("FIM")){
            System.out.println(str + ": =>" + arvore.pesquisar(str));
            str = scanner.nextLine();
        }

        scanner.close();
    }

}
