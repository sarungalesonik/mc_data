
import com.example.mynetwok.MarsPhoto
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET


interface ApiService {
    @GET("photos")
    suspend fun fetchMarsPhotos(): List<MarsPhoto>
}

