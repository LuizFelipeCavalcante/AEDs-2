#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int fim(char *str){
        return (str[0] == 'F' && str[1] == 'I' && str[2] == 'M')? 0 : 1;
}

int soma(int n){
    if(n / 10 == 0){
        return n;
    }
    else{
        return soma(n / 10) + n % 10;
    }
}

int main(){

char n[1000];
scanf(" %[^\n]", n);

while(fim(n)){
printf("%d\n",  soma(atoi(n)));
scanf(" %[^\n]", n);
}
}