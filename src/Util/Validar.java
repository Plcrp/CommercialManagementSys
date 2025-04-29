package Util;

public class Validar {
    private Validar() {}
    public static boolean validar(String documento){
        if (documento == null) return false;

        documento = documento.replaceAll("[^\\d]", "");
    
        if (documento.length() == 11) {
            // Validação de CPF
            if (documento.matches("(\\d)\\1{10}")) return false;
    
            int soma1 = 0, soma2 = 0;
            for (int i = 0; i < 9; i++) {
                int digito = documento.charAt(i) - '0';
                soma1 += digito * (10 - i);
                soma2 += digito * (11 - i);
            }
    
            int digito1 = 11 - (soma1 % 11);
            digito1 = (digito1 > 9) ? 0 : digito1;
            soma2 += digito1 * 2;
    
            int digito2 = 11 - (soma2 % 11);
            digito2 = (digito2 > 9) ? 0 : digito2;
    
            return documento.charAt(9) - '0' == digito1 && documento.charAt(10) - '0' == digito2;
    
        } else if (documento.length() == 14) {
            // Validação de CNPJ
            if (documento.matches("(\\d)\\1{13}")) return false;
    
            int[] pesos1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
            int[] pesos2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
            int soma1 = 0, soma2 = 0;
            for (int i = 0; i < 12; i++) {
                int digito = documento.charAt(i) - '0';
                soma1 += digito * pesos1[i];
                soma2 += digito * pesos2[i];
            }
    
            int digito1 = soma1 % 11;
            digito1 = (digito1 < 2) ? 0 : 11 - digito1;
            soma2 += digito1 * pesos2[12];
    
            int digito2 = soma2 % 11;
            digito2 = (digito2 < 2) ? 0 : 11 - digito2;
    
            return documento.charAt(12) - '0' == digito1 && documento.charAt(13) - '0' == digito2;
        }
    
        return false;
    }

}
