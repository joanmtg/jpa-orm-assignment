/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import pruebajpa.Personal;
import pruebajpa.PersonalJpaController;
import pruebajpa.Validaciones;

/**
 *
 * @author invitado
 */
public class VentanaBusquedaPersonal extends javax.swing.JFrame {

    /**
     * Creates new form VentanaBusquedaPersonal
     */
    
    JFrame ventanaAnterior;
    Validaciones validador = new Validaciones();
    public VentanaBusquedaPersonal(JFrame anterior) {        
        super("Búsqueda por ID");
        initComponents();
        
        this.ventanaAnterior = anterior;
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelInterno = new javax.swing.JPanel();
        lNumDoc = new javax.swing.JLabel();
        tfNumeroDoc = new javax.swing.JTextField();
        bConsultar = new javax.swing.JButton();
        bAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelInterno.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Búsqueda de Personal", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Lato", 0, 16))); // NOI18N

        lNumDoc.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        lNumDoc.setText("Número de Documento:");

        tfNumeroDoc.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        tfNumeroDoc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfNumeroDocKeyTyped(evt);
            }
        });

        bConsultar.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        bConsultar.setText("Consultar");
        bConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConsultarActionPerformed(evt);
            }
        });

        bAtras.setFont(new java.awt.Font("Lato", 0, 14)); // NOI18N
        bAtras.setText("Atrás");
        bAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelInternoLayout = new javax.swing.GroupLayout(panelInterno);
        panelInterno.setLayout(panelInternoLayout);
        panelInternoLayout.setHorizontalGroup(
            panelInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInternoLayout.createSequentialGroup()
                .addGroup(panelInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelInternoLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lNumDoc))
                    .addGroup(panelInternoLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(bAtras)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bConsultar)
                    .addComponent(tfNumeroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        panelInternoLayout.setVerticalGroup(
            panelInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelInternoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(panelInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNumDoc)
                    .addComponent(tfNumeroDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(panelInternoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAtras)
                    .addComponent(bConsultar))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelInterno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panelInterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAtrasActionPerformed
        
        this.dispose();
        ventanaAnterior.setVisible(true);
        
    }//GEN-LAST:event_bAtrasActionPerformed

    private void bConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConsultarActionPerformed
        // TODO add your handling code here:
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PruebaJPAPU");//PruebaJPAPU es el nombre de nuestra unidad de persistencia
        PersonalJpaController dao = new PersonalJpaController(emf);//Creamos un controlador de personal
        
        String numDoc = tfNumeroDoc.getText().trim();
        
        if(numDoc.equals("")){
            JOptionPane.showMessageDialog(null, "No deje el campo vacío");
        }
        else{
            Personal persona = dao.findPersonal(numDoc);
            
            if(persona != null){
                tfNumeroDoc.setText("");
                VentanaInformacionPersonal ventanaInfo = new VentanaInformacionPersonal(this, persona);
                ventanaInfo.setVisible(true);
                this.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "No existe la persona con el código ingresado", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
    }//GEN-LAST:event_bConsultarActionPerformed

    private void tfNumeroDocKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfNumeroDocKeyTyped
        // TODO add your handling code here:
        validador.validarNumeros(evt);
        validador.validarTamano(tfNumeroDoc, evt, 10);
    }//GEN-LAST:event_tfNumeroDocKeyTyped

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
            java.util.logging.Logger.getLogger(VentanaBusquedaPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaBusquedaPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaBusquedaPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaBusquedaPersonal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaBusquedaPersonal(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAtras;
    private javax.swing.JButton bConsultar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lNumDoc;
    private javax.swing.JPanel panelInterno;
    private javax.swing.JTextField tfNumeroDoc;
    // End of variables declaration//GEN-END:variables
}
