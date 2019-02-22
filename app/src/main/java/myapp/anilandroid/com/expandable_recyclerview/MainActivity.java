package myapp.anilandroid.com.expandable_recyclerview;

import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import myapp.anilandroid.com.expandable_recyclerview.adapter.HeroAdapter;
import myapp.anilandroid.com.expandable_recyclerview.model.Hero;

public class MainActivity extends AppCompatActivity {

    final String url_data="https://simplifiedcoding.net/demos/marvel/";
    RecyclerView recyclerView;
    HeroAdapter heroAdapter;
    List<Hero> heroes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        recyclerView=(RecyclerView) findViewById( R.id.recyclerview );
        recyclerView.setHasFixedSize( true );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        heroes=new ArrayList<>(  );
         loadHeroes();
    }

    private void loadHeroes() {

        StringRequest stringRequest=new StringRequest( Request.Method.GET, url_data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        Hero hero = new Hero(obj.getString("name"),
                                obj.getString("realname"),
                                obj.getString("team"),
                                obj.getString("firstappearance"),
                                obj.getString("createdby"),
                                obj.getString("publisher"),
                                obj.getString("imageurl"),
                                obj.getString("bio")
                        );

                        heroes.add(hero);
                    }

                    heroAdapter = new HeroAdapter(heroes, getApplicationContext());
                    recyclerView.setAdapter(heroAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }

    } );
        RequestQueue requestQueue= Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );
    }
}
