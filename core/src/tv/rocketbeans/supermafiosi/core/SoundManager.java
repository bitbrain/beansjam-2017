/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.rocketbeans.supermafiosi.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import java.util.HashMap;
import java.util.Map.Entry;
import tv.rocketbeans.supermafiosi.assets.Asset;

/**
 *
 * @author Gterminator
 */
public class SoundManager
{

   private static SoundManager instance = null;

   private HashMap<String, Sound> soundMap = new HashMap<String, Sound>();

   private SoundManager()
   {
   }

   public static SoundManager getInstance()
   {
      if (instance == null)
      {
         instance = new SoundManager();
      }
      return instance;
   }

   public void playSound(String key, String path)
   {
      startSound(key, path, false);
   }
   
   public void loopSound(String key, String path)
   {
       startSound(key, path, true);
   }
   
   public void stopSound(String key)
   {
      if(soundMap.get(key) != null)
      {
         soundMap.get(key).stop();
      }
   }
   
   public void stopAllSounds()
   {
      for( Entry<String, Sound> entry: soundMap.entrySet())
      {
         if(entry.getValue() != null)
         {
            entry.getValue().stop();
         }
      }
   }

   private void startSound(String key, String path, boolean loop)
   {
      if (soundMap.get(key) != null)
      {
         throw new RuntimeException("Sound: key: " + key + " path: " + path + " already playing");
      }

      Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
      soundMap.put(key, sound);
      if (loop)
      {
         sound.loop();
      }
      else
      {
         sound.play();
      }

   }

   

}
