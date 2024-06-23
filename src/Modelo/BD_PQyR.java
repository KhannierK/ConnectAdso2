package Modelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class BD_PQyR {
    private HashMap<String, PQyR> pqyrMap;

    public BD_PQyR() {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader("pqyr.json");
            Type type = new TypeToken<HashMap<String, PQyR>>(){}.getType();
            pqyrMap = gson.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            pqyrMap = new HashMap<>();
        }
        updateJson();
    }

    public HashMap<String, PQyR> getPqyrMap() {
        return pqyrMap;
    }

    public void asignarReferencia() {
        int i = 0;
        for (String key : pqyrMap.keySet()) {
            String referencia = String.format("%03d", i++);
            PQyR pqyr = pqyrMap.get(key);
            pqyr.setReferencia(referencia);
            pqyrMap.put(referencia, pqyr);
            pqyrMap.remove(key);
        }
        updateJson();
    }

    public PQyR validarPQyRString(String ref) {
        return pqyrMap.get(ref);
    }

    public void agregarPQyR(PQyR pqyr) {
        int nuevaReferencia = pqyrMap.size();
        String referencia = String.format("%03d", nuevaReferencia);
        pqyr.setReferencia(referencia);
        pqyrMap.put(referencia, pqyr);
        System.out.println("La solicitud ha sido agregada con éxito con número de referencia " + pqyr.getReferencia());
        updateJson();
    }

    public void eliminarPQyR(String ref) {
        if (pqyrMap.remove(ref) != null) {
            asignarReferencia();
            System.out.println("Se ha eliminado correctamente la solicitud con número de referencia " + ref);
        } else {
            System.out.println("No se ha encontrado una solicitud con número de referencia " + ref);
        }
        updateJson();
    }

    public HashMap<String, PQyR> consultarPQyR(String casa) {
        HashMap<String, PQyR> resultados = new HashMap<>();
        for (PQyR pqr : pqyrMap.values()) {
            if (pqr.getCasa().equalsIgnoreCase(casa)) {
                resultados.put(pqr.getReferencia(), pqr);
            }
        }
        return resultados;
    }

    public void mostrarPQyR(HashMap<String, PQyR> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay solicitudes por mostrar");
        } else {
            for (PQyR pqyr : lista.values()) {
                System.out.println(pqyr);
            }
        }
    }

    public void updateJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            FileWriter writer = new FileWriter("pqyr.json");
            gson.toJson(this.pqyrMap, writer);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
