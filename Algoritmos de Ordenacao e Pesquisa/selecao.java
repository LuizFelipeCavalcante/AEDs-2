class classeSelecao{

}



public class selecao {


    public int[] fSelecao(int[] array){
        for(int i = 0; i < array.length - 1; i++){
            int menor = i;
            for(int j = i+1; j < array.length; i++){
                if(array[menor] > array[j]){
                    menor = j;
                }
            }
            int tmp = array[menor];
            array[menor] = array[0];
            array[0] = tmp;
        }
        return array;
        
    }
    public static void main(String[] args){

    }
}
