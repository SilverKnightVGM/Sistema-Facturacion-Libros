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
import javax.swing.*;

public class SistemaFacturacionLibros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
    // Is your UI already created? So you will have to update the component-tree
            // of your current frame (or actually all of them...)
            //SwingUtilities.updateComponentTreeUI(yourFrame);
        } catch (Exception e) { /* Most of the time you're just going to ignore it */ }

            JFrame window = new JFrame("Look and feel");
            window.setVisible(true);
            window.setSize(500, 500);
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel panel = new JPanel();
            window.add(panel);

            JButton button = new JButton("Look and feel");
            panel.add(button);

            JProgressBar pb = new JProgressBar();
            pb.setValue(75);
            panel.add(pb);

        }

    }
