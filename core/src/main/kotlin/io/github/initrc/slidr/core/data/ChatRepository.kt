package io.github.initrc.slidr.core.data

import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class ChatRepository() {

    // https://documenter.getpostman.com/view/8854915/Szf7znEe#068625c4-95f6-4e06-afb3-7ca055e72eb0
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://universities.hipolabs.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val universityService = retrofit.create(UniversityService::class.java)

    suspend fun searchUniversities(name: String) = universityService.searchUniversities(name)

}

interface UniversityService {
    @GET("/search")
    suspend fun searchUniversities(@Query("name") name: String): List<University>
}

data class University(
    @SerializedName("alpha_two_code")
    var alphaTwoCode: String,
    @SerializedName("domains")
    var domains: List<String>,
    @SerializedName("web_pages")
    var webPages: List<String>,
    @SerializedName("name")
    var name: String,
    @SerializedName("state-province")
    var stateProvince: String?,
    @SerializedName("country")
    var country: String,
)
