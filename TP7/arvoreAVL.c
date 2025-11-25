#include <stdio.h>


typedef struct No{
    int elemento;
    struct No *esq;
    struct No *dir;
} No;

No* NovoNo(int x){
    No* temp = (No*) malloc(sizeof(No));
    temp->valor = x;
    temp->esq = NULL;
    temp->dir = NULL;
    return temp;
}

No* insercao(No *raiz, int x){
    if(raiz == NULL){
        return NovoNo(x);
    }
    if(raiz->){

    }
}



int main(){



    return 0;
}