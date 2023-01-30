package com.qcp.common_utils.textview

import android.graphics.Paint
import android.util.Log
import android.view.View
import android.widget.TextView
import com.qcp.common_utils.contants.Constants.DEFAULT_MAX_LINE
import com.qcp.common_utils.contants.Constants.DEFAULT_SUFFIX
import com.qcp.common_utils.contants.Constants.SPACE_VALUE

/**
 * @author   Quang Chien Pham <https://github.com/quangchien99>
 * @since    18/01/2023
 */
object TextViewUtils {

    /** Make the content of the text view ellipsized with maxLines and suffix defined.
     * @param maxLines the line applied ellipsize.
     * @param suffix the suffix when ellipsized.
     */
    fun TextView.setEllipsizedSuffix(
        maxLines: Int = DEFAULT_MAX_LINE, suffix: String = DEFAULT_SUFFIX
    ) {
        val allText = text.toString()
        var newText = allText
        val tvWidth = width
        val textSize = textSize
        if (!textHasEllipsized(newText, tvWidth, textSize, maxLines)) return

        while (textHasEllipsized(newText, tvWidth, textSize, maxLines)) {
            newText = newText.substring(0, newText.length - 1).trim()
        }

        // now replace the last few chars with the suffix if we can

        var endIndex = newText.length - suffix.length - 2
        if (endIndex > 0) {
            if (newText[endIndex].toString() == SPACE_VALUE) {
                newText[endIndex]
                newText = "${newText.substring(0, endIndex).trim()}$suffix"
            } else {
                while (newText[endIndex - 1].toString() != SPACE_VALUE) {
                    endIndex -= 1
                }
                newText[endIndex]
                newText = "${newText.substring(0, endIndex).trim()}$suffix"
            }
        }

        text = newText

    }

    /**
     * To determine the text view has been ellipsized or not
     */
    private fun textHasEllipsized(
        text: String, tvWidth: Int, textSize: Float, maxLines: Int
    ): Boolean {
        val paint = Paint()
        paint.textSize = textSize
        val size = paint.measureText(text).toInt()

        return size > tvWidth * maxLines
    }
}