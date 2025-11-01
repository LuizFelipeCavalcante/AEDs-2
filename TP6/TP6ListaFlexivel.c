#include <stdio.h>

#define max_str 500;
#define max_array 20;
typedef struct{
    int id;
    char name[max_str];
    char releaseDate[max_str];
    int estimatedOwners;
    float price;
    char supportedLanguages[max_str];
    int metacriticStore;
    float userScore;
    int achievements;
    char publishers[max_str][max_array];
    char developers[max_str][max_array];
    char categories[max_str][max_array];
    char genres[max_str][max_array];
    char tags[max_str][max_array];
}Game;





int main(){
    FILE *arquivo;

    arquivo = fopen("games.csv");
    
    if(arquivo == NULL){
        printf("Erro ao abrir o arquivo");
        return 1;
    }

    return 0;
}