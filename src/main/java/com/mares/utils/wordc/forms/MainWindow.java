/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mares.utils.wordc.forms;

import java.awt.event.WindowEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.jdesktop.observablecollections.ObservableCollections;

/**
 *
 * @author jan
 */
public class MainWindow extends javax.swing.JFrame {
    private List<DocumentReference> documents;
    final JFileChooser fileChooser = new JFileChooser();
    private static final int DEFAULT_PER_PAGE = 1800;



    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        this.documents = ObservableCollections.observableList(new ArrayList());
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setDragEnabled(false);
        fileChooser.setDialogTitle("Select a file to add to the list");
        initComponents();
        perPageTxt.setText(Integer.toString(DEFAULT_PER_PAGE));
        perPageTxt.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent de) {
                updated();
            }

            public void removeUpdate(DocumentEvent de) {
                updated();
            }

            public void changedUpdate(DocumentEvent de) {
                updated();
            }
            
            private void updated(){
                recount();
            }
        });
        refreshListSelected();
    }

    public List<DocumentReference> getDocuments() {
        return documents;
    }
    
    public void recount(){
        double count = 0;
        for(DocumentReference documentReference : documents){
            count += documentReference.getCount();
        }
        int perPage;
        try{
            perPage = Integer.parseInt(perPageTxt.getText());
        } catch(NumberFormatException e){
            perPage = DEFAULT_PER_PAGE;
//            perPageTxt.setText(Integer.toString(perPage));
        }
        DecimalFormat decimalFormat = new DecimalFormat("###.###");
        pageCountTxt.setText(decimalFormat.format(count/perPage));
    }

    public DocumentReference getSelectedDocument(){
        int selected = documentsList.getSelectedIndex();
        if(selected >= 0){
            return documents.get(selected);
        } else {
            return null;
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        documentsList = new javax.swing.JList();
        addBtn = new javax.swing.JButton();
        removeBtn = new javax.swing.JButton();
        closeBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        perPageTxt = new javax.swing.JTextField();
        perPageLbl = new javax.swing.JLabel();
        pageCountTxt = new javax.swing.JTextField();
        showBtn = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Word Counter");

        documentsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${documents}");
        org.jdesktop.swingbinding.JListBinding jListBinding = org.jdesktop.swingbinding.SwingBindings.createJListBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, documentsList);
        jListBinding.setDetailBinding(org.jdesktop.beansbinding.ELProperty.create("${fileName}"));
        bindingGroup.addBinding(jListBinding);

        documentsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                documentsListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(documentsList);

        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        removeBtn.setText("Remove");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        closeBtn.setText("Close");
        closeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeBtnActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Values"));

        jLabel1.setText("Nr. of pages:");

        perPageTxt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        perPageLbl.setText("Chars per page:");

        pageCountTxt.setEditable(false);
        pageCountTxt.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        pageCountTxt.setText("0");
        pageCountTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pageCountTxtActionPerformed(evt);
            }
        });
        pageCountTxt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pageCountTxtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pageCountTxtFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(perPageLbl)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(perPageTxt)
                    .addComponent(pageCountTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(perPageLbl)
                    .addComponent(perPageTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pageCountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        showBtn.setText("Show");
        showBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(removeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                    .addComponent(showBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(closeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showBtn)
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(closeBtn)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeBtnActionPerformed
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }//GEN-LAST:event_closeBtnActionPerformed

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        int returnVal = fileChooser.showOpenDialog(addBtn);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File[] selectedFiles = fileChooser.getSelectedFiles();
            for(File file: selectedFiles){
                documents.add(new DocumentReference(file));
            }
            recount();
        }
    }//GEN-LAST:event_addBtnActionPerformed

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        int selected = documentsList.getSelectedIndex();
        if(selected >= 0){
            documents.remove(selected);
            recount();
        }
    }//GEN-LAST:event_removeBtnActionPerformed

    private void pageCountTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pageCountTxtActionPerformed
        
    }//GEN-LAST:event_pageCountTxtActionPerformed

    private void pageCountTxtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pageCountTxtFocusGained
        pageCountTxt.setSelectionStart(0);
        pageCountTxt.setSelectionEnd(pageCountTxt.getText().length());
    }//GEN-LAST:event_pageCountTxtFocusGained

    private void pageCountTxtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pageCountTxtFocusLost
        pageCountTxt.setSelectionStart(0);
        pageCountTxt.setSelectionEnd(0);
    }//GEN-LAST:event_pageCountTxtFocusLost

    private void showBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showBtnActionPerformed
        DocumentReference selected = getSelectedDocument();
        if(selected != null){
            BrowseWindow.showText(selected);
        }
    }//GEN-LAST:event_showBtnActionPerformed

    private void documentsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_documentsListValueChanged
        refreshListSelected();
    }//GEN-LAST:event_documentsListValueChanged

    private void refreshListSelected() {
        boolean isSelected = getSelectedDocument() != null;
        showBtn.setEnabled(isSelected);
        removeBtn.setEnabled(isSelected);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton closeBtn;
    private javax.swing.JList documentsList;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField pageCountTxt;
    private javax.swing.JLabel perPageLbl;
    private javax.swing.JTextField perPageTxt;
    private javax.swing.JButton removeBtn;
    private javax.swing.JButton showBtn;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}