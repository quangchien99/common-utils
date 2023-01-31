package com.qcp.common_utils.utils

import android.graphics.Color
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.Random

interface IColorGenerator {
    fun getColor(input: Int, extra: Int): Int

    fun getColor(input: String): Int
}

class ColorGenerator : IColorGenerator {
    override fun getColor(input: Int, extra: Int): Int {
        val r = Random()

        return Color.rgb(r.nextInt(256), r.nextInt(256), r.nextInt(256))
    }

    override fun getColor(input: String): Int {
        return try {
            // chỉ lấy 6 kí tự đầu làm mã màu
            val hsv = getHSVFromString(input)
            switchColorFromValueH(hsv[0])
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            getColor(0, 0)
        }
    }

    private fun getHSVFromString(input: String): FloatArray {
        val md5 = genMD5fromString(input)
        return convertHexToHSV(md5.substring(0, 6))
    }

    fun getColorString(input: String): String {
        val hsv = getHSVFromString(input)
        return getColorStringFromValueH(hsv[0])
    }

    private fun switchColorFromValueH(h: Float): Int {
        return Color.parseColor(getColorStringFromValueH(h))
    }

    private fun getColorStringFromValueH(h: Float): String {
        if (h > 340) {
            return "#F25A5A"
        }

        if (h > 310) {
            return "#EF549B"
        }

        if (h > 240) {
            return "#AE56D8"
        }

        if (h > 180) {
            return "#22A1D3"
        }

        if (h > 145) {
            return "#21D6AA"
        }

        if (h > 95) {
            return "#2DB84C"
        }

        if (h > 45) {
            return "#FFD661"
        }

        return "#F29B4C"
    }

    private fun convertHexToHSV(input: String): FloatArray {
        val color = Color.parseColor("#$input")
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        return hsv
    }

    @Throws(NoSuchAlgorithmException::class)
    private fun genMD5fromString(input: String): String {
        val md = MessageDigest.getInstance("MD5")
        md.update(input.toByteArray())
        val messageDigest = md.digest()
        val hexString = StringBuilder()
        for (aMessageDigest in messageDigest) {
            var h = Integer.toHexString(0xFF and aMessageDigest.toInt())
            while (h.length < 2)
                h = "0$h"
            hexString.append(h)
        }
        return hexString.toString()
    }
}
