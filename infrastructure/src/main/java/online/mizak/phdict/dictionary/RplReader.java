package online.mizak.phdict.dictionary;

import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import pl.gov.ezdrowie.rejestry.rpl.eksport_danych_v5_0.ProduktyLecznicze;

import java.io.IOException;

@Component
class RplReader {

    private final ResourceLoader resourceLoader;

    public RplReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @PostConstruct
    void read() {
        Resource resource = resourceLoader.getResource("classpath:Rejestr_Produktow_Leczniczych_calosciowy_stan_na_dzien_20241212_5.0.0.xml");

        try {
            JAXBContext context = JAXBContext.newInstance(ProduktyLecznicze.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ProduktyLecznicze products = (ProduktyLecznicze) unmarshaller.unmarshal(resource.getFile());

            products.getProduktLeczniczy().forEach(product -> {
                var tradeName = product.getNazwaProduktu();
                product.getOpakowania().getOpakowanie().forEach(pkg -> {
//                    pkg.getSkasowane()
                    System.out.printf("pkg.getKodGTIN() ::  %s%n", pkg.getKodGTIN());
                });
            });

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
