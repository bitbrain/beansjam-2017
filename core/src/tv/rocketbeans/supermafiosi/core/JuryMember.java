/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.rocketbeans.supermafiosi.core;

/**
 *
 * @author Gterminator
 */
public class JuryMember
{
   private String Name;
   private String HeadSprite;
   private String HandsSprite;
   private String AvatarSprite;
   
   public JuryMember(String name, String headSprite, String handsSprite, String avatarSprite)
   {
      this.Name = name;
      this.HeadSprite = headSprite;
      this.HandsSprite = handsSprite;
      this.AvatarSprite = avatarSprite;
   }

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
}
