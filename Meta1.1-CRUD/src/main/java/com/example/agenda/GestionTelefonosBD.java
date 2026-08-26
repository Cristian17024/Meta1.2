package com.example.agenda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GestionTelefonosBD {

    public List<Telefono> obtenerTelefonosDePersona(int personaId) {

        List<Telefono> telefonos = new ArrayList<>();

        String sql = "SELECT id, personaId, telefono " +
                "FROM Telefonos WHERE personaId = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, personaId);

            try (ResultSet resultado = sentencia.executeQuery()) {

                while (resultado.next()) {

                    Telefono telefono = new Telefono(
                            resultado.getInt("id"),
                            resultado.getInt("personaId"),
                            resultado.getString("telefono")
                    );

                    telefonos.add(telefono);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return telefonos;
    }

    public boolean agregarTelefono(Telefono telefono) {

        String sql = "INSERT INTO Telefonos (personaId, telefono) " +
                "VALUES (?, ?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, telefono.getPersonaId());
            sentencia.setString(2, telefono.getTelefono());

            sentencia.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarTelefono(Telefono telefono) {

        String sql = "UPDATE Telefonos SET telefono = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, telefono.getTelefono());
            sentencia.setInt(2, telefono.getId());

            sentencia.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarTelefono(int id) {

        String sql = "DELETE FROM Telefonos WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, id);

            sentencia.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}