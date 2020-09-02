package com.apphud.sdk.domain

enum class ApphudKind {
    NONE,
    NONRENEWABLE,
    AUTORENEWABLE;

    companion object {
        fun map(source: String?) =
            values().find { it.name == source } ?: NONE
    }
}