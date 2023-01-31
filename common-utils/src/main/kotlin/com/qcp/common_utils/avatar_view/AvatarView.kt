package com.qcp.common_utils.avatar_view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qcp.common_utils.R
import com.qcp.common_utils.databinding.LayoutAvatarViewBinding
import com.qcp.common_utils.utils.ColorGenerator

/**
 * To define avatar view which can be set from URL, Drawable or auto generate by name
 * @author   Quang Chien Pham <https://github.com/quangchien99>
 * @since    30/01/2023
 */
class AvatarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleRes) {

    private val binding: LayoutAvatarViewBinding by lazy {
        LayoutAvatarViewBinding.inflate(
            LayoutInflater.from(context),
            this
        )
    }

    private val tvAvatar: AppCompatTextView
        get() = binding.tvAvatar
    private val imgAvatar: AppCompatImageView
        get() = binding.imgAvatar

    private var autoTextSize = true
    private var textSize: Float
        get() = tvAvatar.textSize
        set(value) {
            tvAvatar.textSize = value
        }

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet? = null) {
        // inflate layout
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.AvatarView)
        val currentTextSize = typeArray.getDimension(R.styleable.AvatarView_textSize, -1f)
        if (currentTextSize != -1f) {
            textSize = currentTextSize
            autoTextSize = false
        }

        typeArray.recycle()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (autoTextSize) {
            updateAutoTextSize(w)
        }
    }

    private fun updateAutoTextSize(width: Int) {
        // calculate text size which depend on avatar size. 40dp => text 14, 80dp => text 24
        post {
            // after post, could get real size of with and height
            val avatarSizeDp = width / resources.displayMetrics.density
            val textSizeDp = 14 + (avatarSizeDp - 40) / 4
            tvAvatar.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSizeDp)
        }
    }

    private fun setAvatarByName(name: String) {
        tvAvatar.apply {
            visibility = View.VISIBLE
            text = getAvatarShortName(name)
        }
        imgAvatar.visibility = View.GONE
        setTextNameColor(name)
    }

    private fun setAvatarByImage(imgUrl: String) {
        tvAvatar.visibility = View.GONE
        imgAvatar.let {
            it.visibility = View.VISIBLE
            Glide.with(this)
                .load(imgUrl)
                .placeholder(R.drawable.ic_person)
                .apply(RequestOptions.circleCropTransform())
                .into(it)
        }
    }

    private fun getAvatarShortName(name: String): String {
        if (name.trim().isBlank()) return ""
        val arr = name.trim().split(" ")
        return if (arr.size == 1) arr[0][0].toString()
        else arr.last()[0].toString() + arr.first()[0].toString()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun setAvatar(imgUrl: String?, name: String?, drawable: Drawable?) {
        when {
            imgUrl.isNullOrBlank().not() -> imgUrl?.let { setAvatarByImage(it) }
            name.isNullOrBlank().not() -> name?.let { setAvatarByName(it) }
            drawable != null -> context.getDrawable(R.drawable.ic_person)?.let { setAvatarByDrawable(it) }
            else -> context.getDrawable(R.drawable.ic_person)?.let { setAvatarByDrawable(it) }
        }
    }

    private fun setAvatarByDrawable(drawable: Drawable) {
        tvAvatar.visibility = View.GONE
        imgAvatar.apply {
            visibility = View.VISIBLE
            setImageDrawable(drawable)
        }
    }

    fun setAvatarBackground(@DrawableRes drawableRes: Int) =
        tvAvatar.setBackgroundResource(drawableRes)

    private fun setTextNameColor(name: String) {
        tvAvatar.backgroundTintList = ColorStateList.valueOf(ColorGenerator().getColor(name))
    }
}