package com.hexascribe.vertexai.initializer

import com.hexascribe.vertexai.VertexAI
import com.hexascribe.vertexai.di.module.LibraryModule
import com.hexascribe.vertexai.features.TextRequest
import org.koin.core.KoinApplication

internal class VertexImpl(builderParams: BuilderParams): VertexAI() {

    val koinApp = KoinApplication.init()

    init {
        val modules = LibraryModule(builderParams).modules()
        koinApp.modules(modules)
    }

    override fun textRequest(): TextRequest {
        return koinApp.koin.get()
    }
}
