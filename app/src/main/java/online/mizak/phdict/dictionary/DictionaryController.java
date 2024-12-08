package online.mizak.phdict.dictionary;

import online.mizak.phdict.dictionary.dto.CreateDictionaryProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dictionary")
class DictionaryController {

    private final DictionaryFacade dictionaryFacade;

    DictionaryController(DictionaryFacade dictionaryFacade) {
        this.dictionaryFacade = dictionaryFacade;
    }

    @PostMapping("/product")
    ResponseEntity<?> createDictionaryProduct(@RequestBody CreateDictionaryProduct createDictionaryProduct) {
        dictionaryFacade.createDictionaryProduct(createDictionaryProduct.eanCode(), createDictionaryProduct.tradeName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/product/all")
    ResponseEntity<?> getAllDictionaryProducts() {
        return ResponseEntity.ok(dictionaryFacade.showAllProducts());
    }

    @GetMapping("/product/{eanCode}")
    ResponseEntity<?> getDictionaryProductByEanCode(@PathVariable String eanCode) {
        return ResponseEntity.ok(dictionaryFacade.showDictionaryProductByEanCode(eanCode));
    }

    @GetMapping("/product/all/count")
    ResponseEntity<Long> countAllProducts() {
        return ResponseEntity.ok(dictionaryFacade.showProductsCount());
    }

}
