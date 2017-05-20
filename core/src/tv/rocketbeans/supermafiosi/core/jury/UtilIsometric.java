/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tv.rocketbeans.supermafiosi.core.jury;

import java.awt.Point;
import java.awt.geom.Point2D;

/**
 *
 * @author Gterminator
 */
public class UtilIsometric
{

   public static Point2D.Double convertCartesisToIsoMorph(Point2D.Double p)
   {
      Point2D.Double p2 = new Point.Double(p.getX() - p.getY(), (p.getX() + p.getY()) / 2);
      return p2;
   }

   public static Point2D convertIsoMorphToCartesis(Point2D p)
   {
      Point2D.Double p2 = new Point.Double((2 * p.getY() + p.getX()) / 2, (2 * p.getY() - p.getX()) / 2);
      return p2;
   }
}
