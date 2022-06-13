package crud_java.Controllers;

import crud_java.Models.*;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JTable;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JComponent;
import javax.swing.table.DefaultTableModel;

// Clase encargada de comunicarse entre la vista y el modelo que se comunica a su vez con la base de datos
public class MedicosController {

    // Lista de medicos obtenidas de la base de datos
    List<MedicoModel> medicos;
    // Medico seleccionada
    MedicoModel medicoActual;

    public MedicoModel getMedicoActual() {
        return medicoActual;
    }

    public void setMedicoActual(int indice) {
        this.medicoActual = medicos.get(indice);
    }

    public void CargarMedicos(JTable tabla) throws SQLException {
        // Obtenemos la lista de medicos de la base de datos
        medicos = DBSingleton.Instancia().obtenerMedicos();
        // Declaramos un arreglo para mandarlo despues de la tabla
        String[][] listaMedicos = new String[medicos.size()][11];

        // Llenamos el arreglo
        for (int i = 0; i < medicos.size(); i++) {
            listaMedicos[i][0] = medicos.get(i).getId();
            listaMedicos[i][1] = medicos.get(i).getNumeroColegiado();
            listaMedicos[i][2] = medicos.get(i).getDni();
            listaMedicos[i][3] = medicos.get(i).getNombre();
            listaMedicos[i][4] = medicos.get(i).getApellido1();
            listaMedicos[i][5] = medicos.get(i).getApellido2();
            listaMedicos[i][6] = medicos.get(i).getTelefono();
            listaMedicos[i][7] = medicos.get(i).getSexo();
            listaMedicos[i][8] = medicos.get(i).getEspecialidadId();
            listaMedicos[i][9] = medicos.get(i).getHorarioId();
            listaMedicos[i][10] = medicos.get(i).getUserId();
        }

        // Mandamos el array a la tabla para mostrar la informacion en pantalla
        tabla.setModel(new DefaultTableModel(
                listaMedicos,
                new String[]{
                    "ID",
                    "Numero colegiado",
                    "DNI",
                    "Nombre",
                    "Primer apellido",
                    "Segundo apellido",
                    "Telefono",
                    "Sexo",
                    "ID Especialidad",
                    "ID Horario",
                    "ID Usuario"
                }));
    }

    public boolean AgregarMedico(
            String numeroColegiado,
            String dni,
            String nombre,
            String apellido1,
            String apellido2,
            String telefono,
            String sexo,
            String idEspecialidad,
            String idHorario,
            String idUser) {
        return DBSingleton.Instancia().agregarMedico(
                String.valueOf(Integer.parseInt(medicos.get(medicos.size() - 1).getId()) + 1), // Ponemos como id el id del ultimo elemento de la lista + 1
                numeroColegiado,
                dni,
                nombre,
                apellido1,
                apellido2,
                telefono,
                sexo,
                idEspecialidad,
                idHorario,
                idUser);
    }

    public boolean ActualizarMedico(
            String numeroColegiado,
            String dni,
            String nombre,
            String apellido1,
            String apellido2,
            String telefono,
            String sexo,
            String idEspecialidad,
            String idHorario,
            String idUser) {
        return medicoActual.actualizarMedico(
                medicoActual.getId(),
                numeroColegiado,
                dni,
                nombre,
                apellido1,
                apellido2,
                telefono,
                sexo,
                idEspecialidad,
                idHorario,
                idUser);
    }

    public void EliminarMedico(JTable tabla) {
        // Obtenemos el indice del elemento seleccionado
        int indice = tabla.getSelectedRow();
        // Borramos el elemento de la tabla con el id dado
        DBSingleton.Instancia().borrarElemento("medicos", medicos.get(indice).getId());
        // Removemos el elemento de la tabla de la vista
        ((DefaultTableModel) tabla.getModel()).removeRow(indice);
        // Tambien lo borramos de la lista
        medicos.remove(indice);;
    }

    public void ExportarACSV(JTable tabla, JComponent vista) {
        // Creamos un "JFileChooser" para mostra una ventana para elegir la ubicacion
        // para guardar el documento
        JFileChooser fileChooser = new JFileChooser();
        // Variable para guardar la opcion seleccionada (Guardar/Cancelar)
        int opcion = fileChooser.showSaveDialog(vista);

        // Si selecciona guardar
        if (opcion == JFileChooser.APPROVE_OPTION) {
            String nombreDelArchivo = fileChooser.getSelectedFile().getName();
            String ruta = fileChooser.getSelectedFile().getParentFile().getPath();
            String archivo = ruta + "\\" + nombreDelArchivo + ".csv";
            DBSingleton.Instancia().ExportarACSV(tabla, new File(archivo));
        }
    }
}
