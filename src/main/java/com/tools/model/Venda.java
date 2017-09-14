package com.tools.model;

import java.io.Serializable;

/**
 * Classe que representa uma venda.
 *
 * @author Rafael
 */
public class Venda implements Serializable, Comparable<Venda> {

    /** Constante de serialização. */
    private static final long serialVersionUID = -2878983460658658068L;

    /** Identificador da venda. */
    private Long id;

    /** Identificador do item vendido. */
    private Long idItem;

    /** Quantidade de itens vendidos. */
    private Long qtdeItem;

    /** Preço do item vendido. */
    private Double precoItem;

    /** Nome do vendedor. */
    private String nomeVendedor;

    /**
     * Construtor de {@link Venda}.
     */
    public Venda() {
        // Deixado intencionalmente vazio.
    }

    /**
     * Construtor de {@link Venda}.
     *
     * @param id identificador da venda.
     * @param idItem identificador do item vendido.
     * @param qtdeItem quantidade de itens vendidos.
     * @param precoItem preço do item vendido.
     * @param nomeVendedor nome do vendedor.
     */
    public Venda(final Long id, final Long idItem, final Long qtdeItem, final Double precoItem, final String nomeVendedor) {
        this.id = id;
        this.idItem = idItem;
        this.qtdeItem = qtdeItem;
        this.precoItem = precoItem;
        this.nomeVendedor = nomeVendedor;
    }

    /**
     * Enum que representa as informações de uma {@link Venda} em uma linha do
     * arquivo lido.
     *
     * @author Rafael
     */
    public enum Campos {

        ID(1), ID_ITEM(2), QTDE_ITEM(3), PRECO_ITEM(4), NOME_VENDEDOR(5);

        /**
         * O index da informação.
         */
        private final int index;

        /**
         * Construtor de {@link Campos}.
         *
         * @param index o index da informação.
         */
        private Campos(final int index) {
            this.index = index;
        }

        /**
         * @return the index
         */
        public int getIndex() {
            return this.index;
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Venda)) {
            return false;
        }
        final Venda other = (Venda) obj;
        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!this.id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     * @return the idItem
     */
    public Long getIdItem() {
        return this.idItem;
    }

    /**
     * @param idItem the idItem to set
     */
    public void setIdItem(final Long idItem) {
        this.idItem = idItem;
    }

    /**
     * @return the qtdeItem
     */
    public Long getQtdeItem() {
        return this.qtdeItem;
    }

    /**
     * @param qtdeItem the qtdeItem to set
     */
    public void setQtdeItem(final Long qtdeItem) {
        this.qtdeItem = qtdeItem;
    }

    /**
     * @return the precoItem
     */
    public Double getPrecoItem() {
        return this.precoItem;
    }

    /**
     * @param precoItem the precoItem to set
     */
    public void setPrecoItem(final Double precoItem) {
        this.precoItem = precoItem;
    }

    /**
     * @return the nomeVendedor
     */
    public String getNomeVendedor() {
        return this.nomeVendedor;
    }

    /**
     * @param nomeVendedor the nomeVendedor to set
     */
    public void setNomeVendedor(final String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
    }

    @Override
    public int compareTo(final Venda other) {
        final Double valorTotal = this.qtdeItem * this.precoItem;
        final Double valorTotalOther = other.getQtdeItem() * other.getPrecoItem();
        return valorTotal.compareTo(valorTotalOther);
    }

}
