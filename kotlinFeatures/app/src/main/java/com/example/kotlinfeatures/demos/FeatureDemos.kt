package com.example.kotlinfeatures.demos

import kotlinx.coroutines.delay
import kotlin.properties.Delegates

// Demo classes showcasing Kotlin features

// 1. Data Class Demo
data class User(
    val name: String,
    val age: Int,
    val email: String
) {
    fun incrementAge() = copy(age = age + 1)
}

// 2. Sealed Class Demo
sealed class NetworkResult<out T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class Error(val message: String, val code: Int) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
    object Empty : NetworkResult<Nothing>()
}

// 3. Extension Functions Demo
fun String.isPalindrome(): Boolean {
    val cleaned = this.replace(" ", "").lowercase()
    return cleaned == cleaned.reversed()
}

fun Int.isEven(): Boolean = this % 2 == 0

fun Int.factorial(): Long {
    return if (this <= 1) 1 else this * (this - 1).factorial()
}

fun List<Int>.average(): Double {
    return if (isEmpty()) 0.0 else sum().toDouble() / size
}

// 4. Operator Overloading Demo
data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point) = Point(x + other.x, y + other.y)
    operator fun minus(other: Point) = Point(x - other.x, y - other.y)
    operator fun times(scalar: Int) = Point(x * scalar, y * scalar)
    operator fun unaryMinus() = Point(-x, -y)
    
    fun distanceFrom(other: Point): Double {
        val dx = (x - other.x).toDouble()
        val dy = (y - other.y).toDouble()
        return kotlin.math.sqrt(dx * dx + dy * dy)
    }
}

data class Complex(val real: Double, val imaginary: Double) {
    operator fun plus(other: Complex) = Complex(real + other.real, imaginary + other.imaginary)
    operator fun times(other: Complex) = Complex(
        real * other.real - imaginary * other.imaginary,
        real * other.imaginary + imaginary * other.real
    )
    
    override fun toString(): String = when {
        imaginary >= 0 -> "$real + ${imaginary}i"
        else -> "$real - ${-imaginary}i"
    }
}

// 5. Singleton Object Demo
object AppConfig {
    const val APP_NAME = "Kotlin Features Demo"
    const val VERSION = "1.0.0"
    var debugMode = true
    
    private val features = mutableListOf<String>()
    
    fun addFeature(feature: String) {
        features.add(feature)
    }
    
    fun getFeatures() = features.toList()
    
    fun logConfig() = """
        App: $APP_NAME
        Version: $VERSION
        Debug: $debugMode
        Features: ${features.size}
    """.trimIndent()
}

// 6. Companion Object Demo
class Database private constructor(val name: String) {
    companion object Factory {
        private var instance: Database? = null
        
        fun getInstance(name: String = "default.db"): Database {
            return instance ?: Database(name).also { instance = it }
        }
        
        const val VERSION = 1
        
        fun clearInstance() {
            instance = null
        }
    }
    
    fun query(sql: String) = "Executing: $sql on $name"
}

// 7. Infix Function Demo
infix fun Int.pow(exponent: Int): Long {
    var result = 1L
    repeat(exponent) {
        result *= this
    }
    return result
}

infix fun String.concat(other: String): String = this + other

class Person(val name: String) {
    infix fun likes(food: String) = "$name likes $food"
    infix fun meets(other: Person) = "$name meets ${other.name}"
}

// 8. Property Delegation Demo
class DelegationExample {
    // Lazy initialization
    val lazyValue: String by lazy {
        println("Computing lazy value...")
        "Lazy Initialized!"
    }
    
    // Observable property
    var observableValue: String by Delegates.observable("Initial") { prop, old, new ->
        println("${prop.name}: $old -> $new")
    }
    
    // Vetoable property
    var vetoableValue: Int by Delegates.vetoable(0) { _, old, new ->
        new > old // Only accept increasing values
    }
}

// 9. Sealed Interface Demo (Kotlin feature)
sealed interface UIState {
    object Idle : UIState
    object Loading : UIState
    data class Content(val items: List<String>) : UIState
    data class Error(val message: String) : UIState
}

// 10. Inline Class Demo (Value class)
@JvmInline
value class UserId(val id: String) {
    fun validate(): Boolean = id.isNotEmpty() && id.length >= 5
}

@JvmInline
value class Email(val address: String) {
    fun isValid(): Boolean = address.contains("@") && address.contains(".")
}

// 11. Higher-Order Functions Demo
object FunctionalUtils {
    fun <T> List<T>.customFilter(predicate: (T) -> Boolean): List<T> {
        val result = mutableListOf<T>()
        for (item in this) {
            if (predicate(item)) result.add(item)
        }
        return result
    }
    
    fun <T, R> List<T>.customMap(transform: (T) -> R): List<R> {
        val result = mutableListOf<R>()
        for (item in this) {
            result.add(transform(item))
        }
        return result
    }
    
    fun repeat(times: Int, action: (Int) -> Unit) {
        for (i in 0 until times) {
            action(i)
        }
    }
    
    fun <T> applyTwice(value: T, operation: (T) -> T): T {
        return operation(operation(value))
    }
}

// 12. Inline Function Demo
inline fun <T> measureTimeMillis(block: () -> T): Pair<T, Long> {
    val start = System.currentTimeMillis()
    val result = block()
    val elapsed = System.currentTimeMillis() - start
    return result to elapsed
}

inline fun <reified T> isInstanceOf(value: Any): Boolean {
    return value is T
}

// 13. Type Aliases Demo
typealias UserName = String
typealias UserIdAlias = Int
typealias UserCallback = (User) -> Unit
typealias StringValidator = (String) -> Boolean
typealias Point2D = Pair<Int, Int>

// 14. Destructuring Demo
data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val category: String,
    val inStock: Boolean
) {
    operator fun component6() = if (inStock) "Available" else "Out of Stock"
}

// 15. Coroutines Demo
class CoroutineExamples {
    suspend fun fetchUser(id: Int): User {
        delay(500) // Simulate network call
        return User("User $id", 25 + id, "user$id@example.com")
    }
    
    suspend fun fetchUserPosts(userId: Int): List<String> {
        delay(300)
        return listOf("Post 1", "Post 2", "Post 3")
    }
    
    suspend fun processData(): String {
        delay(1000)
        return "Processed Data"
    }
}

// 16. Scope Functions Demo
object ScopeFunctionExamples {
    fun letExample(name: String?): Int? {
        return name?.let {
            println("Name is $it")
            it.length
        }
    }
    
    fun applyExample(): User {
        return User("", 0, "").apply {
            println("Configuring user...")
        }
    }
    
    fun runExample(value: String): String {
        return value.run {
            uppercase()
        }
    }
    
    fun alsoExample(): List<Int> {
        return mutableListOf(1, 2, 3).also {
            println("List created with ${it.size} items")
        }
    }
    
    fun withExample(user: User): String {
        return with(user) {
            "$name is $age years old"
        }
    }
}

// 17. Smart Cast Demo
fun smartCastExample(value: Any): String {
    return when (value) {
        is String -> "String of length ${value.length}: $value"
        is Int -> "Integer: ${value * 2}"
        is List<*> -> "List with ${value.size} elements"
        is User -> "User: ${value.name}, age ${value.age}"
        else -> "Unknown type"
    }
}

// 18. When Expression Demo
fun whenExamples(value: Any): String {
    return when (value) {
        0, 1 -> "Zero or One"
        in 2..10 -> "Between 2 and 10"
        is String -> "String: $value"
        !is Int -> "Not an integer"
        else -> "Something else"
    }
}

fun whenWithoutArgument(x: Int, y: Int): String {
    return when {
        x > y -> "$x is greater than $y"
        x < y -> "$x is less than $y"
        else -> "$x equals $y"
    }
}

// 19. Range Demo
object RangeExamples {
    fun rangeIterations(): List<String> {
        val results = mutableListOf<String>()
        
        // Standard range
        for (i in 1..5) {
            results.add("Standard: $i")
        }
        
        // Until (exclusive)
        for (i in 1 until 5) {
            results.add("Until: $i")
        }
        
        // Step
        for (i in 0..10 step 2) {
            results.add("Step: $i")
        }
        
        // Downward
        for (i in 5 downTo 1) {
            results.add("Down: $i")
        }
        
        return results
    }
    
    fun rangeChecks(value: Int): String {
        return when (value) {
            in 1..10 -> "Single digit or 10"
            in 11..100 -> "Between 11 and 100"
            !in 0..100 -> "Outside 0-100"
            else -> "Edge case"
        }
    }
}

// 20. Class Delegation Demo
interface Printer {
    fun print(message: String)
    fun printBold(message: String)
}

class ConsolePrinter : Printer {
    override fun print(message: String) {
        println("Print: $message")
    }
    
    override fun printBold(message: String) {
        println("Bold: $message")
    }
}

class Logger(printer: Printer) : Printer by printer {
    private val logs = mutableListOf<String>()
    
    fun getLogs() = logs.toList()
    
    override fun print(message: String) {
        logs.add(message)
        println("Logged: $message")
    }
}

// 21. Null Safety Demo
object NullSafetyExamples {
    fun safeCallExample(name: String?): String {
        return name?.uppercase() ?: "NO NAME"
    }
    
    fun elvisOperatorExample(value: String?): Int {
        return value?.length ?: 0
    }
    
    fun safeCastExample(value: Any?): String? {
        return (value as? String)?.uppercase()
    }
    
    fun letChainingExample(name: String?): String {
        return name?.let {
            it.trim()
        }?.let {
            it.uppercase()
        }?.let {
            "Hello, $it!"
        } ?: "No name provided"
    }
}

// 22. String Template Demo
object StringTemplateExamples {
    fun basicTemplate(name: String, age: Int): String {
        return "Name: $name, Age: $age"
    }
    
    fun expressionTemplate(a: Int, b: Int): String {
        return "Sum of $a and $b is ${a + b}"
    }
    
    fun multilineTemplate(user: User): String {
        return """
            User Information:
            ================
            Name: ${user.name}
            Age: ${user.age}
            Email: ${user.email}
            Status: ${if (user.age >= 18) "Adult" else "Minor"}
        """.trimIndent()
    }
    
    fun rawStringWithDollar(): String {
        val price = 50
        return "Price: ${'$'}$price"
    }
}

