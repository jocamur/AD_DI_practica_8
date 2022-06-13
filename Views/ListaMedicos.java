package crud_java.Views;

import crud_java.Controllers.MedicosController;
import java.awt.Color;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import static crud_java.Views.Dashboard.content;
import javax.swing.JOptionPane;

public class ListaMedicos extends javax.swing.JPanel {
    MedicosController controlador = new MedicosController();

    public ListaMedicos() {
        initComponents();
        
        // Hacemos que no se pueda editar las columnas de datos
        jTable1.setDefaultEditor(Object.class, null);

        // Cargamos los medicos y mostrarlos en la tabla
        try {
            controlador.CargarMedicos(jTable1);
        } catch (SQLException ex) {
            Logger.getLogger(ListaMedicos.class.getName()).log(Level.SEVERE, null, ex);
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

        body = new javax.swing.JPanel();
        Title = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonAgregar = new javax.swing.JButton();
        buttonActualizar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        buttonExportar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(750, 430));
        setPreferredSize(new java.awt.Dimension(750, 430));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        body.setBackground(new java.awt.Color(255, 255, 255));
        body.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        add(body, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Title.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        Title.setText("Medicos");
        add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Numero colegiado", "DNI", "Nombre", "Primer apellido", "Segundo apellido", "Telefono", "Sexo", "ID Especialidad", "ID Horario", "ID Usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 750, 330));

        buttonAgregar.setBackground(new java.awt.Color(18, 90, 173));
        buttonAgregar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonAgregar.setForeground(new java.awt.Color(255, 255, 255));
        buttonAgregar.setText("Agregar");
        buttonAgregar.setBorder(null);
        buttonAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonAgregarMouseClicked(evt);
            }
        });
        add(buttonAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 390, 140, 30));

        buttonActualizar.setBackground(new java.awt.Color(18, 90, 173));
        buttonActualizar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonActualizar.setForeground(new java.awt.Color(255, 255, 255));
        buttonActualizar.setText("Actualizar");
        buttonActualizar.setBorder(null);
        buttonActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonActualizarMouseClicked(evt);
            }
        });
        add(buttonActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, 140, 30));

        buttonEliminar.setBackground(new java.awt.Color(18, 90, 173));
        buttonEliminar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonEliminar.setForeground(new java.awt.Color(255, 255, 255));
        buttonEliminar.setText("Eliminar");
        buttonEliminar.setBorder(null);
        buttonEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonEliminarMouseClicked(evt);
            }
        });
        add(buttonEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 390, 140, 30));

        buttonExportar.setBackground(new java.awt.Color(53, 173, 16));
        buttonExportar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        buttonExportar.setForeground(new java.awt.Color(255, 255, 255));
        buttonExportar.setText("Exportar");
        buttonExportar.setBorder(null);
        buttonExportar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buttonExportarMouseClicked(evt);
            }
        });
        add(buttonExportar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 140, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonAgregarMouseClicked
        // Cuando se clickea el boton de agregar cargamos la siguiente pantalla
        AgregarActualizarMedico p1 = new AgregarActualizarMedico(controlador, true);
        p1.setSize(750, 430);
        p1.setLocation(0, 0);

        content.removeAll();
        content.add(p1, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_buttonAgregarMouseClicked

    private void buttonActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonActualizarMouseClicked
        // Si no hay elemento seleccionado para actualizar mostramos mensaje y regresamos
        if (jTable1.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "¡Selecciona un elemento!", "Alerta", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Cuando se clickea el boton de actualizar cargamos la siguiente pantalla
        controlador.setMedicoActual(jTable1.getSelectedRow());
        AgregarActualizarMedico p1 = new AgregarActualizarMedico(controlador, false);
        p1.setSize(750, 430);
        p1.setLocation(0, 0);

        // Limpiamos pantalla
        content.removeAll();
        content.add(p1, BorderLayout.CENTER);
        content.revalidate();
        content.repaint();
    }//GEN-LAST:event_buttonActualizarMouseClicked

    private void buttonEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonEliminarMouseClicked
        // Si no hay elemento seleccionado para eliminar mostramos mensaje y regresamos
        if (jTable1.getSelectedRow() == -1)
        {
            JOptionPane.showMessageDialog(this, "¡Selecciona un elemento!", "Alerta", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Eliminamos el elemento seleccionado
        controlador.EliminarMedico(jTable1);
        JOptionPane.showMessageDialog(this, "¡Elemento eliminado!", "Alerta", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_buttonEliminarMouseClicked

    private void buttonExportarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buttonExportarMouseClicked
        // Exportamos la tabla
        controlador.ExportarACSV(jTable1, this);
        JOptionPane.showMessageDialog(this, "¡Exportacion finalizada!", "Alerta", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_buttonExportarMouseClicked

    void setColor(JPanel panel) {
        panel.setBackground(new Color(21, 101, 192));
    }

    void resetColor(JPanel panel) {
        panel.setBackground(new Color(18, 90, 173));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Title;
    private javax.swing.JPanel body;
    private javax.swing.JButton buttonActualizar;
    private javax.swing.JButton buttonAgregar;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonExportar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
