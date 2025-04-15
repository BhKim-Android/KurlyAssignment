package com.kurly.kurlyassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.kurly.kurlyassignment.domain.usecase.SectionsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    sectionsUseCase: SectionsUseCase
) : ViewModel() {

    val sections = sectionsUseCase().cachedIn(viewModelScope)
}