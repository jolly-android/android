package com.example.kotlinfeatures.model

data class KotlinFeature(
    val id: Int,
    val title: String,
    val description: String,
    val category: FeatureCategory,
    val codeExample: String,
    val output: String
)

enum class FeatureCategory {
    TYPE_SAFETY,
    SYNTAX_SUGAR,
    FUNCTIONAL,
    CONCURRENCY,
    OOP,
    DELEGATION
}

object KotlinFeatures {
    val allFeatures = listOf(
        KotlinFeature(
            id = 1,
            title = "Null Safety",
            description = "Kotlin's type system distinguishes between nullable and non-nullable types, eliminating NullPointerExceptions.",
            category = FeatureCategory.TYPE_SAFETY,
            codeExample = """
                // Non-nullable type
                var name: String = "Kotlin"
                // name = null // Compile error!
                
                // Nullable type
                var nullableName: String? = "Kotlin"
                nullableName = null // OK
                
                // Safe call operator
                val length = nullableName?.length
                
                // Elvis operator
                val len = nullableName?.length ?: 0
                
                // Non-null assertion
                val l = nullableName!!.length
            """.trimIndent(),
            output = "Safe handling of null values without NPE"
        ),
        KotlinFeature(
            id = 2,
            title = "Data Classes",
            description = "Automatically generates equals(), hashCode(), toString(), copy() methods.",
            category = FeatureCategory.SYNTAX_SUGAR,
            codeExample = """
                data class User(
                    val name: String,
                    val age: Int,
                    val email: String
                )
                
                val user1 = User("Alice", 25, "alice@example.com")
                val user2 = user1.copy(age = 26)
                
                println(user1) // toString() auto-generated
                println(user1 == user2) // equals() auto-generated
            """.trimIndent(),
            output = "User(name=Alice, age=25, email=alice@example.com)\nfalse"
        ),
        KotlinFeature(
            id = 3,
            title = "Extension Functions",
            description = "Add functions to existing classes without modifying their source code.",
            category = FeatureCategory.FUNCTIONAL,
            codeExample = """
                fun String.isPalindrome(): Boolean {
                    val cleaned = this.replace(" ", "").lowercase()
                    return cleaned == cleaned.reversed()
                }
                
                fun Int.isEven(): Boolean = this % 2 == 0
                
                println("racecar".isPalindrome())
                println(4.isEven())
            """.trimIndent(),
            output = "true\ntrue"
        ),
        KotlinFeature(
            id = 4,
            title = "Sealed Classes",
            description = "Restricted class hierarchies that provide more control over inheritance.",
            category = FeatureCategory.OOP,
            codeExample = """
                sealed class Result<out T> {
                    data class Success<T>(val data: T) : Result<T>()
                    data class Error(val message: String) : Result<Nothing>()
                    object Loading : Result<Nothing>()
                }
                
                fun handleResult(result: Result<String>) = when(result) {
                    is Result.Success -> "Data: ${'$'}{result.data}"
                    is Result.Error -> "Error: ${'$'}{result.message}"
                    is Result.Loading -> "Loading..."
                }
            """.trimIndent(),
            output = "Type-safe state management without else branch"
        ),
        KotlinFeature(
            id = 5,
            title = "Coroutines",
            description = "Lightweight threads for asynchronous programming without callback hell.",
            category = FeatureCategory.CONCURRENCY,
            codeExample = """
                suspend fun fetchUser(): User {
                    delay(1000) // Non-blocking delay
                    return User("Alice", 25, "alice@example.com")
                }
                
                fun loadData() = viewModelScope.launch {
                    val user = fetchUser()
                    // Update UI with user
                }
                
                // Multiple concurrent operations
                suspend fun loadAll() = coroutineScope {
                    val user = async { fetchUser() }
                    val posts = async { fetchPosts() }
                    return@coroutineScope user.await() to posts.await()
                }
            """.trimIndent(),
            output = "Async operations without blocking threads"
        ),
        KotlinFeature(
            id = 6,
            title = "Smart Casts",
            description = "Automatically casts variables after type checks.",
            category = FeatureCategory.TYPE_SAFETY,
            codeExample = """
                fun demo(x: Any) {
                    if (x is String) {
                        // x is automatically cast to String
                        println(x.length)
                    }
                    
                    when (x) {
                        is Int -> println(x + 10)
                        is String -> println(x.uppercase())
                        is List<*> -> println(x.size)
                    }
                }
            """.trimIndent(),
            output = "No explicit casting needed after type check"
        ),
        KotlinFeature(
            id = 7,
            title = "When Expression",
            description = "More powerful than Java's switch statement, can be used as an expression.",
            category = FeatureCategory.SYNTAX_SUGAR,
            codeExample = """
                fun describe(obj: Any): String = when (obj) {
                    1 -> "One"
                    "Hello" -> "Greeting"
                    is Long -> "Long number"
                    !is String -> "Not a string"
                    in 1..10 -> "In range"
                    else -> "Unknown"
                }
                
                val result = when {
                    x > 0 -> "Positive"
                    x < 0 -> "Negative"
                    else -> "Zero"
                }
            """.trimIndent(),
            output = "Flexible pattern matching with expressions"
        ),
        KotlinFeature(
            id = 8,
            title = "Default Parameters",
            description = "Specify default values for function parameters.",
            category = FeatureCategory.SYNTAX_SUGAR,
            codeExample = """
                fun greet(
                    name: String,
                    greeting: String = "Hello",
                    punctuation: String = "!"
                ): String {
                    return "${'$'}greeting ${'$'}name${'$'}punctuation"
                }
                
                println(greet("Alice"))
                println(greet("Bob", "Hi"))
                println(greet("Charlie", "Hey", "!!!"))
            """.trimIndent(),
            output = "Hello Alice!\nHi Bob!\nHey Charlie!!!"
        ),
        KotlinFeature(
            id = 9,
            title = "Named Arguments",
            description = "Call functions with named parameters for better readability.",
            category = FeatureCategory.SYNTAX_SUGAR,
            codeExample = """
                fun createUser(
                    name: String,
                    age: Int,
                    email: String,
                    isActive: Boolean = true
                ) = User(name, age, email)
                
                val user = createUser(
                    name = "Alice",
                    email = "alice@example.com",
                    age = 25
                )
            """.trimIndent(),
            output = "Improves code readability and prevents argument mistakes"
        ),
        KotlinFeature(
            id = 10,
            title = "String Templates",
            description = "Embed expressions directly in strings.",
            category = FeatureCategory.SYNTAX_SUGAR,
            codeExample = """
                val name = "Kotlin"
                val version = 1.9
                
                // Simple template
                println("Hello, ${'$'}name!")
                
                // Expression template
                println("Version: ${'$'}{version * 10}")
                
                // Multi-line strings
                val text = ${"\"\"\""}
                    |Hello ${'$'}name,
                    |Version: ${'$'}version
                ${"\"\"\"".trimMargin()}
            """.trimIndent(),
            output = "Hello, Kotlin!\nVersion: 19.0"
        ),
        KotlinFeature(
            id = 11,
            title = "Destructuring Declarations",
            description = "Decompose objects into multiple variables.",
            category = FeatureCategory.SYNTAX_SUGAR,
            codeExample = """
                data class Person(val name: String, val age: Int)
                
                val person = Person("Alice", 25)
                val (name, age) = person
                
                // In loops
                val map = mapOf("a" to 1, "b" to 2)
                for ((key, value) in map) {
                    println("${'$'}key -> ${'$'}value")
                }
                
                // Lambda parameters
                map.forEach { (key, value) ->
                    println("${'$'}key: ${'$'}value")
                }
            """.trimIndent(),
            output = "a -> 1\nb -> 2"
        ),
        KotlinFeature(
            id = 12,
            title = "Operator Overloading",
            description = "Define custom behavior for operators.",
            category = FeatureCategory.OOP,
            codeExample = """
                data class Point(val x: Int, val y: Int) {
                    operator fun plus(other: Point) = 
                        Point(x + other.x, y + other.y)
                    
                    operator fun times(scalar: Int) = 
                        Point(x * scalar, y * scalar)
                }
                
                val p1 = Point(1, 2)
                val p2 = Point(3, 4)
                val p3 = p1 + p2
                val p4 = p1 * 3
            """.trimIndent(),
            output = "Point(x=4, y=6)\nPoint(x=3, y=6)"
        ),
        KotlinFeature(
            id = 13,
            title = "Range Expressions",
            description = "Express ranges and progressions concisely.",
            category = FeatureCategory.SYNTAX_SUGAR,
            codeExample = """
                // Range
                for (i in 1..5) print(i) // 12345
                
                // Until (exclusive)
                for (i in 1 until 5) print(i) // 1234
                
                // Step
                for (i in 1..10 step 2) print(i) // 13579
                
                // Downward
                for (i in 5 downTo 1) print(i) // 54321
                
                // Check membership
                val inRange = 3 in 1..10 // true
            """.trimIndent(),
            output = "Elegant iteration and range checks"
        ),
        KotlinFeature(
            id = 14,
            title = "Scope Functions",
            description = "Execute code blocks in the context of an object: let, apply, run, also, with.",
            category = FeatureCategory.FUNCTIONAL,
            codeExample = """
                // let - null safety
                val length = name?.let {
                    println("Name: ${'$'}it")
                    it.length
                }
                
                // apply - configure object
                val person = Person().apply {
                    name = "Alice"
                    age = 25
                }
                
                // run - compute value
                val result = "test".run {
                    uppercase()
                }
                
                // also - side effects
                val list = mutableListOf(1, 2).also {
                    println("Before: ${'$'}it")
                }.add(3)
            """.trimIndent(),
            output = "Chainable object configuration and operations"
        ),
        KotlinFeature(
            id = 15,
            title = "Property Delegation",
            description = "Delegate property getter/setter logic to another object.",
            category = FeatureCategory.DELEGATION,
            codeExample = """
                // Lazy initialization
                val lazyValue: String by lazy {
                    println("Computed!")
                    "Hello"
                }
                
                // Observable
                var name: String by Delegates.observable("initial") {
                    prop, old, new ->
                    println("${'$'}old -> ${'$'}new")
                }
                
                // Custom delegate
                class Example {
                    var p: String by Delegate()
                }
            """.trimIndent(),
            output = "Reusable property behavior"
        ),
        KotlinFeature(
            id = 16,
            title = "Object Declarations",
            description = "Singleton pattern built into the language.",
            category = FeatureCategory.OOP,
            codeExample = """
                object DatabaseConfig {
                    const val DB_NAME = "myapp.db"
                    var timeout = 30
                    
                    fun connect() {
                        println("Connecting to ${'$'}DB_NAME")
                    }
                }
                
                // Usage
                DatabaseConfig.connect()
                println(DatabaseConfig.timeout)
                
                // Object expression (anonymous)
                val listener = object : ClickListener {
                    override fun onClick() {
                        println("Clicked!")
                    }
                }
            """.trimIndent(),
            output = "Built-in singleton pattern"
        ),
        KotlinFeature(
            id = 17,
            title = "Companion Objects",
            description = "Static-like members that belong to a class.",
            category = FeatureCategory.OOP,
            codeExample = """
                class MyClass {
                    companion object Factory {
                        const val TYPE = "Default"
                        
                        fun create(): MyClass = MyClass()
                    }
                }
                
                // Usage
                val instance = MyClass.create()
                println(MyClass.TYPE)
                
                // Can implement interfaces
                interface Factory<T> {
                    fun create(): T
                }
            """.trimIndent(),
            output = "Static-like functionality with extra features"
        ),
        KotlinFeature(
            id = 18,
            title = "Higher-Order Functions",
            description = "Functions that take functions as parameters or return functions.",
            category = FeatureCategory.FUNCTIONAL,
            codeExample = """
                // Function as parameter
                fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
                    return operation(x, y)
                }
                
                val sum = calculate(5, 3) { a, b -> a + b }
                val product = calculate(5, 3) { a, b -> a * b }
                
                // Function returning function
                fun makeMultiplier(factor: Int): (Int) -> Int {
                    return { number -> number * factor }
                }
                
                val double = makeMultiplier(2)
                println(double(5)) // 10
            """.trimIndent(),
            output = "8\n15\n10"
        ),
        KotlinFeature(
            id = 19,
            title = "Inline Functions",
            description = "Reduce overhead of higher-order functions by inlining the bytecode.",
            category = FeatureCategory.FUNCTIONAL,
            codeExample = """
                inline fun <T> measureTime(block: () -> T): T {
                    val start = System.currentTimeMillis()
                    val result = block()
                    val time = System.currentTimeMillis() - start
                    println("Time: ${'$'}time ms")
                    return result
                }
                
                val result = measureTime {
                    // Some computation
                    (1..1000000).sum()
                }
            """.trimIndent(),
            output = "No lambda object allocation at runtime"
        ),
        KotlinFeature(
            id = 20,
            title = "Type Aliases",
            description = "Create alternative names for existing types.",
            category = FeatureCategory.SYNTAX_SUGAR,
            codeExample = """
                typealias UserId = String
                typealias UserMap = Map<UserId, User>
                typealias Predicate<T> = (T) -> Boolean
                
                fun findUser(
                    id: UserId,
                    users: UserMap
                ): User? = users[id]
                
                val isAdult: Predicate<Int> = { age -> age >= 18 }
            """.trimIndent(),
            output = "Improved code readability"
        ),
        KotlinFeature(
            id = 21,
            title = "Infix Functions",
            description = "Call functions without dot notation for more readable code.",
            category = FeatureCategory.SYNTAX_SUGAR,
            codeExample = """
                infix fun Int.multiplyBy(other: Int): Int {
                    return this * other
                }
                
                infix fun String.concat(other: String): String {
                    return this + other
                }
                
                // Usage
                val result = 5 multiplyBy 3
                val text = "Hello" concat " World"
                
                // Built-in: to, until, downTo, step
                val pair = "key" to "value"
            """.trimIndent(),
            output = "15\nHello World"
        ),
        KotlinFeature(
            id = 22,
            title = "Class Delegation",
            description = "Delegate interface implementation to another object.",
            category = FeatureCategory.DELEGATION,
            codeExample = """
                interface Base {
                    fun print()
                }
                
                class BaseImpl(val x: Int) : Base {
                    override fun print() { println(x) }
                }
                
                class Derived(b: Base) : Base by b {
                    // Can override if needed
                    fun printDouble() {
                        print()
                        print()
                    }
                }
                
                val base = BaseImpl(10)
                val derived = Derived(base)
            """.trimIndent(),
            output = "Composition over inheritance made easy"
        )
    )
    
    fun getCategorizedFeatures(): Map<FeatureCategory, List<KotlinFeature>> {
        return allFeatures.groupBy { it.category }
    }
}

