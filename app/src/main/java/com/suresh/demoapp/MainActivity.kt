package com.suresh.demoapp

import android.os.Bundle
import android.text.InputType
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.suresh.appkeyboard.AppKeyboard


class MainActivity : AppCompatActivity() {
    lateinit var editTextInput: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextInput = findViewById(R.id.editTextInput)
        connectToAppKeyboard(editTextInput)
    }

    private fun connectToAppKeyboard(editText: EditText) {
        val appKeyboard: AppKeyboard = findViewById(R.id.appKeyboard)
        editText.apply {
            setRawInputType(InputType.TYPE_CLASS_TEXT)
            setTextIsSelectable(true)
            showSoftInputOnFocus = false
            isSoundEffectsEnabled = false
            isLongClickable = false
            setOnFocusChangeListener { _, _ ->
                appKeyboard.setInputConnection(this.onCreateInputConnection(EditorInfo()))
            }
        }
    }
}