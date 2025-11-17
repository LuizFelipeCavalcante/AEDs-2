#include <stdio.h>

typedef struct{
    int id;
    char name[100];
    char releaseDate[10];
    int estimatedOwners;
    float price;
    char supportedLanguages[100][100];
    int metacriticScore;
    float userScore;
    int achievements;
    char publishers[100][100];
    char developers[100][100];
    char categories[100][100];
    char genres[100][100];
    char tags[100][100];
}Game;

void separador(char* str){
    char resultado
    for(int i = 0; str[i] != "\0"; i++){
        if(str[i] == ","){

        }
    }
}


int main(){
   FILE *file;
   file = fopen("games.csv", "r");
   char linha[1000]; 
   while(fgets(linha, 1000, file) != NULL){
    printf("%s", linha);
   }
   
    return 0;
}