package ir.shariaty.quran2.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import ir.shariaty.quran2.repository.SurahRepo;
import ir.shariaty.quran2.repository.SurahResponse;

public class SurahViewModel extends ViewModel {

    private SurahRepo surahRepo;

    public SurahViewModel() {
        surahRepo =  new SurahRepo();
    }

    public LiveData <SurahResponse> getSurah() {
        return surahRepo.getSurah();
    }
}
