package Modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class BD_Entregas {
    private HashMap<String, Entrega> entregas;

    public BD_Entregas() {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("entregas.json");
            Type type = new TypeToken<HashMap<String, Entrega>>(){}.getType();
            entregas = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            entregas = new HashMap<>();
        }
        updateJson();
    }

    public HashMap<String, Entrega> getEntregas() {
        return entregas;
    }

    public boolean validarEntregaInstancia(Entrega entrega) {
        return entregas.containsKey(entrega.getReferencia());
    }

    public Entrega validarEntregaString(String referencia) {
        return entregas.get(referencia);
    }

    public void agregarEntrega(Entrega entrega) {
        if (validarEntregaInstancia(entrega)) {
            JOptionPane.showMessageDialog(null, "La entrega con referencia " + entrega.getReferencia() + " ya existe", "Error", JOptionPane.ERROR_MESSAGE);

        } else {
            entregas.put(entrega.getReferencia(), entrega);
            JOptionPane.showMessageDialog(null, "La entrega con referencia " + entrega.getReferencia() + " se ha agregado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

        }
        updateJson();
    }

    public void eliminarEntrega(String referencia) {
        if (entregas.remove(referencia) != null) {
            JOptionPane.showMessageDialog(null, "La entrega con referencia " + referencia + " se ha eliminado correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "No se ha encontrado un paquete con referencia " + referencia, "Error", JOptionPane.ERROR_MESSAGE);


        }
        updateJson();
    }

    public void updateJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter("entregas.json");
            gson.toJson(this.entregas, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
