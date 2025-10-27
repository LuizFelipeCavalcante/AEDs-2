import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class Game {

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

    public Game() {
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id.trim()); 
    }

    public int getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        String str = removeAspas(releaseDate).trim(); 
        String[] partes = str.split(" ");
        String mes = "";

        if (partes.length >= 3) {
            mes = partes[0].substring(0, 3); 

            switch (mes) {
                case "Jan": mes = "01"; break;
                case "Feb": mes = "02"; break;
                case "Mar": mes = "03"; break;
                case "Apr": mes = "04"; break;
                case "May": mes = "05"; break;
                case "Jun": mes = "06"; break;
                case "Jul": mes = "07"; break;
                case "Aug": mes = "08"; break;
                case "Sep": mes = "09"; break;
                case "Oct": mes = "10"; break;
                case "Nov": mes = "11"; break;
                case "Dec": mes = "12"; break;
                default: mes = "01"; break; 
            }

            String diaComVirgula = partes[1];
            String dia = diaComVirgula.replace(",", "");
            
            if (dia.length() == 1) { 
                dia = "0" + dia;
            }

            String ano = partes[2];
            
            this.releaseDate = dia + "/" + mes + "/" + ano;
        } else {
             this.releaseDate = "01/01/1970"; 
        }
    }


    public void setEstimatedOwners(String estimatedOwners) {
        String limpo = estimatedOwners.replace(",", "").replace("+", "").trim(); 
        this.estimatedOwners = Integer.parseInt(limpo);
    }

    public int getEstimatedOwners() {
        return this.estimatedOwners;
    }

    public void setPrice(String price) {
        this.price = Float.parseFloat(price);
    }
    public float getPrice() {
        return this.price;
    }

    public void setSupportedLanguages(String supportedLanguages) {
        String str = removeAspas(supportedLanguages);
        str = removeColchete(str);
        str = removeAspasSimples(str);
        str = removeEspacoEntreVirgulas(str);
        
        if (str.trim().isEmpty()) {
             this.supportedLanguages = new String[0];
             return;
        }
        
        this.supportedLanguages = separadorVirgula(str);
    }

    public void setMetacriticScore(String metacriticScore) {
        this.metacriticScore = Integer.parseInt(metacriticScore.trim());
    }

    public void setUserScore(String userScore) {
        String s = userScore.trim();
        if (s.equals("") || s.equalsIgnoreCase("tbd")) {
            this.userScore = -1.00f;
        } else {
            this.userScore = Float.parseFloat(s);
        }
    }

    public void setAchievement(String achievements) {
        String s = achievements.trim();
        if (s.isEmpty()) {
            this.achievements = 0;
        } else {
            this.achievements = Integer.parseInt(s);
        }
    }

    public void setPublisher(String publishers) {
        String str = removeAspas(publishers).trim();
        if (str.isEmpty()) {
            this.publishers = new String[0];
            return;
        }
        this.publishers = separadorVirgula(str);
    }

    public void setDevelopers(String developers) {
        String str = removeAspas(developers).trim();
        if (str.isEmpty()) {
            this.developers = new String[0];
            return;
        }
        this.developers = separadorVirgula(str);
    }

    public void setCategories(String categories) {
        String str = removeAspas(categories);
        str = removeColchete(str);
        str = removeAspasSimples(str);
        str = removeEspacoEntreVirgulas(str);
         if (str.trim().isEmpty()) {
            this.categories = new String[0];
            return;
        }
        this.categories = separadorVirgula(str);
    }

    public void setGenres(String genres) {
        String str = removeAspas(genres);
        str = removeColchete(str);
        str = removeAspasSimples(str);
        str = removeEspacoEntreVirgulas(str);
         if (str.trim().isEmpty()) {
            this.genres = new String[0];
            return;
        }
        this.genres = separadorVirgula(str);
    }

    public void setTags(String tags) {
        String str = removeAspas(tags);
        str = removeColchete(str);
        str = removeEspacoEntreVirgulas(str);
        if (str.trim().isEmpty()) {
            this.tags = new String[0];
            return;
        }
        this.tags = separadorVirgula(str);
    }

    public void imprimir() {
        String e = " ## ";
        System.out.println("=> " + String.valueOf(this.id) + e + this.name + e + this.releaseDate + e
                + String.valueOf(this.estimatedOwners)
                + e + String.valueOf(this.price) + e + "[" + imprimirArrayVirgula(this.supportedLanguages) + "]" + e
                + String.valueOf(this.metacriticScore)
                + e + String.valueOf(this.userScore) + e + String.valueOf(this.achievements) + e + "["
                + imprimirArray(this.publishers) + "]"
                + e + "[" + imprimirArray(this.developers) + "]" + e + "[" + imprimirArrayVirgula(this.categories) + "]"
                + e + "[" + imprimirArrayVirgula(this.genres) + "]" + e + "[" + imprimirArrayVirgula(this.tags)
                + "] ##");
    }

    private String imprimirArray(String[] str) {
        String resultado = "";
        for (int i = 0; i < str.length; i++) {
            if (i == str.length - 1) {
                resultado = resultado + str[i];
            } else {
                resultado = resultado + str[i]; 
            }
        }
        return resultado;
    }

    private String imprimirArrayVirgula(String[] str) {
        String resultado = "";
        for (int i = 0; i < str.length; i++) {
            String item = str[i].trim(); 
            if (i == str.length - 1) {
                resultado = resultado + item;
            } else {
                resultado = resultado + item + ","; 
            }
        }
        return resultado;
    }

    private String removeAspas(String str) {
        return str.replace("\"", ""); 
    }

    private String removeAspasSimples(String str) {
        return str.replace("'", ""); 
    }

    private String removeColchete(String str) {
        return str.replace("[", "").replace("]", ""); 
    }

    private String removeEspacoEntreVirgulas(String str) {
        return str.replace(", ", ",").replace(" ,", ",").trim();
    }
    
    private String[] separadorVirgula(String str) {
        return str.split(",");
    }
}

public class mergeSortGame {
    
    private static long comparacoes = 0;
    private static long movimentacoes = 0;

    private static void logToFile(String message) {
        String nomeArquivo = "log_execucao.txt";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = dtf.format(LocalDateTime.now());
        String logEntry = String.format("[%s] %s", timestamp, message);

        try (
            FileWriter fileWriter = new FileWriter(nomeArquivo, true);
            PrintWriter printWriter = new PrintWriter(fileWriter)
        ) {
            printWriter.println(logEntry);
            
        } catch (IOException e) {
        }
    }

    public static String lerId(String linha) {
        int i = 0;
        String resultado = "";
        while (i < linha.length() && linha.charAt(i) != ',') {
            resultado = resultado + linha.charAt(i);
            i++;
        }
        return resultado;
    }

    public static String[] separador(String linha) {
        String[] resultado = new String[14];
        int aspas = 0;
        int j = 0;
        
        for (int i = 0; i < 14; i++) {
            resultado[i] = "";

            while ((j < linha.length()) && (linha.charAt(j) != ',' || aspas == 1)) {
                if (linha.charAt(j) == '"') {
                    aspas = 1 - aspas;
                }
                resultado[i] = resultado[i] + linha.charAt(j);
                j++;
            }
            j++;
            
            resultado[i] = resultado[i].trim();
        }
        return resultado;
    }

    public static void mergeSort(Game[] array, int n) {
        Game[] temp = new Game[n];
        mergeSortRecursive(array, temp, 0, n - 1);
    }

    private static void mergeSortRecursive(Game[] array, Game[] temp, int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSortRecursive(array, temp, inicio, meio);
            mergeSortRecursive(array, temp, meio + 1, fim);
            merge(array, temp, inicio, meio, fim);
        }
    }

    private static void merge(Game[] array, Game[] temp, int inicio, int meio, int fim) {
        int i, j, k;
        
        for (i = inicio; i <= fim; i++) {
            temp[i] = array[i];
            movimentacoes++; 
        }
        
        i = inicio; 
        j = meio + 1; 
        k = inicio; 

        while (i <= meio && j <= fim) {
            if (isFirstGameSmaller(temp[i], temp[j])) {
                array[k] = temp[i]; 
                i++;
            } else {
                array[k] = temp[j]; 
                j++;
            }
            movimentacoes++;
            k++;
        }

        while (i <= meio) {
            array[k++] = temp[i++];
            movimentacoes++;
        }
        
        while (j <= fim) {
            array[k++] = temp[j++];
            movimentacoes++;
        }
    }

    private static boolean isFirstGameSmaller(Game game1, Game game2) {
        comparacoes++;
        
        if (game1.getPrice() < game2.getPrice()) {
            return true; 
        }
        if (game1.getPrice() > game2.getPrice()) {
            return false; 
        }

        comparacoes++;
        int id1 = game1.getId();
        int id2 = game2.getId();

        if (id1 < id2) {
            return true;
        }
        
        return false;
    }
    
    public static void main(String[] args) {

        logToFile("Início da execução do programa.");

        Scanner scanner = new Scanner(System.in);
        String chave = scanner.nextLine();
        File file = new File("/tmp/games.csv");
        
        StringBuilder chavesDigitadas = new StringBuilder();
        int counter = 0;
        
        while (!chave.equalsIgnoreCase("FIM")) {
            chavesDigitadas.append(chave).append('\n'); 

            try (Scanner scanearArquivo = new Scanner(file)) {
                String linha = scanearArquivo.nextLine(); 
                
                while (scanearArquivo.hasNextLine()) {
                    linha = scanearArquivo.nextLine();
                    
                    if (chave.equals(lerId(linha))) {
                        counter++;
                        break; 
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Erro ao abrir o arquivo: " + e.getMessage());
                e.printStackTrace();
                return;
            }

            chave = scanner.nextLine();
        }

        Game[] game = new Game[counter];
        int indiceGame = 0;
        
        Scanner leitorDeChaves = new Scanner(chavesDigitadas.toString());
        
        while(leitorDeChaves.hasNextLine()){
            chave = leitorDeChaves.nextLine();
            
            try (Scanner scanearArquivo = new Scanner(file)) {
                String linha = scanearArquivo.nextLine();

                while (scanearArquivo.hasNextLine() && indiceGame < counter) {
                    linha = scanearArquivo.nextLine();

                    if (chave.equals(lerId(linha))) {
                        String[] campos = separador(linha); 

                        game[indiceGame] = new Game();
                        game[indiceGame].setId(campos[0]);
                        game[indiceGame].setName(campos[1]);
                        game[indiceGame].setReleaseDate(campos[2]);
                        game[indiceGame].setEstimatedOwners(campos[3]);
                        game[indiceGame].setPrice(campos[4]);
                        game[indiceGame].setSupportedLanguages(campos[5]);
                        game[indiceGame].setMetacriticScore(campos[6]);
                        game[indiceGame].setUserScore(campos[7]);
                        game[indiceGame].setAchievement(campos[8]);
                        game[indiceGame].setPublisher(campos[9]);
                        game[indiceGame].setDevelopers(campos[10]);
                        game[indiceGame].setCategories(campos[11]);
                        game[indiceGame].setGenres(campos[12]);
                        game[indiceGame].setTags(campos[13]);

                        indiceGame++;
                        break; 
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Erro ao reabrir o arquivo: " + e.getMessage());
                e.printStackTrace();
                return;
            }
        }
        leitorDeChaves.close();
        scanner.close();

        String matricula = "00879283"; 

        long startTime = System.nanoTime();
        
        mergeSort(game, counter);
        
        long endTime = System.nanoTime();
        
        long tempoExecucaoMs = (endTime - startTime) / 1_000_000; 
        
        
        MyIO.println("| 5 preços mais caros |");
        for (int i = 1; i <= 5; i++) {
            if (counter - i >= 0) {
                 game[counter-i].imprimir();
            }
        }
        MyIO.println("");
        
        MyIO.println("| 5 preços mais baratos |");
        for(int i = 0; i < 5; i++){
            if (i < counter) {
                game[i].imprimir();
            }
        }

        String nomeLogFinal = matricula + "_mergesort.txt";
        
        try (
            FileWriter fileWriter = new FileWriter(nomeLogFinal);
            PrintWriter printWriter = new PrintWriter(fileWriter)
        ) {
            String logFinal = String.format("%s\t%d\t%d\t%d", 
                                                matricula, 
                                                comparacoes, 
                                                movimentacoes, 
                                                tempoExecucaoMs);
                                                
            printWriter.println(logFinal);
            
        } catch (IOException e) {
        }

        logToFile("Fim da execução do programa.");
    }
}