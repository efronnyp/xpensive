package com.efronnypardede.xpensive.di

import javax.inject.Qualifier

@Qualifier
@Target(AnnotationTarget.FIELD, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class LocalXpenseSourceDataSource
