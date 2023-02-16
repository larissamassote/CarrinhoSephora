package com.projetospring.SpringProject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TesteSephora {



    private double adicionarCarrinho() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lari_\\Downloads\\chromedriver.exe");

        Sephora sephora = new Sephora("https://www.sephora.com.br/perfume-dior-miss-dior-blooming-bouquet-feminino-eau-de-toilette-88990726-88990726.html");

        //WebElement cookies = sephora.getSearchInput("//*[@id=\"onetrust-accept-btn-handler\"]");
        //cookies.submit();

        WebElement botaoAddCarrinho = sephora.getSearchInput("//*[@id=\"add-to-cart\"]");
        botaoAddCarrinho.submit();

        WebElement valorCarrinho = sephora.getSearchInput("//*[@id=\"valueOrderTotalCart\"]");
        String entrada = valorCarrinho.getText().replace("R$ ", "");
        return Double.parseDouble(entrada.replace(",","."));

    }

    private double getValorProduto() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lari_\\Downloads\\chromedriver.exe");

        Sephora sephora = new Sephora("https://www.sephora.com.br/perfume-dior-miss-dior-blooming-bouquet-feminino-eau-de-toilette-88990726-88990726.html");

        WebElement valorProduto = sephora.getSearchInput("//*[@id=\"product-content\"]/div[4]/div[2]/div/div/div[2]/span/span[1]");

        String entrada = valorProduto.getText().replace("R$ ", "");
        return Double.parseDouble(entrada.replace(",", "."));
    }

    private double getValorCarrinho(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lari_\\Downloads\\chromedriver.exe");

        Sephora sephora1 = new Sephora("https://www.sephora.com.br/checkout/cart/");

        WebElement valorCarrinho = sephora1.getSearchInput("//*[@id=\"valueOrderTotalCart\"]");

        String entrada = valorCarrinho.getText().replace("R$ ", "");
        return Double.parseDouble(entrada.replace(",", "."));
    }

    private double aumentarQuantidade(int qtd){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lari_\\Downloads\\chromedriver.exe");

        Sephora sephora = new Sephora("https://www.sephora.com.br/perfume-dior-miss-dior-blooming-bouquet-feminino-eau-de-toilette-88990726-88990726.html");

        //WebElement cookies = sephora.getSearchInput("//*[@id=\"onetrust-accept-btn-handler\"]");
        //cookies.submit();

        WebElement maisUm = sephora.getSearchInput("//*//*[@id=\"dwfrm_product_addtocart_d0bijnzdijgr\"]/fieldset/div[1]/div/button[2]");
        for(int i=0; i<(qtd-1); i++) {
            maisUm.submit();
        }

        WebElement botaoAddCarrinho = sephora.getSearchInput("//*[@id=\"add-to-cart\"]");
        botaoAddCarrinho.submit();

        WebElement valorCarrinho = sephora.getSearchInput("//*[@id=\"valueOrderTotalCart\"]");
        String entrada = valorCarrinho.getText().replace("R$ ", "");
        return Double.parseDouble(entrada.replace(",","."));
    }

    @Test
    public void aoSubmeterUmProdutoAoCarrinho_VerificarValorTotalNoCarrinho(){

        double esperado = getValorProduto();

        double resultado = adicionarCarrinho();

        Assertions.assertEquals(esperado, resultado);
    }

    @Test
    public void aoSubmeterMaisDeUmaQuantidadeDeUmProduto_VerificarValorTotalNoCarrinho(){
        int quantidade = 2;

        double esperado = quantidade*getValorProduto();

        double resultado = aumentarQuantidade(quantidade);

        Assertions.assertEquals(esperado, resultado);
    }

}
