package com.egpayawal.itunesexam.sealedclass

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
sealed class ListItemView<out T> {

    data class ItemView<T>(val item: T) : ListItemView<T>()

    object LoadingView : ListItemView<Nothing>()

    object EndView : ListItemView<Nothing>()

}
