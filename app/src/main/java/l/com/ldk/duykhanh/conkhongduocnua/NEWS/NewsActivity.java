package l.com.ldk.duykhanh.conkhongduocnua.NEWS;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import l.com.ldk.duykhanh.conkhongduocnua.NEWS.ADAPTER.NewsAdapter;
import l.com.ldk.duykhanh.conkhongduocnua.NEWS.MODEL.NewsModel;
import l.com.ldk.duykhanh.conkhongduocnua.R;

public class NewsActivity extends AppCompatActivity implements NewsAdapter.OnItemClickListener {

    private RecyclerView rvlistNews;
    private NewsAdapter newsAdapter;
    String titleReceive;

    private final String urlNewsWorld = "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fthanhnien.vn%2Frss%2Fdoi-song%2Fdan-sinh.rss";
    private final String urlNewsHeath = "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fvnexpress.net%2Frss%2Fsuc-khoe.rss";
    private final String urlNewsNews = "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fvnexpress.net%2Frss%2Fthoi-su.rss";
    private final String urlNewsSport = "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fvnexpress.net%2Frss%2Fthe-thao.rss";
    private final String urlTechnology = "https://api.rss2json.com/v1/api.json?rss_url=http%3A%2F%2Ffeeds.twit.tv%2Fbrickhouse.xml";
    private final String urlNewsCar = "https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fvnexpress.net%2Frss%2Foto-xe-may.rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rvlistNews = findViewById(R.id.rvListNews);
        rvlistNews.setLayoutManager(new LinearLayoutManager(this));
        titleReceive = getIntent().getStringExtra("Title");
        getSupportActionBar().setTitle(titleReceive);
        if(titleReceive != null){
            if(titleReceive.equals("Thế giới")) {
                new loadRSS().execute(urlNewsWorld);
            }
            else if(titleReceive.equals("Sức khỏe")) {
                new loadRSS().execute(urlNewsHeath);
            }
            else if(titleReceive.equals("Thời sự")){
                new loadRSS().execute(urlNewsNews);
            }
            else if(titleReceive.equals("Thể thao")) {
                new loadRSS().execute(urlNewsSport);
            }
            else if(titleReceive.equals("Công nghệ")){
                new loadRSS().execute(urlTechnology);
            }
            else if(titleReceive.equals("Xe")){
                new loadRSS().execute(urlNewsCar);
            }
        }
        newsAdapter = new NewsAdapter(NewsActivity.this,this);
        rvlistNews.setAdapter(newsAdapter);
    }

    @Override
    public void onItemClick(NewsModel.NewsBean newsModel) {
        Intent link = new Intent(NewsActivity.this, NewsDetailActivity.class);
        link.putExtra("LINK",newsModel.getLink());
        link.putExtra("CATEGORY",titleReceive);
        startActivity(link);
    }

    class loadRSS extends AsyncTask<String, Void, String> {

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(NewsActivity.this);
            dialog.setMessage("Loading...");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try{
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null){
                    buffer.append(line+"\n");
                }
                return buffer.toString();
            }catch (MalformedURLException e){
                e.printStackTrace();
            }
            catch (IOException e){
                e.printStackTrace();
            }
            finally {
                if(connection != null){
                    connection.disconnect();
                }
                try{
                    if(reader != null){
                        reader.close();
                    }

                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            dialog.dismiss();
            if (json != null) {
                Gson gson = new Gson();
                Type type = new TypeToken<NewsModel>(){

                }.getType();
                NewsModel newsModel = gson.fromJson(json,type);
                List<NewsModel.NewsBean> newsArrayList = newsModel.getItems();
                newsAdapter.setNewsModelList(newsArrayList);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:

                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


