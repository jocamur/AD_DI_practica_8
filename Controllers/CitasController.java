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
public class CitasController {

    // Lista de citas obtenidas de la base de datos
    List<CitaModel> citas;
    // Cita seleccionada
    CitaModel citaActual;

    public CitaModel obtenerCitaActual() {
        return citaActual;
    }

    public void asignarCitaActual(int indice) {
        this.citaActual = citas.get(indice);
    }

    public void CargarCitas(JTable tabla) throws SQLException {
        // Obtenemos la lista de citas de la base de datos
        citas = DBSingleton.Instancia().obtenerCitas();
        // Declaramos un arreglo para mandarlo despues de la tabla
        String[][] listaCitas = new String[citas.size()][5];

        // Llenamos el arreglo
        for (int i = 0; i < citas.size(); i++) {
            listaCitas[i][0] = citas.get(i).getId();
            listaCitas[i][1] = citas.get(i).getFecha();
            listaCitas[i][2] = citas.get(i).getMedicoId();
            listaCitas[i][3] = citas.get(i).getEnfermeroId();
            listaCitas[i][4] = citas.get(i).getPacienteId();
        }

        // Mandamos el array a la tabla para mostrar la informacion en pantalla
        tabla.setModel(new DefaultTableModel(
                listaCitas,
                new String[]{
                    "ID",
                    "Fecha",
                    "ID Medico",
                    "ID Enfermero",
                    "ID Paciente"
                }));
    }

    public boolean AgregarCita(
            String fecha,
            String idMedico,
            String idEnfermero,
            String idPaciente) {
        return DBSingleton.Instancia().agregarCita(
                String.valueOf(Integer.parseInt(citas.get(citas.size() - 1).getId()) + 1), // Ponemos como id el id del ultimo elemento de la lista + 1
                fecha,
                idMedico,
                idEnfermero,
                idPaciente);
    }

    public boolean ActualizarCita(
            String fecha,
            String idMedico,
            String idEnfermero,
            String idPaciente) {
        return citaActual.actualizarCita(
                citaActual.getId(),
                fecha,
                idMedico,
                idEnfermero,
                idPaciente);
    }

    public void EliminarCita(JTable tabla) {
        // Obtenemos el indice del elemento seleccionado
        int indice = tabla.getSelectedRow();
        // Borramos el elemento de la tabla con el id dado
        DBSingleton.Instancia().borrarElemento("citas", citas.get(indice).getId());
        // Removemos el elemento de la tabla de la vista
        ((DefaultTableModel) tabla.getModel()).removeRow(indice);
        // Tambien lo borramos de la lista
        citas.remove(indice);
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
