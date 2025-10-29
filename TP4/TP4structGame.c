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
    char resultado[100][14];
    int aspas;
    int j = 0;

    for(int i = 0; i < 14; i++){
        while((j < linha.length()) && (linha[j] != ',' || aspas == 1)){
            if(linha[j] == '"'){
                aspas ++;
                if(aspas >= 2){aspas = 0;}
            }
            resultado[i] = resultado[i] + linha.charAt[j];
            j++;
        }
        j++;
    
        }
        return resultado;
}


int main(){
    FILE *arquivo;
    arquivo = fopen("games.csv");

    if (arquivo == NULL) {
    printf("Erro ao abrir o arquivo.\n");
    return 1; 
    }

    char linha[100];
    while (fgets(linha, sizeof(linha), arquivo) != NULL) {
    
    }

    fclose(arquivo);
    return 0;
}