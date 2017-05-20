/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.rocketbeans.supermafiosi.assets;

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
   
}
