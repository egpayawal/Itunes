package com.egpayawal.itunesexam.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
open class Movie : RealmObject() {
    @PrimaryKey
    var trackId: Long = 0L
    var wrapperType: String? = null
    var kind: String? = null
    var artistName: String? = null
    var collectionName: String? = null
    var trackName: String? = null
    var collectionCensoredName: String? = null
    var trackCensoredName: String? = null
    var collectionArtistId: Long = 0L
    var collectionArtistViewUrl: String? = null
    var collectionViewUrl: String? = null
    var trackViewUrl: String? = null
    var previewUrl: String? = null
    var artworkUrl30: String? = null
    var artworkUrl60: String? = null
    var artworkUrl100: String? = null
    var collectionPrice: Double = 0.0
    var trackPrice: Double = 0.0
    var trackRentalPrice: Double = 0.0
    var collectionHdPrice: Double = 0.0
    var trackHdPrice: Double = 0.0
    var trackHdRentalPrice: Double = 0.0
    var releaseDate: String? = null
    var collectionExplicitness: String? = null
    var trackExplicitness: String? = null
    var discCount: Int = 0
    var discNumber: Int = 0
    var trackCount: Int = 0
    var trackNumber: Int = 0
    var trackTimeMillis: Long = 0L
    var country: String? = null
    var currency: String? = null
    var primaryGenreName: String? = null
    var contentAdvisoryRating: String? = null
    var shortDescription: String? = null
    var longDescription: String? = null
    var hasITunesExtras: Boolean = false

    val displayTitle: String
        get() = trackName ?: collectionName ?: ""

    val displayPrice: String
        get() = "$currency ${if (trackPrice > 0.0) trackPrice else collectionPrice}"

    val displayGenre: String
        get() = "Genre: $primaryGenreName"

    val displaySynopsis: String?
        get() = longDescription ?: shortDescription

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (trackId != other.trackId) return false
        if (wrapperType != other.wrapperType) return false
        if (kind != other.kind) return false
        if (artistName != other.artistName) return false
        if (collectionName != other.collectionName) return false
        if (trackName != other.trackName) return false
        if (collectionCensoredName != other.collectionCensoredName) return false
        if (trackCensoredName != other.trackCensoredName) return false
        if (collectionArtistId != other.collectionArtistId) return false
        if (collectionArtistViewUrl != other.collectionArtistViewUrl) return false
        if (collectionViewUrl != other.collectionViewUrl) return false
        if (trackViewUrl != other.trackViewUrl) return false
        if (previewUrl != other.previewUrl) return false
        if (artworkUrl30 != other.artworkUrl30) return false
        if (artworkUrl60 != other.artworkUrl60) return false
        if (artworkUrl100 != other.artworkUrl100) return false
        if (collectionPrice != other.collectionPrice) return false
        if (trackPrice != other.trackPrice) return false
        if (trackRentalPrice != other.trackRentalPrice) return false
        if (collectionHdPrice != other.collectionHdPrice) return false
        if (trackHdPrice != other.trackHdPrice) return false
        if (trackHdRentalPrice != other.trackHdRentalPrice) return false
        if (releaseDate != other.releaseDate) return false
        if (collectionExplicitness != other.collectionExplicitness) return false
        if (trackExplicitness != other.trackExplicitness) return false
        if (discCount != other.discCount) return false
        if (discNumber != other.discNumber) return false
        if (trackCount != other.trackCount) return false
        if (trackNumber != other.trackNumber) return false
        if (trackTimeMillis != other.trackTimeMillis) return false
        if (country != other.country) return false
        if (currency != other.currency) return false
        if (primaryGenreName != other.primaryGenreName) return false
        if (contentAdvisoryRating != other.contentAdvisoryRating) return false
        if (shortDescription != other.shortDescription) return false
        if (longDescription != other.longDescription) return false
        if (hasITunesExtras != other.hasITunesExtras) return false

        return true
    }

    override fun hashCode(): Int {
        var result = trackId.hashCode()
        result = 31 * result + (wrapperType?.hashCode() ?: 0)
        result = 31 * result + (kind?.hashCode() ?: 0)
        result = 31 * result + (artistName?.hashCode() ?: 0)
        result = 31 * result + (collectionName?.hashCode() ?: 0)
        result = 31 * result + (trackName?.hashCode() ?: 0)
        result = 31 * result + (collectionCensoredName?.hashCode() ?: 0)
        result = 31 * result + (trackCensoredName?.hashCode() ?: 0)
        result = 31 * result + collectionArtistId.hashCode()
        result = 31 * result + (collectionArtistViewUrl?.hashCode() ?: 0)
        result = 31 * result + (collectionViewUrl?.hashCode() ?: 0)
        result = 31 * result + (trackViewUrl?.hashCode() ?: 0)
        result = 31 * result + (previewUrl?.hashCode() ?: 0)
        result = 31 * result + (artworkUrl30?.hashCode() ?: 0)
        result = 31 * result + (artworkUrl60?.hashCode() ?: 0)
        result = 31 * result + (artworkUrl100?.hashCode() ?: 0)
        result = 31 * result + collectionPrice.hashCode()
        result = 31 * result + trackPrice.hashCode()
        result = 31 * result + trackRentalPrice.hashCode()
        result = 31 * result + collectionHdPrice.hashCode()
        result = 31 * result + trackHdPrice.hashCode()
        result = 31 * result + trackHdRentalPrice.hashCode()
        result = 31 * result + (releaseDate?.hashCode() ?: 0)
        result = 31 * result + (collectionExplicitness?.hashCode() ?: 0)
        result = 31 * result + (trackExplicitness?.hashCode() ?: 0)
        result = 31 * result + discCount
        result = 31 * result + discNumber
        result = 31 * result + trackCount
        result = 31 * result + trackNumber
        result = 31 * result + trackTimeMillis.hashCode()
        result = 31 * result + (country?.hashCode() ?: 0)
        result = 31 * result + (currency?.hashCode() ?: 0)
        result = 31 * result + (primaryGenreName?.hashCode() ?: 0)
        result = 31 * result + (contentAdvisoryRating?.hashCode() ?: 0)
        result = 31 * result + (shortDescription?.hashCode() ?: 0)
        result = 31 * result + (longDescription?.hashCode() ?: 0)
        result = 31 * result + hasITunesExtras.hashCode()
        return result
    }


}