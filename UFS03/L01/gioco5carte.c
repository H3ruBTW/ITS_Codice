#include <stdio.h>
#include <stdlib.h>

int main(){
    int carte[5][2];

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

    }

    int cartasegreta[3];
    int carte_buttate[4][3];
    int esci = 0;


    for(int i=0; i<5; i++){
        for(int j=i+1; j<5; j++){
            if(carte[i][0] == carte[j][0]){
                cartasegreta[0] = carte[i][0];
                cartasegreta[1] = carte[i][1];
                cartasegreta[2] = i;
                carte_buttate[0][0] = carte[j][0];
                carte_buttate[0][1] = carte[j][1];
                carte_buttate[0][2] = j;
                esci = 1;
                break;
            }

        }

        if(esci == 1){
            break;
        }
    }

    //switcha le carte se cartasegreta+6 non arriva ciclicamente a carta buttata1
    if(cartasegreta[1]-carte_buttate[0][1] < 0){
        if(!(cartasegreta[1]+6 >= carte_buttate[0][1])){
            
        }
    }

    int temp=1;
    
    for(int i=0; i<5; i++){
        if(i != cartasegreta[2] && i != carte_buttate[0][2]){
            carte_buttate[temp][0] = carte[i][0];
            carte_buttate[temp][1] = carte[i][1];
            carte_buttate[temp][2] = i;
            temp++;
        }
    }

}