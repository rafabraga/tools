package com.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.tools.model.Sumario;
import com.tools.model.TipoInformacao;
import com.tools.model.Venda;

/**
 * Classe para rodar a aplicação.
 *
 * @author Rafael
 */
public class Application {

    /** {@link Path} para o diretório de arquivos a serem processados. */
    private static final String PATH_DIRETORIO_IN = "dados/in";

    /** {@link Path} para o diretório de destino dos arquivos processados. */
    private static final String PATH_DIRETORIO_OUT = "dados/out";

    /** Extensão dos arquivos a serem processados. */
    private static final String EXTENSAO_ARQUIVO_A_PROCESSAR = ".dat";

    /** Extensão dos arquivos processados. */
    private static final String EXTENSAO_ARQUIVO_PROCESSADO = ".proc";

    // @formatter:off
    public static void main(final String[] args) throws IOException {
        final Path diretorioIn = Paths.get(PATH_DIRETORIO_IN);
        Application.criarPastas();
        Arrays.asList(diretorioIn.toFile().listFiles())
            .stream()
                .filter(f -> f.getName().endsWith(EXTENSAO_ARQUIVO_A_PROCESSAR))
                .forEach((arquivo) -> {
                    final Application app = new Application();
                    app.processarArquivo(arquivo);
                });

    }

    // @formatter:on
    /**
     * Cria as pastas para entrada e saída de arquivos.
     */
    private static void criarPastas() {
        try {
            if (!Paths.get(PATH_DIRETORIO_IN).toFile().exists()) {
                Files.createDirectories(Paths.get(PATH_DIRETORIO_IN));
            }
            if (!Paths.get(PATH_DIRETORIO_OUT).toFile().exists()) {
                Files.createDirectories(Paths.get(PATH_DIRETORIO_OUT));
            }
        } catch (final IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar pastas.");
        }

    }

    /**
     * Realiza o processamento do arquivo.
     *
     * @param arquivo o {@link File} lido.
     */
    protected void processarArquivo(final File arquivo) {
        final Sumario sumario = this.montarSumario(arquivo);
        this.gravarArquivo(sumario);
        JOptionPane.showMessageDialog(null, "Concluído.");
    }

    // @formatter:off
    /**
     * Monta o {@link Sumario} de um arquivo.
     *
     * @param arquivo o arquivo a ser processado.
     * @return o {@link Sumario}.
     */
    protected Sumario montarSumario(final File arquivo) {
        final Path pathArquivoDestino = Paths.get(PATH_DIRETORIO_OUT).resolve(arquivo.getName() + EXTENSAO_ARQUIVO_PROCESSADO);

        List<String> linhasArquivo = new ArrayList<>();
        try {
            linhasArquivo = Files.readAllLines(arquivo.toPath());
        } catch (final IOException e) {
            // TODO os erros poderiam ser gravados em um arquivo de log.
            e.printStackTrace();
        }
        final TreeSet<Venda> vendas = this.listarVendas(linhasArquivo);

        final Long quantidadeClientes = linhasArquivo.stream().filter(TipoInformacao.CLIENTE::verificarEUm).count();
        final Long quantidadeVendedores = linhasArquivo.stream().filter(TipoInformacao.VENDEDOR::verificarEUm).count();
        final Long idVendaMaiorValor = vendas.last().getId();
        final String nomeVendedorMenosVendeu = vendas.first().getNomeVendedor();

        return new Sumario(quantidadeClientes, quantidadeVendedores, idVendaMaiorValor, nomeVendedorMenosVendeu, pathArquivoDestino);
    }

    /**
     * Filtra as linhas do arquivo lido, selecionando todas as vendas.
     *
     * @param linhasArquivo a lista de linhas lidas.
     * @return o {@link TreeSet} contendo as vendas do arquivo lido.
     */
    protected TreeSet<Venda> listarVendas(final List<String> linhasArquivo) {
        final List<String> linhasVendas = linhasArquivo
                .stream()
                    .filter(TipoInformacao.VENDA::verificarEUm)
                    .collect(Collectors.toList());
        return this.converterLinhasParaVendas(linhasVendas)
                .stream()
                    .collect(Collectors.toCollection(TreeSet::new));
    }

    // @formatter:on
    /**
     * Grava o {@link Sumario} de um arquivo.
     *
     * @param sumario o {@link Sumario}.
     */
    protected void gravarArquivo(final Sumario sumario) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(sumario.getPathArquivoDestino().toFile()))) {
            bufferedWriter.write("1. Quantidade de Clientes: " + sumario.getQuantidadeClientes());
            bufferedWriter.newLine();
            bufferedWriter.write("2. Quantidade de Vendedores: " + sumario.getQuantidadeVendedores());
            bufferedWriter.newLine();
            bufferedWriter.write("3. ID da Venda de valor mais alto: " + sumario.getIdVendaMaiorValor());
            bufferedWriter.newLine();
            bufferedWriter.write("4. Nome do Vendedor que menos vendeu: " + sumario.getNomeVendedorMenosVendeu());
        } catch (final IOException e) {
            // TODO os erros poderiam ser gravados em um arquivo de log.
            e.printStackTrace();
        }
    }

    /**
     * Cria uma venda com dados contidos na linha de um arquivo.
     *
     * @param linha a linha do arquivo.
     * @return a {@link Venda}.
     */
    protected List<Venda> converterLinhasParaVendas(final List<String> linhas) {
        Integer contadorLinha = 1;
        final List<Venda> vendas = new ArrayList<>();
        for (final String linha : linhas) {
            try {
                final String[] informacoesLinha = linha.split(";");
                final Long id = Long.valueOf(informacoesLinha[Venda.Campos.ID.getIndex()]);
                final Long idItem = Long.valueOf(informacoesLinha[Venda.Campos.ID_ITEM.getIndex()]);
                final Long qtdeItem = Long.valueOf(informacoesLinha[Venda.Campos.QTDE_ITEM.getIndex()]);
                final Double precoItem = Double.valueOf(informacoesLinha[Venda.Campos.PRECO_ITEM.getIndex()]);
                final String nomeVendedor = informacoesLinha[Venda.Campos.NOME_VENDEDOR.getIndex()];

                vendas.add(new Venda(id, idItem, qtdeItem, precoItem, nomeVendedor));
            } catch (final Exception e) {
                // TODO os erros poderiam ser gravados em um arquivo de log.
                this.registrarErro(contadorLinha, linha);
            } finally {
                contadorLinha++;
            }
        }
        return vendas;
    }

    /**
     * Registra um erro ao processar linha do arquivo.
     *
     * @param numeroLinha o número da linha do arquivo.
     * @param linha o conteúdo da linha.
     */
    protected void registrarErro(final Integer numeroLinha, final String linha) {
        JOptionPane.showMessageDialog(null, "Erro ao ler linha " + numeroLinha + " - " + linha);
    }

}
