import java.util.*;
import java.io.*;

class Game {
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

    public void setId(String s) {
        if (s.isEmpty()) this.id = 0;
        else this.id = Integer.parseInt(s.trim());
    }

    public void setName(String s) {
        this.name = s.trim();
    }

    public void setReleaseDate(String s) {
        String dia = "01", mes = "01", ano = "0";
        String[] meses = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        s = s.trim();
        if (s.length() == 0) { this.releaseDate = "01/01/0000"; return; }

        String[] parts = s.split(" ");
        if (parts.length == 2) { 
            for (int i=0;i<meses.length;i++) if (meses[i].equals(parts[0])) mes = String.format("%02d", i+1);
            ano = parts[1].replaceAll("[^0-9]", "");
        } else if (parts.length >= 3) { 
            for (int i=0;i<meses.length;i++) if (meses[i].equals(parts[0])) mes = String.format("%02d", i+1);
            dia = parts[1].replaceAll("[^0-9]", "");
            if (dia.length()==1) dia = "0"+dia;
            ano = parts[2].replaceAll("[^0-9]", "");
        }
        this.releaseDate = dia + "/" + mes + "/" + ano;
    }

    public void setEstimatedOwners(String s) {
        String digits = s.replaceAll("\\D", "");
        if (digits.isEmpty()) this.estimatedOwners = 0;
        else this.estimatedOwners = Integer.parseInt(digits);
    }

    public void setPrice(String s) {
        s = s.trim();
        if (s.equals("Free to Play") || s.isEmpty()) this.price = 0.0f;
        else this.price = Float.parseFloat(s);
    }

    private String[] parseLista(String s) {
        s = s.trim();
        if (s.startsWith("[") && s.endsWith("]")) s = s.substring(1, s.length()-1);
        if (s.isEmpty()) return new String[0];
        List<String> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean inQuotes = false;
        for (char c : s.toCharArray()) {
            if (c == '\'') inQuotes = !inQuotes;
            else if (c == ',' && !inQuotes) {
                lista.add(sb.toString().trim());
                sb.setLength(0);
            } else sb.append(c);
        }
        if (sb.length() > 0) lista.add(sb.toString().trim());
        return lista.toArray(new String[0]);
    }

    public void setSupportedLanguages(String s) { this.supportedLanguages = parseLista(s); }
    public void setMetacriticScore(String s) { this.metacriticScore = s.isEmpty() ? -1 : Integer.parseInt(s.trim()); }
    public void setUserScore(String s) { this.userScore = (s.isEmpty() || s.equals("tbd")) ? -1.0f : Float.parseFloat(s.trim()); }
    public void setAchievement(String s) { this.achievements = s.isEmpty() ? 0 : Integer.parseInt(s.trim()); }
    public void setPublishers(String s) { this.publishers = parseLista(s); }
    public void setDevelopers(String s) { this.developers = parseLista(s); }
    public void setCategories(String s) { this.categories = parseLista(s); }
    public void setGenres(String s) { this.genres = parseLista(s); }
    public void setTags(String s) { this.tags = parseLista(s); }

    public void printGame() {
        System.out.print(id + " ## " + name + " ## " + releaseDate + " ## " + estimatedOwners + " ## " + price + " ## ");
        printArray(supportedLanguages);
        System.out.print(" ## " + metacriticScore + " ## " + userScore + " ## " + achievements + " ## ");
        printArray(publishers);
        System.out.print(" ## ");
        printArray(developers);
        System.out.print(" ## ");
        printArray(categories);
        System.out.print(" ## ");
        printArray(genres);
        System.out.print(" ## ");
        printArray(tags);
        System.out.println(" ##");
    }

    private void printArray(String[] arr) {
        System.out.print("[");
        for (int i=0;i<arr.length;i++) {
            System.out.print(arr[i]);
            if (i<arr.length-1) System.out.print(", ");
        }
        System.out.print("]");
    }
}

public class ClasseGame {
    public static void main(String[] arg) {
        Scanner entrada = new Scanner(System.in);
        String idBuscado = entrada.nextLine().trim();

        while (!idBuscado.equals("FIM")) {
            try {
                File arquivo = new File("/tmp/games.csv");
                Scanner scanner = new Scanner(arquivo);
                if (scanner.hasNextLine()) scanner.nextLine(); 

                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    String[] campos = parseLinhaCSV(linha);
                    if (campos.length >= 14 && campos[0].equals(idBuscado)) {
                        Game g = new Game();
                        g.setId(campos[0]);
                        g.setName(campos[1]);
                        g.setReleaseDate(campos[2]);
                        g.setEstimatedOwners(campos[3]);
                        g.setPrice(campos[4]);
                        g.setSupportedLanguages(campos[5]);
                        g.setMetacriticScore(campos[6]);
                        g.setUserScore(campos[7]);
                        g.setAchievement(campos[8]);
                        g.setPublishers(campos[9]);
                        g.setDevelopers(campos[10]);
                        g.setCategories(campos[11]);
                        g.setGenres(campos[12]);
                        g.setTags(campos[13]);
                        System.out.print("=> ");
                        g.printGame();
                        break;
                    }
                }
                scanner.close();
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado: /tmp/games.csv");
            }
            idBuscado = entrada.nextLine().trim();
        }
        entrada.close();
    }

    public static String[] parseLinhaCSV(String linha) {
        List<String> campos = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean aspas = false;

        for (char c : linha.toCharArray()) {
            if (c == '"') aspas = !aspas;
            else if (c == ',' && !aspas) {
                campos.add(sb.toString().trim().replaceAll("^\"|\"$", ""));
                sb.setLength(0);
            } else sb.append(c);
        }
        campos.add(sb.toString().trim().replaceAll("^\"|\"$", ""));
        return campos.toArray(new String[0]);
    }
}
