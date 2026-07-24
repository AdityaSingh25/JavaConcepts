//2

package RipplingExpensePolicyRuleEngine.rules;

import lombok.Data;

@Data
public class Violation {
    private final String message;

    public Violation(String message) {
        this.message = message;
    }

    // same thing a constructor would do but this is more readable
    public static Violation of(String message){
        return new Violation(message);
    }
}
