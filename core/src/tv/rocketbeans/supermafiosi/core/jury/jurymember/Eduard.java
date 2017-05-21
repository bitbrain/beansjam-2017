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
public class Eduard extends JuryMember
{
   public Eduard()
   {
      super("Eduard Laser", Asset.Textures.JURY_LASER, Asset.Textures.JURY_LASER_ARM, Asset.Textures.AVATAR_EDUARD_LASER, "dialog.eduardlaser.jury_good", "dialog.eduardlaser.jury_medium", "dialog.eduardlaser.jury_bad");
   }

   @Override
   public String getJudgeText(MiniGameResult minigameresult)
   {
      return "dialog.eduardlaser.jury_bad";
   }

   @Override
   public boolean getIsGettingBullet(MiniGameResult minigameresult)
   {
      return true;
   }
}
