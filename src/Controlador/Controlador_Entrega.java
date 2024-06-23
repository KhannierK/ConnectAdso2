package Controlador;

import Modelo.BD_Entregas;
import Modelo.Entrega;
import Vista.Vista_Entrega;

import javax.swing.*;

public class Controlador_Entrega {
    BD_Entregas modelo;
    Vista_Entrega vista;

    public Controlador_Entrega(BD_Entregas modelo, Vista_Entrega vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        int op;
        while (true) {
            op = Vista_Entrega.menu();
            switch (op) {
                case 1:
                    vista.consultarEntregas(modelo.getEntregas());
                    break;
                case 2:
                    Entrega entrega = Vista_Entrega.detallesEntrega();
                    modelo.agregarEntrega(entrega);
                    break;
                case 3:
                    String referenciaEliminar = vista.getString("referencia de la entrega a eliminar");
                    if (referenciaEliminar != null) {
                        modelo.eliminarEntrega(referenciaEliminar);
                    }
                    break;
                case 4:
                    String referenciaRecogida = vista.getString("referencia de la entrega a marcar como recogida");
                    if (referenciaRecogida != null) {
                        Entrega entregaRecogida = modelo.validarEntregaString(referenciaRecogida);
                        if (entregaRecogida != null) {
                            entregaRecogida.setRecogido(true);
                            modelo.updateJson();
                            JOptionPane.showMessageDialog(null, "La entrega ha sido marcada como recogida", "Información", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "No se encontró una entrega con esa referencia", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Error... Opción no válida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
