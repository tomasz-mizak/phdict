package online.mizak.phdict.dictionary.dto;

import java.util.List;

public record BulkImportReport(
        Integer total,
        Integer successful,
        Integer failed,
        List<RejectedProduct> rejectedObjects,
        List<DuplicatePair> duplicatePairs
) {
}
