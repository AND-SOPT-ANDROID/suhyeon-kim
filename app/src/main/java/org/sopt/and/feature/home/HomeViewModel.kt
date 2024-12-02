package org.sopt.and.feature.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import org.sopt.and.R
import org.sopt.and.data.remote.model.TodayTopData

class HomeViewModel : ViewModel() {
    private val _homeTabText = listOf(
        R.string.new_classic,
        R.string.drama,
        R.string.variety_show,
        R.string.movie,
        R.string.animation,
        R.string.overseas_series
    )

    val homeTabText: List<String>
        @Composable
        get() = _homeTabText.map { stringResource(it) }

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