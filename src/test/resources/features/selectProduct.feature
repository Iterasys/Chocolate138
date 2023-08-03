# #language: pt
#  Funcionalidade: Selecionar Produto na Loja
#    Cenario: Selecionar Produto com Sucesso
#      Dado que acesso a loja SauceDemo
#      Quando preencho o usuario e senha
#      E clica em Login
#      Entao exibe o titulo da pagina como "Products"
#      E exibe o link do carrinho de compras
#      Quando clica no produto "Sauce Labs Backpack"

  Feature: Select Product in Store
    Scenario: Selecting Product with Success
      Given I access SauceDemo store
      When I filled a user "standard_user" and password "secret_sauce"
      And I click in Login
      Then show page's title "Products"
      And show cart's link
      When I click in product "4"
      Then I verify the product title "Sauce Labs Backpack"
      And I verify the product price "$29.99"
      When I click in Add to Cart
      And I click in Cart icon
      Then I verify the page's title "Your Cart"
      And I verify the product title "Sauce Labs Backpack"
      And I verify the quantity is "1"
      And I verify the product price "$29.99"
