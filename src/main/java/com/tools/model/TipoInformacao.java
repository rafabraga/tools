/**
 *
 */
package com.tools.model;

/**
 * Enum que representa o tipo de informação lido.
 *
 * @author Rafael
 */
public enum TipoInformacao {

    VENDEDOR("001"), CLIENTE("002"), VENDA("003");

    /**
     * O identificador do tipo de informação.
     */
    final String idTipoInformacao;

    /**
     * Construtor de {@link TipoInformacao}.
     *
     * @param idTipoInformacao o identificador do tipo de informação.
     */
    private TipoInformacao(final String idTipoInformacao) {
        this.idTipoInformacao = idTipoInformacao;
    }

    /**
     * Verifica se a informação contida na linha do arquivo é sobre um vendedor,
     * cliente ou venda.
     *
     * @param informacao a linha lida do arquivo.
     * @return true se a informação é do tipo desejado, senão retorna false.
     */
    public boolean verificarEUm(final String informacao) {
        return informacao.startsWith(this.idTipoInformacao);
    }

    /**
     * @return the idTipoInformacao
     */
    public String getIdTipoInformacao() {
        return this.idTipoInformacao;
    }

}
