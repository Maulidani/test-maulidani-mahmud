package model

// Constants data for the result sentiment analysis.
class ResultKeys {
    // Keys for various aspects of the sentiment analysis result map
    val userId = "UserId"
    val overallSentiment = "OverallSentiment"
    val aspectSentiments = "AspectSentiments"
    val extractedAspects = "ExtractedAspects"
    val error = "Error"

    // Result sentiment analysized
    val resultAnalysized: (result: Boolean) -> String = { result ->
        if (result) "Positive" else "Negative"
    }
}