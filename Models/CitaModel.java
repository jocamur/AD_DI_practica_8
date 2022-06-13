package crud_java.Models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.InputStream;

public class CitaModel {

    private String id;
    private String fecha;
    private String medicoId;
    private String enfermeroId;
    private String pacienteId;

    public CitaModel(String id, String fecha, String medicoId, String enfermeroId, String pacienteId) {
        this.id = id;
        this.fecha = fecha;
        this.medicoId = medicoId;
        this.enfermeroId = enfermeroId;
        this.pacienteId = pacienteId;
    }

    public boolean actualizarCita(String id, String fecha, String medicoId, String enfermeroId, String pacienteId) {
        try {
            // Definimos la URL
            String urlString = "http://localhost/HospitalPHP/sw_citas.php?action=update&id=" + id + "&fecha=" + fecha + "&idMedico=" + medicoId + "&idEnfermero=" + enfermeroId + "&idPaciente=" + pacienteId;
            // Reemplazamos los espacios con %20 para que no de error, %20 es el equivalente a un espacio
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

    public String getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public String getEnfermeroId() {
        return enfermeroId;
    }

    public String getPacienteId() {
        return pacienteId;
    }
}
