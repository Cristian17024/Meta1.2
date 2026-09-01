package com.example.agenda.gestion;

import com.example.agenda.conexion.ConexionBD;
import com.example.agenda.modulos.Direccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GestionDireccionesBD {

    public List<Direccion> obtenerTodasLasDirecciones() {

        List<Direccion> direcciones = new ArrayList<>();

        String sql = "SELECT id, direccion FROM Direcciones";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {

                Direccion direccion = new Direccion(
                        resultado.getInt("id"),
                        resultado.getString("direccion")
                );

                direcciones.add(direccion);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return direcciones;
    }

    public boolean agregarDireccion(Direccion direccion) {

        String sql = "INSERT INTO Direcciones (direccion) VALUES (?)";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, direccion.getDireccion());

            sentencia.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificarDireccion(Direccion direccion) {

        String sql = "UPDATE Direcciones SET direccion = ? WHERE id = ?";

        try (Connection conexion = ConexionBD.conectar();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, direccion.getDireccion());
            sentencia.setInt(2, direccion.getId());

            sentencia.executeUpdate();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarDireccion(int id) {

        String sql = "DELETE FROM Direcciones WHERE id = ?";

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