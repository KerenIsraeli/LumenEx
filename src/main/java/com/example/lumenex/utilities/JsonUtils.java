package com.example.lumenex.utilities;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import androidx.annotation.Nullable;

public class JsonUtils {

    // region Public Methods                                                                           

    /**
     * Method reads list from json.                                                                    
     *
     * @param path of json to read.                                                                    
     * @param type the class of the items to parse.                                                    
     * @param converters helps converting read-data to items.                                          
     * @param <T> type of the items to read.                                                           
     *
     * @return collection of read items.                                                               
     */
    @Nullable
    public static <T> List<T> readSubListFromJson(String path, Class<T> type, Object... converters) {
        List<T> list = null;

        JsonAdapter<List<T>> adapter = createListJsonAdapter(type, converters);

        try {
            String sublist = convertJsonString(path);
            list = adapter.fromJson(sublist);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    // endregion                                                                                       

    // region Private Methods

    /**
     * Create json adapter to read list of given type.                                                 
     *
     * @param type of instane to read list of.                                                         
     * @param adapters help converting the read json items to the actual objects.                      
     * @param <T> the type of items to read.                                                           
     *
     * @return the json adapter helps reading list of items.                                           
     */
    private static <T> JsonAdapter<List<T>> createListJsonAdapter(Class type, Object... adapters) {
        Type listToWriteType = Types.newParameterizedType(List.class, type);

        return createMoshiBuilder(adapters).build().adapter(listToWriteType );
    }

    /**
     * Create a moshi builder, which helps converting json types, using the given type converters.     
     *
     * @param adapters to convert json to types, set in moshi.                                         
     *
     * @return the moshi builder.                                                                      
     */
    private static Moshi.Builder createMoshiBuilder(Object... adapters) {
        Moshi.Builder builder = new Moshi.Builder();

        for (Object adapter : adapters) {
            builder.add(adapter);
        }

        return builder;
    }

    /**
     * Read the json in the given path.                                                                
     *
     * @param path of json to read.                                                                    
     *
     * @return read json.                                                                              
     * @throws IOException for readaing error,                                                         
     */
    public static String convertJsonString(String path) throws IOException {
        StringBuilder json= new StringBuilder();
        Scanner sc = new Scanner(new File(path));

        while (sc.hasNext()) {
            json.append(sc.next());
        }

        return json.toString();
    }
}
