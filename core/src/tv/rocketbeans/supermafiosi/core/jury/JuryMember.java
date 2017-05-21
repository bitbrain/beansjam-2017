/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.rocketbeans.supermafiosi.core.jury;

import tv.rocketbeans.supermafiosi.minigame.MiniGameResult;

/**
 *
 * @author Gterminator
 */
public abstract class JuryMember
{
   private String Name;
   private String HeadSprite;
   private String HandsSprite;
   private String AvatarSprite;
   
   private String judgeGoodKey;
   private String judgeMediumKey;
   private String judgeBadKey;
   
   private int tableposition = 1;
   
 
   
   public JuryMember(String name, String headSprite, String handsSprite, String avatarSprite, String judgegoodkey, String judgemediumkey, String judgebadkey)
   {
      this.Name = name;
      this.HeadSprite = headSprite;
      this.HandsSprite = handsSprite;
      this.AvatarSprite = avatarSprite;
      this.judgeGoodKey = judgegoodkey;
      this.judgeMediumKey = judgemediumkey;
      this.judgeBadKey = judgebadkey;
   }
   
   public abstract String getJudgeText(MiniGameResult minigameresult, String playername);
   public abstract boolean getIsGettingBullet(MiniGameResult minigameresult, String playername);

   /**
    * @return the Name
    */
   public String getName()
   {
      return Name;
   }
   
   public String getNameHands()
   {
      return Name + "_Hands";
   }

   /**
    * @return the HeadSprite
    */
   public String getHeadSprite()
   {
      return HeadSprite;
   }

   /**
    * @return the HandsSprite
    */
   public String getHandsSprite()
   {
      return HandsSprite;
   }

   /**
    * @return the AvatarSprite
    */
   public String getAvatarSprite()
   {
      return AvatarSprite;
   }

   /**
    * @return the tableposition
    */
   public int getTableposition()
   {
      return tableposition;
   }

   /**
    * @param tableposition the tableposition to set
    */
   public void setTableposition(int tableposition)
   {
      this.tableposition = tableposition;
   }
}
