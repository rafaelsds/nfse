package com.apoia.nfse.utils;

public class FunctionCustom {
    public static String mascara(String valor, String mascara) {
        if (valor == null) return null;

        // remove tudo que não for número
        valor = valor.replaceAll("\\D", "");

        StringBuilder resultado = new StringBuilder();
        int indiceValor = 0;

        for (int i = 0; i < mascara.length(); i++) {
            if (indiceValor >= valor.length()) {
                break;
            }

            char c = mascara.charAt(i);
            if (c == '#') {
                resultado.append(valor.charAt(indiceValor));
                indiceValor++;
            } else {
                resultado.append(c);
            }
        }

        return resultado.toString();
    }

    public static String nvl(Object conteudo, String retorno){
        if(conteudo == null) {
            return retorno;
        }
        if(String.valueOf(conteudo).isEmpty()) {
            return retorno;
        }

        return conteudo.toString();
    }

}
