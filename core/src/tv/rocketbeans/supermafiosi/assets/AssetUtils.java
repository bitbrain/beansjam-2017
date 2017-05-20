/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.rocketbeans.supermafiosi.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import de.bitbrain.braingdx.assets.SharedAssetManager;

/**
 *
 * @author Gterminator
 */
public class AssetUtils
{
   
   
   public static Vector2 getDimensionOfTexture(String path)
   {
          Texture texture = SharedAssetManager.getInstance().get(path);
          if(texture == null)
          {
             System.out.println("Texture: " + path + " not found!");
             return null;
          }
          
          Vector2 dimension = new Vector2(texture.getWidth(), texture.getHeight());
          return dimension;
   }
   
   public static void playSound(String path, boolean loop)
   {
      final Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
      if(loop)
      {
          sound.loop();
      }
      else
      {
         sound.play();
      }
   }
   
    public static void stopSound(String path, float duration)
    {
       final Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));

    }
   
}
