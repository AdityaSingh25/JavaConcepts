package RipplingExpensePolicyRuleEngine.models;

import RipplingExpensePolicyRuleEngine.enums.ExpenseType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class Expense {

    private String id;

    private String tripId;

    private Double amount;

    private ExpenseType expenseType;

    // ideally made using a builder
    public Expense(String id, String tripId, Double amount, ExpenseType expenseType) {
        this.id = id;
        this.tripId = tripId;
        this.amount = amount;
        this.expenseType = expenseType;
    }

//    public String getId() { return id; }
//    public String getTripId() { return tripId; }
//    public Double getAmount() { return amount; }
//    public ExpenseType getExpenseType() { return expenseType; }
}
