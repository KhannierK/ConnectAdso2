package Vista;

import Modelo.Entrega;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class Vista_Entrega {

    public Vista_Entrega() { }

    public static int menu() {
        String[] opciones = {"Consultar entregas", "Agregar nueva entrega", "Eliminar entrega", "Marcar entrega como recogida", "Salir"};
        int i = JOptionPane.showOptionDialog(
                null,
                "Seleccione una opción",
                "Menú",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);
        return i + 1;
    }

    public static Entrega detallesEntrega() {
        String referencia = null;
        String casa = null;
        String tipo = null;
        String descripcion = null;

        while (true) {
            referencia = JOptionPane.showInputDialog(null, "Introduce la referencia de la entrega:", "Entrada de datos", JOptionPane.QUESTION_MESSAGE);
            if (referencia == null || referencia.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error... La referencia no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            casa = JOptionPane.showInputDialog(null, "Introduce la casa:", "Entrada de datos", JOptionPane.QUESTION_MESSAGE);
            if (casa == null || casa.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error... La casa no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            tipo = JOptionPane.showInputDialog(null, "Introduce el tipo de entrega:", "Entrada de datos", JOptionPane.QUESTION_MESSAGE);
            if (tipo == null || tipo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error... El tipo no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            descripcion = JOptionPane.showInputDialog(null, "Introduce la descripción de la entrega:", "Entrada de datos", JOptionPane.QUESTION_MESSAGE);
            if (descripcion == null || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error... La descripción no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            break; // Salir del bucle si todo es válido
        }

        return new Entrega(referencia, casa, tipo, descripcion);
    }

    public String getString(String parametro) {
        String str;
        while (true) {
            str = JOptionPane.showInputDialog(null, "Introduce el " + parametro + ":", "Entrada de datos", JOptionPane.QUESTION_MESSAGE);
            if (str == null || str.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error... No puede haber ningún campo vacío", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            break;
        }
        return str;
    }

    public void consultarEntregas(HashMap<String, Entrega> lista) {
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay entregas disponibles", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder resultado = new StringBuilder();
            for (Map.Entry<String, Entrega> entry : lista.entrySet()) {
                String clave = entry.getKey();
                Entrega valor = entry.getValue();
                resultado.append("Entrega: ").append(clave).append(" ").append(valor).append("\n");
            }
            JOptionPane.showMessageDialog(null, resultado.toString(), "Entregas Encontradas", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
