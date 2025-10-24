package com.unsoed.responsi1mobile_h1d023119.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class TeamResponse(
    @SerializedName("id")
    val id: Int,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("shortName")
    val shortName: String,
    
    @SerializedName("tla")
    val tla: String,
    
    @SerializedName("crest")
    val crest: String,
    
    @SerializedName("address")
    val address: String,
    
    @SerializedName("website")
    val website: String,
    
    @SerializedName("founded")
    val founded: Int,
    
    @SerializedName("clubColors")
    val clubColors: String,
    
    @SerializedName("venue")
    val venue: String,
    
    @SerializedName("coach")
    val coach: Coach,
    
    @SerializedName("squad")
    val squad: List<Player>
)

data class Coach(
    @SerializedName("id")
    val id: Int?,
    
    @SerializedName("firstName")
    val firstName: String?,
    
    @SerializedName("lastName")
    val lastName: String?,
    
    @SerializedName("name")
    val name: String?,
    
    @SerializedName("dateOfBirth")
    val dateOfBirth: String?,
    
    @SerializedName("nationality")
    val nationality: String?,
    
    @SerializedName("contract")
    val contract: Contract?
)

data class Contract(
    @SerializedName("start")
    val start: String?,
    
    @SerializedName("until")
    val until: String?
)

@Parcelize
data class Player(
    @SerializedName("id")
    val id: Int,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("position")
    val position: String,
    
    @SerializedName("dateOfBirth")
    val dateOfBirth: String,
    
    @SerializedName("nationality")
    val nationality: String
) : Parcelable
