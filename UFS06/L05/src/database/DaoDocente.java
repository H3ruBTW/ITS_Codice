package database;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DaoDocente {
    public static ArrayList<Materia> getInfo(String username){
        Connection c = ConnDB.openConnection();

        try {
            PreparedStatement ps = c.prepareStatement(
                "SELECT DISTINCT m.nome FROM Materie m " +
                "JOIN Insegnamenti i ON m.id = i.id_materia " +
                "JOIN Docenti d ON d.id = i.id_docente " +
                "WHERE d.username = ?"
            );

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            ArrayList<Materia> materie = new ArrayList<>();

            int i=0;

            while (rs.next()) {
                materie.add(new Materia());
                materie.get(i).setNome(rs.getString("nome"));
                i++;
            }

            for(i = 0; i < materie.size(); i++){
                ps = c.prepareStatement(
                    "select s.*" +
                    "from studenti s " +
                    "cross join materie m " +
                    "where m.nome = ?"
                );

                ps.setString(1, materie.get(i).getNome());
                rs = ps.executeQuery();

                while(rs.next()){
                    materie.get(i).addStudenti(rs.getString("nome"), rs.getString("mid_nome"), rs.getString("cognome"), rs.getDate("data"));
                }
            }

            return materie;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
