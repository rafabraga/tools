package com.tools;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.tools.model.Sumario;
import com.tools.model.Venda;
import com.tools.util.UnitTestUtil;

/**
 * Classe para testes unitários de {@link Application}.
 *
 * @author Rafael
 */
public class ApplicationTest {

    /** Objeto que representa a classe a ser testada. */
    @InjectMocks
    private final Application classeEmTeste = Mockito.spy(Application.class);

    /**
     * Inicializa os mocks antes de cada teste.
     */
    @Before
    public void setUpBefore() {
        MockitoAnnotations.initMocks(Application.class);
    }

    /**
     * Testa o método {@link Application#processarArquivo(File)}.
     */
    @Test
    public void processarArquivoTest() {
        Mockito.doReturn(new Sumario()).when(this.classeEmTeste).montarSumario(Mockito.any(File.class));
        Mockito.doNothing().when(this.classeEmTeste).gravarArquivo(Mockito.any(Sumario.class));
        this.classeEmTeste.processarArquivo(new File(""));
        Assert.assertTrue(true);
    }

    /**
     * Testa o método {@link Application#montarSumario(File)}.
     */
    @Test
    public void montarSumarioTest() {
        Mockito.doReturn(UnitTestUtil.criarTreeSetVenda()).when(this.classeEmTeste).listarVendas(Mockito.<String>anyList());
        final Sumario sumario = this.classeEmTeste.montarSumario(UnitTestUtil.criarFile());
        Assert.assertEquals(2L, sumario.getQuantidadeClientes().longValue());
        Assert.assertEquals(2L, sumario.getQuantidadeVendedores().longValue());
        Assert.assertEquals(8L, sumario.getIdVendaMaiorValor().longValue());
        Assert.assertEquals("Renato", sumario.getNomeVendedorMenosVendeu());
    }

    /**
     * Testa o método {@link Application#gravarArquivo(Sumario)}.
     */
    @Test
    public void gravarArquivoTest() throws IOException {
        final Sumario sumario = UnitTestUtil.criarSumario();
        this.classeEmTeste.gravarArquivo(sumario);
        final File arquivoGerado = new File("dados/out/arquivoTeste.dat.proc");
        Assert.assertTrue(arquivoGerado.exists());
        final File arquivoGeradoEsperado = UnitTestUtil.criarArquivoProcessado();
        final List<String> linhasArquivoGerado = Files.readAllLines(arquivoGerado.toPath());
        final List<String> linhasArquivoGeradoEsperado = Files.readAllLines(arquivoGeradoEsperado.toPath());
        for (int i = 0; i < linhasArquivoGerado.size(); i++) {
            Assert.assertEquals(linhasArquivoGeradoEsperado.get(i), linhasArquivoGerado.get(i));
        }
        Files.delete(arquivoGeradoEsperado.toPath());
    }

    /**
     * Testa o método {@link Application#listarVendas(List)}.
     */
    @Test
    public void listarVendasTest() {
        Mockito.doReturn(UnitTestUtil.criarListaVenda()).when(this.classeEmTeste).converterLinhasParaVendas(Mockito.<String>anyList());
        final List<String> linhasArquivo = new ArrayList<>();
        linhasArquivo.add("001;1234567891234;Diego;5000.00");
        linhasArquivo.add("002;2345675434544345;Jose da Silva;Rural");
        linhasArquivo.add("003;08;13410;540;2400.10;Renato");
        final TreeSet<Venda> actual = this.classeEmTeste.listarVendas(linhasArquivo);
        Assert.assertEquals(1, actual.size());
    }

    /**
     * Testa o método {@link Application#converterLinhasParaVendas(List)} contendo
     * problema em uma linha.
     */
    @Test
    public void converterLinhasParaVendasTest() {
        Mockito.doNothing().when(this.classeEmTeste).registrarErro(Mockito.anyInt(), Mockito.anyString());
        final List<String> linhasArquivo = new ArrayList<>();
        linhasArquivo.add("003;08;13410;540;2400.10;Renato");
        linhasArquivo.add("003;10;11010;3a00;3403.30;Diego");
        linhasArquivo.add("003;08;13410;540;Renato");
        final List<Venda> actual = this.classeEmTeste.converterLinhasParaVendas(linhasArquivo);
        Mockito.verify(this.classeEmTeste, Mockito.times(2)).registrarErro(Mockito.anyInt(), Mockito.anyString());
        Assert.assertEquals(1, actual.size());
    }

}
