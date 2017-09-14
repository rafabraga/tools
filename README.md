# tools

Sistema para processamento de arquivos em lote. Os arquivos podem possuir 3 tipos de informação:

```
1. Dados de Vendedores: (001;CPF;nome;salário)
2. Dados de Clientes: (002;CNPJ;nome;ramo de atividade)
3. Dados de Vendas: (003;ID da venda;ID do item;qtde do item;preço do item;Nome do Vendedor)
```
Exemplo:

```
001;1234567891234;Diego;5000.00
002;2345675434544345;Jose da Silva;Rural
002;2345675433444345;Eduardo Gonsalvez Pereira;Rural
001;3245678865434;Renato;4000.00
003;10;11010;300;3403.30;Diego
003;08;13410;540;2400.10;Renato
```

O programa lê todos os arquivos com extensão .dat que forem colocados no diretório /dados/in/. Estes arquivos são processados e suas informação sumarizadas em um novo arquivo em /dados/out/${nomedoarquivolido}.dat.proc. O sumário possui as informações: 

```
1. Quantidade de Clientes:
2. Quantidade de Vendedores:
3. ID da Venda de valor mais alto:
4. Nome do Vendedor que menos vendeu:
```

O que foi utilizado no desenvolvimento:

```
Java 8
Maven
JUnit
Mockito
```
