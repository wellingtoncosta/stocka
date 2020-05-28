package io.github.wellingtoncosta.todoapp.data.api

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.ios.Ios

actual val engine: HttpClientEngineFactory<HttpClientEngineConfig> = Ios
