package online.mizak.rplsupplier;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "phdictApiClient", url = "localhost:8080/phdict/api/v1")
interface PhDictApi {

    @PostMapping("/dictionary/product")
    ResponseEntity<Object> createDictionaryProduct(@RequestBody CreateDictionaryProduct createDictionaryProduct);

}
