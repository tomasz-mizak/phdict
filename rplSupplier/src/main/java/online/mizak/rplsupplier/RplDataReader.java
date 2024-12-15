package online.mizak.rplsupplier;

import jakarta.annotation.PostConstruct;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import pl.gov.ezdrowie.rejestry.rpl.eksport_danych_v5_0.ProduktyLecznicze;

import java.io.IOException;

@Slf4j
class RplDataReader {

    private final ResourceLoader resourceLoader;

    public RplDataReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    void read() {
        Resource resource = resourceLoader.getResource("classpath:Rejestr_Produktow_Leczniczych_calosciowy_stan_na_dzien_20241212_5.0.0.xml");

        try {
            JAXBContext context = JAXBContext.newInstance(ProduktyLecznicze.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ProduktyLecznicze products = (ProduktyLecznicze) unmarshaller.unmarshal(resource.getFile());

            products.getProduktLeczniczy().forEach(product -> {
                var tradeName = product.getNazwaProduktu();
                for (ProduktyLecznicze.ProduktLeczniczy.Opakowania.Opakowanie opakowanie : product.getOpakowania().getOpakowanie()) {
                    log.info(opakowanie.getSkasowane());
                }
            });

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
