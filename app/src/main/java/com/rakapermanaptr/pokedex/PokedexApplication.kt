package com.rakapermanaptr.pokedex

import android.app.Application
import com.rakapermanaptr.data.di.dataModules
import com.rakapermanaptr.data.di.ktorModule
import com.rakapermanaptr.domain.di.useCaseModules
import com.rakapermanaptr.presentation.di.viewModelModules
import org.koin.core.context.startKoin

class PokedexApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                ktorModule,
                *dataModules.toTypedArray(),
                *useCaseModules.toTypedArray(),
                *viewModelModules.toTypedArray()
            )
        }
    }
}