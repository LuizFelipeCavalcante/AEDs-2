#include <stdio.h>
#include <string.h>

int main(){

    char str1[100];
    char str2[100];

    char str3[200];
    
    int t = 0;

    if(strlen(str2) < strlen(str1) ){
        t = strlen(str1);
    }
    else{
        t = strlen(str2);
    }
    int x = 0;
    int j = 0;
    for(int i = 0; i < t; i+=x ){
        if(j > strlen(str1)){
                str3[i] = str2[j];
                x = 1;
        }else if(j > strlen(str2)){
            str3[i] = str1[j];
            x = 1;
        }
        else{
            str3[i] = str1[j];
            str3[i+1] = str2[j];
            x = 2;
        }
        j++;
    }
    
        printf("%s", str3);

    return 0;
}
