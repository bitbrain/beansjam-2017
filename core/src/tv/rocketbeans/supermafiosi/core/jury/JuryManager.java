/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.rocketbeans.supermafiosi.core.jury;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.bitbrain.braingdx.graphics.renderer.SpriteRenderer;
import de.bitbrain.braingdx.screens.AbstractScreen;
import de.bitbrain.braingdx.world.GameObject;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import tv.rocketbeans.supermafiosi.SuperMafiosiGame;
import tv.rocketbeans.supermafiosi.assets.Asset;
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
   private static final String TYPE_JURYTABLE = "JuryTable";
   
   private static JuryManager instance = null;

   private ArrayList<Mafiosi> potenzialJuryMembers = new ArrayList<Mafiosi>();
   private ArrayList<Mafiosi> juryMembers = new ArrayList<Mafiosi>();

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
      potenzialJuryMembers.add(new Mafiosi("Keidi Hlumm", Message.DIALOG_KEIDIHLUMM_GREETING, 49, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01));
      potenzialJuryMembers.add(new Mafiosi("Kimmy Jimmel", Message.DIALOG_KIMMYJIMMEL_GREETING, 38, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01));
      potenzialJuryMembers.add(new Mafiosi("Pedro Aldente", Message.DIALOG_PEDRO_GREETING, 67, Asset.Textures.DUMMY, Asset.Textures.AVATAR_01));

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

   public void initRender(AbstractScreen<SuperMafiosiGame> screen)
   {
      final Vector2 startPosition = new Vector2(900f, 300f);
      final float mafiosiWidth = 64f;
      final float mafiosiGap = 20f;

      int i = 0;
      for (Mafiosi jurymember : juryMembers)
      {
         GameObject o = screen.getGameWorld().addObject();
         o.setType(jurymember.getName());
         o.setDimensions(mafiosiWidth, mafiosiWidth * 2f);

         Point2D.Double p_cartesis = new Point2D.Double(0, i * 50);
         Point2D.Double p_isomorph = UtilIsometric.convertCartesisToIsoMorph(p_cartesis);

         o.setPosition((int) (startPosition.x + p_isomorph.x), (int) (startPosition.y + p_isomorph.y));
         screen.getRenderManager().register(jurymember.getName(), new SpriteRenderer(jurymember.getSpriteId()));
         i++;
      }
      
      /*
      *  Set JuryTable
      */
      GameObject jurytableObject = screen.getGameWorld().addObject();
      jurytableObject.setType(TYPE_JURYTABLE);
      jurytableObject.setDimensions(340 * 0.6f, 280 * 0.6f);
      
      Point2D.Double p_cartesis = new Point2D.Double(-120, 40);
      Point2D.Double p_isomorph = UtilIsometric.convertCartesisToIsoMorph(p_cartesis);
      
      jurytableObject.setPosition((int) (startPosition.x + p_isomorph.x), (int) (startPosition.y + p_isomorph.y));
      screen.getRenderManager().register(TYPE_JURYTABLE, new SpriteRenderer(Asset.Textures.JURY_TABLE));
      
   }

}
