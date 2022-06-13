package crud_java.Models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.json.*;
import java.sql.*;

public class DBSingleton {

    private static DBSingleton instancia = null;
    private static Connection conexion;

    // Metodo para obtener la instancia de estaclase
    public static DBSingleton Instancia() 
    {
        // Si la instancia no existe, es decir no se ha creado, la creamos
        if (instancia == null) {
            instancia = new DBSingleton();

            // Establecemos conexion con la base de datos hospital, con usuario por default root y contrasena vacia
            try {
                Class.forName("com.mysql.jdbc.Driver");
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "");
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        // Regresamos la instancia
        return instancia;
    }

    // Metodo para obtener la conexion establecida actual
    public static Connection Conexion() {
        return conexion;
    }

    // Metodo para exportar una tabla a un archio CSV Excel
    public void ExportarACSV(JTable tabla, File archivo) {
        try {
            TableModel modeloTabla = tabla.getModel();
            FileWriter archivoCSV = new FileWriter(archivo);

            // Tenemos que escribir el archivo manualmente, asi que vamos
            // haciendo en modo columnas/filas y separando por comas
            
            // Empezamos creando las cabeceras de las columnas y asignando los nombres de tal forma que quede asi
            // Nombre 1  Nombre 2  Nombre 3
            // etc
            for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
                archivoCSV.write(modeloTabla.getColumnName(i) + ",");
            }

            // Salto de linea
            archivoCSV.write("\n");

            // Empezamos a escribir los datos, empezamos por la primera fila y llenamos todas las columnas
            // repetimos hasta llenar todos los datos
            for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                    archivoCSV.write(modeloTabla.getValueAt(i, j) + ",");
                }
                
                // Hacemos salto de linea cuando terminamos con una fila
                archivoCSV.write("\n");
            }

            // Cerramos el archivo
            archivoCSV.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    public boolean borrarElemento(String tabla, String id) {
        try {
            // Definimos la URL
            String urlString = "http://localhost/HospitalPHP/sw_"+tabla+".php?action=delete&id="+id;
            URL url = new URL(urlString);
            // Iniciamos la conexion
            HttpURLConnection conexionHTTP = (HttpURLConnection) url.openConnection();
            // Lo hacemos en modo POST porque estamos enviando informacion 
            conexionHTTP.setRequestMethod("POST");
            conexionHTTP.setRequestProperty("Content-Type", "application/json");
            conexionHTTP.setRequestProperty("Accept", "application/json");
            // Una conexion puede ser usada para entrada o salida, especificamos que es salida
            conexionHTTP.setDoOutput(true);
            conexionHTTP.connect();
             // Obtenemos el resultado
            InputStream resultadoStream = conexionHTTP.getInputStream();
            // Creamos un lector para poder traducir el resultado
            BufferedReader lector = new BufferedReader(new InputStreamReader(resultadoStream));

            // StringBuffer para almacenar el resultado
            StringBuffer resultado = new StringBuffer();

            // Linea de entrada para ir leyendo 1x1 las lineas del resultado hasta llegar al final
            String lineaDeEntrada = "";

            while ((lineaDeEntrada = lector.readLine()) != null) {
                resultado.append(lineaDeEntrada);
            }

            lector.close();
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregarCita(String id, String fecha, String medicoId, String enfermeroId, String pacienteId) {
        try {
            // Definimos la URL
            String urlString = "http://localhost/HospitalPHP/sw_citas.php?action=insert&id=" + id + "&fecha=" + fecha + "&idMedico=" + medicoId + "&idEnfermero=" + enfermeroId + "&idPaciente=" + pacienteId;
            urlString = urlString.replace(" ", "%20");
            URL url = new URL(urlString);
            // Iniciamos la conexion
            HttpURLConnection conexionHTTP = (HttpURLConnection) url.openConnection();
            // Lo hacemos en modo POST porque estamos enviando informacion 
            conexionHTTP.setRequestMethod("POST");
            conexionHTTP.setRequestProperty("Content-Type", "application/json");
            conexionHTTP.setRequestProperty("Accept", "application/json");
            // Una conexion puede ser usada para entrada o salida, especificamos que es salida
            conexionHTTP.setDoOutput(true);
            conexionHTTP.connect();
             // Obtenemos el resultado
            InputStream resultadoStream = conexionHTTP.getInputStream();
            // Creamos un lector para poder traducir el resultado
            BufferedReader lector = new BufferedReader(new InputStreamReader(resultadoStream));

            // StringBuffer para almacenar el resultado
            StringBuffer resultado = new StringBuffer();

            // Linea de entrada para ir leyendo 1x1 las lineas del resultado hasta llegar al final
            String lineaDeEntrada = "";

            while ((lineaDeEntrada = lector.readLine()) != null) {
                resultado.append(lineaDeEntrada);
            }
            
            lector.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregarMedico(String id, String numeroColegiado, String dni, String nombre, String apellido1, String apellido2,
            String telefono, String sexo, String especialidadId, String horarioId, String userId) {
        try {
            // Definimos la URL
            String urlString = "http://localhost/HospitalPHP/sw_medicos.php?action=insert&id=" + id + "&numeroColegiado=" + numeroColegiado + ""
                    + "&dni=" + dni + "&nombre=" + nombre + "&apellido1=" + apellido1 + "&apellido2=" + apellido2 + "&telefono=" + telefono + "&sexo=" + sexo + ""
                    + "&idEspecialidad=" + especialidadId + "&idHorario=" + horarioId + "&idUser=" + userId;
            urlString = urlString.replace(" ", "%20");
            URL url = new URL(urlString);
            // Iniciamos la conexion
            HttpURLConnection conexionHTTP = (HttpURLConnection) url.openConnection();
            // Lo hacemos en modo POST porque estamos enviando informacion 
            conexionHTTP.setRequestMethod("POST");
            conexionHTTP.setRequestProperty("Content-Type", "application/json");
            conexionHTTP.setRequestProperty("Accept", "application/json");
            // Una conexion puede ser usada para entrada o salida, especificamos que es salida
            conexionHTTP.setDoOutput(true);
            conexionHTTP.connect();
             // Obtenemos el resultado
            InputStream resultadoStream = conexionHTTP.getInputStream();
            // Creamos un lector para poder traducir el resultado
            BufferedReader lector = new BufferedReader(new InputStreamReader(resultadoStream));

            // StringBuffer para almacenar el resultado
            StringBuffer resultado = new StringBuffer();

            // Linea de entrada para ir leyendo 1x1 las lineas del resultado hasta llegar al final
            String lineaDeEntrada = "";

            while ((lineaDeEntrada = lector.readLine()) != null) {
                resultado.append(lineaDeEntrada);
            }
            
            lector.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean agregarPaciente(String id, String sip, String dni, String nombre, String apellido1, String apellido2,
            String telefono, String sexo, String fechaNacimiento, String localidad, String calle, String numero,
            String puerta, String piso, String medicoId, String enfermeroId, String userId) {
        try {
            // Definimos la URL
            String urlString = "http://localhost/HospitalPHP/sw_pacientes.php?action=insert&id=" + id + "&sip=" + sip + ""
                    + "&dni=" + dni + "&nombre=" + nombre + "&apellido1=" + apellido1 + "&apellido2=" + apellido2 + "&telefono=" + telefono + "&sexo=" + sexo + ""
                    + "&fechaNacimiento=" + fechaNacimiento + "&localidad=" + localidad + "&calle=" + calle + "&numero=" + numero + "&puerta=" + puerta
                    + "&piso=" + piso + "&idMedico=" + medicoId + "&idEnfermero=" + enfermeroId + "&idUser=" + userId;
            urlString = urlString.replace(" ", "%20");
            URL url = new URL(urlString);
            // Iniciamos la conexion
            HttpURLConnection conexionHTTP = (HttpURLConnection) url.openConnection();
            // Lo hacemos en modo POST porque estamos enviando informacion 
            conexionHTTP.setRequestMethod("POST");
            conexionHTTP.setRequestProperty("Content-Type", "application/json");
            conexionHTTP.setRequestProperty("Accept", "application/json");
            // Una conexion puede ser usada para entrada o salida, especificamos que es salida
            conexionHTTP.setDoOutput(true);
            conexionHTTP.connect();
             // Obtenemos el resultado
            InputStream resultadoStream = conexionHTTP.getInputStream();
            // Creamos un lector para poder traducir el resultado
            BufferedReader lector = new BufferedReader(new InputStreamReader(resultadoStream));

            // StringBuffer para almacenar el resultado
            StringBuffer resultado = new StringBuffer();

            // Linea de entrada para ir leyendo 1x1 las lineas del resultado hasta llegar al final
            String lineaDeEntrada = "";

            while ((lineaDeEntrada = lector.readLine()) != null) {
                resultado.append(lineaDeEntrada);
            }
            
            lector.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CitaModel> obtenerCitas() {
        // Lista de citas
        List<CitaModel> listaCitas = new ArrayList<>();
        // Lista JSON obtenida de la base de datos
        JSONArray respuestaJSON =  obtenerTabla("citas").getJSONArray("data");

        // Ejecutamos para todos los elementos
        for (int i = 0; i < respuestaJSON.length(); i++) {
            // Cargamos la fila con indice i
            JSONObject cita = (JSONObject) respuestaJSON.get(i);
            
            // Agregamos una nueva cita con los datos de la cita actual
            listaCitas.add(new CitaModel(
                    cita.get("id").toString(),
                    cita.get("fecha").toString(),
                    cita.get("medico_id").toString(),
                    cita.get("enfermero_id").toString(),
                    cita.get("paciente_id").toString()));
        }
        
        // Regreamos la lista llenada
        return listaCitas;
    }

    public List<MedicoModel> obtenerMedicos() {
        // Lista de medicos
        List<MedicoModel> listaMedicos = new ArrayList<>();
        // Lista JSON obtenida de la base de datos
        JSONArray respuestaJSON = obtenerTabla("medicos").getJSONArray("data");

        // Ejecutamos para todos los elementos
        for (int i = 0; i < respuestaJSON.length(); i++) {
            // Cargamos la fila con indice i
            JSONObject medico = (JSONObject) respuestaJSON.get(i);
            
            // Agregamos un nuevo medico con los datos del medico actual
            listaMedicos.add(new MedicoModel(
                    medico.get("id").toString(),
                    medico.get("numero_colegiado").toString(),
                    medico.get("dni").toString(),
                    medico.get("nombre").toString(),
                    medico.get("apellido1").toString(),
                    medico.get("apellido2").toString(),
                    medico.get("telefono").toString(),
                    medico.get("sexo").toString(),
                    medico.get("especialidad_id").toString(),
                    medico.get("horario_id").toString(),
                    medico.get("user_id").toString()));
        }
        
        // Regreamos la lista llenada
        return listaMedicos;
    }

    public List<PacienteModel> obtenerPacientes() {
        // Lista de pacientes
        List<PacienteModel> listaPacientes = new ArrayList<>();
        // Lista JSON obtenida de la base de datos
        JSONArray respuestaJSON = obtenerTabla("pacientes").getJSONArray("data");

        // Ejecutamos para todos los elementos
        for (int i = 0; i < respuestaJSON.length(); i++) {
            // Cargamos la fila con indice i
            JSONObject paciente = (JSONObject) respuestaJSON.get(i);
            
            // Agregamos un nuevo paciente con los datos del paciente actual
            listaPacientes.add(new PacienteModel(
                    paciente.get("id").toString(),
                    paciente.get("sip").toString(),
                    paciente.get("dni").toString(),
                    paciente.get("nombre").toString(),
                    paciente.get("apellido1").toString(),
                    paciente.get("apellido2").toString(),
                    paciente.get("telefono").toString(),
                    paciente.get("sexo").toString(),
                    paciente.get("fecha_nacimiento").toString(),
                    paciente.get("localidad").toString(),
                    paciente.get("calle").toString(),
                    paciente.get("numero").toString(),
                    paciente.get("puerta").toString(),
                    paciente.get("piso").toString(),
                    paciente.get("medico_id").toString(),
                    paciente.get("enfermero_id").toString(),
                    paciente.get("user_id").toString()));
        }
        
        // Regreamos la lista llenada
        return listaPacientes;
    }

    private JSONObject obtenerTabla(String tabla) {
        // StringBuffer para almacenar el resultado
        StringBuffer respuesta = null;

        try {
            // Definimos la URL
            String urlString = "http://localhost/HospitalPHP/sw_"+tabla+".php?action=get";
            URL url = new URL(urlString);
            // Iniciamos la conexion
            HttpURLConnection conexionHTTP = (HttpURLConnection) url.openConnection();
            // Lo hacemos en modo GET porque estamos recibiendo informacion 
            conexionHTTP.setRequestMethod("GET");
            // Una conexion puede ser usada para entrada o salida, especificamos que es entrada
            conexionHTTP.setDoInput(true);
            // Creamos un lector para poder traducir el resultad
            BufferedReader lector = new BufferedReader(new InputStreamReader(conexionHTTP.getInputStream()));
            // Linea de entrada para ir leyendo 1x1 las lineas del resultado hasta llegar al final
            String lineaDeEntrada;
            
            respuesta = new StringBuffer();

            while ((lineaDeEntrada = lector.readLine()) != null) {
                respuesta.append(lineaDeEntrada);
            }

            lector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JSONObject datosJSON = new JSONObject(respuesta.toString());
        return datosJSON;
    }
}
