package com.example.gamer.ejemploreproductor;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    YouTubePlayerView youTubePlayerView;
    String claveyoutube="AIzaSyCeVVz9o7CiURJ2wa-XXKOEgbWhjSXV_nY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Resources res = getResources();
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("",
                res.getDrawable(R.drawable.ic_home));
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("",
                res.getDrawable(R.drawable.ic_usuario));
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

                if(tabId=="mitab1"){
                    Toast.makeText(getApplication(),"Inicio",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplication(),"usuario",Toast.LENGTH_SHORT).show();
                }

            }
        });

        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.youtube_view);
        youTubePlayerView.initialize(claveyoutube, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean restaurado) {
        if(!restaurado){
            youTubePlayer.cueVideo("CJinWua98NA");

        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            if(youTubeInitializationResult.isUserRecoverableError()){
                youTubeInitializationResult.getErrorDialog(this, 1).show();
            } else{
                String Error= "Error al inicializar youtube"+ youTubeInitializationResult.toString();
                Toast.makeText(getApplication(), Error, Toast.LENGTH_SHORT).show();
            }
    }

    protected  void onActivityResult(int requestcode , int resultcode , int  intentdata){

        if(resultcode==1){
            getyoutubeplayerporvider().initialize(claveyoutube, this);
        }
    }
    protected   YouTubePlayer.Provider getyoutubeplayerporvider(){
        return  youTubePlayerView;
    }
}
