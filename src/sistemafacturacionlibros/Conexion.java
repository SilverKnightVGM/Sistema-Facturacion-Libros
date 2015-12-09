package sistemafacturacionlibros;

/**
 *
 * @author Enzo
 */
import java.sql.*;
import java.util.Random;
import javax.swing.JOptionPane;

public class Conexion {

    Connection c = null;
    Statement stmt = null;
    ResultSet rs;
    Connection conn = null;

    public static Connection dbConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn  = DriverManager.getConnection("jdbc:sqlite:SistemaLibros.sqlite");
            System.out.println("Static Opened database successfully");
            return conn;
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
        }
        return null;
    }

    public Conexion() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SistemaLibros.sqlite");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            System.out.println("Opened database successfully");
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        } catch (SQLException ex1) {
            ex1.printStackTrace();
        }

        //miconexion.close();
        //stSentencias.close();
    }

//    public void ProbarConexion() throws ClassNotFoundException, SQLException {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:SistemaLibros.sqlite");
//            c.setAutoCommit(false);
//            System.out.println("Opened database successfully first");
//        } catch (ClassCastException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex1) {
//            ex1.printStackTrace();
//        }
//
//        c.close();
//        stmt.close();
//    }
    public ResultSet consulta(String sql) throws SQLException {
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            throw ex;
        }
        return rs;
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
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Inventario INTEGER NOT NULL, "
                    + "Precio REAL NOT NULL, "
                    + "Titulo TEXT NOT NULL, "
                    + "Autor TEXT NOT NULL, "
                    + "Editora TEXT, "
                    + "Edicion INTEGER, "
                    + "Genero TEXT)";
            stmt.executeUpdate(sql);

            //Tabla Empleados
            sql = "CREATE TABLE IF NOT EXISTS Empleados "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Nombre TEXT, "
                    + "Apellido TEXT, "
                    + "Usuario TEXT NOT NULL, "
                    + "Password TEXT, "
                    + "Nivel TEXT NOT NULL)";
            stmt.executeUpdate(sql);

            //Tabla Ventas
            sql = "CREATE TABLE IF NOT EXISTS Ventas "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Fecha NUMERIC NOT NULL DEFAULT (datetime('now','localtime')), "
                    + "IDLibro INTEGER NOT NULL, "
                    + "IDEmpleado INTEGER NOT NULL, "
                    + "IDCliente INTEGER NOT NULL, "
                    + "PrecioTotal REAL NOT NULL)";
            stmt.executeUpdate(sql);

            //Tabla Clientes
            sql = "CREATE TABLE IF NOT EXISTS Clientes "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Nombre TEXT, "
                    + "Apellido TEXT, "
                    + "Telefono INTEGER, "
                    + "Email TEXT)";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit(); //if c.setAutoCommit(false);
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void CrearCarrito() {

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:SistemaLibros.sqlite");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();

            //Tabla Carrito
            String sql = "CREATE TABLE IF NOT EXISTS Carrito "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "IDLibro INTEGER NOT NULL, "
                    + "Titulo TEXT NOT NULL, "
                    + "Autor TEXT NOT NULL, "
                    + "Precio REAL NOT NULL, "
                    + "Cantidad INTEGER NOT NULL)";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit(); //if c.setAutoCommit(false);
            c.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void BorrarCarrito() {
        //TODO
    }

    public String randomString(int len, String chars, Random rnd) {
//        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
