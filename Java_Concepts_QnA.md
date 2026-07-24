# ☕ Java Concepts — Quick Revision (Q&A)

---

## 🔷 JVM, JRE, JDK

---

**Q: What is the JVM?**

The JVM (Java Virtual Machine) is the engine that **runs `.class` files**.

When you write Java code, it compiles into `.class` files (called bytecode).
The JVM reads that bytecode and executes it on your machine.

```
.java  →  (javac compiles)  →  .class  →  (JVM runs it)  →  Output
```

> The JVM is what makes Java "write once, run anywhere" — the same `.class` file runs on Windows, Mac, Linux — as long as JVM is installed.

---

**Q: What is the JRE?**

JRE (Java Runtime Environment) = **JVM + Standard Library**

The JVM alone can execute bytecode, but it has nothing to work with.
The Standard Library gives it `String`, `ArrayList`, `System`, `Math`, and ~4000+ pre-built classes.

```
Without JRE:  JVM has no idea what "String" or "System.out.println" means
With JRE:     JVM has everything it needs to run your program
```

> JRE is for **running** Java programs. End users need JRE, not JDK.

---

**Q: What is the JDK?**

JDK (Java Development Kit) = **JRE + Compiler (javac) + Dev Tools**

It has everything in JRE, plus `javac` — the compiler that turns your `.java` files into `.class` files.

> JDK is for **developers**. You need JDK to build Java programs.

---

**Q: What is the "fuel" in JRE?**

The Standard Library — pre-written classes that ship with Java:

| Package | What it gives you |
|---|---|
| `java.lang` | `String`, `Math`, `System`, `Object` |
| `java.util` | `ArrayList`, `HashMap`, `List` |
| `java.io` | Reading/writing files |
| `java.net` | HTTP requests, networking |
| `java.time` | Dates and times |
| `java.sql` | Database connections |

Without these, even `System.out.println("Hello")` wouldn't work!

---

**Q: Simple summary — JVM vs JRE vs JDK?**

```
┌──────────────────────────────────────┐
│                JDK                   │
│  ┌────────────────────────────────┐  │
│  │              JRE               │  │
│  │  ┌──────────────────────────┐  │  │
│  │  │          JVM             │  │  │
│  │  │   (runs .class files)    │  │  │
│  │  └──────────────────────────┘  │  │
│  │  + Standard Library            │  │
│  └────────────────────────────────┘  │
│  + javac (compiler)                  │
│  + debugger, profiler, etc.          │
└──────────────────────────────────────┘
```

| | JVM | JRE | JDK |
|---|---|---|---|
| Runs `.class` files | ✅ | ✅ | ✅ |
| Standard Library | ❌ | ✅ | ✅ |
| `javac` compiler | ❌ | ❌ | ✅ |
| Who needs it | — | End users | Developers |

> **One liner:** JVM = engine. JRE = engine + fuel. JDK = engine + fuel + factory.

---

## 🔷 Build

---

**Q: What is a "Build"?**

Build = **running `javac` on all your `.java` files to produce `.class` files**.

```
Build → javac Expense.java       → Expense.class
        javac DisallowRule.java  → DisallowRule.class
        ... for every .java file in your project
```

IntelliJ's **Build → Rebuild Project** does exactly this.

---

**Q: What is the difference between Build and Run?**

| | Build | Run |
|---|---|---|
| What happens | `.java` → `.class` | JVM executes `.class` |
| Who does it | `javac` (JDK) | JVM (JRE) |
| Output | `.class` files | Program output |

---

## 🔷 Maven

---

**Q: If JDK has everything, why do we need Maven?**

JDK only has Java's **own** standard library. It does NOT have:
- Spring Boot
- Hibernate
- Lombok
- Jackson (JSON)
- ... any third-party library

If you want those, you have to download them. That's Maven's job.

---

**Q: What did people do before Maven?**

Manually:
1. Go to each library's website → download `.jar` file
2. That library needs 5 other jars → download those too
3. Those 5 need 20 more → download all of them
4. Add all 50+ jars to the project manually
5. Next version releases → repeat everything 😭

---

**Q: What does Maven do?**

You just tell Maven what you need in `pom.xml`:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <version>3.2.0</version>
</dependency>
```

Maven then:
- Downloads the jar automatically
- Finds what THAT jar needs → downloads those too
- Manages all versions so nothing conflicts
- Puts everything on your classpath

---

**Q: What are Maven's jobs?**

| Job | Description |
|---|---|
| Dependency Manager | Downloads jars from Maven Central automatically |
| Transitive Dependencies | If A needs B and C, Maven gets B and C too |
| Build Orchestrator | Tells JDK to compile, in the right order |
| Version Manager | Keeps all library versions compatible |
| Packager | Zips `.class` files into a `.jar` for deployment |

---

**Q: Does Maven compile the code itself?**

**No.** Maven cannot compile Java code. It **delegates to `javac` (JDK)**.

```
You run: mvn build

Maven → "Hey javac, compile these .java files"  →  javac does it
Maven → "Hey JVM, run the tests"               →  JVM does it
Maven → zips .class files into .jar            →  Maven does this part
```

> Maven is the **boss/project manager**. JDK is the **worker**. Maven tells JDK what to do — JDK does the actual compiling.

---

**Q: Why does Spring Boot specifically need Maven?**

Spring Boot is not one library — it's hundreds working together:

```
spring-boot-starter-web pulls in:
  ├── spring-core
  ├── spring-webmvc
  ├── tomcat-embed  (the web server!)
  ├── jackson-core
  ├── jackson-databind
  └── ... 30+ more jars
```

Without Maven you'd manage all of these manually. With Maven, it's 4 lines of XML.

> Think of Maven like **npm** (JavaScript) or **pip** (Python) — every language has a package manager. Maven is Java's.

---

## 🔷 Lombok

---

**Q: What is Lombok?**

Lombok is a library that **auto-generates boilerplate code** like getters, setters, constructors — so you don't have to write them.

```java
// Without Lombok — you write all this manually:
public String getId() { return id; }
public void setId(String id) { this.id = id; }
public String getName() { return name; }
public void setName(String name) { this.name = name; }
// ... for every field

// With Lombok — just one annotation:
@Data
public class Expense { ... }  // getters + setters generated automatically ✅
```

---

**Q: When does Lombok run?**

Lombok runs **during the Build step** (compilation), not at runtime.

```
Build starts → javac runs → Lombok hooks in → reads @Data
             → generates getter/setter code on the fly
             → javac compiles that generated code too
             → .class file comes out with getters already baked in
```

That's why Lombok needs to be **compatible with your JDK version** — it runs inside `javac`.

---

**Q: What is the difference between the Lombok Plugin and the Lombok JAR?**

| | Lombok IntelliJ Plugin | Lombok JAR |
|---|---|---|
| What it is | IDE extension | Library in your project |
| What it does | IDE understands Lombok (no red lines) | Actually generates code at compile time |
| Where it lives | IntelliJ's plugins folder | Your project's dependencies |
| Both needed? | ✅ For a good dev experience | ✅ For actual compilation |

> Plugin = makes IntelliJ **understand** Lombok. JAR = makes the **compiler use** Lombok.

---

**Q: What version of Lombok works with Java 24?**

| Java Version | Minimum Lombok Version |
|---|---|
| Java 8–11 | Any 1.18.x |
| Java 17 | 1.18.24+ |
| Java 21 | 1.18.30+ |
| Java 24 | **1.18.38+** (1.18.46 recommended) |

Using an older Lombok with Java 24 gives this error:
```
java.lang.ExceptionInInitializerError
com.sun.tools.javac.code.TypeTag :: UNKNOWN
```

---

## 🔷 Quick Reference — Everything Together

```
You write .java
      ↓
Maven downloads all needed libraries (Spring, Lombok, Jackson...)
      ↓
Maven tells javac (JDK) to compile
      ↓
Lombok runs during compilation → generates getters/setters
      ↓
javac produces .class files
      ↓
Maven packages .class files → .jar
      ↓
JVM (JRE) runs the .jar → your app is live 🚀
```
