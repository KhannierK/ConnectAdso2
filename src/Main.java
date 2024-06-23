import Controlador.Controlador_Entrega;
import Modelo.BD_Entregas;
import Modelo.BD_Residencias;
import Vista.Vista_Entrega;

public class Main {
    public static void main(String[] args) {

        BD_Entregas modelo = new BD_Entregas();
        Vista_Entrega vista = new Vista_Entrega();
        Controlador_Entrega controlador = new Controlador_Entrega(modelo, vista);
        controlador.iniciar();
    }
}