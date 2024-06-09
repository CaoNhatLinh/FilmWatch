package com.appxemphim.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.appxemphim.Api.ApiClient;
import com.appxemphim.R;
import com.appxemphim.adapters.FavoriteListAdapter;
import com.appxemphim.data.Phim;
import com.appxemphim.data.YeuThich;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteListActivity extends AppCompatActivity {

    private ListView listView;
    private FavoriteListAdapter adapter;
    private int currentUserId;
    private List<Phim> phimYeuThichList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);

        listView = findViewById(R.id.listView);
        currentUserId = getIntent().getIntExtra("MaNguoiDung", 0);
        phimYeuThichList = new ArrayList<>();

        loadFavoriteList();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Phim selectedPhim = phimYeuThichList.get(position);
                // Chuyển sang màn hình chi tiết phim và truyền thông tin của bộ phim đó
//                Intent intent = new Intent(FavoriteListActivity.this, ChiTietPhimActivity.class);
//                intent.putExtra("phim", selectedPhim);
//                startActivity(intent);
            }
        });
    }

    private void loadFavoriteList() {
        Call<List<YeuThich>> call = ApiClient.apiClient.getListYeuThichByUserId(currentUserId);
        call.enqueue(new Callback<List<YeuThich>>() {
            @Override
            public void onResponse(Call<List<YeuThich>> call, Response<List<YeuThich>> response) {
                if (response.isSuccessful()) {
                    List<YeuThich> favoriteList = response.body();
                    if (favoriteList != null && !favoriteList.isEmpty()) {
                        for (YeuThich yeuThich : favoriteList) {
                            getPhimInfo(yeuThich.getMaPhim());
                        }
                    } else {
                        // Hiển thị thông báo rằng không có phim yêu thích nào
                    }
                } else {
                    // Xử lý khi không thể lấy danh sách yêu thích từ API
                }
            }

            @Override
            public void onFailure(Call<List<YeuThich>> call, Throwable t) {
                // Xử lý khi có lỗi xảy ra trong quá trình gửi yêu cầu lấy danh sách yêu thích từ API
            }
        });
    }

    private void getPhimInfo(int maPhim) {
        Call<Phim> call = ApiClient.apiClient.getPhimById(maPhim);
        call.enqueue(new Callback<Phim>() {
            @Override
            public void onResponse(Call<Phim> call, Response<Phim> response) {
                if (response.isSuccessful()) {
                    Phim phim = response.body();
                    if (phim != null) {
                        phimYeuThichList.add(phim);
                        adapter = new FavoriteListAdapter(FavoriteListActivity.this, phimYeuThichList);
                        listView.setAdapter(adapter);
                    } else {
                        // Xử lý khi không tìm thấy thông tin của phim
                    }
                } else {
                    // Xử lý khi không thể lấy thông tin phim từ API
                }
            }

            @Override
            public void onFailure(Call<Phim> call, Throwable t) {
                // Xử lý khi có lỗi xảy ra trong quá trình gửi yêu cầu lấy thông tin phim từ API
            }
        });
    }

    private void removeFavorite(Phim phim) {
        // Lấy mã phim từ đối tượng Phim
        int maPhim = phim.getMaPhim(); // Thay thế bằng trường tương ứng nếu có

        // Gọi phương thức API để xóa yêu thích cho bộ phim
        Call<Void> call = ApiClient.apiClient.deleteYeuThich(currentUserId, maPhim);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Xóa thành công, cập nhật lại danh sách yêu thích và adapter
                    phimYeuThichList.remove(phim);
                    adapter.notifyDataSetChanged();
                } else {
                    // Xử lý khi không thể xóa yêu thích
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Xử lý khi có lỗi xảy ra trong quá trình gửi yêu cầu xóa yêu thích
            }
        });
    }

}
