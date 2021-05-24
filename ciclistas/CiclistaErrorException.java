package ciclistas;

@SuppressWarnings("serial")
public class CiclistaErrorException extends IllegalArgumentException {
  public CiclistaErrorException(String message) {
    super(message);
  }
}
