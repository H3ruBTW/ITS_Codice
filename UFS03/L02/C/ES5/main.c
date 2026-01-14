#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>

int main(){
    int num;

    scanf("%d", &num);

    bool primo = true;

    for(int i=floor(sqrt(num)); i>1 && num!=2; i--){
        if(num%i==0)
            primo = false;
    }

    if(primo)
        printf("Numero primo");
    else {
        printf("Numero ordinario");
    }
        
}