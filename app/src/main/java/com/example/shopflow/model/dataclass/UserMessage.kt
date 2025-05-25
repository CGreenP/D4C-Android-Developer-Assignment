package com.example.shopflow.model.dataclass

/**
 * Sealed class representing different types of messages or events intended for the user.
 *
 * This allows for a structured way to communicate various user-facing notifications
 * or actions from different parts of the application (e.g., ViewModels) to the UI layer.
 *
 * Subclasses define specific message types.
 */
// Sealed class for different types of user messages/events
sealed class UserMessage {
    data class ToastMessage(val message: String) : UserMessage()
    // We could add other types like Snackbar, Dialog, etc.
}