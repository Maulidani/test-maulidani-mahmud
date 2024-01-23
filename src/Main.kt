import kotlinx.coroutines.runBlocking
import model.ResultKeys
import model.UserFeedback
import service.AIService
import kotlin.random.Random
import service.SentimentAnalysisService

fun main() {
    // Instantiate the implementation of AIService
    val aiService: AIService = SentimentAnalysisService()

    // Using CoroutineScope for asynchronous programming
    runBlocking {
        // Create a sample user feedback
        val userFeedback = UserFeedback(
            userId = Random.nextLong(),
            feedbackText = "The service is outstanding! Very user-friendly and helpful support.",
            aspects = listOf("Ease of Use", "Support"),
            timestamp = System.currentTimeMillis()
        )

        // Call the analyzeUserFeedback function within a coroutine
        val result = aiService.analyzeUserFeedback(userFeedback)

        // Handling the result
        println("Sentiment Analysis and Aspect Extraction Results:")
        result.forEach { (key, value) ->
            // Constant keys for the result
            val resultKeys = ResultKeys()

            when (key) {
                resultKeys.aspectSentiments -> {
                    println("* $key:")
                    (value as Map<*, *>).forEach { (aspect, sentiment) ->
                        println(" - $aspect: $sentiment")
                    }
                }

                resultKeys.extractedAspects -> println("* $key: $value")
                else -> println("* $key: $value")
            }
        }
    }
}
