package com.hexascribe.vertexai.di.module

import com.hexascribe.vertexai.data.api.VertexApi
import com.hexascribe.vertexai.data.repository.VertexRepository
import com.hexascribe.vertexai.di.provider.HttpClientProvider
import com.hexascribe.vertexai.features.TextRequest
import com.hexascribe.vertexai.features.impl.TextRequestImpl
import com.hexascribe.vertexai.initializer.BuilderParams
import com.hexascribe.vertexai.network.infrastructure.ApiExecutor
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

internal class LibraryModule(private val builderParams: BuilderParams) {

    fun modules(): List<Module> =
        featureModule + dataModule

    private val featureModule = module {
        factory<TextRequest> { TextRequestImpl(Dispatchers.Default, get()) }
    }

    private val dataModule = module {
        single { HttpClientProvider(builderParams).provide() }
        factory { ApiExecutor(get()) }
        factory { VertexApi(builderParams.projectId, builderParams.region, get()) }
        factory { VertexRepository(get()) }
    }
}
