package com.bellaarestakadang.ajr_0372;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.bellaarestakadang.ajr_0372.adapters.PromoAdapter;
import com.bellaarestakadang.ajr_0372.api.ApiClient;
import com.bellaarestakadang.ajr_0372.api.ApiInterface;
import com.bellaarestakadang.ajr_0372.modelUser.User;
import com.bellaarestakadang.ajr_0372.models.PromoResponse;
import com.bellaarestakadang.ajr_0372.preferences.UserPreferences;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PromoActivity extends AppCompatActivity {
    public static Activity promoActivity;
    private UserPreferences userPreferences;
    private PromoAdapter adapter;
    private ApiInterface apiService;
    private SwipeRefreshLayout srPromo;
    private SearchView svPromo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        promoActivity = this;
        setContentView(R.layout.activity_promo);

        setTitle("Promo");
        userPreferences = new UserPreferences(PromoActivity.this);
        apiService = ApiClient.getClient().create(ApiInterface.class);

        srPromo = findViewById(R.id.sr_promo);
        svPromo = findViewById(R.id.sv_promo);

        srPromo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getAllPromo();
            }
        });
        svPromo.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });

        RecyclerView rvPromo = findViewById(R.id.rv_promo);
        adapter = new PromoAdapter(new ArrayList<>(), this);
        rvPromo.setLayoutManager(new LinearLayoutManager(PromoActivity.this,
                LinearLayoutManager.VERTICAL, false));
        rvPromo.setAdapter(adapter);

        getAllPromo();
    }

    private void getAllPromo(){
        Call<PromoResponse> call = apiService.getAllPromo();
        srPromo.setRefreshing(true);
        call.enqueue(new Callback<PromoResponse>() {
            @Override
            public void onResponse(Call<PromoResponse> call, Response<PromoResponse> response) {
                if (response.isSuccessful()) {
                    adapter.setPromoList(response.body().getPromoList());
                    adapter.getFilter().filter(svPromo.getQuery());
                }else {
                    try {
                        JSONObject jObjError = new
                                JSONObject(response.errorBody().string());
                        Toast.makeText(PromoActivity.this,
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(PromoActivity.this,
                                e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                srPromo.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<PromoResponse> call, Throwable t) {
                Toast.makeText(PromoActivity.this, "Network error",
                        Toast.LENGTH_SHORT).show();
                srPromo.setRefreshing(false);
            }
        });
    }
}