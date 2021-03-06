package com.example.adilos.githubrepo;

/**
 * Created by ADILOS on 10-07-2017.
 */

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the network.
 */
public class NetworkUtils {

    final static String GITHUB_BASE_URL =
            "https://api.github.com/search/repositories";

    final static String PARAM_QUERY = "q";

    /*
     * The sort field. One of stars, forks, or updated.
     * Default: results are sorted by best match if no field is specified.
     */
    final static String PARAM_SORT = "sort";
    final static String sortBy = "stars";

    /**
     * Builds the URL used to query Github.
     *
     * @param githubSearchQuery The keyword that will be queried for.
     * @return The URL to use to query the weather server.
     */
    public static URL buildUrl(String githubSearchQuery) {
        // COMPLETED (1) Fill in this method to build the proper Github query URL
        Uri builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, githubSearchQuery)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        String result = "";
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    in, "UTF-8"));
//            String tmp;
//            StringBuilder sb = new StringBuilder();
//            while ((tmp = reader.readLine()) != null) {
//                sb.append(tmp).append("\n");
//            }
//            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') {
//                sb.setLength(sb.length() - 1);
//            }
//            reader.close();
//            Log.i("JSON",sb.toString());
//            return sb.toString();


            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}