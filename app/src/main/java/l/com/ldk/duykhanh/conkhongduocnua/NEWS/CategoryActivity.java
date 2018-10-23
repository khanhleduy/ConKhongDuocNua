package l.com.ldk.duykhanh.conkhongduocnua.NEWS;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import l.com.ldk.duykhanh.conkhongduocnua.NEWS.ADAPTER.CategoryAdapter;
import l.com.ldk.duykhanh.conkhongduocnua.NEWS.MODEL.Category;
import l.com.ldk.duykhanh.conkhongduocnua.R;

public class CategoryActivity extends AppCompatActivity implements CategoryAdapter.OnItemClickListener {

    private RecyclerView rcvCategory;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rcvCategory = findViewById(R.id.rcvCategory);
        rcvCategory.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        categoryList = new ArrayList<>();
        categoryList.add(new Category(R.drawable.world, "Thế giới"));
        categoryList.add(new Category(R.drawable.health, "Sức khỏe"));
        categoryList.add(new Category(R.drawable.startup, "Thời sự"));
        categoryList.add(new Category(R.drawable.sport, "Thể thao"));
        categoryList.add(new Category(R.drawable.technology, "Công nghệ"));
        categoryList.add(new Category(R.drawable.car, "Xe"));
        categoryAdapter = new CategoryAdapter(CategoryActivity.this, this);
        categoryAdapter.setCategoryList(categoryList);
        rcvCategory.setAdapter(categoryAdapter);
    }

    @Override
    public void onItemClick(Category category) {
        Intent intent = new Intent(CategoryActivity.this, NewsActivity.class);
        intent.putExtra("Title", category.getTitle());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


