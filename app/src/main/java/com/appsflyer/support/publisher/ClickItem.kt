package com.appsflyer.support.publisher

class ClickItem(
//        override var articleText: String? = null,
//        override var articleImage: Int? = null,
//        override var adText: String? = null,
//        override var adImage: Int? = null,
        override var type: Int,
        override var text: String?,
        override var image: Int?,
        var targetUrl: String? = null,
        var impressUrl: String? = null
//        override var onClick: ((CardBindable) -> Unit)? = null,
//        override var onLongClick: ((CardBindable) -> Unit)? = null,
) : CardBindable {}