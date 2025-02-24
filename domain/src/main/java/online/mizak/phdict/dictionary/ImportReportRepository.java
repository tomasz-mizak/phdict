package online.mizak.phdict.dictionary;

import java.util.List;

interface ImportReportRepository {

    ImportReport save(ImportReport importReport);

    List<ImportReport> findAll();

}
