package controlador;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.vistauser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;


public class ControladorU implements ActionListener {

    UsuarioDAO dao = new UsuarioDAO();
    Usuario u = new Usuario();
    vistauser vista = new vistauser();
    DefaultTableModel modelo = new DefaultTableModel();

    public ControladorU(vistauser d) {
        this.vista = d;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnDelete.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.btnNuevo.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.btnListar) {
            limpiarTabla();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnAgregar) {
            add();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnEditar) {
            int fila = vista.tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(vista, "Debe Seleccionar Una fila..!!");
            } else {
                int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
                String user = (String) vista.tabla.getValueAt(fila, 1);
                String contra = (String) vista.tabla.getValueAt(fila, 2);
                String nombre = (String) vista.tabla.getValueAt(fila, 3);
                String rol = (String) vista.tabla.getValueAt(fila, 4);
                vista.txtId.setText("" + id);
                vista.txtUser.setText(user);
                vista.txtContra.setText(contra);
                vista.txtNom.setText(nombre);
                vista.txtRol.setText(rol);
            }
        }
        if (e.getSource() == vista.btnActualizar) {
            Actualizar();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnDelete) {
            delete();
            listar(vista.tabla);
            nuevo();
        }
        if (e.getSource() == vista.btnNuevo) {
            nuevo();
        }
    }

    void nuevo() {
        vista.txtId.setText("");
        vista.txtUser.setText("");
        vista.txtContra.setText("");
        vista.txtNom.setText("");
        vista.txtRol.setText("");
        vista.txtUser.requestFocus();
    }

    public void delete() {
        int fila = vista.tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(vista, "Debe Seleccionar una Fila...!!!");
        } else {
            int id = Integer.parseInt((String) vista.tabla.getValueAt(fila, 0).toString());
            dao.eliminar(id);
            JOptionPane.showMessageDialog(vista, "Usuario Eliminado...!!!");
        }
        limpiarTabla();
    }

    public void add() {
        String user = vista.txtUser.getText();
        String contra = vista.txtContra.getText();
        String nombre = vista.txtNom.getText();
        String rol = vista.txtRol.getText();
        u.setUser(user);
        u.setContra(contra);
        u.setNombre(nombre);
        u.setRol(rol);
        int r = dao.agregar(u);
        if (r == 1) {
            JOptionPane.showMessageDialog(vista, "Usuario Agregado con Éxito.");
        } else {
            JOptionPane.showMessageDialog(vista, "Error");
        }
        limpiarTabla();
    }

    public void Actualizar() {
        if (vista.txtId.getText().equals("")) {
            JOptionPane.showMessageDialog(vista, "No se Identifica el Id, debe seleccionar la opción Editar");
        } else {
            int id = Integer.parseInt(vista.txtId.getText());
            String user = vista.txtUser.getText();
            String contra = vista.txtContra.getText();
            String nombre = vista.txtNom.getText();
            String rol = vista.txtRol.getText();
            u.setId(id);
            u.setUser(user);
            u.setContra(contra);
            u.setNombre(nombre);
            u.setRol(rol);
            int r = dao.actualizar(u);
            if (r == 1) {
                JOptionPane.showMessageDialog(vista, "Usuario Actualizado con Éxito.");
            } else {
                JOptionPane.showMessageDialog(vista, "Error");
            }
        }
        limpiarTabla();
    }

    public void listar(JTable tabla) {
        centrarCeldas(tabla);
        modelo = (DefaultTableModel) tabla.getModel();
        tabla.setModel(modelo);
        List<Usuario> lista = dao.listar();
        Object[] objeto = new Object[5];  
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getId();
            objeto[1] = lista.get(i).getUser();
            objeto[2] = lista.get(i).getContra();
            objeto[3] = lista.get(i).getNombre();
            objeto[4] = lista.get(i).getRol();
            modelo.addRow(objeto);
        }
        tabla.setRowHeight(35);
        tabla.setRowMargin(10);
    }

    void centrarCeldas(JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < vista.tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(tcr);
        }
    }

    void limpiarTabla() {
        for (int i = 0; i < vista.tabla.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
    }
}
