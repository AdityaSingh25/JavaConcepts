# Expense Rule Policy Engine for the Employee to clain the expense within the rules and finance team to approve the expense claim based on the rules defined in the system.


### Managers can define policies(rules) to control expenses, snsuring employess do not misuse the system.

# Goals will be : 


1. Evaluates individual expense against a set of rules.
2. Evaluate aggregated trip level expense against a set of rules.
3. Flags any violation.

# Inout Format

1. A list of Expense where each expense is represented as a dictionary/map of string keys and values.

Ex : 
Expense_id
trip_id
amount
expense_type: ("resturant", "hotel", "transportation", "miscellaneous")
vendor_type

2. A list of rules to evaluate. rules can be applied at :
expense level
trip level



## Basic rules

1. No resturant expense can be exceed $75.
2. No airfare expense are allowed.

----- if you are able to code 1,2 then go for 3,4-------
3. No entertainment expense are allowed.
4. No single expense can exceed $250.

## Extended rules

Later, add support for trip-level rules

5. A trip cannot exceed $2000 in total expense.
6. Total meal (resturant) expense per trip cannot exceed $1000.



## Output format

1. For each expense return whether it is approved or rejected woith reasons for rejection.
2. For each trip return whether it is OK or has violations with reasons.


example input :

{
"expense_id": "001",
"trip_id": "T001",
"amount": 80,
"expense_type": "resturant",
"vendor_type": "vendor1"
},
{
"expense_id": "002",
"trip_id": "T001",
"amount": 150,
"expense_type": "hotel",
"vendor_type": "vendor2"
},
{
"expense_id": "003",
"trip_id": "T001",
"amount": 50,
"expense_type": "resturant",
"vendor_type": "vendor3"
}




