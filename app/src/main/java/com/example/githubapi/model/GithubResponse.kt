package com.example.githubapi.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class GithubResponse(
    val name: String,
    val html_url: String,
    val description: String,
    val created_at: String,
    val owner: Owner
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(Owner::class.java.classLoader)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(html_url)
        parcel.writeString(description)
        parcel.writeString(created_at)
        parcel.writeParcelable(owner, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GithubResponse> {
        override fun createFromParcel(parcel: Parcel): GithubResponse {
            return GithubResponse(parcel)
        }

        override fun newArray(size: Int): Array<GithubResponse?> {
            return arrayOfNulls(size)
        }
    }
}
