/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MessageForm.java
 *
 * Created on 17-sep-2010, 8:46:21
 */

package com.openkm.openoffice.ui;

import com.openkm.openoffice.OpenKMAddOn;

/**
 *
 * @author Administrador
 */
public class ErrorForm extends javax.swing.JFrame {

    /** Creates new form MessageForm */
    public ErrorForm(Exception ex) {
        String message = ex.getMessage();
        message += "\nCause:" +ex.getCause();
        message += "\n\nStackTrace:\n";

        for (int i=0; i<ex.getStackTrace().length; i++) {
            message += ex.getStackTrace()[i] + "\n";
        }

        initComponents();
        setLocationByPlatform(true);
        setLocationRelativeTo(getParent());
        setTitle(OpenKMAddOn.get().getLang().getString("error.title"));
        closeButton.setText(OpenKMAddOn.get().getLang().getString("button.close"));
        errorTextArea.setText(message);
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPanel = new javax.swing.JScrollPane();
        errorTextArea = new javax.swing.JTextArea();
        closeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        errorTextArea.setColumns(20);
        errorTextArea.setRows(5);
        scrollPanel.setViewportView(errorTextArea);

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(closeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(closeButton)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JTextArea errorTextArea;
    private javax.swing.JScrollPane scrollPanel;
    // End of variables declaration//GEN-END:variables

}
