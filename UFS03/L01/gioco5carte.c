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
    
}