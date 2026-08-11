
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author HP
 */
public class Prestamo {

    private int idPrestamo;
    private int idLibro;
    private int idUsuario;
    private Date fechaPrestamo;
    private Date fechaDevolucionEsperada;
    private Date fechaDevolucionFinal;
    private boolean devuelto;
    private double multa;

    public Prestamo(int idPrestamo, int idLibro, int idUsuario, Date fechaPrestamo, Date fechaDevolucionEsperada, Date fechaDevolucionFinal, boolean devuelto, double multa) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.fechaDevolucionFinal = fechaDevolucionFinal;
        this.devuelto = devuelto;
        this.multa = multa;
    }

    public Prestamo() {
        this.idPrestamo = 0;
        this.idLibro = 0;
        this.idUsuario = 0;
        this.fechaPrestamo = null;
        this.fechaDevolucionEsperada = null;
        this.fechaDevolucionFinal = null;
        this.devuelto = false;
        this.multa = 0.0;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucionEsperada() {
        return fechaDevolucionEsperada;
    }

    public void setFechaDevolucionEsperada(Date fechaDevolucionEsperada) {
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
    }

    public Date getFechaDevolucionFinal() {
        return fechaDevolucionFinal;
    }

    public void setFechaDevolucionFinal(Date fechaDevolucionFinal) {
        this.fechaDevolucionFinal = fechaDevolucionFinal;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    // Marca el préstamo como devuelto y guarda la fecha real de devolución
    public void registrarDevolucion(Date fechaDevolucionFinal) {
        this.fechaDevolucionFinal = fechaDevolucionFinal;
        this.devuelto = true;
    }

    // Revisa si el préstamo está atrasado (comparando con hoy si aún no se devuelve)
    public boolean estaAtrasado() {
        if (devuelto) {
            return false;
        }
        Date hoy = new Date();
        return hoy.after(fechaDevolucionEsperada);
    }

    // Calcula cuántos días de atraso lleva (o llevó al momento de devolverse)
    public long diasAtraso() {
        Date referencia = devuelto ? fechaDevolucionFinal : new Date();
        if (referencia == null || fechaDevolucionEsperada == null) {
            return 0;
        }
        if (referencia.before(fechaDevolucionEsperada)) {
            return 0;
        }
        long diferenciaMs = referencia.getTime() - fechaDevolucionEsperada.getTime();
        return diferenciaMs / (1000 * 60 * 60 * 24);
    }

    // Calcula y guarda la multa según los días de atraso y una tarifa por día
    public double calcularMulta(double tarifaPorDia) {
        this.multa = diasAtraso() * tarifaPorDia;
        return this.multa;
    }

    @Override
    public String toString() {
        return "Préstamo #" + idPrestamo
                + " - ID Libro: " + idLibro
                + " - ID Usuario: " + idUsuario
                + " - Devuelto: " + (devuelto ? "Sí" : "No")
                + " - Multa: " + multa;
    }
}
