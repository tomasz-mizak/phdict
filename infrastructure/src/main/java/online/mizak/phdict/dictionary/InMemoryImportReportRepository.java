package online.mizak.phdict.dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

class InMemoryImportReportRepository implements ImportReportRepository {

    private final ConcurrentHashMap<Long, ImportReport> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @Override
    public ImportReport save(ImportReport importReport) {
        var id = importReport.isPersisted() ? importReport.getId() : idGenerator.incrementAndGet();
        ImportReport persisted = new ImportReport(id, importReport.getType(), importReport.getCreatedAt(), importReport.getDetails());
        store.put(persisted.getId(), persisted);
        return persisted;
    }

    @Override
    public List<ImportReport> findAll() {
        return new ArrayList<>(store.values());
    }

}
