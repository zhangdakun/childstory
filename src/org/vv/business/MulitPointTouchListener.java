// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package org.vv.business;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MulitPointTouchListener
    implements android.view.View.OnTouchListener
{

    public MulitPointTouchListener()
    {
        matrix = new Matrix();
        savedMatrix = new Matrix();
        mode = 0;
        start = new PointF();
        mid = new PointF();
        oldDist = 1.0F;
    }

    private void dumpEvent(MotionEvent motionevent)
    {
        int i = 0;
        String as[] = new String[10];
        as[i] = "DOWN";
        as[1] = "UP";
        as[2] = "MOVE";
        as[3] = "CANCEL";
        as[4] = "OUTSIDE";
        as[5] = "POINTER_DOWN";
        as[6] = "POINTER_UP";
        as[7] = "7?";
        as[8] = "8?";
        as[9] = "9?";
        StringBuilder stringbuilder = new StringBuilder();
        int j = motionevent.getAction();
        int k = j & 0xff;
        stringbuilder.append("event ACTION_").append(as[k]);
        if(k == 5 || k == 6)
        {
            stringbuilder.append("(pid ").append(j >> 8);
            stringbuilder.append(")");
        }
        stringbuilder.append("[");
        for(; i < motionevent.getPointerCount(); i++)
        {
            stringbuilder.append("#").append(i);
            stringbuilder.append("(pid ").append(motionevent.getPointerId(i));
            stringbuilder.append(")=").append((int)motionevent.getX(i));
            stringbuilder.append(",").append((int)motionevent.getY(i));
            if(i + 1 < motionevent.getPointerCount())
                stringbuilder.append(";");
        }

        stringbuilder.append("]");
    }

    private void midPoint(PointF pointf, MotionEvent motionevent)
    {
        float f = motionevent.getX(0) + motionevent.getX(1);
        float f1 = motionevent.getY(0) + motionevent.getY(1);
        pointf.set(f / 2.0F, f1 / 2.0F);
    }

    private float spacing(MotionEvent motionevent)
    {
        float f = motionevent.getX(0) - motionevent.getX(1);
        float f1 = motionevent.getY(0) - motionevent.getY(1);
        return FloatMath.sqrt(f * f + f1 * f1);
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        ImageView imageview;
        imageview = (ImageView)view;
        dumpEvent(motionevent);
//        0xff & motionevent.getAction();
//        JVM INSTR tableswitch 0 6: default 60
//    //                   0 70
//    //                   1 162
//    //                   2 170
//    //                   3 60
//    //                   4 60
//    //                   5 115
//    //                   6 162;
//           goto _L1 _L2 _L3 _L4 _L1 _L1 _L5 _L3
//_L1:
//        imageview.setImageMatrix(matrix);
//        return true;
//_L2:
//        matrix.set(imageview.getImageMatrix());
//        savedMatrix.set(matrix);
//        start.set(motionevent.getX(), motionevent.getY());
//        mode = 1;
//        continue; /* Loop/switch isn't completed */
//_L5:
//        oldDist = spacing(motionevent);
//        if(oldDist > 10F)
//        {
//            savedMatrix.set(matrix);
//            midPoint(mid, motionevent);
//            mode = 2;
//        }
//        continue; /* Loop/switch isn't completed */
//_L3:
//        mode = 0;
//        continue; /* Loop/switch isn't completed */
//_L4:
//        if(mode == 1)
//        {
//            matrix.set(savedMatrix);
//            matrix.postTranslate(motionevent.getX() - start.x, motionevent.getY() - start.y);
//        } else
//        if(mode == 2)
//        {
//            float f = spacing(motionevent);
//            if(f > 10F)
//            {
//                matrix.set(savedMatrix);
//                float f1 = f / oldDist;
//                matrix.postScale(f1, f1, mid.x, mid.y);
//            }
//        }
//        if(true) goto _L1; else goto _L6
//_L6:
        switch (0xff & motionevent.getAction()) {
		case 0:
	        matrix.set(imageview.getImageMatrix());
	        savedMatrix.set(matrix);
	        start.set(motionevent.getX(), motionevent.getY());
	        mode = 1;
			break;
		case 1:
			mode = 0;
			break;
		case 2:
	        if(mode == 1)
	        {
	            matrix.set(savedMatrix);
	            matrix.postTranslate(motionevent.getX() - start.x, motionevent.getY() - start.y);
	        } else
	        if(mode == 2)
	        {
	            float f = spacing(motionevent);
	            if(f > 10F)
	            {
	                matrix.set(savedMatrix);
	                float f1 = f / oldDist;
	                matrix.postScale(f1, f1, mid.x, mid.y);
	            }
	        }
	        imageview.setImageMatrix(matrix);
			break;
		case 3:
		case 4:
			imageview.setImageMatrix(matrix);
			break;
		case 5:
	        oldDist = spacing(motionevent);
	        if(oldDist > 10F)
	        {
	            savedMatrix.set(matrix);
	            midPoint(mid, motionevent);
	            mode = 2;
	        }
			break;
		case 6:
			break;
		default:
			break;
		}
        
        return true;
    }

    static final int DRAG = 1;
    static final int NONE = 0;
    static final int ZOOM = 2;
    Matrix matrix;
    PointF mid;
    int mode;
    float oldDist;
    Matrix savedMatrix;
    PointF start;
}
