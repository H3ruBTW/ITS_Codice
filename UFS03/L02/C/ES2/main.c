#include <stdio.h>
#include <string.h>

int main(){
    char User[15], Pass[15];

    scanf("%s", User);
    scanf("%s", Pass);

    if(strcmp(User, "admin") == 0 && strcmp(Pass, "1234") == 0)
        printf("Login Effettuato");
    else
        printf("Credenziali errate");
}