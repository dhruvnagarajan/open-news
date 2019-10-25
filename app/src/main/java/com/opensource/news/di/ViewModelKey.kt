package com.opensource.news.di

import androidx.lifecycle.ViewModel

import dagger.MapKey
import kotlin.reflect.KClass

/**
 * @author Dhruvaraj Nagarajan
 */
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)