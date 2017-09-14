package com.tools.model;

import java.io.Serializable;
import java.nio.file.Path;

/**
 * Classe que representa o sumário de um arquivo processado.
 *
 * @author Rafael
 */
public class Sumario implements Serializable {

    /** Constante de serialização. */
    private static final long serialVersionUID = -7232237679351821609L;

    /** A quantidade de clientes contidos no arquivo. */
    private Long quantidadeClientes;

    /** A quantidade de vendedores contidos no arquivo. */
    private Long quantidadeVendedores;

    /** Identificador da maior venda. */
    private Long idVendaMaiorValor;

    /** Nome do vendedor que menos vendeu. */
    private String nomeVendedorMenosVendeu;

    /** Path para o arquivo processado. */
    private Path pathArquivoDestino;

    /**
     * Construtor.
     */
    public Sumario() {
        // Deixado intencionalmente vazio.
    }

    /**
     * @param quantidadeClientes
     * @param quantidadeVendedores
     * @param idVendaMaiorValor
     * @param nomeVendedorMenosVendeu
     * @param pathArquivoDestino
     */
    public Sumario(final Long quantidadeClientes, final Long quantidadeVendedores, final Long idVendaMaiorValor,
            final String nomeVendedorMenosVendeu, final Path pathArquivoDestino) {
        this.quantidadeClientes = quantidadeClientes;
        this.quantidadeVendedores = quantidadeVendedores;
        this.idVendaMaiorValor = idVendaMaiorValor;
        this.nomeVendedorMenosVendeu = nomeVendedorMenosVendeu;
        this.pathArquivoDestino = pathArquivoDestino;
    }

    /**
     * @return the quantidadeClientes
     */
    public Long getQuantidadeClientes() {
        return this.quantidadeClientes;
    }

    /**
     * @param quantidadeClientes the quantidadeClientes to set
     */
    public void setQuantidadeClientes(final Long quantidadeClientes) {
        this.quantidadeClientes = quantidadeClientes;
    }

    /**
     * @return the quantidadeVendedores
     */
    public Long getQuantidadeVendedores() {
        return this.quantidadeVendedores;
    }

    /**
     * @param quantidadeVendedores the quantidadeVendedores to set
     */
    public void setQuantidadeVendedores(final Long quantidadeVendedores) {
        this.quantidadeVendedores = quantidadeVendedores;
    }

    /**
     * @return the idVendaMaiorValor
     */
    public Long getIdVendaMaiorValor() {
        return this.idVendaMaiorValor;
    }

    /**
     * @param idVendaMaiorValor the idVendaMaiorValor to set
     */
    public void setIdVendaMaiorValor(final Long idVendaMaiorValor) {
        this.idVendaMaiorValor = idVendaMaiorValor;
    }

    /**
     * @return the nomeVendedorMenosVendeu
     */
    public String getNomeVendedorMenosVendeu() {
        return this.nomeVendedorMenosVendeu;
    }

    /**
     * @param nomeVendedorMenosVendeu the nomeVendedorMenosVendeu to set
     */
    public void setNomeVendedorMenosVendeu(final String nomeVendedorMenosVendeu) {
        this.nomeVendedorMenosVendeu = nomeVendedorMenosVendeu;
    }

    /**
     * @return the pathArquivoDestino
     */
    public Path getPathArquivoDestino() {
        return this.pathArquivoDestino;
    }

    /**
     * @param pathArquivoDestino the pathArquivoDestino to set
     */
    public void setPathArquivoDestino(final Path pathArquivoDestino) {
        this.pathArquivoDestino = pathArquivoDestino;
    }

}
