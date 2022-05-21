package ir.shariaty.quran2.network;

import ir.shariaty.quran2.repository.SurahResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    @GET("surah")
    Call<SurahResponse> getSurah();
}
