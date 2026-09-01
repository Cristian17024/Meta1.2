package com.example.agenda.gestion;

import com.example.agenda.conexion.ConexionBD;
import com.example.agenda.modulos.Direccion;
import com.example.agenda.modulos.Persona;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GestionPersonaDireccionBD {

    public boolean asociarDireccion(int personaId, int direccionId) {

        String sql = "INSERT INTO PersonaDireccion " +
                "(personaId, direccionId) VALUES (?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, personaId);
            sentencia.setInt(2, direccionId);

            sentencia.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarAsociacion(int personaId, int direccionId) {

        String sql = "DELETE FROM PersonaDireccion " +
                "WHERE personaId = ? AND direccionId = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, personaId);
            sentencia.setInt(2, direccionId);

            sentencia.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Direccion> obtenerDireccionesDePersona(int personaId) {

        List<Direccion> direcciones = new ArrayList<>();

        String sql =
                "SELECT d.id, d.direccion " +
                        "FROM Direcciones d " +
                        "INNER JOIN PersonaDireccion pd " +
                        "ON d.id = pd.direccionId " +
                        "WHERE pd.personaId = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, personaId);

            try (ResultSet resultado = sentencia.executeQuery()) {

                while (resultado.next()) {

                    Direccion direccion = new Direccion(
                            resultado.getInt("id"),
                            resultado.getString("direccion")
                    );

                    direcciones.add(direccion);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return direcciones;
    }

    public List<Persona> obtenerPersonasDeDireccion(int direccionId) {

        List<Persona> personas = new ArrayList<>();

        String sql =
                "SELECT p.id, p.nombre " +
                        "FROM Personas p " +
                        "INNER JOIN PersonaDireccion pd " +
                        "ON p.id = pd.personaId " +
                        "WHERE pd.direccionId = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, direccionId);

            try (ResultSet resultado = sentencia.executeQuery()) {

                while (resultado.next()) {

                    Persona persona = new Persona(
                            resultado.getInt("id"),
                            resultado.getString("nombre")
                    );

                    personas.add(persona);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return personas;
    }
}