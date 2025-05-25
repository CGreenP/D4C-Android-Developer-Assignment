package com.example.shopflow.di

import com.example.shopflow.viewmodel.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Koin module for application-level dependencies.
 * This module provides the necessary dependencies for the application, such as ViewModels.
 */
val appModule = module {
    viewModelOf(::MainViewModel)
}