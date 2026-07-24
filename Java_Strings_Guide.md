# Java Strings — From Basics to Advanced (Q&A)

---

## 🔷 PART 1: THE BASICS

---

**Q: What is a String in Java?**

A String is a sequence of characters. In Java, String is NOT a primitive type like `int` or `boolean` — it is a **class** (an object).

```java
String name = "Aditya";
```

Internally, Java stores a String as an array of characters:
`'A', 'd', 'i', 't', 'y', 'a'`

---

**Q: How many ways can you create a String?**

Two ways:

```java
// Way 1 — String Literal
String s1 = "Hello";

// Way 2 — Using new keyword
String s2 = new String("Hello");
```

These look the same but behave very differently in memory. This is one of the most important things to understand.

---

## 🔷 PART 2: MEMORY — WHERE DOES THE VALUE LIVE?

---

**Q: What are Stack, Heap, and String Pool?**

**Stack:**
- Stores local variables and references (pointers)
- Very fast, small memory
- Automatically cleared when a method finishes
- Stores the REFERENCE (address), NOT the actual String object

**Heap:**
- Stores all objects (including String objects)
- Larger memory
- Managed by Garbage Collector
- The actual String content lives here

**String Pool (also called Intern Pool):**
- A special area INSIDE the Heap
- Only stores String literals
- Java reuses Strings here to save memory
- If the same literal already exists, Java returns the same object

---

**Q: What happens in memory with String literal?**

```java
String s1 = "Hello";
String s2 = "Hello";
```

Memory picture:

```
STACK                   HEAP
------                  ----------------
s1  ──────────────────► [ "Hello" ]  ← String Pool
s2  ──────────────────►      ↑
                        (same object!)
```

- Java sees `"Hello"` → checks String Pool → not found → creates it
- `s1` on stack points to that pool object
- Java sees `"Hello"` again → checks String Pool → FOUND → reuses it
- `s2` also points to the SAME object

**Only one "Hello" object is created. Both references point to it.**

---

**Q: What happens in memory with `new String()`?**

```java
String s1 = "Hello";
String s3 = new String("Hello");
```

Memory picture:

```
STACK                   HEAP
------                  ----------------
s1  ──────────────────► [ "Hello" ]  ← String Pool
                        
s3  ──────────────────► [ "Hello" ]  ← Regular Heap (different object!)
```

- `new String()` always creates a **brand new object** in the regular heap
- It does NOT use the String Pool
- Even if "Hello" already exists in the pool, `new` ignores it

**Two separate "Hello" objects exist in memory now.**

---

**Q: Why does this matter?**

Because of how `==` works. (Explained in Part 3.)

---

**Q: Where was String Pool before Java 7?**

Before Java 7: String Pool was in **PermGen** (Permanent Generation) — a fixed-size memory area outside heap.
This caused `OutOfMemoryError: PermGen space` if too many strings were interned.

From Java 7 onwards: String Pool moved to the **Heap** — so it can grow dynamically and be garbage collected.

---

## 🔷 PART 3: IMMUTABILITY

---

**Q: What does "String is immutable" mean?**

Once a String object is created, **its value can never be changed**.

```java
String s = "Hello";
s = s + " World";
```

You might think `s` is being modified. It is NOT. What actually happens:

```
Step 1: "Hello" created in pool
Step 2: " World" created in pool
Step 3: "Hello World" is a NEW object created in heap
Step 4: s now points to "Hello World"
Step 5: "Hello" still exists in pool (unchanged)
```

The original "Hello" was never touched. A new object was created.

---

**Q: Why is String made immutable in Java?**

Three reasons:

1. **String Pool works because of immutability.**
   If Strings could change, two references pointing to the same pool object would be dangerous — changing one would change the other.

2. **Security.**
   Strings are used for passwords, file paths, network URLs. If they could be changed after creation, it would be a security risk.

3. **Thread Safety.**
   Immutable objects are automatically safe to share between threads — no synchronization needed.

---

**Q: If String is immutable, what is the `replace()` method doing?**

It returns a NEW String — it does not modify the original.

```java
String s = "Hello";
String s2 = s.replace("H", "J");

System.out.println(s);   // Hello  ← unchanged
System.out.println(s2);  // Jello  ← new object
```

Every String method that "modifies" a string actually returns a new String.

---

## 🔷 PART 4: == vs .equals()

---

**Q: What is the difference between == and .equals() for Strings?**

`==` compares **references** (memory addresses — are they the same object?)
`.equals()` compares **values** (do they have the same content?)

```java
String s1 = "Hello";
String s2 = "Hello";
String s3 = new String("Hello");

System.out.println(s1 == s2);        // true  → same pool object
System.out.println(s1 == s3);        // false → different objects
System.out.println(s1.equals(s3));   // true  → same content
```

**Rule: Always use `.equals()` to compare String values. Never use `==`.**

---

**Q: What is `.equalsIgnoreCase()`?**

Same as `.equals()` but ignores uppercase/lowercase difference.

```java
"hello".equalsIgnoreCase("HELLO")  // true
"hello".equals("HELLO")            // false
```

---

## 🔷 PART 5: IMPORTANT STRING METHODS

---

**Q: What are the most commonly used String methods?**

```java
String s = "  Hello World  ";

s.length()              // 15 — number of characters
s.trim()                // "Hello World" — removes leading/trailing spaces
s.toLowerCase()         // "  hello world  "
s.toUpperCase()         // "  HELLO WORLD  "
s.contains("World")     // true
s.startsWith("  Hello") // true
s.endsWith("  ")        // true
s.replace("World","Java") // "  Hello Java  "
s.isEmpty()             // false
s.isBlank()             // false (Java 11+, also checks whitespace-only)

String s2 = "Hello,World,Java";
s2.split(",")           // ["Hello", "World", "Java"]
s2.indexOf("World")     // 6 — position of first character
s2.substring(6)         // "World,Java"
s2.substring(6, 11)     // "World"
s2.charAt(0)            // 'H'
s2.toCharArray()        // ['H','e','l','l','o',',',...]
```

---

**Q: How do you convert other types to String?**

```java
int num = 42;
String s1 = String.valueOf(num);   // "42"
String s2 = Integer.toString(num); // "42"
String s3 = "" + num;              // "42" (works but bad practice)

// String to int
int n = Integer.parseInt("42");    // 42
```

---

## 🔷 PART 6: STRING CONCATENATION AND PERFORMANCE

---

**Q: What is the problem with + for String concatenation in a loop?**

```java
String result = "";
for (int i = 0; i < 1000; i++) {
    result = result + i;   // BAD!
}
```

Each `+` creates a NEW String object. After 1000 iterations, you have created 1000 intermediate String objects in heap — most of them immediately become garbage.

This is extremely slow and wastes memory.

---

**Q: What should you use instead? StringBuilder.**

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i);   // GOOD — modifies in place, no new objects
}
String result = sb.toString();
```

StringBuilder is mutable — it changes the same object internally.

---

## 🔷 PART 7: String vs StringBuilder vs StringBuffer

---

**Q: What is the difference between String, StringBuilder, and StringBuffer?**

String:
- Immutable — cannot be changed
- Thread safe (because immutable)
- Slow for repeated modifications
- Use when value does not change often

StringBuilder:
- Mutable — can be changed in place
- NOT thread safe
- Fast — best for single-threaded use
- Use when you are building a String with many concatenations

StringBuffer:
- Mutable — can be changed in place
- Thread safe (all methods are synchronized)
- Slower than StringBuilder because of synchronization overhead
- Use when multiple threads are modifying the same string

```
In 99% of cases: use StringBuilder
In multi-threaded case: use StringBuffer
For a fixed value: use String
```

---

**Q: How does StringBuilder work internally?**

StringBuilder maintains a **char array** internally with some extra capacity.

```java
StringBuilder sb = new StringBuilder(); // default capacity: 16 chars
sb.append("Hello");   // uses first 5 slots of the array
sb.append(" World");  // uses next 6 slots
```

When the array is full, it creates a bigger array (usually double the size) and copies content over.
This is much cheaper than creating a new String object every time.

---

## 🔷 PART 8: STRING INTERNING

---

**Q: What is String.intern()?**

`intern()` tells Java: "Take this String object and put it in the String Pool. Return the pool version."

```java
String s1 = new String("Hello");  // in heap, NOT in pool
String s2 = s1.intern();          // now in pool

String s3 = "Hello";              // from pool

System.out.println(s2 == s3);     // true — both from pool now
System.out.println(s1 == s3);     // false — s1 still in regular heap
```

---

**Q: When would you use intern()?**

Mostly in performance-critical code where the same String value appears millions of times and you want to save memory by reusing pool objects instead of keeping thousands of duplicate heap objects.

In normal application code, you rarely need `intern()` manually.

---

## 🔷 PART 9: ADVANCED — JAVA 9+ COMPACT STRINGS

---

**Q: What changed about String internals in Java 9?**

Before Java 9: String stored characters as a `char[]` array (each char = 2 bytes, UTF-16)

Java 9+: String now uses a `byte[]` array with an encoding flag.
- If all characters are Latin-1 (basic English), it uses 1 byte per character
- If characters need UTF-16, it uses 2 bytes

This is called **Compact Strings** and reduces memory usage by up to 50% for typical English text.

You do not need to do anything — this is automatic.

---

## 🔷 PART 10: COMMON INTERVIEW GOTCHAS

---

**Q: What does this print?**

```java
String s1 = "Hello";
String s2 = "Hel" + "lo";
System.out.println(s1 == s2);
```

Answer: **true**

Why? Because `"Hel" + "lo"` is a compile-time constant. Java's compiler evaluates it at compile time to `"Hello"` and uses the pool. Both s1 and s2 point to the same pool object.

---

**Q: What does this print?**

```java
String a = "Hel";
String s1 = "Hello";
String s2 = a + "lo";
System.out.println(s1 == s2);
```

Answer: **false**

Why? `a` is a variable, not a compile-time constant. The `+` happens at runtime and creates a new object in heap. `s2` is not in the pool.

---

**Q: Can you put a String in a switch statement?**

Yes, since Java 7.

```java
String day = "Monday";
switch (day) {
    case "Monday" -> System.out.println("Start of week");
    case "Friday" -> System.out.println("End of week");
}
```

Internally Java uses `.equals()` for comparison.

---

**Q: Is String thread safe?**

Yes. Because String is immutable, it can be safely shared between multiple threads without any synchronization. No thread can modify a String — they can only get a new String back.

---

**Q: What is the difference between null and empty string?**

```java
String s1 = null;    // no object at all, reference points to nothing
String s2 = "";      // a real String object, but with zero characters

s1.length()          // NullPointerException!
s2.length()          // 0 — works fine

s1 == null           // true
s2 == null           // false
s2.isEmpty()         // true
```

Always check for null before calling methods on a String.

---

## 🔷 QUICK CHEAT SHEET

---

```
Literal "Hello"        → goes to String Pool (heap)
new String("Hello")    → goes to regular Heap (NOT pool)
s1 == s2               → compares memory address (reference)
s1.equals(s2)          → compares actual content ← always use this
String                 → immutable, thread safe, slow to modify
StringBuilder          → mutable, NOT thread safe, fast
StringBuffer           → mutable, thread safe, slower than StringBuilder
intern()               → moves string to pool, returns pool reference
+ in a loop            → BAD (creates many objects)
StringBuilder.append() → GOOD (modifies in place)
Java 7+                → String Pool is in Heap
Java 9+                → Compact Strings (byte[] instead of char[])
```

---

## 🔷 MEMORY SUMMARY DIAGRAM

```
STACK                HEAP
------               ----------------------------------
                     |  String Pool                   |
s1 ─────────────────►|  [ "Hello" ] ◄──── s2          |
                     |  [ "Java"  ]                   |
                     |                                |
s3 ─────────────────►|  [ "Hello" ] (regular heap)    |
                     |  (created with new String())   |
                     ----------------------------------

s1 == s2  → true   (same pool object)
s1 == s3  → false  (different objects)
s1.equals(s3) → true  (same content)
```
