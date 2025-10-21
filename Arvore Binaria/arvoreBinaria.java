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

    private void imprimirRec(No visitando){
        if(visitando == null){
            return;
        }
        else{
            if(visitando.esq == null){
                System.out.print(visitando);
                imprimirRec(visitando.dir);
            }
        }
    }

    private 
}
