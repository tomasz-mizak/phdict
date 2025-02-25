package online.mizak.phdict.dictionary;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "import_reports")
@AllArgsConstructor
@NoArgsConstructor
@Data
class JpaImportReport {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String type;

    private LocalDateTime createdAt;

    @Type(JsonBinaryType.class)
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "details", columnDefinition = "jsonb")
    private Object details;

    static JpaImportReport toJpaEntity(ImportReport importReport) {
        return new JpaImportReport(
                importReport.getId(),
                importReport.getType().name(),
                importReport.getCreatedAt(),
                importReport.getDetails()
        );
    }

    ImportReport fromJpaEntity() {
        return new ImportReport(
                id,
                ImportReport.Type.valueOf(type),
                createdAt,
                details
        );
    }

}
