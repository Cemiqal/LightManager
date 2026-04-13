package config;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigLoader {

   public Config loadConfig() {
      Gson gson = new Gson();
      try {
         String path = "config.example.json";
         if(Files.exists(Path.of("config.json"))) path = "config.json";

         BufferedReader reader = new BufferedReader(new FileReader(path));
         return gson.fromJson(reader, Config.class);
      } catch (FileNotFoundException e) {
         throw new RuntimeException(e);
      }
   }
}
