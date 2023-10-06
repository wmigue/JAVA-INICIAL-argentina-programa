public class Utilidades {
    public static String quitarUltimoCaracter(String cadena) {
        if (cadena != null && cadena.length() > 0) {
            cadena = cadena.trim();
            return cadena.substring(0, cadena.length() - 1).trim();
        }
        return cadena;
    }
}