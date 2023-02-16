package com.projetospring.SpringProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Sephora extends Page{
    public Sephora(String endereco){
        super();
        driver.get(endereco);
    }

    public WebElement getSearchInput(String xpath){

        return driver.findElement(By.xpath(xpath));
    }

    @Override
    public String getTitle(){
        return driver.getTitle();
    }

    public Double getValorProduto(){
        WebElement preco = driver.findElement(By.xpath("//*[@id=\"product-content\"]/div[3]/div[1]/div/div/span/span[1]"));
        return Double.parseDouble(preco.getText());
    }
}
