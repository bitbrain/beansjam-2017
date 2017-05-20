package tv.rocketbeans.supermafiosi.tweens;

import aurelienribon.tweenengine.TweenAccessor;
import box2dLight.ConeLight;
import de.bitbrain.braingdx.tweens.ColorTween;

public class ConeLightTween implements TweenAccessor<ConeLight> {

	public static final int DIRECTION = 1;
	public static final int DEGREE = 2;
	public static final int DISTANCE = 3;
	public static final int R = 4;
	public static final int G = 5;
	public static final int B = 6;
	public static final int A = 7;
	
	private final ColorTween colorTween = new ColorTween();
	
	@Override
	public int getValues(ConeLight arg0, int arg1, float[] arg2) {
		switch (arg1) {
			case DIRECTION:
				arg2[0] = arg0.getDirection();
				return 1;
			case DEGREE:
				arg2[0] = arg0.getConeDegree();
				return 1;
			case DISTANCE:
				arg2[0] = arg0.getDistance();
				return 1;
			case R:
				colorTween.getValues(arg0.getColor(), ColorTween.R, arg2);
				return 1;
			case G:
				colorTween.getValues(arg0.getColor(), ColorTween.G, arg2);
				return 1;
			case B:
				colorTween.getValues(arg0.getColor(), ColorTween.B, arg2);
				return 1;
			case A:
				colorTween.getValues(arg0.getColor(), ColorTween.A, arg2);
				return 1;

		}
		return 0;
	}

	@Override
	public void setValues(ConeLight arg0, int arg1, float[] arg2) {
		switch (arg1) {
		case DIRECTION:
			arg0.setDirection(arg2[0]);
			break;
		case DEGREE:
			arg0.setConeDegree(arg2[0]);
			break;
		case DISTANCE:
			arg0.setDistance(arg2[0]);
			break;
		case R:
			colorTween.setValues(arg0.getColor(), ColorTween.R, arg2);
			break;
		case G:
			colorTween.setValues(arg0.getColor(), ColorTween.G, arg2);
			break;
		case B:
			colorTween.setValues(arg0.getColor(), ColorTween.B, arg2);
			break;
		case A:
			colorTween.setValues(arg0.getColor(), ColorTween.A, arg2);
			break;
	}
	}

}
