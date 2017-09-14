package com.tools.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.tools.model.Sumario;
import com.tools.model.Venda;

/**
 * Classe utilitária para testes unitários.
 *
 * @author Rafael
 */
public class UnitTestUtil {

    /**
     * Cria um objeto {@link File}.
     *
     * @return o objeto {@link File}.
     */
    public static File criarFile() {
        final File file = new File("dados/in/arquivo.dat");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("001;1234567891234;Diego;5000.00");
            bufferedWriter.newLine();
            bufferedWriter.write("002;2345675434544345;Jose da Silva;Rural");
            bufferedWriter.newLine();
            bufferedWriter.write("002;2345675433444345;Eduardo Gonsalvez Pereira;Rural");
            bufferedWriter.newLine();
            bufferedWriter.write("001;3245678865434;Renato;4000.00");
            bufferedWriter.newLine();
            bufferedWriter.write("003;10;11010;300;3403.30;Diego");
            bufferedWriter.newLine();
            bufferedWriter.write("003;08;13410;540;2400.10;Renato");

        } catch (final IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * Cria um objeto {@link Sumario}.
     *
     * @return o objeto {@link Sumario}.
     */
    public static Sumario criarSumario() {
        return new Sumario(2L, 2L, 8L, "Diego", Paths.get("dados/out/arquivoTeste.dat.proc"));
    }

    /**
     * Cria um objeto {@link File} representando o sumário gerado.
     *
     * @return o objeto {@link File}.
     */
    public static File criarArquivoProcessado() {
        final File file = new File("dados/out/arquivoTeste.dat.proc");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write("1. Quantidade de Clientes: 2");
            bufferedWriter.newLine();
            bufferedWriter.write("2. Quantidade de Vendedores: 2");
            bufferedWriter.newLine();
            bufferedWriter.write("3. ID da Venda de valor mais alto: 8");
            bufferedWriter.newLine();
            bufferedWriter.write("4. Nome do Vendedor que menos vendeu: Diego");
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * Cria um objeto {@link Venda}.
     *
     * @return o objeto {@link Venda}.
     */
    public static Venda criarVenda() {
        return new Venda(8L, 13410L, 540L, 2400.10, "Renato");
    }

    /**
     * Cria um {@link TreeSet} de {@link Venda}.
     *
     * @return o {@link TreeSet} de {@link Venda}.
     */
    public static TreeSet<Venda> criarTreeSetVenda() {
        final TreeSet<Venda> vendas = new TreeSet<>();
        vendas.add(criarVenda());
        return vendas;
    }

    /**
     * Cria uma {@link List} de {@link Venda}.
     *
     * @return a {@link List} de {@link Venda}.
     */
    public static List<Venda> criarListaVenda() {
        final List<Venda> vendas = new ArrayList<>();
        vendas.add(criarVenda());
        return vendas;
    }

}
