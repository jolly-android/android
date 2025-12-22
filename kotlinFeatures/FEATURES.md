# Kotlin Features - Quick Reference

## Complete Feature List

### 1. Null Safety ⭐️
**Category**: Type Safety

**What it is**: Kotlin's type system distinguishes between nullable and non-nullable types.

**Java equivalent**: None - requires manual null checks everywhere

**Key operators**:
- `?.` - Safe call operator
- `?:` - Elvis operator (default value)
- `!!` - Non-null assertion
- `as?` - Safe cast

**Example**:
```kotlin
var name: String? = null
val length = name?.length ?: 0  // Returns 0 if null
```

**Impact**: Eliminates NullPointerException at compile time!

---

### 2. Data Classes 📦
**Category**: Syntax Sugar

**What it is**: Automatically generates `equals()`, `hashCode()`, `toString()`, `copy()`, and `componentN()` functions.

**Java equivalent**: Requires 50+ lines of boilerplate or Lombok

**Example**:
```kotlin
data class User(val name: String, val age: Int, val email: String)
```

**Impact**: Reduces boilerplate by 95% for model classes

---

### 3. Extension Functions 🔧
**Category**: Functional

**What it is**: Add functions to existing classes without inheritance or decorator pattern.

**Java equivalent**: Static utility methods (less readable)

**Example**:
```kotlin
fun String.isPalindrome(): Boolean = this == this.reversed()
"racecar".isPalindrome() // true
```

**Impact**: Makes code more readable and organized

---

### 4. Sealed Classes 🔒
**Category**: OOP

**What it is**: Restricted class hierarchies where all subclasses are known at compile time.

**Java equivalent**: Enums (limited) or abstract classes (not exhaustive)

**Example**:
```kotlin
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}
```

**Impact**: Exhaustive when expressions, perfect for state machines

---

### 5. Coroutines 🚀
**Category**: Concurrency

**What it is**: Lightweight threads for asynchronous programming without callback hell.

**Java equivalent**: Callbacks, RxJava, CompletableFuture (all more complex)

**Example**:
```kotlin
suspend fun fetchUser(): User {
    delay(1000) // Non-blocking!
    return User("Alice", 25, "alice@example.com")
}
```

**Impact**: Write async code that looks synchronous

---

### 6. Smart Casts 🧠
**Category**: Type Safety

**What it is**: Compiler automatically casts variables after type checks.

**Java equivalent**: Manual casting after instanceof

**Example**:
```kotlin
fun process(x: Any) {
    if (x is String) {
        println(x.length) // x is automatically cast to String
    }
}
```

**Impact**: Cleaner code, no redundant casting

---

### 7. When Expression 🎯
**Category**: Syntax Sugar

**What it is**: More powerful than switch, can be used as expression, supports ranges and types.

**Java equivalent**: switch statement (limited)

**Example**:
```kotlin
val result = when (x) {
    is String -> x.length
    in 1..10 -> "Number in range"
    else -> "Unknown"
}
```

**Impact**: More expressive pattern matching

---

### 8. Default Parameters 📝
**Category**: Syntax Sugar

**What it is**: Specify default values for function parameters.

**Java equivalent**: Method overloading (requires multiple methods)

**Example**:
```kotlin
fun greet(name: String, greeting: String = "Hello") = "$greeting $name"
greet("Alice") // Uses default "Hello"
```

**Impact**: Eliminates need for multiple overloaded methods

---

### 9. Named Arguments 🏷️
**Category**: Syntax Sugar

**What it is**: Call functions specifying parameter names.

**Java equivalent**: None - positional only

**Example**:
```kotlin
createUser(name = "Alice", age = 25, email = "alice@example.com")
```

**Impact**: Prevents argument order mistakes, improves readability

---

### 10. String Templates 📄
**Category**: Syntax Sugar

**What it is**: Embed expressions directly in strings.

**Java equivalent**: String concatenation or String.format()

**Example**:
```kotlin
val name = "Kotlin"
println("Hello, $name! Version: ${1.9 * 10}")
```

**Impact**: Cleaner string construction

---

### 11. Destructuring Declarations 📦
**Category**: Syntax Sugar

**What it is**: Decompose objects into multiple variables.

**Java equivalent**: None - manual property access

**Example**:
```kotlin
val (name, age, email) = user
for ((key, value) in map) { ... }
```

**Impact**: Cleaner code for working with data

---

### 12. Operator Overloading ➕
**Category**: OOP

**What it is**: Define custom behavior for operators (+, -, *, etc.).

**Java equivalent**: None - named methods only

**Example**:
```kotlin
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
}
val p3 = p1 + p2
```

**Impact**: Natural mathematical code

---

### 13. Range Expressions 📊
**Category**: Syntax Sugar

**What it is**: Express ranges and progressions concisely.

**Java equivalent**: Traditional for loops

**Example**:
```kotlin
for (i in 1..10 step 2) { ... }
if (age in 18..65) { ... }
```

**Impact**: More readable iteration code

---

### 14. Scope Functions 🎯
**Category**: Functional

**What it is**: Execute code blocks in the context of an object.

**Types**: `let`, `apply`, `run`, `also`, `with`

**Example**:
```kotlin
val user = User().apply {
    name = "Alice"
    age = 25
}
```

**Impact**: Cleaner object configuration and null handling

---

### 15. Property Delegation 🎭
**Category**: Delegation

**What it is**: Delegate property getter/setter to another object.

**Java equivalent**: Manual getter/setter logic

**Example**:
```kotlin
val lazyValue: String by lazy { computeValue() }
var name: String by Delegates.observable("initial") { _, old, new ->
    println("$old -> $new")
}
```

**Impact**: Reusable property behaviors

---

### 16. Object Declarations 🔱
**Category**: OOP

**What it is**: Singleton pattern built into the language.

**Java equivalent**: Manual singleton implementation

**Example**:
```kotlin
object DatabaseConfig {
    const val DB_NAME = "myapp.db"
    fun connect() { ... }
}
```

**Impact**: Thread-safe singletons without boilerplate

---

### 17. Companion Objects 👥
**Category**: OOP

**What it is**: Static-like members that can implement interfaces.

**Java equivalent**: static members (less flexible)

**Example**:
```kotlin
class MyClass {
    companion object {
        fun create(): MyClass = MyClass()
    }
}
```

**Impact**: Factory patterns, static-like with OOP benefits

---

### 18. Higher-Order Functions 🔄
**Category**: Functional

**What it is**: Functions that take or return functions.

**Java equivalent**: Functional interfaces (more verbose)

**Example**:
```kotlin
fun calculate(x: Int, y: Int, op: (Int, Int) -> Int) = op(x, y)
val sum = calculate(5, 3) { a, b -> a + b }
```

**Impact**: Functional programming made easy

---

### 19. Inline Functions ⚡
**Category**: Functional

**What it is**: Inline bytecode at call sites to eliminate function call overhead.

**Java equivalent**: None - JVM optimization only

**Example**:
```kotlin
inline fun <T> measureTime(block: () -> T): T {
    val start = System.currentTimeMillis()
    return block().also {
        println("Time: ${System.currentTimeMillis() - start}ms")
    }
}
```

**Impact**: Zero-overhead abstractions

---

### 20. Type Aliases 📛
**Category**: Syntax Sugar

**What it is**: Create alternative names for types.

**Java equivalent**: None

**Example**:
```kotlin
typealias UserId = String
typealias UserCallback = (User) -> Unit
```

**Impact**: Improved code readability

---

### 21. Infix Functions ↔️
**Category**: Syntax Sugar

**What it is**: Call functions without dot and parentheses.

**Java equivalent**: None

**Example**:
```kotlin
infix fun Int.pow(exponent: Int): Long { ... }
val result = 2 pow 10 // 1024
```

**Impact**: DSL creation, more readable code

---

### 22. Class Delegation 🎪
**Category**: Delegation

**What it is**: Delegate interface implementation to another object.

**Java equivalent**: Manual forwarding methods

**Example**:
```kotlin
class Logger(printer: Printer) : Printer by printer {
    override fun print(msg: String) {
        // Custom logic + delegation
    }
}
```

**Impact**: Composition over inheritance made easy

---

## Comparison Summary

| Feature | Java Lines | Kotlin Lines | Savings |
|---------|-----------|--------------|---------|
| Data Class | ~50 | 1 | 98% |
| Null Safety | Manual checks | Built-in | 100% |
| Extension Function | Static utility | Member-like | 90% |
| Singleton | ~20 | 1 | 95% |
| Coroutines | Callbacks/RxJava | suspend | 70% |

## Learning Path

### Beginner
Start with: Null Safety, Data Classes, String Templates, Default Parameters

### Intermediate
Move to: Extension Functions, When Expressions, Scope Functions, Smart Casts

### Advanced
Master: Coroutines, Sealed Classes, Delegation, Inline Functions

### Expert
Explore: DSLs with Infix, Type Systems, Advanced Coroutines

---

## Tips for Java Developers

1. **Start Small**: Learn null safety first - it's everywhere
2. **Data Classes**: Replace all your POJOs
3. **Extension Functions**: Organize utilities better
4. **Coroutines**: Say goodbye to callback hell
5. **Sealed Classes**: Better than enums for state
6. **Smart Casts**: Trust the compiler
7. **When**: Forget about switch forever
8. **Scope Functions**: Use `apply` for builders, `let` for nulls
9. **No Static**: Use companion objects or top-level functions
10. **Immutability**: Prefer `val` over `var`

## Common Patterns

### Safe Navigation
```kotlin
user?.address?.street?.uppercase() ?: "No street"
```

### Builder Pattern
```kotlin
val user = User().apply {
    name = "Alice"
    age = 25
}
```

### Result Handling
```kotlin
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val error: String) : Result<Nothing>()
}
```

### Async Operations
```kotlin
viewModelScope.launch {
    val user = async { fetchUser() }
    val posts = async { fetchPosts() }
    updateUI(user.await(), posts.await())
}
```

---

**Remember**: These features aren't just syntax sugar - they make your code safer, more concise, and more maintainable!

