package RipplingExpensePolicyRuleEngine.rules.impl;

import RipplingExpensePolicyRuleEngine.models.Expense;
import RipplingExpensePolicyRuleEngine.rules.ExpenseRule;
import RipplingExpensePolicyRuleEngine.rules.Violation;

import java.util.Optional;

// this we have created after seeing
// No airfare expense are allowed & No entertainment expense are allowed.
public class DisallowRule implements ExpenseRule {

    @Override
    public Optional<Violation> check(Expense e){
        if(e.getExpenseType().name().equals("AIRFARE")){
            return null;
        }
        return null;
    }
}
