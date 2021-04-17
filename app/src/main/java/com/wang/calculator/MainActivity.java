package com.wang.calculator;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.wang.calculator.listview.ListData;
import com.wang.calculator.listview.MyAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private AdView mAdView;

    AppUpdateManager appUpdateManager;
    private static final int MY_REQUEST_CODE = 100;

    ArrayList<ListData> calculatorDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        this.InitializeMovieData();

        ListView listView = (ListView) findViewById(R.id.listView);
        final MyAdapter myAdapter = new MyAdapter(this, calculatorDataList);

        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), myAdapter.getItem(i).getCalculator_Name(), Toast.LENGTH_LONG).show();

                if (myAdapter.getItem(i).getCalculator_Activity() == Calculator.class) {
                    Intent intent = new Intent(MainActivity.this, Calculator.class);
                    startActivity(intent);
                } else if (myAdapter.getItem(i).getCalculator_Activity() == Calculator1.class) {
                    Intent intent = new Intent(MainActivity.this, Calculator1.class);
                    startActivity(intent);
                } else if (myAdapter.getItem(i).getCalculator_Activity() == Calculator2.class) {
                    Intent intent = new Intent(MainActivity.this, Calculator2.class);
                    startActivity(intent);
                }else if (myAdapter.getItem(i).getCalculator_Activity() == CheckSheet.class) {
                    Intent intent = new Intent(MainActivity.this, CheckSheet.class);
                    startActivity(intent);
                }
            }
        });

        appUpdateManager = AppUpdateManagerFactory.create(getApplicationContext());
        appUpdateManager.registerListener(listener);
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE)) {
                try {
                    appUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo,
                            AppUpdateType.FLEXIBLE,
                            MainActivity.this,
                            MY_REQUEST_CODE
                    );
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                popupSnackbarForCompleteUpdate();
            }
        });
    }

    private InstallStateUpdatedListener listener = state -> {
        // (Optional) Provide a download progress bar.
        if (state.installStatus() == InstallStatus.DOWNLOADING) {
            long bytesDownloaded = state.bytesDownloaded();
            long totalBytesToDownload = state.totalBytesToDownload();
            // Implement progress bar.
        } else if (state.installStatus() == InstallStatus.DOWNLOADED) {
            popupSnackbarForCompleteUpdate();
        } else if (state.installStatus() == InstallStatus.INSTALLED) {
            if (appUpdateManager != null) {
                //appUpdateManager.unregisterListener(listener);
            }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                Log.d(TAG, "Update flow failed! Result code: " + resultCode);
                // If the update is cancelled or fails,
                // you can request to start the update again.
            }
        }
    }

    /* Displays the snackbar notification and call to action. */
    private void popupSnackbarForCompleteUpdate() {
        Snackbar snackbar =
                Snackbar.make(
                        findViewById(R.id.adView),
                        "다운로드완료되었습니다.",
                        Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("설치", view -> {
            if (appUpdateManager != null) {
                appUpdateManager.completeUpdate();
            }
        });
        snackbar.setActionTextColor(
                getResources().getColor(R.color.design_default_color_primary_variant));
        snackbar.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (appUpdateManager != null) {
            appUpdateManager.unregisterListener(listener);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        appUpdateManager
                .getAppUpdateInfo()
                .addOnSuccessListener(appUpdateInfo -> {
                    if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED) {
                        popupSnackbarForCompleteUpdate();
                    }
                });
    }

    public void InitializeMovieData() {
        calculatorDataList = new ArrayList<ListData>();

        calculatorDataList.add(new ListData(R.drawable.baseline_calculate_black_48dp, "0~100% 보기", "LRV와 URV를 입력하면 25% 단위로 출력", Calculator.class));
        calculatorDataList.add(new ListData(R.drawable.baseline_calculate_black_48dp, "mA to percent 계산", "mA를 입력하면 %로 출력", Calculator1.class));
        calculatorDataList.add(new ListData(R.drawable.baseline_calculate_black_48dp, "mA로 계산", "Zero, Span, Value를 입력하면 mA로 출력", Calculator2.class));
        calculatorDataList.add(new ListData(R.drawable.baseline_calculate_black_48dp, "점검시트", "점검시트", CheckSheet.class));
    }


}
