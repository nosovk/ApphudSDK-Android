package com.apphud.sdk.storage

import com.android.billingclient.api.SkuDetails
import com.apphud.sdk.ApphudUserProperty
import com.apphud.sdk.domain.*

interface Storage {
    var lastRegistration: Long
    var userId: String?
    var deviceId: String?
    var customer: Customer?
    var advertisingId: String?
    var isNeedSync: Boolean
    var facebook: FacebookInfo?
    var firebase: String?
    var appsflyer: AppsflyerInfo?
    var adjust: AdjustInfo?
    var productGroups: List<ApphudGroup>?
    var paywalls: List<ApphudPaywall>?
    var skuDetails: List<String>?
    var properties: HashMap<String, ApphudUserProperty>?
}