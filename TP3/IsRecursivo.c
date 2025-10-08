#include <stdio.h>
#include <string.h>

int fim(char *str){
    return (str[0] == 'F' && str[1] == 'I' && str[2] == 'M')? 0 : 1;
}

int numeroRealRec(char *str, int i, int virgula){
    if(str[i] == '\0') return 1;
    if((str[i] == ',' || str[i] == '.') && virgula < 1) return numeroRealRec(str, i+1, virgula+1);
    else if(str[i] >= '0' && str[i] <= '9') return numeroRealRec(str, i+1, virgula);
    else return 0;
}
int numeroReal(char *str){
    return numeroRealRec(str, 0, 0);
}

int numeroInteiroRec(char *str, int i){
    if(str[i] == '\0') return 1;
    if(str[i] >= '0' && str[i] <= '9') return numeroInteiroRec(str, i+1);
    else return 0;
}
int numeroInteiro(char *str){
    return numeroInteiroRec(str, 0);
}

int consoantesRec(char *str, int i){
    if(str[i] == '\0') return 1;
    if(str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U' || str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u') return 0;
    if((str[i] >= 'A' && str[i] <= 'Z') || (str[i] >= 'a' && str[i] <= 'z')) return consoantesRec(str, i+1);
    return 0;
}
int consoantes(char *str){
    return consoantesRec(str, 0);
}

int vogaisRec(char *str, int i){
    if(str[i] == '\0') return 1;
    if(str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U' || str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u') return vogaisRec(str, i+1);
    return 0;
}
int vogais(char *str){
    return vogaisRec(str, 0);
}

int main(){
    char str[1000];
    scanf(" %[^\n]", str);
    while(fim(str)){
        printf("%s %s %s %s\n", vogais(str) == 1 ? "SIM" : "NAO", consoantes(str) == 1 ? "SIM" : "NAO", numeroInteiro(str) == 1 ? "SIM" : "NAO", numeroReal(str) == 1 ? "SIM" : "NAO");
        scanf(" %[^\n]", str);
    }
}
