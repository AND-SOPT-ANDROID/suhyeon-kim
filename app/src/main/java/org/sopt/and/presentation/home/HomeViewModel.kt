package org.sopt.and.presentation.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.sopt.and.R
import org.sopt.and.data.local.TodayTopData
import org.sopt.and.domain.type.HomeTabType
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {
    val homeTabText = HomeTabType.entries.toTypedArray()

    val editorDummy = List(20) {
        TodayTopData(
            painterId = R.drawable.iv_editor_recommended_work,
            ranking = it + 1
        )
    }

    val top20Dummy = List(20) {
        TodayTopData(
            painterId = R.drawable.iv_today_top_20,
            ranking = it + 1
        )
    }
}