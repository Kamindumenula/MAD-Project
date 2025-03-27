package com.example.interviewbuddy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.card.MaterialCardView

class TestInterview : AppCompatActivity() {

    private lateinit var interviewContainer: FrameLayout
    private lateinit var progressSteps: LinearLayout
    private lateinit var btnBack: ImageButton
    private var currentStep = 0
    private val totalSteps = 4 // Resume, Type, Questions, Results

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_interview)

        interviewContainer = findViewById(R.id.interviewContainer)
        progressSteps = findViewById(R.id.progressSteps)
        btnBack = findViewById(R.id.btnBack)

        setupBottomNavigation()
        setupProgressSteps()
        showResumeSelection()

        btnBack.setOnClickListener {
            when (currentStep) {
                1 -> showResumeSelection()
                2 -> showInterviewType()
                3 -> showQuestions()
                else -> finish()
            }
        }
    }

    private fun setupBottomNavigation() {
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavView.selectedItemId = R.id.navigation_interview

        bottomNavView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun setupProgressSteps() {
        progressSteps.removeAllViews()

        for (i in 0 until totalSteps) {
            val stepView = LayoutInflater.from(this).inflate(R.layout.step_indicator, progressSteps, false)
            val stepCircle = stepView.findViewById<View>(R.id.stepCircle)
            val stepLabel = stepView.findViewById<TextView>(R.id.stepLabel)

            stepLabel.text = when (i) {
                0 -> "Resume"
                1 -> "Type"
                2 -> "Questions"
                else -> "Results"
            }

            progressSteps.addView(stepView)
        }
        updateStepIndicators()
    }

    private fun updateStepIndicators() {
        for (i in 0 until progressSteps.childCount) {
            val stepView = progressSteps.getChildAt(i)
            val stepCircle = stepView.findViewById<View>(R.id.stepCircle)
            val stepLabel = stepView.findViewById<TextView>(R.id.stepLabel)

            if (i < currentStep) {
                stepCircle.background = ContextCompat.getDrawable(this, R.drawable.step_completed)
                stepLabel.setTextColor(ContextCompat.getColor(this, R.color.light_blue))
            } else if (i == currentStep) {
                stepCircle.background = ContextCompat.getDrawable(this, R.drawable.step_active)
                stepLabel.setTextColor(ContextCompat.getColor(this, R.color.black))
            } else {
                stepCircle.background = ContextCompat.getDrawable(this, R.drawable.step_inactive)
                stepLabel.setTextColor(ContextCompat.getColor(this, R.color.light_gray))
            }
        }
    }

    private fun showResumeSelection() {
        currentStep = 0
        updateStepIndicators()

        val view = LayoutInflater.from(this).inflate(R.layout.layout_interview_resume, interviewContainer, false)
        interviewContainer.removeAllViews()
        interviewContainer.addView(view)

        val resumeDropdown = view.findViewById<AutoCompleteTextView>(R.id.resumeDropdown)
        val btnNext = view.findViewById<Button>(R.id.btnNextResume)

        // Setup dropdown
        val resumes = arrayOf("My_Resume_2023.pdf", "My_Resume_2024.pdf", "My_Resume_Latest.pdf")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, resumes)
        resumeDropdown.setAdapter(adapter)

        btnNext.setOnClickListener {
            if (resumeDropdown.text.isNotEmpty()) {
                showInterviewType()
            } else {
                Toast.makeText(this, "Please select a resume", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showInterviewType() {
        currentStep = 1
        updateStepIndicators()

        val view = LayoutInflater.from(this).inflate(R.layout.layout_interview_type, interviewContainer, false)
        interviewContainer.removeAllViews()
        interviewContainer.addView(view)

        val radioGroup = view.findViewById<RadioGroup>(R.id.radioInterviewType)
        val btnNext = view.findViewById<Button>(R.id.btnNextType)

        btnNext.setOnClickListener {
            val selectedId = radioGroup.checkedRadioButtonId
            if (selectedId != -1) {
                showQuestions()
            } else {
                Toast.makeText(this, "Please select an interview type", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showQuestions() {
        currentStep = 2
        updateStepIndicators()

        val view = LayoutInflater.from(this).inflate(R.layout.layout_interview_questions, interviewContainer, false)
        interviewContainer.removeAllViews()
        interviewContainer.addView(view)

        val tvQuestionNumber = view.findViewById<TextView>(R.id.tvQuestionNumber)
        val tvQuestionText = view.findViewById<TextView>(R.id.tvQuestionText)
        val etAnswer = view.findViewById<EditText>(R.id.etAnswer)
        val btnNext = view.findViewById<Button>(R.id.btnNextQuestion)

        // Sample questions - replace with your actual questions
        val questions = listOf(
            "What are your strengths and weaknesses?",
            "Tell me about a time you faced a challenge and how you overcame it.",
            "Where do you see yourself in 5 years?",
            "Why do you want to work for our company?",
            "Describe a project you're particularly proud of."
        )

        var currentQuestion = 0
        updateQuestion(currentQuestion, questions, tvQuestionNumber, tvQuestionText)

        btnNext.setOnClickListener {
            if (etAnswer.text.isNotEmpty()) {
                currentQuestion++
                if (currentQuestion < questions.size) {
                    updateQuestion(currentQuestion, questions, tvQuestionNumber, tvQuestionText)
                    etAnswer.text.clear()
                } else {
                    showResults()
                }
            } else {
                Toast.makeText(this, "Please enter your answer", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateQuestion(index: Int, questions: List<String>,
                               tvQuestionNumber: TextView, tvQuestionText: TextView) {
        tvQuestionNumber.text = "Question ${index + 1} of ${questions.size}"
        tvQuestionText.text = questions[index]
    }

    private fun showResults() {
        currentStep = 3
        updateStepIndicators()

        val view = LayoutInflater.from(this).inflate(R.layout.layout_interview_results, interviewContainer, false)
        interviewContainer.removeAllViews()
        interviewContainer.addView(view)

        val tvScore = view.findViewById<TextView>(R.id.tvScore)
        val tvFeedback = view.findViewById<TextView>(R.id.tvFeedback)
        val btnDone = view.findViewById<Button>(R.id.btnDone)

        // Set sample results - replace with your actual scoring logic
        tvScore.text = "85%"
        tvFeedback.text = "Good job! You performed better than 70% of users."

        btnDone.setOnClickListener {
            finish()
        }
    }
}