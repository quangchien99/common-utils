package com.qcp.common_utils.date_time

import android.content.Context
import com.qcp.common_utils.R
import com.qcp.common_utils.contants.Constants.FOUR_WEEK
import com.qcp.common_utils.contants.Constants.ONE_DAY
import com.qcp.common_utils.contants.Constants.ONE_HOUR
import com.qcp.common_utils.contants.Constants.ONE_MINUTE
import com.qcp.common_utils.contants.Constants.ONE_MONTH
import com.qcp.common_utils.contants.Constants.ONE_WEEK
import com.qcp.common_utils.contants.Constants.ONE_YEAR
import java.util.concurrent.TimeUnit

/**
 * To format time with current time
 * @author   Quang Chien Pham <https://github.com/quangchien99>
 * @since    01/02/2023
 */
object DataTimeUtils {
    fun formatTimeWithCurrent(context: Context, createAt: Long): String {
        val timeMillis = System.currentTimeMillis() - createAt
        return when {
            timeMillis < ONE_MINUTE -> context.getString(R.string.common_just_now)
            timeMillis < ONE_HOUR -> context.getString(
                R.string.common_x_min,
                TimeUnit.MILLISECONDS.toMinutes(timeMillis)
            )
            timeMillis < ONE_DAY -> context.getString(
                R.string.common_x_hour,
                TimeUnit.MILLISECONDS.toHours(timeMillis)
            )
            timeMillis < ONE_WEEK -> context.getString(R.string.common_x_day, timeMillis / ONE_DAY)
            timeMillis < FOUR_WEEK -> context.getString(
                R.string.common_x_week,
                timeMillis / ONE_WEEK
            )
            timeMillis < ONE_YEAR -> context.getString(
                R.string.common_x_month,
                timeMillis / ONE_MONTH
            )
            else -> context.getString(R.string.common_x_year, timeMillis / ONE_YEAR)
        }
    }
}