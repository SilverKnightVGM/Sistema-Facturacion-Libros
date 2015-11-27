package sistemafacturacionlibros;

/**
 *
 * @author Enzo
 */
import java.sql.*;

public class Conexion {

    Connection c = null;
    Statement stmt = null;

    public Conexion() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SistemaLibros.sqlite");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        } catch (SQLException ex1) {
            ex1.printStackTrace();
        }

        //miconexion.close();
        //stSentencias.close();
    }

    public void CrearTablas() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SistemaLibros.sqlite");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            
            //Tabla Libros
            String sql = "CREATE TABLE IF NOT EXISTS Libros "
                    + "(ID INTEGER PRIMARY KEY,"
                    + "Inventory INTEGER NOT NULL, "
                    + "Price REAL NOT NULL, "
                    + "Title TEXT NOT NULL, "
                    + "Author TEXT NOT NULL, "
                    + "Publisher TEXT, "
                    + "Edition INTEGER, "
                    + "Category TEXT) ";
            stmt.executeUpdate(sql);
            
            //Tabla Empleados
            sql = "CREATE TABLE IF NOT EXISTS Empleados "
                    + "(ID INTEGER PRIMARY KEY ,"
                    + "Nombre TEXT, "
                    + "Apellido TEXT, "
                    + "Usuario TEXT NOT NULL, "
                    + "Password TEXT, "
                    + "Nivel TEXT NOT NULL) ";
            stmt.executeUpdate(sql);
            
            //Tabla Ventas
            //TODO, ventas realizadas con fecha y nombre de usuario
            sql = "CREATE TABLE IF NOT EXISTS Empleados "
                    + "(ID INTEGER PRIMARY KEY ,"
                    + "Nombre TEXT, "
                    + "Apellido TEXT, "
                    + "Usuario TEXT NOT NULL, "
                    + "Password TEXT, "
                    + "Nivel TEXT NOT NULL) ";
            stmt.executeUpdate(sql);
            
            //Tabla Log
            //TODO
            sql = "CREATE TABLE IF NOT EXISTS Empleados "
                    + "(ID INTEGER PRIMARY KEY ,"
                    + "Nombre TEXT, "
                    + "Apellido TEXT, "
                    + "Usuario TEXT NOT NULL, "
                    + "Password TEXT, "
                    + "Nivel TEXT NOT NULL) ";
            stmt.executeUpdate(sql);

            stmt.close();
            c.close();

        } catch (ClassCastException ex) {
            ex.printStackTrace();
        } catch (SQLException ex1) {
            ex1.printStackTrace();
        }

        //miconexion.close();
        //stSentencias.close();
    }
    
}
