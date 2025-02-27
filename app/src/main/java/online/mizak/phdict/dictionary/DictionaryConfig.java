package online.mizak.phdict.dictionary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DictionaryConfig {

    static DictionaryFacade dictionaryFacade() {
        return new DictionaryFacade(new InMemoryProductRepository(), new InMemoryImportReportRepository());
    }

    @Bean
    DictionaryFacade dictionaryFacade(ProductRepository productRepository, ImportReportRepository importReportRepository) {
        return new DictionaryFacade(productRepository, importReportRepository);
    }

}
