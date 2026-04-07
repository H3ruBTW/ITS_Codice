package ES02;

public class Libro {
    private String ISBN, nome, autore;

    public Libro(String ISBN, String nome, String autore){
        setISBN(ISBN);
        setAutore(autore);
        setNome(nome);
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAutore() {
        return autore;
    }

    public String getNome() {
        return nome;
    }

    public void setAutore(String autore) {
        if(!autore.isBlank())
            this.autore = autore;
    }

    public void setISBN(String ISBN) {
        if(ISBN.length() == 13)
            this.ISBN = ISBN;
    }

    public void setNome(String nome) {
        if(!nome.isBlank())
            this.nome = nome;
    }



}
