package co.com.alura.model.errores.gateways;

public interface Validador<T> {
    void validar(T datos);
}
