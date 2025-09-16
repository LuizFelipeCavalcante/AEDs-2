#include <stdio.h>
#include <string.h>

int fim(char *str){
        return (str[0] == 'F' && str[1] == 'I' && str[2] == 'M')? 0 : 1;
	}

int numeroReal(char *str){
    if(str[i] < 47 || str[i] > 58){
        return 0;
    }else{
        
    }
    int result = 0, virgula = 0;
    for(int i = 0; i < strlen(str); i++){
        if((str[i] == 44 || str[i] == 46) && virgula < 1){virgula++;}
        else if(str[i] < 47 || str[i] > 58)
        {result++; i = strlen(str);}
        else{}
    }
    return (result > 0) ? 0 : 1;
}

int numeroInteiro(char *str){
    int result = 0;
    for(int i = 0; i < strlen(str); i++){
        if(str[i] < 47 || str[i] > 58)
        {result++; i = strlen(str);}
        else{}
    }
    return (result > 0) ? 0 : 1;
}

int consoantes(char *str){
    int result = 0;
    for(int i = 0; i < strlen(str); i++){
        if(str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U' || str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u')
        {result++;}
        else if(str[i] < 65 || str[i] > 122){result++;}else{}
    }
    return (result > 0) ? 0 : 1;
}

int vogais(char *str){
    int result = 0;
    for(int i = 0; i < strlen(str); i++){
        if(str[i] == 'A' || str[i] == 'E' || str[i] == 'I' || str[i] == 'O' || str[i] == 'U' || str[i] == 'a' || str[i] == 'e' || str[i] == 'i' || str[i] == 'o' || str[i] == 'u'){}
        else{result++;}
    }
    return (result > 0) ? 0 : 1;
}


int main(){

char str[1000];
scanf(" %[^\n]", str);
while(fim(str)){
printf("%s %s %s %s\n", vogais(str) == 1 ? "SIM" : "NAO", consoantes(str) == 1 ? "SIM" : "NAO", numeroInteiro(str) == 1 ? "SIM" : "NAO", numeroReal(str) == 1 ? "SIM" : "NAO");
scanf(" %[^\n]", str);
}

}