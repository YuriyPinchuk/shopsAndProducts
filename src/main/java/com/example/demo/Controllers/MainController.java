package com.example.demo.Controllers;

import com.example.demo.DAO.ProductsDAO;
import com.example.demo.DAO.ShopDAO;
import com.example.demo.Models.Products;
import com.example.demo.Models.Shops;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
public class MainController {
    @Autowired
    private ProductsDAO productsDAO;
    @Autowired
    private ShopDAO shopDAO;

    @GetMapping ("/")
    public  List<Shops> home (){
        return shopDAO.findAll();
    }
    @PostMapping("/saveShop")
    public String saveShop (@RequestBody String ShopName){
        Shops shop = new Shops();
        shop.setShopName(ShopName);
        shopDAO.save(shop);
        return "Shop saved";
    }
    @PostMapping ("/saveProduct")
    public String saveProduct (@RequestBody String params){
        //Сплітую отримані дані, щоб дістати назву продукту і назву магазину
        String[] split = params.split("&");
        String[] name = split[0].split("=");
        String[] id = split[1].split("=");
        //По назві магазину знаходжу ID цього мазину
        List<Shops> all = shopDAO.findAll();
        int shopId = 0;
        for (Shops shops : all) {
            //Назву магазину яка прийшла порівнюю з тими що є, при true записую ID
            if(id[1].equals(shops.getShopName())){
                shopId = shops.getShopId();
            }
        }
        Shops shops = shopDAO.findById(shopId).get();
        //Створюю продукт по назві
        Products products = new Products();
        products.setProductName(name[1]);
        products.setShops(shops);
        //Зберігаю продукт в знайдений магазин
        shops.getProducts().add(products);
        shopDAO.save(shops);
        return "Product saved";
    }
}
