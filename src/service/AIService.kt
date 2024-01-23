package service

import model.UserFeedback

// Interface for AI service
interface AIService {
    suspend fun analyzeUserFeedback(feedback: UserFeedback): Map<String, Any>
}