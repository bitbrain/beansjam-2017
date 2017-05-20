package tv.rocketbeans.supermafiosi.assets;


public interface Asset
{

   // TODO
   public interface Textures
   {

      /**
       * Menue
       */
      String BUTTON_9PATCH = "textures/button.9.png";
      String LOGO = "textures/logo.png";

      /**
       * Ingame
       */
      String JURY_BACKGROUND = "textures/Jury_bg.png";
      String JURY_TABLE = "textures/Jury_table.png";
      String JURY_UNDECIDED = "textures/Jury_nonbullet.png";
      String JURY_NONBULLET = "textures/Jury_nonbullet.png";
      String JURY_BULLET = "textures/Jury_bullet.png";
      
      String JURY_RICK = "textures/jury_rick.png";
      String JURY_RICK_ARM = "textures/jury_rick_arm.png";
      
      String JURY_LASER = "textures/jury_laser.png";
      String JURY_LASER_ARM = "textures/jury_laser_hands.png";
      
      String JURY_BLOB = "textures/jury_blob.png";
      String JURY_BLOB_ARM = "textures/jury_blob_hands.png";
      
      String DUMMY = "textures/dummy.png";
      String STAGE = "textures/stage.png";
      String AVATAR_01 = "textures/avatar_01.png";
      String LABEL_9PATCH = "textures/label.9.png";
      /**
       * Intro
       */
      String KRANKENBETT = "textures/krankenbett.png";
      String OLDDON = "textures/OldDon.png";
      String KAPPO = "textures/Kappo.png";
   }

   public interface Sounds
   {
      String AUDIANCE_BOO = "sounds/audience_boo.ogg";
      String AUDIANCE_CLAPPING1 = "sounds/audience_clapping_1.ogg";
      String AUDIANCE_CLAPPING2 = "sounds/audience_clapping_2.ogg";
      String AUDIANCE_CLAPPING3 = "sounds/audience_clapping_3.ogg";
      String AUDIANCE_DRUMROLL = "sounds/drum_roll.ogg";
   }

   public interface Music
   {

      String DYING_DON = "music/dying_don_Main.ogg";
      String MENU_CHAR_SELECT = "music/opener_char-select_Intro.ogg";
      String TENSION = "music/tension_1_Main.ogg";
   }

   public interface Fonts
   {

      String EIGHT_BIT_WONDER = "fonts/8bitwonder.ttf";
   }
}
