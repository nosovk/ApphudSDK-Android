package com.apphud.sdk.client

import com.apphud.sdk.ApiKey
import com.apphud.sdk.body.*
import com.apphud.sdk.client.dto.AttributionDto
import com.apphud.sdk.client.dto.CustomerDto
import com.apphud.sdk.client.dto.ResponseDto
import com.google.gson.reflect.TypeToken

/**
 * Сервис для работы с apphud api
 *
 * Пока используется TypeToken из библиотеки Gson. Нужно будет посмотреть как от этого уйти
 */
class ApphudServiceV1(
    private val apiKey: ApiKey,
    private val executor: NetworkExecutor
) {

    companion object {
        private const val API_KEY = "api_key"
    }

    /**
     * Регистрация юзера
     */
    fun registration(body: RegistrationBody): ResponseDto<CustomerDto> = executor.call(
        RequestConfig(
            path = "customers",
            type = object : TypeToken<ResponseDto<CustomerDto>>() {}.type,
            queries = mapOf(API_KEY to apiKey),
            requestType = RequestType.POST
        ),
        body
    )

    /**
     * Отправка атрибуции
     */
    fun send(body: AttributionBody): ResponseDto<AttributionDto> =
        executor.call(
            RequestConfig(
                path = "customers/attribution",
                type = object : TypeToken<ResponseDto<AttributionDto>>(){}.type,
                queries = mapOf(API_KEY to apiKey),
                requestType = RequestType.POST
            ),
            body
        )

    /**
     * Отправка пуш токена
     */
    fun send(body: PushBody): ResponseDto<AttributionDto> =
        executor.call(
            RequestConfig(
                path = "customers/push_token",
                type = object : TypeToken<ResponseDto<AttributionDto>>(){}.type,
                queries = mapOf(API_KEY to apiKey),
                requestType = RequestType.PUT
            ),
            body
        )

    /**
     * Отправка данных после успешной покупки
     */
    fun purchase(body: PurchaseBody): ResponseDto<CustomerDto> =
        executor.call(
            RequestConfig(
                path = "subscriptions",
                type = object : TypeToken<ResponseDto<CustomerDto>>() {}.type,
                queries = mapOf(API_KEY to apiKey),
                requestType = RequestType.POST
            ),
            body
        )

    /**
     * Отправка user property
     */
    fun sendUserProperties(body: UserPropertiesBody): ResponseDto<AttributionDto> =
        executor.call(
            RequestConfig(
                path = "customers/properties",
                type = object : TypeToken<ResponseDto<AttributionDto>>(){}.type,
                queries = mapOf(API_KEY to apiKey),
                requestType = RequestType.POST
            ),
            body
        )

    /**
     * Sending Error Logs
     */
    fun sendErrorLogs(body: ErrorLogsBody): ResponseDto<AttributionDto> =
        executor.call(
            RequestConfig(
                path = "logs",
                type = object : TypeToken<ResponseDto<AttributionDto>>(){}.type,
                queries = mapOf(API_KEY to apiKey),
                requestType = RequestType.POST
            ),
            body
        )

    fun sendPaywallEvent(body: PaywallEventBody): ResponseDto<AttributionDto> =
        executor.call(
            RequestConfig(
                path = "events",
                type = object : TypeToken<ResponseDto<AttributionDto>>(){}.type,
                queries = mapOf(API_KEY to apiKey),
                requestType = RequestType.POST
            ),
            body
        )
}