package crud_java.Models;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PacienteModel {

    private String id;
    private String sip;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String telefono;
    private String sexo;
    private String fechaNacimiento;
    private String localidad;
    private String calle;
    private String numero;
    private String puerta;
    private String piso;
    private String medicoId;
    private String enfermeroId;
    private String userId;

    public PacienteModel(String id, String sip, String dni, String nombre, String apellido1, String apellido2, String telefono, String sexo, String fechaNacimiento, String localidad, String calle, String numero, String puerta, String piso, String medicoId, String enfermeroId, String userId) {
        this.id = id;
        this.sip = sip;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.localidad = localidad;
        this.calle = calle;
        this.numero = numero;
        this.puerta = puerta;
        this.piso = piso;
        this.medicoId = medicoId;
        this.enfermeroId = enfermeroId;
        this.userId = userId;
    }

    public boolean actualizarPaciente(String id, String sip, String dni, String nombre, String apellido1, String apellido2,
            String telefono, String sexo, String fechaNacimiento, String localidad, String calle, String numero,
            String puerta, String piso, String medicoId, String enfermeroId, String userId) {
        try {
            // Definimos la URL
            String urlString = "http://localhost/HospitalPHP/sw_pacientes.php?action=update&id=" + id + "&sip=" + sip + ""
                    + "&dni=" + dni + "&nombre=" + nombre + "&apellido1=" + apellido1 + "&apellido2=" + apellido2 + "&telefono=" + telefono + "&sexo=" + sexo + ""
                    + "&fechaNacimiento=" + fechaNacimiento + "&localidad=" + localidad + "&calle=" + calle + "&numero=" + numero + "&puerta=" + puerta
                    + "&piso=" + piso + "&idMedico=" + medicoId + "&idEnfermero=" + enfermeroId + "&idUser=" + userId;
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

    public String getSip() {
        return sip;
    }

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getSexo() {
        return sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getLocalidad() {
        return localidad;
    }

    public String getCalle() {
        return calle;
    }

    public String getNumero() {
        return numero;
    }

    public String getPuerta() {
        return puerta;
    }

    public String getPiso() {
        return piso;
    }

    public String getMedicoId() {
        return medicoId;
    }

    public String getEnfermeroId() {
        return enfermeroId;
    }

    public String getUserId() {
        return userId;
    }
}
