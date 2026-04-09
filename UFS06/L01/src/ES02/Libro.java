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

    public void setAutore(String autore) throws IllegalArgumentException {
        if(!autore.isBlank())
            this.autore = autore;
        else
            throw new IllegalArgumentException("Nome non inserito");
    }

    public void setISBN(String ISBN) throws IllegalArgumentException {
        
        if(ISBN.length() == 13)
            this.ISBN = ISBN;
        else
            throw new IllegalArgumentException("ISBN in formato errato");
        
    }

    public void setNome(String nome) throws IllegalArgumentException {
        if(!nome.isBlank())
            this.nome = nome;
        else
            throw new IllegalArgumentException("Titolo non inserito");
    }

    @Override
    public String toString(){
        return "{\nISBN: " + ISBN + "\nTitolo: " + nome + "\nAutore: " + autore + "\n}";
    }

}
