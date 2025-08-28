#include <stdio.h>
#include <string.h>


int palindromo(char* str, int i, int tam){
    if(i >= tam/2){
        return 1;
    }
    else{
        if(str[i] == str[tam - i - 1]){
            return palindromo(str, i+1, tam);
        }else{
        return 0;
        }
    }

}




int main(){
char str[2000];
int result;
scanf(" %[^\n]", str);
while(strcmp(str, "FIM")){
result = palindromo(str, 0, strlen(str));
if(result) printf("SIM\n");
else printf("NAO\n");
scanf(" %[^\n]", str);
}
return 0;
}
