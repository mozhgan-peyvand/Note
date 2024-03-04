package com.peivandian.base.util

import com.peivandian.base.R


sealed class Exceptions(open val messageId: Int) : Throwable() {

    data class NoInternetConnectionException(override val messageId: Int = R.string.msg_connect_to_internet) :
        Exceptions(messageId)

    data class GeneralRemoteException(override val messageId: Int = R.string.msg_general_remote_exception) :
        Exceptions(messageId)

}