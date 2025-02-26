package online.mizak.rplsupplier;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import online.mizak.rplsupplier.dto.CreateDictionaryProduct;
import pl.gov.ezdrowie.rejestry.rpl.eksport_danych_v5_0.ProduktyLecznicze;

import java.io.File;
import java.time.LocalDate;
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

            for (ProduktyLecznicze.ProduktLeczniczy product : products.getProduktLeczniczy()) {

                if (!product.getRodzajPreparatu().equals("ludzki")) continue;

                var expiryDate = product.getWaznoscPozwolenia();
                if (expiryDate != null && !expiryDate.isBlank()) {
                    try {
                        var parsedExpiryDate = LocalDate.parse(expiryDate);
                        var skip = LocalDate.now().isAfter(parsedExpiryDate);
                        if (skip) continue;
                    } catch (Exception ignored) {
                        // Because PRL provider uses phrases like 'Bezterminowy', and in the future can use other phrases.
                    }
                }
                ;

                var tradeName = product.getNazwaProduktu();
                if (tradeName == null || tradeName.isEmpty()) continue;

                for (ProduktyLecznicze.ProduktLeczniczy.Opakowania.Opakowanie opakowanie : product.getOpakowania().getOpakowanie()) {
                    var eanCode = opakowanie.getKodGTIN();

                    var isNotDeleted = opakowanie.getSkasowane().equals("NIE");
                    var isEanValid = eanCode != null && !eanCode.isEmpty();

                    var flyerURL = (product.getUlotka() == null || product.getUlotka().isBlank()) ? product.getUlotkaImportRownolegly() : product.getUlotka();
                    var characteristicsURL = product.getCharakterystyka();

                    if (isNotDeleted && isEanValid) {
                        createDictionaryProducts.add(new CreateDictionaryProduct(eanCode, tradeName, flyerURL, characteristicsURL));
                    }
                }
            }


        } catch (JAXBException e) {
            log.error("An exception occurred while reading file.", e);
        }

        return createDictionaryProducts;
    }

}
