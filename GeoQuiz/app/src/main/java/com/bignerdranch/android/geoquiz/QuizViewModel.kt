package com.bignerdranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val CHEATED_QUESTIONS_KEY = "CHEATED_QUESTIONS_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
    private var currentIndex: Int
        get() = savedStateHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )

    private var cheatedQuestions: MutableSet<Int>
        get() = savedStateHandle.get(CHEATED_QUESTIONS_KEY) ?: mutableSetOf()
        set(value) = savedStateHandle.set(CHEATED_QUESTIONS_KEY, value)

    var hasEverCheated: Boolean = false

    fun didCheatOnCurrentQuestion() = currentIndex in cheatedQuestions

    fun cheatOnCurrentQuestion() {
        cheatedQuestions.add(currentIndex)
        hasEverCheated = true
    }

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}
