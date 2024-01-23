package service

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import model.ResultKeys
import model.UserFeedback
import kotlin.random.Random

// Implementation of AI service for sentiment analysis and aspect extraction
class SentimentAnalysisService : AIService {

    // Simulated suspend function for sentiment analysis and aspect extraction
    private suspend fun performSentimentAnalysisAndAspectExtraction(feedback: UserFeedback): Map<String, Any> {
        // Keys result
        val result = ResultKeys()

        // Simulating AI service process
        println("Simulated processing ...")

        // Simulated aspect extraction based on keywords
        val extractedAspects = feedback.feedbackText.split(
            " ", ",", "." // Symbols seperated words
        ).filter { it.length > 3 }

        // Received data from simulated (randoming the results)
        val overallSentiment = result.resultAnalysized(Random.nextBoolean())
        val aspectSentiments = feedback.aspects.associateWith {
            result.resultAnalysized(Random.nextBoolean())
        }

        delay(3000) // Simulating network delay

        return mapOf(
            result.userId to feedback.userId,
            result.extractedAspects to extractedAspects,
            result.overallSentiment to overallSentiment,
            result.aspectSentiments to aspectSentiments
        )
    }

    // Implementation of analyzeUserFeedback from the AIService interface
    override suspend fun analyzeUserFeedback(feedback: UserFeedback): Map<String, Any> {
        return coroutineScope {
            try {
                // Launch a coroutine to perform AI sentiment analysis and aspect extraction asynchronously
                val analysisResult = async {
                    performSentimentAnalysisAndAspectExtraction(feedback)
                }

                // Await the result of sentiment analysis and aspect extraction
                val analysisResults = analysisResult.await()

                // Return the combined analysis results
                analysisResults
            } catch (e: Exception) {
                // Handle exceptions such as network errors or AI service failures
                mapOf(ResultKeys().error to "Error analyzing feedback: ${e.message}")
            }
        }
    }
}
