package com.dhruvnagarajan.androidplatform.di

import javax.inject.Qualifier

/**
 * @author Dhruvaraj Nagarajan
 */
@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Activity

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Fragment

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Application

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class Disk