package com.example.authtest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "productsBlocking", url = "http://localhost:9090")
public interface AuthClient {

//    @GetMapping(value = "/api/usuario", produces = "application/json")
//    List<Product> getProductsBlocking(URI baseUrl);
}
