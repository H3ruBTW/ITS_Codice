#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <math.h>
#include <unistd.h>

int main(){
    int col = 1; //Rosso = 1, Giallo = 2, Verde = 3
    printf("Rosso\n");

    while(true){
        switch (col)
        {
        case 1:
            sleep(3);
            col = 3;
            printf("Verde\n");
            break;

        case 2:
            sleep(1);
            col = 1;
            printf("Rosso\n");
            break;

        case 3:
            sleep(2);
            col = 2;
            printf("Giallo\n");
            break;
        
        default:
            break;
        }
    }
        
}