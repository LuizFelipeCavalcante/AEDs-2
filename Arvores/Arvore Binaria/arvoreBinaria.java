public class arvoreBinaria {
    private No raiz;
    private class No{
        public int val;
        public No esq;
        public No dir;

        public void arvoreBinaria(int val){this.val = val;}
    }

    private No inserirRec(No visitando, int val){
        
        if(visitando == null){
            visitando.val = val;
            this.raiz.val = val;
            return visitando;
        }
        else{
            if(visitando.val < val){
                visitand.dir = inserirRec(visitando.dir, val);
            }else{
                visitando.esq = inserirRec(visitando.esq, val);
            }
        }
        
        
        return visitando;
    }

    public void inserir(int val){
    inserirRec(this.Raiz, val);
    }

    private void imprimirRecCentral(No visitando){
        if(visitando != null){
            imprimirRecCentral(visitando.esq);
            System.out.println(visitante.elemento + " ");
            imprimirRecCentral(visitando.dir);
        }
    }

    private void imprimirRecPos(No visitando){
        if(visitando != null){
            imprimirRecPos(visitando.esq);
            imprimirRecPos(visitando.dir);
            System.out.println(visitante.elemento + " ");
        }
    }

    private void imprimirRecPre(No visitando){
        if(visitando != null){
            System.out.println(visitante.elemento + " ");
            imprimirRecPre(visitando.esq);
            imprimirRecPre(visitando.dir);
        }
    }

    private int alturaArvore(No i, int altura){
        if(i == null){
            return altura--;
        }else{
            int alturaEsq = alturaArvore(i.esq, altura + 1);
            int alturaDir = alturaArvore(i.dir, altura + 1);
            altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
            return altura;
        }   
    }
    

    
}
