import java.util.Scanner;

class No{
    No dir;
    No esq;
    String elemento;

    No(String elemento){this.elemento = elemento; this.dir = null; this.esq = null;}
    No(String elemento, No esq, No dir){this.elemento = elemento; this.esq = esq; this.dir = dir;}
}

class Arvore{
    No raiz = null;


    public Arvore(){raiz = null;}

    public void inserir(int esq, int dir, String str){
        int meio = str.length()/2;
        inserirFato(str.charAt(meio));
        inserir(esq, meio, str);
        inserir(meio, dir, str);
    }

    public void inserirFato(Char str){
        
    }
}




public class questao1{

    public static void main(String[] arg){
    Scanner scanner = new Scanner(System.in);
    String n = scanner;
    }
}