package com.apptrick.collagemaker.editingCollageUtilityClasses

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.FrameLayout.LayoutParams
import androidx.core.content.ContextCompat
import com.apptrick.collagemaker.R
import com.apptrick.collagemaker.databinding.FragmentSelectCollageBinding
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoEditorView
import ja.burhanrashid52.photoeditor.PhotoFilter


class AddingText: View.OnTouchListener {
    private var _xDelta = 0
    private var _yDelta = 0
    lateinit var editText: EditText
    fun addingText(binding: FragmentSelectCollageBinding, context: Context){
        editText = EditText(context)
        editText.hint = "Type Something..."
        editText.setTextColor(Color.parseColor("#bdbdbd"))
        editText.setBackground(ContextCompat.getDrawable(context, R.drawable.txt_bg))
        val layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        layoutParams.leftMargin = 50
        layoutParams.topMargin = 50
        layoutParams.bottomMargin = -250
        layoutParams.rightMargin = -250
        editText.layoutParams = layoutParams
        editText.setOnTouchListener(this)
        binding.frameLayoutParent.addView(editText)
    }

    override fun onTouch(view: View, event: MotionEvent): Boolean {
        val X = event.rawX.toInt()
        val Y = event.rawY.toInt()
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                val lParams = view.layoutParams as LayoutParams
                _xDelta = X - lParams.leftMargin
                _yDelta = Y - lParams.topMargin
            }
            MotionEvent.ACTION_UP -> {}
            MotionEvent.ACTION_POINTER_DOWN -> {}
            MotionEvent.ACTION_POINTER_UP -> {}
            MotionEvent.ACTION_MOVE -> {
                val layoutParams = view.layoutParams as LayoutParams
                layoutParams.leftMargin = X - _xDelta
                layoutParams.topMargin = Y - _yDelta
                layoutParams.rightMargin = -250
                layoutParams.bottomMargin = -250
                view.layoutParams = layoutParams
            }
        }
        return false
    }
}