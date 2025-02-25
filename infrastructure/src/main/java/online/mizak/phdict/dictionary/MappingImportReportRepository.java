package online.mizak.phdict.dictionary;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
class MappingImportReportRepository implements ImportReportRepository {

    private final JpaImportReportRepository repository;

    @Override
    public ImportReport save(ImportReport importReport) {
        return repository.save(JpaImportReport.toJpaEntity(importReport)).fromJpaEntity();
    }

    @Override
    public List<ImportReport> findAll() {
        return repository.findAll().stream().map(JpaImportReport::fromJpaEntity).toList();
    }

}
