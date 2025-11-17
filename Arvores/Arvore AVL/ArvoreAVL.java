import java.util.Scanner;

class No{
    int elemento;
    No esq, dir;

    No(int elemento){this.elemento = elemento; esq = null; dir = null;}
}


class Arvore{
    No raiz;
    Arvore(){this.raiz = null;}
    Arvore(int elemento){this.raiz = new No(elemento);}

    private No inserirRec(int x, No raiz){
        if(raiz == null){
            return new No(x);
        }
        if(x > raiz.elemento)
            raiz.dir = inserirRec(x, raiz.dir);
        if(x < raiz.elemento)
            raiz.esq = inserirRec(x, raiz.esq);
        return raiz;
    }

    public void inserir(int elemento){
        inserirRec(elemento, this.raiz);
    }

    public void mostrarCentral(No raiz){
        if(raiz == null){
            return;
        }
        mostrarCentral(raiz.esq);
        System.out.print(raiz.elemento + " ");
        mostrarCentral(raiz.dir);
    }

    public void mostrarPre(No raiz){
        if(raiz == null){
            return;
        }
        System.out.print(raiz.elemento + " ");
        mostrarPre(raiz.esq);
        mostrarPre(raiz.dir);
    }

    public void mostrarPos(No raiz){
        if(raiz == null){
            return;
        }
        mostrarPos(raiz.esq);
        mostrarPos(raiz.dir);
        System.out.print(raiz.elemento + " ");
    }

    public void mostrar(){
        mostrarPre(this.raiz);
        System.out.println("");
        mostrarCentral(this.raiz);
        System.out.println("");
        mostrarPos(this.raiz);
    }

    public int getMaior(No raiz){
        if(raiz.dir == null)
            return raiz.elemento;
        int maior = getMaior(raiz.dir);
        return maior;
    }
    

    public No remover(int x, No raiz){
        if(raiz == null){
            return raiz;
        }
        if(x == raiz.elemento){
            if(raiz.esq == null && raiz.dir == null){
                return null;
            }
            else if(raiz.esq == null && raiz.dir != null){
                return raiz.dir;
            }else if(raz.dir == null && raiz.esq != null){
                return raiz.esq;
            }
        }
        raiz.dir = remover(x, raiz.dir);
        raiz.esq = remover(x, raiz.esq);
    }
}

public class ArvoreAVL{
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        Arvore arvore = new Arvore();
        int x = scanner.nextInt();

        while(x != 0){
            arvore.inserir(x);
            x = scanner.nextInt();
        }
        arvore.mostrar();
        int maior = arvore.getMaior(arvore.raiz);
        System.out.println(maior);
    }

}