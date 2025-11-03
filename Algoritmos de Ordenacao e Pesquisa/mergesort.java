public class mergesort {
    public static void main(String[] args) {
        int[] array = { 10, 23, 19, 85, 5 };
        fMergeSort(array, 0, array.length - 1);

        for (int n : array)
            System.out.print(n + " ");
    }

    public static void fMergeSort(int[] array, int esq, int dir) {
        if (esq >= dir)
            return;
        else {
            int meio = (esq + dir) / 2;
            fMergeSort(array, esq, meio);
            fMergeSort(array, meio + 1, dir);
            merge(array, esq, meio, dir);
        }
    }

    public static void merge(int[] array, int esq, int meio, int dir) {

        int nEsq = meio - esq + 1;
        int nDir = dir - meio;

        int[] esquerda = new int[nEsq];
        int[] direita = new int[nDir];
        for (int i = 0; i < nEsq; i++) {
            esquerda[i] = array[esq + i];
        }
        for (int j = 0; j < nDir; j++) {
            direita[j] = array[meio + 1 + j];
        }
        int i = 0, j = 0, k = esq;
        while (i < nEsq && j < nDir) {
            if (esquerda[i] <= direita[j]) {
                array[k] = esquerda[i];
                i++;
                k++;
            } else {
                array[k] = direita[j];
                j++;
                k++;
            }
        }
        while (i < nEsq) {
            array[k++] = esquerda[i++];
        }
        while (j < nDir) {
            array[k++] = direita[j++];
        }
    }
}
