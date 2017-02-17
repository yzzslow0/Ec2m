package com.easycode.util;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

/**
 * Created by yzz on 2017/2/17.
 */

public class QrUtil {
    public Bitmap CreateQR(String str){
            Bitmap bitmap = null;
            BitMatrix result = null;
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, 200, 200);
                // 使用 ZXing Android Embedded 要写的代码
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                bitmap = barcodeEncoder.createBitmap(result);
            } catch (WriterException e){
                e.printStackTrace();
            } catch (IllegalArgumentException iae){ // ?
                return null;
            }

            // 如果不使用 ZXing Android Embedded 的话，要写的代码

//        int w = result.getWidth();
//        int h = result.getHeight();
//        int[] pixels = new int[w * h];
//        for (int y = 0; y < h; y++) {
//            int offset = y * w;
//            for (int x = 0; x < w; x++) {
//                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
//            }
//        }
//        bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);
//        bitmap.setPixels(pixels,0,100,0,0,w,h);

            return bitmap;
        }
}
