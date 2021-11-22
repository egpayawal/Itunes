package com.egpayawal.itunesexam.ui.ext

import com.facebook.shimmer.ShimmerFrameLayout

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
fun ShimmerFrameLayout.startOrStopShimmer(b: Boolean) {
    if (b) {
        this.startShimmer()
    } else {
        this.stopShimmer()
    }
}