//1

package RipplingExpensePolicyRuleEngine.rules;

import RipplingExpensePolicyRuleEngine.models.Expense;

import java.util.Optional;

public interface ExpenseRule {

    Optional<Violation> check(Expense e);

    // Optional because some time we will have the violation and some time we will not have the violation. So we can return an empty optional in case of no violation.
}
