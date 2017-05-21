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
public class Rick extends JuryMember
{
   public Rick()
   {
      super("Rick Sanchez", Asset.Textures.JURY_RICK, Asset.Textures.JURY_RICK_ARM, Asset.Textures.AVATAR_RICK, "dialog.rick.jury_good", "dialog.rick.jury_medium", "dialog.rick.jury_bad");
   }

   @Override
   public String getJudgeText(MiniGameResult minigameresult)
   {
      return "dialog.rick.jury_bad";
   }

   @Override
   public boolean getIsGettingBullet(MiniGameResult minigameresult)
   {
      return true;
   }
}
