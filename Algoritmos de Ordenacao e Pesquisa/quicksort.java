public class quicksort{
    public static void main(String[] arg){
        int[] array = {10,23,19,85,5};
        quickSort(array, 0, array.length - 1);

        
        for (int n : array) {
            System.out.print(n + " ");
        }
    }


    public static void swap(int[] array, int x, int y){
        int tmp = array[x];
        array[x] = array[y];
        array[y] = tmp;
    }

    public static void fQuickSort(int[] array, int esq, int dir){
        int i = esq;
        int j = dir;
        int pivo = (dir + esq)/2;

        while(i <= j){
            while(array[i] < array[pivo]){
            i++;
            }
            while(array[j] > array[pivo]){
            j--;
            }
            if(i <= j){
                swap(j, i);
                i++;
                j--;
            }else{
                break;
            }
        }
        if (esq < j) {
            quickSort(array, esq, j);
        }
        if (i < dir) {
            quickSort(array, i, dir);
        }
    }
}