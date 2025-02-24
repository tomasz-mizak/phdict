package online.mizak.phdict.dictionary;

import java.time.LocalDateTime;

class ImportReport {

    enum Type {
        SINGLE,
        BULK
    }

    private final Long id;
    private final Type type;
    private final LocalDateTime createdAt;
    private final Object details;

    ImportReport(Long id, Type type, LocalDateTime createdAt, Object details) {
        this.id = id;
        this.type = type;
        this.createdAt = createdAt;
        this.details = details;
    }

    static ImportReport bulkReport(Object details) {
        return new ImportReport(null, Type.BULK, LocalDateTime.now(), details);
    }

    boolean isPersisted() { return this.id != null; }

    Long getId() { return this.id; }

    Type getType() { return this.type; }

    LocalDateTime getCreatedAt() { return this.createdAt; }

    Object getDetails() { return this.details; }

}
