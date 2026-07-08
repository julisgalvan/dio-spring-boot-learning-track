package dio.budgeting.application.dto;

import dio.budgeting.domain.Category;

public class TransactionRequest {
    private String description;
    private long amount;
    private Category category;

    public TransactionRequest() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
