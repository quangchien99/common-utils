package com.qcp.common_utils.extensions

internal val map11To10 = mapOf(
    "0162" to "032",
    "0163" to "033",
    "0164" to "034",
    "0165" to "035",
    "0166" to "036",
    "0167" to "037",
    "0168" to "038",
    "0169" to "039",
    "0120" to "070",
    "0121" to "079",
    "0122" to "077",
    "0126" to "076",
    "0128" to "078",
    "0123" to "083",
    "0124" to "084",
    "0125" to "085",
    "0127" to "081",
    "0129" to "082",
    "0186" to "056",
    "0188" to "058",
    "0199" to "059"
)

fun String.transformPhone11To10(): String {
    val originalPhone = this
    if (originalPhone.length <= 4) return originalPhone

    val key = originalPhone.substring(0, 4)
    if (map11To10.containsKey(key)) {
        return originalPhone.replaceFirst(key, map11To10[key].orEmpty(), false)
    }
    return originalPhone
}