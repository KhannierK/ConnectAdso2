package Modelo;

public class Entrega {
    private String referencia;
    private String casa;
    private String tipo;
    private String descripcion;
    private boolean recogido;

    public Entrega(String referencia, String casa, String tipo, String descripcion) {
        this.referencia = referencia;
        this.casa = casa;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.recogido = false;
    }

    public String getReferencia() {
        return referencia;
    }

    public boolean isRecogido() {
        return recogido;
    }

    public void setRecogido(boolean recogido) {
        this.recogido = recogido;
    }

    @Override
    public String toString() {
        return "Referencia: " + referencia + ", Casa: " + casa + ", Tipo: " + tipo + ", Descripción: " + descripcion + ", Recogido: " + (recogido ? "Sí" : "No");
    }
}
