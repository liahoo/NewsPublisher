package com.appsflyer.support.publisher

import android.content.Context

object ClickItems {
    lateinit var news: List<ClickItem>
    lateinit var gameAds: List<ClickItem>
    lateinit var gameAdsRetargeting: List<ClickItem>
    fun init(context: Context) {
        news = listOf(
                ClickItem(0, context.getString(R.string.article_text_1), R.mipmap.skittlesstill),
                ClickItem(1, null, R.mipmap.mm, "http://bit.do/es7BM?clickid={epoch-time-adid}"),
                ClickItem(0, context.getString(R.string.article_text_2), R.mipmap.the_definitive_market_leader),
                ClickItem(1, "Buy Skittles in an App", null, "http://bit.do/es7BD?clickid={epoch-time-adid}"),
                ClickItem(0, context.getString(R.string.article_text_3), R.mipmap.kindness_add_up),
                ClickItem(1, "Buy M&Ms in an App", null, "http://bit.do/es7Bt?advertising_id={adid}&clickid={epoch-time-adid}"),
                ClickItem(0, context.getString(R.string.article_text_4), R.mipmap.salted_caramel),
                ClickItem(1, "Download the Candy Shopping App Now", null, "http://bit.do/es7Bn?advertising_id={adid}&clickid={epoch-time-adid}"),
                ClickItem(0, context.getString(R.string.article_text_5), R.mipmap.tinder),
                ClickItem(1, null, R.mipmap.skittles, "http://bit.do/es7Bm?advertising_id={adid}&clickid={epoch-time-adid}")
        )
        gameAds = listOf(
                ClickItem(1, null, R.mipmap.mm,
                        targetUrl = "http://bit.do/eCfwa?advertising_id={adid}&clickid={epoch-time-adid}",
                        impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=dlnow&af_ad=MM&af_adset_id=iMM&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=22&advertising_id={adid}&clickid={epoch-time-adid}"),
                ClickItem(1, "Buy Skittles in an App", null,
                        targetUrl = "http://bit.do/eCfwd?advertising_id={adid}&clickid={epoch-time-adid}",
                        impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=dlnow&af_ad=Skittles&af_adset_id=t3&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=15&af_fingerprint_attribution=false&advertising_id={adid}&clickid={epoch-time-adid}"),
                ClickItem(1, "Buy M&Ms in an App", null,
                        targetUrl = "http://bit.do/eCfwh?advertising_id={adid}&clickid={epoch-time-adid}",
                        impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=dlnow&af_ad=MM&af_adset_id=t2&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=12&advertising_id={adid}&clickid={epoch-time-adid}"),
                ClickItem(1,"Download the Candy Shopping App Now", null,
                        targetUrl = "http://bit.do/eCfwj?advertising_id={adid}&clickid={epoch-time-adid}",
                        impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=dlnow&af_ad=General&af_adset_id=t1&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=21&advertising_id={adid}&clickid={epoch-time-adid}"),
                ClickItem(1, null, R.mipmap.skittles,
                        targetUrl = "http://bit.do/eCfwm?advertising_id={adid}&clickid={epoch-time-adid}",
                        impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=dlnow&af_ad=Skittles&af_adset_id=iSK&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=29&advertising_id={adid}&clickid={epoch-time-adid}"),
                ClickItem(1, null, R.mipmap.mm_discount,
                        targetUrl = "https://candyapp.onelink.me/4IvE?pid=ypartner_int&c=publisherapps&af_adset=deferreddeeplink&af_ad=MM&af_adset_id=iMM-ddl&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=12&af_dp=candyapp%3A%2F%2FMM&advertising_id={adid}&clickid={epoch-time-adid}",
                        impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=deferreddeeplink&af_ad=MM&af_adset_id=iMM-ddl&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=12&advertising_id={adid}&clickid={epoch-time-adid}"),
                ClickItem(1, null, R.mipmap.skittles_discount,
                        targetUrl = "https://candyapp.onelink.me/4IvE?pid=ypartner_int&c=publisherapps&af_adset=deferreddeeplink&af_ad=Skittles&af_adset_id=iSK-ddl&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=19&advertising_id={adid}&clickid={epoch-time-adid}",
                        impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=deferreddeeplink&af_ad=Skittles&af_adset_id=iSK-ddl&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=19&advertising_id={adid}&clickid={epoch-time-adid}")
        )
        gameAdsRetargeting = listOf(
                ClickItem(1, null, R.mipmap.mm_discount_dp,
                        targetUrl = "https://candyapp.onelink.me/4IvE?pid=ypartner_int&c=publisherapps&af_adset=deeplink&af_ad=MM&af_adset_id=iMM-ddl&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=12&af_dp=candyapp%3A%2F%2FMM&discount=15&advertising_id={adid}&clickid={epoch-time-adid}",
                        impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=deeplink&af_ad=MM&af_adset_id=iMM-ddl&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=12&is_retargeting=true&advertising_id={adid}&clickid={epoch-time-adid}"),
                ClickItem(1, null, R.mipmap.skittles_discount_dp,
                        targetUrl = "https://app.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=deeplink&af_ad=Skittles&af_adset_id=iSK-ddl&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=19&is_retargeting=true&af_dp=candyapp%3A%2F%2FSkittles&discount=10&advertising_id={adid}&clickid={epoch-time-adid}",
                        impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=deeplink&af_ad=Skittles&af_adset_id=iSK-ddl&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=19&is_retargeting=true&advertising_id={adid}&clickid={epoch-time-adid}") ,
                ClickItem(1, null, R.mipmap.reengage_id_matching,
                        targetUrl = "https://app.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=reengagement_via_id_matching&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=12&is_retargeting=true&advertising_id={adid}&clickid={epoch-time-adid}",
                        impressUrl = "https://impression.appsflyer.com/com.candyapp.appsflyer?pid=ypartner_int&c=publisherapps&af_adset=reengagement_via_id_matching&af_siteid=CG-123&af_cost_currency=USD&af_cost_value=19&is_retargeting=true&advertising_id={adid}&clickid={epoch-time-adid}")
        )
    }
}