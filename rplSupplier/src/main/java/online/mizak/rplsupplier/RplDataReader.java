package online.mizak.rplsupplier;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import pl.gov.ezdrowie.rejestry.rpl.eksport_danych_v5_0.ProduktyLecznicze;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Slf4j
abstract class RplDataReader {

    static List<CreateDictionaryProduct> read(File file) {

        List<CreateDictionaryProduct> createDictionaryProducts = new ArrayList<>();

        try {
            JAXBContext context = JAXBContext.newInstance(ProduktyLecznicze.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ProduktyLecznicze products = (ProduktyLecznicze) unmarshaller.unmarshal(file);

            products.getProduktLeczniczy().forEach(product -> {
                var tradeName = product.getNazwaProduktu();
                for (ProduktyLecznicze.ProduktLeczniczy.Opakowania.Opakowanie opakowanie : product.getOpakowania().getOpakowanie()) {
                    var eanCode = opakowanie.getKodGTIN();

                    var isNotDeleted = opakowanie.getSkasowane().equals("NIE");
                    var isEanValid = eanCode != null && !eanCode.isEmpty();

                    if (isNotDeleted && isEanValid) {
                        createDictionaryProducts.add(new CreateDictionaryProduct(eanCode, tradeName));
                    }
                }
            });

        } catch (JAXBException e) {
            log.error("An exception occurred while reading file.", e);
        }

        return createDictionaryProducts;
    }

}
