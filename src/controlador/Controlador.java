package controlador;

import modelo.Persona;
import modelo.PersonaDAO;
import vista.vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Controlador implements ActionListener {

    PersonaDAO dao = new PersonaDAO();
    Persona p = new Persona();
    vista vistauser = new vista();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlador(vista v) {
        this.vistauser = v;
        this.vistauser.btnListar.addActionListener(this);
        this.vistauser.btnAgregar.addActionListener(this);
        this.vistauser.btnEditar.addActionListener(this);
        this.vistauser.btnDelete.addActionListener(this);
        this.vistauser.btnActualizar.addActionListener(this);
        this.vistauser.btnNuevo.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistauser.btnListar) {
            limpiarTabla();
            listar(vistauser.tabla);
            nuevo();
        }
        if (e.getSource() == vistauser.btnAgregar) {
            add();
            listar(vistauser.tabla);
            nuevo();
        }
        if (e.getSource() == vistauser.btnEditar) {
            int fila = vistauser.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vistauser, "Debee Seleccionar Una fila..!!");
            } else {
                int id = Integer.parseInt((String) vistauser.tabla.getValueAt(fila, 0).toString());
                String nom = (String) vistauser.tabla.getValueAt(fila, 1);
                String correo = (String) vistauser.tabla.getValueAt(fila, 2);
                String tel = (String) vistauser.tabla.getValueAt(fila, 3);
                vistauser.txtId.setText("" + id);
                vistauser.txtNom.setText(nom);
                vistauser.txtCorreo.setText(correo);
                vistauser.txtTel.setText(tel);
            }
        }
        if (e.getSource() == vistauser.btnActualizar) {
            Actualizar();
            listar(vistauser.tabla);
            nuevo();

        }
        if (e.getSource() == vistauser.btnDelete) {
            delete();
            listar(vistauser.tabla);
            nuevo();
        }
        if (e.getSource() == vistauser.btnNuevo) {
            nuevo();
        }

    }

    void nuevo() {
        vistauser.txtId.setText("");
        vistauser.txtNom.setText("");
        vistauser.txtTel.setText("");
        vistauser.txtCorreo.setText("");
        vistauser.txtNom.requestFocus();
    }

    public void delete() {
        int fila = vistauser.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vistauser, "Debe Seleccionar una Fila...!!!");
        } else {
            int id = Integer.parseInt((String) vistauser.tabla.getValueAt(fila, 0).toString());
            dao.Delete(id);
            System.out.println("El Reusltaod es" + id);
            JOptionPane.showMessageDialog(vistauser, "Usuario Eliminado...!!!");
        }
        limpiarTabla();
    }

    public void add() {
        String nom = vistauser.txtNom.getText();
        String correo = vistauser.txtCorreo.getText();
        String tel = vistauser.txtTel.getText();
        p.setNom(nom);
        p.setCorreo(correo);
        p.setTelefono(tel);
        int r = dao.agregar(p);
        if (r == 1) {
            JOptionPane.showMessageDialog(vistauser, "Usuario Agregado con Exito.");
        } else {
            JOptionPane.showMessageDialog(vistauser, "Error");
        }
        limpiarTabla();
    }

    public void Actualizar() {
        if (vistauser.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vistauser, "No se Identifica el Id debe selecionar la opcion Editar");
        } else {
            int id = Integer.parseInt(vistauser.txtId.getText());
            String nom = vistauser.txtNom.getText();
            String correo = vistauser.txtCorreo.getText();
            String tel = vistauser.txtTel.getText();
            p.setId(id);
            p.setNom(nom);
            p.setCorreo(correo);
            p.setTelefono(tel);
            int r = dao.Actualizar(p);
            if (r == 1) {
                JOptionPane.showMessageDialog(vistauser, "Usuario Actualizado con Exito.");
            } else {
                JOptionPane.showMessageDialog(vistauser, "Error");
            }
        }
        limpiarTabla();
    }

    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Persona> lista = dao.listar();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getNom();
            objeto[2] = lista.get(i).getCorreo();
            objeto[3] = lista.get(i).getTelefono();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);

    }

    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vistauser.tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vistauser.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}