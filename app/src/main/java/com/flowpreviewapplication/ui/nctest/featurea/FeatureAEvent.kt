package com.flowpreviewapplication.ui.nctest.featurea

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class FeatureAEvent {

    @Parcelize
    data class DisplayContent(val content: String) : FeatureAEvent(), Parcelable

    object Initial : FeatureAEvent()

    object ActionFirst : FeatureAEvent()

    object ActionSecond : FeatureAEvent()
    object ActionThird : FeatureAEvent()

    object BackAction : FeatureAEvent()

}