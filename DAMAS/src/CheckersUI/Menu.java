/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CheckersUI;

import checkers.Cronometro;

/**
 *
 * @author Santi
 */
public class Menu extends javax.swing.JPanel {

    private javax.swing.JLabel logo, turno, Turno;
    public static javax.swing.JLabel time;
    private javax.swing.JButton exit;
    Cronometro watch=new Cronometro();
  
    
    public Menu() {
        initComponents();
        time.setText("00:00");
        watch.empezar();
        
    }

    public void initComponents() {

        this.setSize(319, 600);
        this.setLayout(null);
        this.setBackground(new java.awt.Color(0, 0, 0));

        logo = new javax.swing.JLabel();
        logo.setBounds(10, 20, 300, 300);
        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/logo_damas2.png")));
        time = new javax.swing.JLabel();
        time.setBounds(105, 340, 123, 45);
        time.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        time.setForeground(new java.awt.Color(234, 234, 234));
        time.setText("Time");
        turno = new javax.swing.JLabel();
        turno.setBounds(94, 390, 123, 30);
        turno.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        turno.setForeground(new java.awt.Color(255, 255, 255));
        turno.setText("TURNO DE:");
        Turno = new javax.swing.JLabel();
        Turno.setBounds(10, 420, 300, 48);
        Turno.setFont(new java.awt.Font("Tempus Sans ITC", 0, 36)); // NOI18N
        Turno.setForeground(new java.awt.Color(234, 234, 234));
        Turno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Turno.setText("NAME");
        exit = new javax.swing.JButton();
        exit.setFont(new java.awt.Font("Tahoma", 0, 24));
        exit.setText("SALIR");
        exit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        exit.setBackground(new java.awt.Color(255, 255, 255));
        exit.setBounds(94, 513, 123, 35);
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitActionPerformed(evt);
            }
        });
        
        this.add(logo);
        this.add(time);
        this.add(turno);
        this.add(Turno);
        this.add(exit);

    }

    private void ExitActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

}
