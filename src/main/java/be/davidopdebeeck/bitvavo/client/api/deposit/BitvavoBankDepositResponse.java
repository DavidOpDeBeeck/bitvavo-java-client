package be.davidopdebeeck.bitvavo.client.api.deposit;

import static java.util.Objects.requireNonNull;

public class BitvavoBankDepositResponse {

    private String iban;
    private String bic;
    private String description;

    private BitvavoBankDepositResponse() {
    }

    private BitvavoBankDepositResponse(Builder builder) {
        iban = requireNonNull(builder.iban);
        bic = requireNonNull(builder.bic);
        description = requireNonNull(builder.description);
    }

    public String getIban() {
        return iban;
    }

    public String getBic() {
        return bic;
    }

    public String getDescription() {
        return description;
    }

    public static final class Builder {

        private String iban;
        private String bic;
        private String description;

        public Builder withIban(String iban) {
            this.iban = iban;
            return this;
        }

        public Builder withBic(String bic) {
            this.bic = bic;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public BitvavoBankDepositResponse build() {
            return new BitvavoBankDepositResponse(this);
        }
    }
}
