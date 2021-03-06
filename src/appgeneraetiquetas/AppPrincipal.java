/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appgeneraetiquetas;

import static appgeneraetiquetas.sendsms.enviar;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.ProgressMonitor;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos Ramirez
 */
public class AppPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form AppPrincipal
     */
    public AppPrincipal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jFileChooser1.setApproveButtonText("Enviar Notificaciones");
        jFileChooser1.setFileFilter(filtroImagen);
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        task = new LongTask();
        jProgressBar1.setMaximum(1000);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        menuBar.setEnabled(false);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Aplicación");

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Salir");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        timer = new Timer(ONE_SECOND, new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jProgressBar1.setValue(task.getCurrent());
                String s = task.getMessage();
                if (s != null) {
                    System.out.println(s);
                    jLabel1.setText(s);
                    //taskOutput.append(s + newline);
                    //taskOutput.setCaretPosition(taskOutput.getDocument().getLength());
                }
                if (task.isDone()) {
                    Toolkit.getDefaultToolkit().beep();
                    timer.stop();
                    //startButton.setEnabled(true);
                    setCursor(null); //turn off the wait cursor
                    jProgressBar1.setValue(jProgressBar1.getMinimum());
                    jFileChooser1.setEnabled(true);
                }
            }
        });

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooser1ActionPerformed
        // TODO add your handling code here:
        System.out.println(evt.getSource());
        javax.swing.JFileChooser caja = (javax.swing.JFileChooser) evt.getSource();
        System.out.println(caja.getSelectedFile().toString());
        try {
            if (caja.APPROVE_SELECTION.equals(evt.getActionCommand())) {
                File f = caja.getSelectedFile();
                if (f.exists()) {
                    //lectura pb = new lectura(f);
                    caja.setEnabled(false);
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    int filas = lectura.contar(f);
                    task.setLongTask(filas);
                    jProgressBar1.setMaximum(filas);
                    task.go(f);
                    timer.start();
                } else {
                    JOptionPane.showMessageDialog(null, "Debe escoger un archivo para procesar");
                }

            } else {
                System.exit(0);
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }


    }//GEN-LAST:event_jFileChooser1ActionPerformed

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AppPrincipal app = new AppPrincipal();

                app.setTitle("Generación de Notificaciones - Paragon Projects Partners");
                app.setResizable(false);
                Image imagenInterna = new ImageIcon(getClass().getResource("/icons/IDI.jpg")).getImage();
                app.setIconImage(imagenInterna);
                app.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    public javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
    private FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("Archivo Excel", "xlsx");
    public final static int ONE_SECOND = 1000;
    private ProgressMonitor progressMonitor;
    private Timer timer;
    private LongTask task;
    private String newline = "\n";
}
