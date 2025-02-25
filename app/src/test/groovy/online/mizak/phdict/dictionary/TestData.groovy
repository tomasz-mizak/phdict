package online.mizak.phdict.dictionary


import online.mizak.phdict.dictionary.dto.CreateDictionaryProduct

trait TestData {

    String issuer = "test"
    String eanCode = "123456"
    String tradeName = "Test Product"
    CreateDictionaryProduct createProductRequest = new CreateDictionaryProduct(eanCode, tradeName, null)

    String anotherIssuer = "anotherTest"
    String anotherEanCode = "654321"
    String anotherTradeName = "Another Product"
    CreateDictionaryProduct anotherProductRequest = new CreateDictionaryProduct(anotherEanCode, anotherTradeName, null)

}