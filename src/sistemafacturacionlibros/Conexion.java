package sistemafacturacionlibros;

/**
 *
 * @author Enzo
 */
import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

    Connection c = null;
    Statement stmt = null;
    ResultSet rs;
    Connection conn = null;
    private static String userName;
    private static int userID;
    private static int clienteID;

    public Conexion() {
    }

    public static Connection dbConnector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:SistemaLibros.sqlite");
            System.out.println("Static Opened database successfully");
            return conn;
        } catch (ClassNotFoundException | SQLException classNotFoundException) {
        }
        return null;
    }

//    public Conexion() throws ClassNotFoundException, SQLException {
//        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:SistemaLibros.sqlite");
//            c.setAutoCommit(false);
//            stmt = c.createStatement();
//            System.out.println("Opened database successfully");
//        } catch (ClassCastException ex) {
//            ex.printStackTrace();
//        } catch (SQLException ex1) {
//            ex1.printStackTrace();
//        }
//
//        //miconexion.close();
//        //stSentencias.close();
//    }
    private ResultSet consulta(String sql) throws SQLException {
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            throw ex;
        }
        return rs;
    }

    public void CrearTablas() {
        try {
//            Class.forName("org.sqlite.JDBC");
//            c = DriverManager.getConnection("jdbc:sqlite:SistemaLibros.sqlite");
//            c.setAutoCommit(false);
//            System.out.println("Opened database successfully");

//            stmt = c.createStatement();
            c = dbConnector();

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
//            stmt.executeUpdate(sql);
            PreparedStatement pst = c.prepareStatement(sql);
            pst.execute();

            //Tabla Empleados
            sql = "CREATE TABLE IF NOT EXISTS Empleados "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Nombre TEXT, "
                    + "Apellido TEXT, "
                    + "Usuario TEXT NOT NULL, "
                    + "Password TEXT, "
                    + "Nivel TEXT NOT NULL)";
            pst = c.prepareStatement(sql);
            pst.execute();

            //Tabla Ventas
            sql = "CREATE TABLE IF NOT EXISTS Ventas "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Fecha NUMERIC NOT NULL DEFAULT (datetime('now','localtime')), "
                    + "IDLibro INTEGER NOT NULL, "
                    + "IDEmpleado INTEGER NOT NULL, "
                    + "IDCliente INTEGER NOT NULL, "
                    + "PrecioTotal REAL NOT NULL)";
            pst = c.prepareStatement(sql);
            pst.execute();

            //Tabla Clientes
            sql = "CREATE TABLE IF NOT EXISTS Clientes "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "Nombre TEXT, "
                    + "Apellido TEXT, "
                    + "Telefono INTEGER, "
                    + "Email TEXT)";
            pst = c.prepareStatement(sql);
            pst.execute();

            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void CrearCarrito() {

        try {
            c = dbConnector();
            //Tabla Carrito
            String sql = "CREATE TABLE IF NOT EXISTS Carrito "
                    + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "IDLibro INTEGER NOT NULL, "
                    + "Titulo TEXT NOT NULL, "
                    + "Autor TEXT NOT NULL, "
                    + "Precio REAL NOT NULL, "
                    + "Cantidad INTEGER NOT NULL,"
                    + "FOREIGN KEY(IDLibro) REFERENCES Libros(ID)"
                    + ")";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.execute();

            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public void BorrarCarrito() {
        try {
            c = dbConnector();

            String sql = "DROP TABLE IF EXISTS Carrito;";
            PreparedStatement pst = c.prepareStatement(sql);
            pst.execute();
            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public String randomString(int len, String chars, Random rnd) {
//        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static void setTextField(String user) {
        userName = user;
    }

    public static String getTexField() {
        return userName;
    }

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        Conexion.userID = userID;
    }

    public static int getClienteID() {
        return clienteID;
    }

    public static void setClienteID(int clienteID) {
        Conexion.clienteID = clienteID;
    }

    public void defaultAdmin() {

        c = dbConnector();

        //Inserts default admin if it is a new database
        int anyUser = checkIfAnyUsers();

        if (anyUser == -1) {
            try {
                String sql = "INSERT INTO Empleados (ID, Nombre, Apellido, Usuario, Password, Nivel) "
                        + "SELECT 1,'Jon','Doe','admin','[a, d, m, i, n]','Admin' "
                        + "WHERE NOT EXISTS (SELECT 1 FROM Empleados WHERE ID=1)";

                PreparedStatement pst = c.prepareStatement(sql);
                pst.execute();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int checkIfAnyUsers() {

        int userCheck = 0;

        c = dbConnector();

        try {
            String sql = "Select ID FROM Empleados";
            PreparedStatement pst = c.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            if (rs.isBeforeFirst()) {
                System.out.println("Found data");
                userCheck = 1;
                pst.close();
                rs.close();
            } else {
                System.out.println("No data");
                userCheck = -1;
                pst.close();
                rs.close();
            }

        } catch (Exception e) {
            System.out.println("checkIfAnyUsers" + e.getMessage());
        }
        return userCheck;
    }
}
