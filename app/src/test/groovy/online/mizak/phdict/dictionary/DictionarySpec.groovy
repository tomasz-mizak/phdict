package online.mizak.phdict.dictionary

import online.mizak.phdict.dictionary.exception.ConflictException
import online.mizak.phdict.dictionary.exception.NotFoundException
import spock.lang.Specification

class DictionarySpec extends Specification implements TestData {

    def dictionaryFacade = DictionaryConfig.dictionaryFacade()

    def "Should create product"() {
        when:
        dictionaryFacade.createDictionaryProduct(createProductRequest, issuer)

        then:
        dictionaryFacade.showAllProducts().size() == 1
    }

    def "Should throw ConflictException when creating duplicate product"() {
        given:
        dictionaryFacade.createDictionaryProduct(createProductRequest, issuer)

        when:
        dictionaryFacade.createDictionaryProduct(createProductRequest, issuer)

        then:
        def e = thrown(ConflictException.class)
        e.message.contains(createProductRequest.eanCode())
    }

    def "Should throw NotFoundException when product with given EAN does not exist"() {
        when:
        dictionaryFacade.showDictionaryProductByEanCode("nonExistingEAN")

        then:
        thrown(NotFoundException.class)
    }

    def "Should return products only for specified issuer"() {
        given:
        dictionaryFacade.createDictionaryProduct(createProductRequest, issuer)
        dictionaryFacade.createDictionaryProduct(anotherProductRequest, anotherIssuer)

        when:
        def productsForTestIssuer = dictionaryFacade.showAllProducts(issuer)
        def productsForAnotherIssuer = dictionaryFacade.showAllProducts(anotherIssuer)

        then:
        productsForTestIssuer.size() == 1
        productsForAnotherIssuer.size() == 1
    }

    def "Bulk import should correctly import products using extended TestData"() {
        given:
        def products = [createProductRequest, anotherProductRequest]

        when:
        def report = dictionaryFacade.createDictionaryProducts(products, issuer)

        then:
        report.total() == 2
        report.successful() == 2
        report.failed() == 0
    }



}
