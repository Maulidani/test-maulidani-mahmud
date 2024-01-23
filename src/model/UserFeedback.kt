package model

// Data class representing feedback with multiple aspects and user information
data class UserFeedback(
    val userId: Long,
    val feedbackText: String,
    val aspects: List<String>,
    val timestamp: Long
)