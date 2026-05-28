package it.buonga.accessdb;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UtenteDAO extends CrudRepository<Utente, Integer> {
    @Query("select u from Utente u where u.username = :username and u.pass= :psw")
    public Utente login(String username, String psw);
}
