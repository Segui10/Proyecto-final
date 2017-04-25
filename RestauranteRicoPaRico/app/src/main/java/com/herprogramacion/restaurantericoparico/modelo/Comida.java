
package com.herprogramacion.restaurantericoparico.modelo;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

/**
 * Modelo de datos estático para alimentar la aplicación
 */
public class Comida {
    private String openLibraryId;
    private String Rating;
    private String nombre;
    private String idDrawable;
    private String descripcion;
    private int position;
    private int voteCount;
    private String date;
    private String img;
    private static final String API_BASE_URL = "https://api.themoviedb.org/3/";
    private AsyncHttpClient client;
    public  String getImg(){return "https://image.tmdb.org/t/p/w185_and_h278_bestv2"+gIMG();}
    public  String getTest(String jpg){return "https://image.tmdb.org/t/p/w185_and_h278_bestv2"+jpg;}
public Comida(){
    this.client = new AsyncHttpClient();
}
    public Comida(String openLibraryId,String Rating, String nombre, String idDrawable , String descripcion , float rating , int position, int voteCount, String date, String img) {
        this.Rating = Rating;
        this.nombre = nombre;
        this.idDrawable = idDrawable;
        this.descripcion = descripcion;
        this.position = position;
        this.voteCount=voteCount;
        this.date=date;
        this.img=img;
        this.openLibraryId=openLibraryId;
    }
    public String getRating() {
        return Rating;
    }
    private String gIMG(){
        return img;
    }
    public String getOpenLibraryId() {
        return openLibraryId;
    }
    public String getNombre() {
        return nombre;
    }

    public String getIdDrawable() {
        return idDrawable;
    }

    public String getDescripcion(){
        return  descripcion;
    }
    public int getVoteCount(){return voteCount;}
    public String getDate(){return date;}

    public int getPosition(){return position;}
    public String getopenLibraryId(){
        return openLibraryId;
    }
    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }
    // Method for accessing books API to get publisher and no. of pages in a book.
    public void getExtraBookDetails(String openLibraryId,int tipo, JsonHttpResponseHandler handler) {
        if(tipo==1) {
            String url = getApiUrl("movie/");
            client.get(url + openLibraryId + "?api_key=2c5b24e9895c627d2e1a2cdaf1c2dbe5&language=en-US", handler);
        } else if (tipo == 2) {
            String url = getApiUrl("tv/");
            client.get(url + openLibraryId + "?api_key=2c5b24e9895c627d2e1a2cdaf1c2dbe5&language=en-US", handler);
        }

    }
    public void  getExtraPersons(String id,int tipo, JsonHttpResponseHandler handler){
        if(tipo==1) {
        String url=getApiUrl("movie/");
        client.get(url+id+"/credits?api_key=2c5b24e9895c627d2e1a2cdaf1c2dbe5", handler);
        } else if (tipo == 2) {
            String url=getApiUrl("tv/");
            client.get(url+id+"/credits?api_key=2c5b24e9895c627d2e1a2cdaf1c2dbe5", handler);
        }
        }

    public void getExtraVideos(String id, JsonHttpResponseHandler handler){
        String url=getApiUrl("movie/");
        client.get(url+id+"/videos?api_key=2c5b24e9895c627d2e1a2cdaf1c2dbe5&language=en-US", handler);
    }
    @Override
    public String toString() {
        return  openLibraryId;
    }
}
