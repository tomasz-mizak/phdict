package online.mizak.phdict.dictionary;

import online.mizak.phdict.dictionary.dto.CreateDictionaryProduct;
import online.mizak.phdict.dictionary.exception.NotAcceptableException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dictionary")
class DictionaryController {

    @Value("${server.http.batch-size:100}")
    int httpBatchSize = 100;

    private final DictionaryFacade dictionaryFacade;

    DictionaryController(DictionaryFacade dictionaryFacade) {
        this.dictionaryFacade = dictionaryFacade;
    }

    // # - - - Product - - - # //

    @GetMapping("/product/all")
    ResponseEntity<?> getAllDictionaryProducts(@RequestParam(required = false) String issuer) {
        return ResponseEntity.ok(issuer == null || issuer.isBlank()
                ? dictionaryFacade.showAllProducts() : dictionaryFacade.showAllProducts(issuer));
    }

    @GetMapping("/product/{eanCode}")
    ResponseEntity<?> getDictionaryProductByEanCode(@PathVariable String eanCode) {
        return ResponseEntity.ok(dictionaryFacade.showDictionaryProductByEanCode(eanCode));
    }

    @GetMapping("/product/all/count")
    ResponseEntity<Long> countAllProducts() {
        return ResponseEntity.ok(dictionaryFacade.showProductsCount());
    }

    @PostMapping("/product")
    ResponseEntity<?> createDictionaryProduct(@RequestBody CreateDictionaryProduct createDictionaryProduct, @RequestParam String issuer) {
        dictionaryFacade.createDictionaryProduct(createDictionaryProduct, issuer);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/product/batch")
    ResponseEntity<?> createDictionaryProducts(
            @RequestBody List<CreateDictionaryProduct> products,
            @RequestParam(required = false, defaultValue = "false") Boolean updateDuplicates,
            @RequestParam String issuer
    ) {
        if (products.size() > httpBatchSize)
            throw new NotAcceptableException("Max batch size is ", ErrorCode.INVALID_BATCH_SIZE);
        return ResponseEntity.ok(dictionaryFacade.createDictionaryProducts(products, updateDuplicates, issuer));
    }

    // # - - - Import Report - - - #

    @GetMapping("/import-report/all")
    ResponseEntity<?> showAllImportReports() {
        return ResponseEntity.ok(dictionaryFacade.showAllImportReports());
    }

}
