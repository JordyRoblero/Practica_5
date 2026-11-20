package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import model.Mecanico;
import model.Vehiculo;

public class Archivos {

    public List<Mecanico> leerMecanicos(String archivo) {

        List<Mecanico> mecanicos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];

                mecanicos.add(new Mecanico(id, nombre));
            }

        } catch (Exception e) {
            System.out.println("Error al leer mecanicos: " + e.getMessage());
        }

        return mecanicos;
    }

    public List<Vehiculo> leerVehiculos(String archivo) {

        List<Vehiculo> vehiculos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";");

                String marca = datos[0];
                String placa = datos[1];

                vehiculos.add(new Vehiculo(marca, placa));
            }

        } catch (Exception e) {
            System.out.println("Error al leer vehiculos: " + e.getMessage());
        }

        return vehiculos;
    }

}