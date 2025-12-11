#include <stdio.h>
#include <string.h>
#include <math.h>

int main(){
    int num;
    scanf("%d", &num);

    int cifre = 1;
    int potenza = 10;

    for(int i=1; num%potenza!= num; i++){
        cifre++;
        potenza*=10;
    }

    printf("%d", cifre);
        
}