  # language: pt

  @cadastro
  Funcionalidade: Realizar todo fluxo onde
                  devo consultar,selcionar,validar e adcionar o produto no
                  Onde o mesmo deve ser validado se esta de acordo.
                  A - Simples
                  1) Consulte por "Ração"

                  2) Selecione o terceiro produto da lista retornada.

                  3) Valide o nome do produto, fornecedor, preço normal e preço para assinante

                  4) Insira o produto no carrinho de compras

                  5) Valide se os dados do item 3 continuam idênticos
 -------------------------------------------------------------------------------------------------------------------------------------------------------------------------
                  B - Com Massa de Teste

                  6) A partir de uma massa de teste em formato csv, json, yml ou xml, consulte 10 itens com nomes de produtos a sua escolha

                  7) Realize novamente os passos anteriores 2, 3, 4e 5 para cada produto.

                  8) Crie screenshots automáticos de cada etapa e o log de execução e salve no disco, em uma pasta criada a cada execução com o formato yyyy-MM-dd HH-mm

    Contexto:
    Dado que tenha acessado a tela do ecommerce

  Esquema do Cenario: A - Simples
    E deve inserir um texto no input para consulta do produto <racao> e retornar com sucesso
    E deve selecionar a ração <nomeprod> e retornar com sucesso
    E Validar o nome do produto, fornecedor, preço normal e preço para assinante
    Quando Inserir o produto no carrinho de compras
    Entao deve validar se os dados do item 3 continuam idênticos
    Exemplos:
      | racao  |nomeprod|
      | "Ração"|"Golden Power Training para Cães Adultos Sabor Frango e Arroz - 15kg"|