#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main() {
    pid_t pid = fork();  // Crea un nuovo processo

    if (pid < 0) {
        // Caso di errore
        fprintf(stderr, "Fork fallito\n");
        return 1;
    } else if (pid == 0) {
        // Codice eseguito dal processo figlio
        printf("Questo è il processo figlio. PID: %d\n", getpid());
    } else {
        // Codice eseguito dal processo genitore
        printf("Questo è il processo genitore. PID: %d, Processo figlio PID: %d\n", getpid(), pid);
    }

    return 0;
}