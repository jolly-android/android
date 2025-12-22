package com.example.kotlinfeatures.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.kotlinfeatures.model.KotlinFeature
import com.example.kotlinfeatures.demos.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeatureDetailScreen(
    feature: KotlinFeature,
    onBackClick: () -> Unit
) {
    var showDemoOutput by remember { mutableStateOf(false) }
    val demoOutput = remember(feature.id) { getDemoOutput(feature.id) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = feature.title,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Category Badge
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = feature.category.name.replace("_", " "),
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
            // Description Card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Description",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = feature.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
            
            // Code Example Card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Code Example",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFF2B2B2B),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = feature.codeExample,
                            modifier = Modifier
                                .horizontalScroll(rememberScrollState())
                                .padding(16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontFamily = FontFamily.Monospace,
                            color = Color(0xFFA9B7C6)
                        )
                    }
                }
            }
            
            // Expected Output Card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Expected Output",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = feature.output,
                        style = MaterialTheme.typography.bodyMedium,
                        fontFamily = FontFamily.Monospace,
                        color = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            }
            
            // Live Demo Button and Output
            if (demoOutput != null) {
                Button(
                    onClick = { showDemoOutput = !showDemoOutput },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(if (showDemoOutput) "Hide Demo" else "Run Live Demo")
                }
                
                if (showDemoOutput) {
                    Card(
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFF1E1E1E)
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Live Demo Output",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4CAF50)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = demoOutput,
                                style = MaterialTheme.typography.bodyMedium,
                                fontFamily = FontFamily.Monospace,
                                color = Color(0xFFE0E0E0)
                            )
                        }
                    }
                }
            }
            
            // Why This Matters Card
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Why This Matters",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = getWhyItMatters(feature.id),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }
        }
    }
}

fun getDemoOutput(featureId: Int): String? {
    return when (featureId) {
        1 -> {
            val name: String? = "Kotlin"
            val length = name?.length ?: 0
            "Safe call: ${name?.length}\nElvis: $length\nNo NullPointerException!"
        }
        2 -> {
            val user1 = User("Alice", 25, "alice@example.com")
            val user2 = user1.copy(age = 26)
            "user1: $user1\nuser2: $user2\nEquals: ${user1 == user2}"
        }
        3 -> {
            val palindrome = "racecar".isPalindrome()
            val isEven = 4.isEven()
            val factorial = 5.factorial()
            "\"racecar\".isPalindrome(): $palindrome\n4.isEven(): $isEven\n5.factorial(): $factorial"
        }
        4 -> {
            val success = NetworkResult.Success("Data loaded")
            val error = NetworkResult.Error("Network error", 404)
            "Success: $success\nError: $error"
        }
        6 -> {
            val result = smartCastExample("Hello")
            "smartCastExample(\"Hello\"): $result\n" +
            "smartCastExample(42): ${smartCastExample(42)}"
        }
        7 -> {
            val result1 = whenExamples(5)
            val result2 = whenExamples("test")
            "whenExamples(5): $result1\nwhenExamples(\"test\"): $result2"
        }
        8 -> {
            val greet = { name: String, greeting: String, punct: String ->
                "$greeting $name$punct"
            }
            "${greet("Alice", "Hello", "!")}\n${greet("Bob", "Hi", "!")}"
        }
        10 -> {
            val name = "Kotlin"
            val version = 1.9
            "Hello, $name!\nVersion: ${version * 10}"
        }
        11 -> {
            val user = User("Alice", 25, "alice@example.com")
            val (name, age, email) = user
            "Destructured:\nName: $name\nAge: $age\nEmail: $email"
        }
        12 -> {
            val p1 = Point(1, 2)
            val p2 = Point(3, 4)
            val p3 = p1 + p2
            val p4 = p1 * 3
            "p1 + p2 = $p3\np1 * 3 = $p4"
        }
        13 -> {
            val results = RangeExamples.rangeIterations().take(6)
            results.joinToString("\n")
        }
        14 -> {
            val list = listOf(1, 2, 3, 4, 5)
            val filtered = FunctionalUtils.run { list.customFilter { it % 2 == 0 } }
            val mapped = FunctionalUtils.run { list.customMap { it * 2 } }
            "Original: $list\nFiltered (even): $filtered\nMapped (x2): $mapped"
        }
        16 -> {
            AppConfig.addFeature("Test Feature")
            "Singleton access:\n${AppConfig.logConfig()}"
        }
        17 -> {
            val db = Database.getInstance("app.db")
            "Database: ${db.name}\nVersion: ${Database.VERSION}"
        }
        18 -> {
            val numbers = listOf(1, 2, 3, 4, 5)
            val filtered = FunctionalUtils.run { numbers.customFilter { it > 2 } }
            val mapped = FunctionalUtils.run { numbers.customMap { it * it } }
            "Filtered: $filtered\nMapped (squared): $mapped"
        }
        21 -> {
            val person = Person("Alice")
            "${person likes "Pizza"}\n${5 pow 3}"
        }
        else -> null
    }
}

fun getWhyItMatters(featureId: Int): String {
    return when (featureId) {
        1 -> "Eliminates the billion-dollar mistake (NullPointerException). Makes your code safer and more predictable."
        2 -> "Reduces boilerplate code dramatically. Java requires 50+ lines for what Kotlin does in one line."
        3 -> "Add functionality to classes you don't own, including Java library classes. Makes code more readable and organized."
        4 -> "Exhaustive when expressions ensure you handle all cases. Perfect for state machines and type-safe error handling."
        5 -> "Write asynchronous code that looks synchronous. No callback hell, cleaner than RxJava, built into the language."
        6 -> "No more manual casting after type checks. The compiler is smart enough to do it for you."
        7 -> "More powerful than switch, can be used as an expression. Supports ranges, type checks, and complex conditions."
        8 -> "Eliminates the need for method overloading. Makes APIs cleaner and easier to use."
        9 -> "Improves code readability and prevents argument order mistakes, especially with boolean parameters."
        10 -> "String concatenation without the pain. Cleaner than StringBuilder or String.format()."
        11 -> "Destructure data classes, maps, and lists. Makes working with multiple return values elegant."
        12 -> "Make your custom types feel like built-in types. Write mathematical code that reads naturally."
        13 -> "Cleaner iteration syntax. No need for traditional for loops in most cases."
        14 -> "Reduce cognitive load with concise, chainable operations. Perfect for null safety and object configuration."
        15 -> "Reuse property behaviors across your codebase. Lazy initialization and observable properties made easy."
        16 -> "Singleton pattern without any boilerplate. Thread-safe by default."
        17 -> "Better than static members. Can implement interfaces and be passed as objects."
        18 -> "Functional programming made easy. Write more expressive and concise code."
        19 -> "Zero overhead abstraction. Get the benefits of functions without the performance cost."
        20 -> "Make complex types readable. Document intent in the type system."
        21 -> "Write DSLs and fluent APIs. Make your code read like natural language."
        22 -> "Favor composition over inheritance with zero boilerplate. Easy to test and maintain."
        else -> "This Kotlin feature makes your code more concise, safe, and maintainable compared to Java."
    }
}

