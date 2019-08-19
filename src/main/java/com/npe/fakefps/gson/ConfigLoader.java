package com.npe.fakefps.gson;

import com.google.gson.Gson;

import java.io.*;

public class ConfigLoader {
    public static Config load(){
        String filename = "FakeFPS.cfg";
        File filecfg = new File(filename);
        try{
            if(!filecfg.exists()){
                filecfg.createNewFile();
                Config c = new Config();
                save(c);
                return c;
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        InputStreamReader r = null;
        try{
            r = new InputStreamReader(new FileInputStream(new File(filename)), "UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Gson().fromJson(r, Config.class);
    }

    public static void save(Config config){
        String filename = "FakeFPS.cfg";
        File f = new File(filename);
        if(!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        OutputStreamWriter wr = null;
        try{
            StringBuilder text = new StringBuilder();
            text.append("//In order to make a fixed FPS - set lockfps to true and change in target the number you need\n");
            text.append("//To do FPS in the interval - set lockfps to false, change min and max value\n");
            wr = new OutputStreamWriter(new FileOutputStream(filename), "UTF-8");
            wr.write(text.toString() + new Gson().toJson(config));
            wr.flush();
            wr.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
