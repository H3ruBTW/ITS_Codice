#include <stdio.h>

int main(){
    int a, b, c;

    scanf("%d", &a);
    scanf("%d", &b);
    scanf("%d", &c);

    if(a>b){
        if(a>c)
            printf("A: %d maggiore", a);
        else
            printf("C: %d maggiore", c);
    } else {
        if(b>c)
            printf("B: %d maggiore", b);
        else
            printf("C: %d maggiore", c);
    }
}