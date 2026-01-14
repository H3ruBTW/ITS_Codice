@echo off
    echo Esercizio 3
    md "Esercizio 3"
    cd "Esercizio 3"
    echo a > a.txt
    echo b > b.txt
    echo c > c.txt
    echo d > d.txt
    dir
    cd ..
    rd /S /Q "Esercizio 3"
    echo Finito.
