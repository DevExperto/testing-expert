package com.devexperto.testingexpert.di

import androidx.compose.runtime.NoLiveLiterals
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

/* This annotation is here because a bug when running tests with Hilt and Compose.
 * See https://stackoverflow.com/a/71189923 */
@NoLiveLiterals

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppExtrasModule::class]
)
@Module
object UnitTestAppExtrasModule : TestAppExtrasModule