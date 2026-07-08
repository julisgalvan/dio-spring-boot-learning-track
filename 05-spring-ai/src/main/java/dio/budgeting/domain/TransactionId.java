package dio.budgeting.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record TransactionId(@JsonProperty("uuid") UUID uuid) {
    public TransactionId() {
        this(UUID.randomUUID());
    }

    @JsonCreator
    public static TransactionId fromJson(@JsonProperty("uuid") UUID uuid) {
        return new TransactionId(uuid);
    }
}