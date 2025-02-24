package online.mizak.phdict.dictionary

import online.mizak.phdict.dictionary.exception.NotAcceptableException
import online.mizak.phdict.dictionary.exception.NotFoundException
import spock.lang.Specification

class DictionarySpec extends Specification {

    def dictionaryFacade = DictionaryConfig.dictionaryFacade()

    // TODO(fixme!)

//    def "Create dictionary product"() {
//        given: "Valid EAN code and trade name"
//        def eanCode = "1234567890123"
//        def tradeName = "Test Product"
//
//        when: "A new dictionary product is created"
//        dictionaryFacade.createDictionaryProduct(eanCode, tradeName)
//
//        then: "The product repository should contain the product"
//        productRepo.findAll().size() == 1
//        def product = productRepo.findAll().get(0)
//        product.eanCode == eanCode
//        product.tradeName == tradeName
//    }
//
//    def "Fail to create dictionary product with duplicate EAN code"() {
//        given: "An existing product in the repository"
//        def eanCode = "1234567890123"
//        productRepo.save(Product.of(eanCode, "Existing Product"))
//
//        when: "Attempting to create a product with the same EAN code"
//        dictionaryFacade.createDictionaryProduct(eanCode, "New Product")
//
//        then: "An exception is thrown"
//        thrown(NotAcceptableException.class)
//    }
//
//    def "Show product count"() {
//        given: "Two products in the repository"
//        productRepo.save(Product.of("1234567890123", "Product 1"))
//        productRepo.save(Product.of("9876543210987", "Product 2"))
//
//        when: "Getting the product count"
//        def count = dictionaryFacade.showProductsCount()
//
//        then: "The correct count is returned"
//        count == 2
//    }
//
//    def "Show dictionary product by EAN code"() {
//        given: "A product in the repository"
//        def eanCode = "1234567890123"
//        productRepo.save(Product.of(eanCode, "Test Product"))
//
//        when: "Retrieving the product by its EAN code"
//        def product = dictionaryFacade.showDictionaryProductByEanCode(eanCode)
//
//        then: "The correct product DTO is returned"
//        product.eanCode() == eanCode
//        product.tradeName() == "Test Product"
//    }
//
//    def "Fail to show dictionary product by non-existing EAN code"() {
//        given: "An empty product repository"
//        def eanCode = "1234567890123"
//
//        when: "Attempting to retrieve a product by a non-existing EAN code"
//        dictionaryFacade.showDictionaryProductByEanCode(eanCode)
//
//        then: "An exception is thrown"
//        thrown(NotFoundException.class)
//    }

}
