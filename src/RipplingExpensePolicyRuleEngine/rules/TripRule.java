package RipplingExpensePolicyRuleEngine.rules;

import RipplingExpensePolicyRuleEngine.models.Expense;

import java.util.List;
import java.util.Optional;

public interface TripRule {
    Optional<List<Violation>> check(List<Expense> expenses);
}
