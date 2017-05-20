/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.rocketbeans.supermafiosi.core.jury;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.bitbrain.braingdx.audio.AudioManager;
import de.bitbrain.braingdx.graphics.renderer.SpriteRenderer;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.world.GameObject;
import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.assets.Asset;
import tv.rocketbeans.supermafiosi.assets.AssetUtils;
import tv.rocketbeans.supermafiosi.core.JuryMember;
import tv.rocketbeans.supermafiosi.core.Mafiosi;
import tv.rocketbeans.supermafiosi.i18n.Message;

/**
 *
 * @author Gterminator
 */
public class JuryManager
{

   /**
    * Constants
    */
   private static final String TYPE_JURYBACKGROUND = "JuryBackground";
   private static final String TYPE_JURYTABLE = "JuryTable";
   private static final String TYPE_JURYUNDECIDED = "JuryLetter";
   private static final String TYPE_JURYNONBULLET = "JuryBullet";
   private static final String TYPE_JURYBULLET = "JuryNonBullet";

   private static final Vector2 startPosition = new Vector2(900f, 300f);
   private static final float SCALEFACTOR_TABLE = 0.6f;

   private static JuryManager instance = null;

   private ArrayList<JuryMember> potenzialJuryMembers = new ArrayList<JuryMember>();
   private ArrayList<JuryMember> juryMembers = new ArrayList<JuryMember>();

   private ArrayList<GameObject> juryObjects = new ArrayList<GameObject>();

   private JuryManager()
   {
      setupJury();
   }

   public static JuryManager getInstance()
   {
      if (instance == null)
      {
         instance = new JuryManager();
      }
      return instance;
   }

   private void setupJury()
   {
      potenzialJuryMembers.add(new JuryMember("Keidi Hlumm", Asset.Textures.JURY_RICK, Asset.Textures.JURY_RICK_ARM, Asset.Textures.AVATAR_01));
      potenzialJuryMembers.add(new JuryMember("Kimmy Jimmel", Asset.Textures.JURY_LASER, Asset.Textures.JURY_LASER_ARM, Asset.Textures.AVATAR_01));
      potenzialJuryMembers.add(new JuryMember("Pedro Aldente", Asset.Textures.JURY_BLOB, Asset.Textures.JURY_BLOB_ARM, Asset.Textures.AVATAR_01));

      findJury();
   }

   /**
    * Gets out of the potenzial Jury the jury for the game.
    */
   private void findJury()
   {
      juryMembers.add(potenzialJuryMembers.get(0));
      juryMembers.add(potenzialJuryMembers.get(1));
      juryMembers.add(potenzialJuryMembers.get(2));
   }

   public void startRateJury(final AbstractScreen<SuperMafiosiGame> screen)
   {
      AudioManager.getInstance().fadeInMusic(Asset.Music.TENSION);

      Tween.call(new TweenCallback()
      {

         @Override
         public void onEvent(int i, BaseTween<?> bt)
         {
             setJuryResult(screen, 1, false);
         }
      }).delay(1).start(screen.getTweenManager());

   }

   private void setJuryResult(AbstractScreen<SuperMafiosiGame> screen, Integer jurymember, boolean hasBullet)
   {
      
      Vector2 resultStartPoint = new Vector2(320,113);
      
      GameObject juryResultNonBullet = screen.getGameWorld().addObject();
      Vector2 ratio = calcRatio();
      Vector2 dimesion_nonbullet = AssetUtils.getDimensionOfTexture(Asset.Textures.JURY_NONBULLET);
      juryResultNonBullet.setType(TYPE_JURYNONBULLET);
      
      juryResultNonBullet.setDimensions(dimesion_nonbullet.x * ratio.x, dimesion_nonbullet.y * ratio.y  );
      juryResultNonBullet.setPosition(resultStartPoint.x, resultStartPoint.y);
      screen.getRenderManager().register(TYPE_JURYNONBULLET, new SpriteRenderer(Asset.Textures.JURY_NONBULLET));
   }

   private Vector2 calcRatio()
   {
      Vector2 dimensionBackground = AssetUtils.getDimensionOfTexture(Asset.Textures.JURY_BACKGROUND);
      float ratio_width = Gdx.graphics.getWidth() / dimensionBackground.x;
      float ratio_height = Gdx.graphics.getHeight() / dimensionBackground.y;
      
      return new Vector2(ratio_width, ratio_height);
   }
   
   public void setJurySceneVisible(boolean visible)
   {
      if (visible)
      {
         for (GameObject juryObject : juryObjects)
         {
            juryObject.getColor().a = 1f;
         }
      }
      else
      {
         for (GameObject juryObject : juryObjects)
         {
            juryObject.getColor().a = 0f;
         }
      }
   }

   public void initRender(AbstractScreen<SuperMafiosiGame> screen)
   {

      /**
       * Calculate Screen Ratio
       */
      Vector2 ratio = calcRatio();


      /*
       *  Set JuryBackground
       */
      GameObject juryBackgroundObject = screen.getGameWorld().addObject();
      juryBackgroundObject.setType(TYPE_JURYBACKGROUND);
      juryBackgroundObject.setDimensions(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
      juryBackgroundObject.setPosition(0, 0);
      screen.getRenderManager().register(TYPE_JURYBACKGROUND, new SpriteRenderer(Asset.Textures.JURY_BACKGROUND));

      /**
       * JuryTable
       */
      GameObject juryTableObject = screen.getGameWorld().addObject();
      juryTableObject.setType(TYPE_JURYTABLE);
      Vector2 dimensionTable = AssetUtils.getDimensionOfTexture(Asset.Textures.JURY_TABLE);
      juryTableObject.setDimensions(dimensionTable.x * ratio.x, dimensionTable.y * ratio.y);
      juryTableObject.setPosition(80, 60);
      screen.getRenderManager().register(TYPE_JURYTABLE, new SpriteRenderer(Asset.Textures.JURY_TABLE));

      /**
       * JuryMembers
       */
      Vector2 jurymembers_startpoint = new Vector2(280, 280);
      final int next_Jury_Width = 260;

      Vector2 jurymembers_hands_startpoint = new Vector2(310, 220);

      int i = 0;
      for (JuryMember jurymember : juryMembers)
      {
         GameObject o_jury = screen.getGameWorld().addObject();
         o_jury.setType(jurymember.getName());

         Vector2 dimension_jurymember = AssetUtils.getDimensionOfTexture(jurymember.getHeadSprite());
         o_jury.setDimensions(dimension_jurymember.x * ratio.x, dimension_jurymember.y * ratio.y);
         o_jury.setPosition(jurymembers_startpoint.x + i * next_Jury_Width, jurymembers_startpoint.y);
         screen.getRenderManager().register(jurymember.getName(), new SpriteRenderer(jurymember.getHeadSprite()));

         GameObject o_jury_hands = screen.getGameWorld().addObject();
         o_jury_hands.setType(jurymember.getNameHands());

         Vector2 dimension_jurymember_hands = AssetUtils.getDimensionOfTexture(jurymember.getHandsSprite());
         o_jury_hands.setDimensions(dimension_jurymember_hands.x * ratio.x, dimension_jurymember_hands.y * ratio.y);
         o_jury_hands.setPosition(jurymembers_hands_startpoint.x + i * next_Jury_Width, jurymembers_hands_startpoint.y);
         screen.getRenderManager().register(jurymember.getNameHands(), new SpriteRenderer(jurymember.getHandsSprite()));

         i++;
      }

   }

}
