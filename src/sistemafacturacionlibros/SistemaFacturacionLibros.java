/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemafacturacionlibros;

/**
 *
 * @author Enzo
 */
import java.sql.SQLException;
import java.util.Random;
import javax.swing.*;
import java.util.UUID;

public class SistemaFacturacionLibros {

    /**
     * @param args the command line arguments
     */
    
    static final String pool = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static Random ran = new Random();

    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
       WindowLogin wl = new WindowLogin();
       wl.setVisible(true);
       wl.setTitle("Login");
//        for (int i = 0; i < 10; i++) {
////                    String uuid = UUID.randomUUID().toString().replaceAll("-", "");
////                    System.out.println("uuid = " + uuid);
//            String test = randomString(i, pool, ran);
//            System.out.println("TEST = " + test);
//        }

//        try {
//            UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
//            // Is your UI already created? So you will have to update the component-tree
//            // of your current frame (or actually all of them...)
//            //SwingUtilities.updateComponentTreeUI(yourFrame);
//        } catch (Exception e) { /* Most of the time you're just going to ignore it */ }
//
//        WindowLogin windowLogin = new WindowLogin();
//        windowLogin.setVisible(true);
//        windowLogin.setSize(500,500);
//        windowLogin.setResizable(false);
//        windowLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static String randomString(int len, String chars, Random rnd) {
//        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }

}
