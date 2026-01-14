#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main(){
    int carte[5][2];

    srand(time(NULL));

    for(int i=0; i<5; i++){
        carte[i][0] = rand() % 4 + 1;
        carte[i][1] = rand() % 13 + 1;
    
        if(i!=0){
            for(int j=0; j<i; j++){
                if(carte[i][0] == carte[j][0] && carte[i][1] == carte[j][1]){
                    i--;
                    break;
                }
            }
        }

        printf("Seme: %d, Numero: %d\n", carte[i][0], carte[i][1]);

    }

    int cartasegreta[3];
    int carte_buttate[4][3];
    int esci = 0;


    for(int i=0; i<4; i++){
        for(int j=i+1; j<5; j++){
            if(carte[i][0] == carte[j][0]){
                if(carte[i][1]-carte[j][1] < 0){
                    if(carte[i][1]+6 >= carte[j][1]){
                        cartasegreta[0] = carte[i][0];
                        cartasegreta[1] = carte[i][1];
                        cartasegreta[2] = i;
                        carte_buttate[0][0] = carte[j][0];
                        carte_buttate[0][1] = carte[j][1];
                        carte_buttate[0][2] = j;
                    } else {
                        cartasegreta[0] = carte[j][0];
                        cartasegreta[1] = carte[j][1];
                        cartasegreta[2] = j;
                        carte_buttate[0][0] = carte[i][0];
                        carte_buttate[0][1] = carte[i][1];
                        carte_buttate[0][2] = i;
                    }
                } else {
                    if(carte[j][1]+6 >= carte[i][1]){
                        
                        cartasegreta[0] = carte[j][0];
                        cartasegreta[1] = carte[j][1];
                        cartasegreta[2] = j;
                        carte_buttate[0][0] = carte[i][0];
                        carte_buttate[0][1] = carte[i][1];
                        carte_buttate[0][2] = i;
                    } else {
                        cartasegreta[0] = carte[i][0];
                        cartasegreta[1] = carte[i][1];
                        cartasegreta[2] = i;
                        carte_buttate[0][0] = carte[j][0];
                        carte_buttate[0][1] = carte[j][1];
                        carte_buttate[0][2] = j;
                    }
                }

                esci = 1;
                break;
            }

        }

        if(esci == 1){
            break;
        }
    }

    int temp=1;
    
    for(int i=0; i<5; i++){
        if(i != cartasegreta[2] && i != carte_buttate[0][2]){
            carte_buttate[temp][0] = carte[i][0];
            carte_buttate[temp][1] = carte[i][1];
            temp++;
        }
    }

    printf("\nCarta segreta: %d - %d\n", cartasegreta[0], cartasegreta[1]);

    for (int i = 0; i < 4; i++)
    {
        printf("Carta %d: %d - %d\n", i, carte_buttate[i][0], carte_buttate[i][1]);
    }
    

    //123 - 132 - 213 - 231 - 312 - 321
    if(cartasegreta[1] >= 8 && carte_buttate[0][1] <= 6){
        carte_buttate[0][1] += 13;
    }

    int scarto = carte_buttate[0][1] - cartasegreta[1];

    printf("\nScarto: %d\n", scarto);

    int min = 1;
    int mag = 1;

    for(int i=2; i<4; i++){
        if(carte_buttate[i][0] < carte_buttate[min][0]){
            min = i;
            continue;
        }

        if(carte_buttate[i][0] == carte_buttate[min][0]){
            if(carte_buttate[i][1] < carte_buttate[min][1])
                min = i;

            if(carte_buttate[i][1] > carte_buttate[mag][1])
                mag = i;

            continue;
        }

        if(carte_buttate[i][0] > carte_buttate[mag][0]){
            mag = i;
            continue;
        }
    }

    int cen;

    if(min == 1){
        if(mag == 2)
            cen = 3;
        else
            cen = 2;
    } else {
        if(min == 2){
            if(mag == 1)
                cen = 3;
            else
                cen = 1;
        } else {
            if(mag == 1)
                cen = 2;
            else
                cen = 1;
        }
    }

    printf("\nMaggiore: %d - Centro: %d - Minore: %d\n", mag, cen, min);

    int cMag[2] = {carte_buttate[mag][0], carte_buttate[mag][1]};
    int cMin[2] = {carte_buttate[min][0], carte_buttate[min][1]};
    int cCen[2] = {carte_buttate[cen][0], carte_buttate[cen][1]};

    switch (scarto){
    case 1:
        carte_buttate[1][0] = cMin[0];
        carte_buttate[1][1] = cMin[1];
        carte_buttate[2][0] = cCen[0];
        carte_buttate[2][1] = cCen[1];
        carte_buttate[3][0] = cMag[0];
        carte_buttate[3][1] = cMag[1];
        break;

    case 2:
        carte_buttate[1][0] = cMin[0];
        carte_buttate[1][1] = cMin[1];
        carte_buttate[3][0] = cCen[0];
        carte_buttate[3][1] = cCen[1];
        carte_buttate[2][0] = cMag[0];
        carte_buttate[2][1] = cMag[1];
        break;

    case 3:
        carte_buttate[2][0] = cMin[0];
        carte_buttate[2][1] = cMin[1];
        carte_buttate[1][0] = cCen[0];
        carte_buttate[1][1] = cCen[1];
        carte_buttate[3][0] = cMag[0];
        carte_buttate[3][1] = cMag[1];
        break;

    case 4:
        carte_buttate[3][0] = cMin[0];
        carte_buttate[3][1] = cMin[1];
        carte_buttate[1][0] = cCen[0];
        carte_buttate[1][1] = cCen[1];
        carte_buttate[2][0] = cMag[0];
        carte_buttate[2][1] = cMag[1];
        break;

    case 5:
        carte_buttate[2][0] = cMin[0];
        carte_buttate[2][1] = cMin[1];
        carte_buttate[3][0] = cCen[0];
        carte_buttate[3][1] = cCen[1];
        carte_buttate[1][0] = cMag[0];
        carte_buttate[1][1] = cMag[1];
        break;

    case 6:
        carte_buttate[3][0] = cMin[0];
        carte_buttate[3][1] = cMin[1];
        carte_buttate[2][0] = cCen[0];
        carte_buttate[2][1] = cCen[1];
        carte_buttate[1][0] = cMag[0];
        carte_buttate[1][1] = cMag[1];
        break; 
    
    default:
        break;
    }
    
    printf("\nModo in cui verrano buttate le carte:\n");
    printf("Carta segreta: %d - %d\n", cartasegreta[0], cartasegreta[1]);

    for (int i = 0; i < 4; i++)
    {
        printf("Carta %d: %d - %d\n", i, carte_buttate[i][0], carte_buttate[i][1]);
    }
}