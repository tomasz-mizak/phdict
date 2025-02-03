package online.mizak.phdict.dictionary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DictionaryConfig {

    @Bean
    DictionaryFacade dictionaryFacade() {
        return new DictionaryFacade(new InMemoryProductRepository(), new InMemoryImportReportRepository());
    }

}
