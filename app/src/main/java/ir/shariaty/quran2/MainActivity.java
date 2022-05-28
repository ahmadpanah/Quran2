package ir.shariaty.quran2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import ir.shariaty.quran2.activities.SurahDetailActivity;
import ir.shariaty.quran2.adapter.SurahAdapter;
import ir.shariaty.quran2.common.Common;
import ir.shariaty.quran2.listener.SurahListner;
import ir.shariaty.quran2.model.Surah;
import ir.shariaty.quran2.viewmodel.SurahViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerview;
    private SurahAdapter surahAdapter;
    private List<Surah> list;
    private SurahViewModel surahViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        recyclerview = findViewById(R.id.surahRV);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();

        surahViewModel = new ViewModelProvider(this).get(SurahViewModel.class);
        surahViewModel.getSurah().observe(this, surahResponse -> {
                    for (int i = 0; i < surahResponse.getList().size(); i++) {
                        list.add(new Surah(surahResponse.getList().get(i).getNumber(),
                                String.valueOf(surahResponse.getList().get(i).getEnglishName()),
                                String.valueOf(surahResponse.getList().get(i).getEnglishNameTranslation()),
                                String.valueOf(surahResponse.getList().get(i).getName()),
                                surahResponse.getList().get(i).getNumberOfAyahs()

                        ));
                    }

                    if (list.size() != 0) {
                        surahAdapter = new SurahAdapter(this , list , this::onSurahListener);
                        recyclerview.setAdapter(surahAdapter);
                        surahAdapter.notifyDataSetChanged();
                    }
                });

    }

    private void onSurahListener(int position) {
        Intent intent = new Intent(MainActivity.this, SurahDetailActivity.class);
        intent.putExtra(Common.SURAH_NO, list.get(position).getNumber());

    }
}