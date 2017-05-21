/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.rocketbeans.supermafiosi.core.jury.jurymember;

import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.core.jury.JuryMember;
import tv.rocketbeans.supermafiosi.minigame.MiniGameResult;

/**
 *
 * @author Gterminator
 */
public class Blobby extends JuryMember
{
   public Blobby()
   {
      super("Blobby", Asset.Textures.JURY_BLOB, Asset.Textures.JURY_BLOB_ARM, Asset.Textures.AVATAR_BLOBBY, "dialog.blobby.jury_good", "dialog.blobby.jury_medium", "dialog.blobby.jury_bad");
   }

   @Override
   public String getJudgeText(MiniGameResult minigameresult, String playername)
   {
      float result = minigameresult.getPlayerScore(playername) / minigameresult.getMaximumPoints();
      
      if(result >= 0.8)
      {
         return "dialog.blobby.jury_good";
      }
      else if(result>=0.4)
      {
         return "dialog.blobby.jury_medium";
      }
      else
      {
         return "dialog.blobby.jury_bad";
      }
   }

   @Override
   public boolean getIsGettingBullet(MiniGameResult minigameresult, String playername)
   {
      float result = minigameresult.getPlayerScore(playername) / minigameresult.getMaximumPoints();
      if(result >= 0.3)
      {
         return false;
      }
      return true;
   }
}
