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
public class PacientesController {

    // Lista de pacientes obtenidas de la base de datos
    List<PacienteModel> pacientes;
    // Paciente seleccionada
    PacienteModel pacienteActual;

    public PacienteModel getPacienteActual() {
        return pacienteActual;
    }

    public void setPacienteActual(int indice) {
        this.pacienteActual = pacientes.get(indice);
    }

    public void CargarPacientes(JTable tabla) throws SQLException {
        // Obtenemos la lista de pacientes de la base de datos
        pacientes = DBSingleton.Instancia().obtenerPacientes();
        // Declaramos un arreglo para mandarlo despues de la tabla
        String[][] listaPacientes = new String[pacientes.size()][17];

        // Llenamos el arreglo
        for (int i = 0; i < pacientes.size(); i++) {
            listaPacientes[i][0] = pacientes.get(i).getId();
            listaPacientes[i][1] = pacientes.get(i).getSip();
            listaPacientes[i][2] = pacientes.get(i).getDni();
            listaPacientes[i][3] = pacientes.get(i).getNombre();
            listaPacientes[i][4] = pacientes.get(i).getApellido1();
            listaPacientes[i][5] = pacientes.get(i).getApellido2();
            listaPacientes[i][6] = pacientes.get(i).getTelefono();
            listaPacientes[i][7] = pacientes.get(i).getSexo();
            listaPacientes[i][8] = pacientes.get(i).getFechaNacimiento();
            listaPacientes[i][9] = pacientes.get(i).getLocalidad();
            listaPacientes[i][10] = pacientes.get(i).getCalle();
            listaPacientes[i][11] = pacientes.get(i).getNumero();
            listaPacientes[i][12] = pacientes.get(i).getPuerta();
            listaPacientes[i][13] = pacientes.get(i).getPiso();
            listaPacientes[i][14] = pacientes.get(i).getMedicoId();
            listaPacientes[i][15] = pacientes.get(i).getEnfermeroId();
            listaPacientes[i][16] = pacientes.get(i).getUserId();
        }

        // Mandamos el array a la tabla para mostrar la informacion en pantalla
        tabla.setModel(new DefaultTableModel(listaPacientes, new String[]{
            "ID",
            "SIP",
            "DNI",
            "Nombre",
            "Primer apellido",
            "Segundo apellido",
            "Telefono", "Sexo",
            "Fecha de nacimiento",
            "Localidad",
            "Calle",
            "Numero",
            "Puerta",
            "Piso",
            "ID Medico",
            "ID Enfermero",
            "ID Usuario"
        }));
    }

    public boolean AgregarPaciente(
            String sip,
            String dni,
            String nombre,
            String apellido1,
            String apellido2,
            String telefono,
            String sexo,
            String fechaNacimiento,
            String localidad,
            String calle,
            String numero,
            String puerta,
            String piso,
            String medicoId,
            String enfermeroId,
            String userId) {
        return DBSingleton.Instancia().agregarPaciente(
                String.valueOf(Integer.parseInt(pacientes.get(pacientes.size() - 1).getId()) + 1), // Ponemos como id el id del ultimo elemento de la lista + 1
                sip,
                dni,
                nombre,
                apellido1,
                apellido2,
                telefono,
                sexo,
                fechaNacimiento,
                localidad,
                calle,
                numero,
                puerta,
                piso,
                medicoId,
                enfermeroId,
                userId);
    }

    public boolean ActualizarPaciente(
            String sip,
            String dni,
            String nombre,
            String apellido1,
            String apellido2,
            String telefono,
            String sexo,
            String fechaNacimiento,
            String localidad,
            String calle,
            String numero,
            String puerta,
            String piso,
            String medicoId,
            String enfermeroId,
            String userId) {
        return pacienteActual.actualizarPaciente(
                pacienteActual.getId(),
                sip,
                dni,
                nombre,
                apellido1,
                apellido2,
                telefono,
                sexo,
                fechaNacimiento,
                localidad,
                calle,
                numero,
                puerta,
                piso,
                medicoId,
                enfermeroId,
                userId);
    }

    public void EliminarPaciente(JTable tabla) {
       // Obtenemos el indice del elemento seleccionado
        int indice = tabla.getSelectedRow();
        // Borramos el elemento de la tabla con el id dado
        DBSingleton.Instancia().borrarElemento("pacientes", pacientes.get(indice).getId());
        // Removemos el elemento de la tabla de la vista
        ((DefaultTableModel) tabla.getModel()).removeRow(indice);
        // Tambien lo borramos de la lista
        pacientes.remove(indice);
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
