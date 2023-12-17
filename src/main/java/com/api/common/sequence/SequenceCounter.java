package com.api.common.sequence;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sequence_counters")
public class SequenceCounter {
    @Id
    private String id;
    private Long seq;

    public SequenceCounter() {}

    public SequenceCounter(String id, Long seq) {
        this.id = id;
        this.seq = seq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
