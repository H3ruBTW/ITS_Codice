#include <stdio.h>
#include <string.h>
#include <math.h>

int main(){
    int ris;
    scanf("%d", &ris);
    

    for(int i = ris-1; i>1; i--){
        ris*=i;
    }

    printf("%d", ris);
        
}