package online.mizak.rplsupplier.dto;

import java.util.List;

public record BulkImportReport(
        Integer total,
        Integer successful,
        Integer failed,
        List<RejectedProduct> rejectedObjects,
        List<DuplicatePair> duplicatePairs
) {
}
