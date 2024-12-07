package online.mizak.phdict.dictionary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class DictionaryConfig {

    @Bean
    DictionaryFacade dictionaryFacade() {
        var repository = new InMemoryProductRepository();
        return new DictionaryFacade(repository, new InMemoryEanCodeUniquenessPolicy(repository));
    }

}
