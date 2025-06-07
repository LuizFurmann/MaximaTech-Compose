package com.furmannsoft.maximatech.di

import com.furmannsoft.maximatech.repository.ShoesRepository
import com.furmannsoft.maximatech.viewModel.ShoesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { ShoesRepository() }
    viewModel { ShoesViewModel(get()) }
}