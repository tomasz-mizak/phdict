package online.mizak.phdict.suppliers;

import java.time.LocalDateTime;

class Supply {

    private final Long id;
    private final String source;
    private final LocalDateTime date;

    Supply(Long id, String source, LocalDateTime date) {
        this.id = id;
        this.source = source;
        this.date = date;
    }

}
