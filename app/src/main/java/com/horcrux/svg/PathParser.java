package com.horcrux.svg;

import android.graphics.Path;
import android.graphics.RectF;
import androidx.appcompat.app.AppCompatDelegate;
import com.facebook.imagepipeline.common.RotationOptions;
import java.util.ArrayList;
import kotlin.io.encoding.Base64;

/* JADX INFO: loaded from: classes2.dex */
class PathParser {
    static ArrayList<PathElement> elements;
    private static int i;
    private static int l;
    private static Path mPath;
    private static boolean mPenDown;
    private static float mPenDownX;
    private static float mPenDownY;
    private static float mPenX;
    private static float mPenY;
    private static float mPivotX;
    private static float mPivotY;
    static float mScale;
    private static String s;

    private static boolean is_cmd(char c) {
        switch (c) {
            case 'A':
            case 'C':
            case 'H':
            case Base64.mimeLineLength /* 76 */:
            case 'M':
            case 'Q':
            case 'S':
            case 'T':
            case 'V':
            case RotationOptions.ROTATE_90 /* 90 */:
            case 'a':
            case 'c':
            case 'h':
            case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR /* 108 */:
            case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY /* 109 */:
            case 'q':
            case 's':
            case 't':
            case 'v':
            case 'z':
                return true;
            default:
                return false;
        }
    }

    private static boolean is_number_start(char c) {
        return (c >= '0' && c <= '9') || c == '.' || c == '-' || c == '+';
    }

    PathParser() {
    }

    static Path parse(String str) {
        elements = new ArrayList<>();
        Path path = new Path();
        mPath = path;
        if (str == null) {
            return path;
        }
        l = str.length();
        s = str;
        i = 0;
        mPenX = 0.0f;
        mPenY = 0.0f;
        mPivotX = 0.0f;
        mPivotY = 0.0f;
        mPenDownX = 0.0f;
        mPenDownY = 0.0f;
        mPenDown = false;
        char c = ' ';
        while (i < l) {
            skip_spaces();
            int i2 = i;
            if (i2 < l) {
                boolean z = true;
                boolean z2 = c != ' ';
                char cCharAt = s.charAt(i2);
                if (!z2 && cCharAt != 'M' && cCharAt != 'm') {
                    throw new IllegalArgumentException(String.format("Unexpected character '%c' (i=%d, s=%s)", Character.valueOf(cCharAt), Integer.valueOf(i), s));
                }
                if (is_cmd(cCharAt)) {
                    i++;
                    z = false;
                    c = cCharAt;
                } else {
                    if (!is_number_start(cCharAt) || !z2) {
                        throw new IllegalArgumentException(String.format("Unexpected character '%c' (i=%d, s=%s)", Character.valueOf(cCharAt), Integer.valueOf(i), s));
                    }
                    if (c == 'Z' || c == 'z') {
                        throw new IllegalArgumentException(String.format("Unexpected number after 'z' (s=%s)", s));
                    }
                    if (c == 'M' || c == 'm') {
                        c = is_absolute(c) ? 'L' : 'l';
                    } else {
                        z = false;
                    }
                }
                boolean zIs_absolute = is_absolute(c);
                switch (c) {
                    case 'A':
                        arcTo(parse_list_number(), parse_list_number(), parse_list_number(), parse_flag(), parse_flag(), parse_list_number(), parse_list_number());
                        break;
                    case 'C':
                        curveTo(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 'H':
                        lineTo(parse_list_number(), mPenY);
                        break;
                    case Base64.mimeLineLength /* 76 */:
                        lineTo(parse_list_number(), parse_list_number());
                        break;
                    case 'M':
                        moveTo(parse_list_number(), parse_list_number());
                        break;
                    case 'Q':
                        quadraticBezierCurveTo(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 'S':
                        smoothCurveTo(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 'T':
                        smoothQuadraticBezierCurveTo(parse_list_number(), parse_list_number());
                        break;
                    case 'V':
                        lineTo(mPenX, parse_list_number());
                        break;
                    case RotationOptions.ROTATE_90 /* 90 */:
                    case 'z':
                        close();
                        break;
                    case 'a':
                        arc(parse_list_number(), parse_list_number(), parse_list_number(), parse_flag(), parse_flag(), parse_list_number(), parse_list_number());
                        break;
                    case 'c':
                        curve(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 'h':
                        line(parse_list_number(), 0.0f);
                        break;
                    case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR /* 108 */:
                        line(parse_list_number(), parse_list_number());
                        break;
                    case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY /* 109 */:
                        move(parse_list_number(), parse_list_number());
                        break;
                    case 'q':
                        quadraticBezierCurve(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 's':
                        smoothCurve(parse_list_number(), parse_list_number(), parse_list_number(), parse_list_number());
                        break;
                    case 't':
                        smoothQuadraticBezierCurve(parse_list_number(), parse_list_number());
                        break;
                    case 'v':
                        line(0.0f, parse_list_number());
                        break;
                    default:
                        throw new IllegalArgumentException(String.format("Unexpected comand '%c' (s=%s)", Character.valueOf(c), s));
                }
                if (z) {
                    c = zIs_absolute ? 'M' : 'm';
                }
            } else {
                return mPath;
            }
        }
        return mPath;
    }

    private static void move(float f, float f2) {
        moveTo(f + mPenX, f2 + mPenY);
    }

    private static void moveTo(float f, float f2) {
        mPenX = f;
        mPivotX = f;
        mPenDownX = f;
        mPenY = f2;
        mPivotY = f2;
        mPenDownY = f2;
        Path path = mPath;
        float f3 = mScale;
        path.moveTo(f * f3, f3 * f2);
        elements.add(new PathElement(ElementType.kCGPathElementMoveToPoint, new Point[]{new Point(f, f2)}));
    }

    private static void line(float f, float f2) {
        lineTo(f + mPenX, f2 + mPenY);
    }

    private static void lineTo(float f, float f2) {
        setPenDown();
        mPenX = f;
        mPivotX = f;
        mPenY = f2;
        mPivotY = f2;
        Path path = mPath;
        float f3 = mScale;
        path.lineTo(f * f3, f3 * f2);
        elements.add(new PathElement(ElementType.kCGPathElementAddLineToPoint, new Point[]{new Point(f, f2)}));
    }

    private static void curve(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = mPenX;
        float f8 = mPenY;
        curveTo(f + f7, f2 + f8, f3 + f7, f4 + f8, f5 + f7, f6 + f8);
    }

    private static void curveTo(float f, float f2, float f3, float f4, float f5, float f6) {
        mPivotX = f3;
        mPivotY = f4;
        cubicTo(f, f2, f3, f4, f5, f6);
    }

    private static void cubicTo(float f, float f2, float f3, float f4, float f5, float f6) {
        setPenDown();
        mPenX = f5;
        mPenY = f6;
        Path path = mPath;
        float f7 = mScale;
        path.cubicTo(f * f7, f2 * f7, f3 * f7, f4 * f7, f5 * f7, f7 * f6);
        elements.add(new PathElement(ElementType.kCGPathElementAddCurveToPoint, new Point[]{new Point(f, f2), new Point(f3, f4), new Point(f5, f6)}));
    }

    private static void smoothCurve(float f, float f2, float f3, float f4) {
        float f5 = mPenX;
        float f6 = mPenY;
        smoothCurveTo(f + f5, f2 + f6, f3 + f5, f4 + f6);
    }

    private static void smoothCurveTo(float f, float f2, float f3, float f4) {
        float f5 = (mPenX * 2.0f) - mPivotX;
        float f6 = (mPenY * 2.0f) - mPivotY;
        mPivotX = f;
        mPivotY = f2;
        cubicTo(f5, f6, f, f2, f3, f4);
    }

    private static void quadraticBezierCurve(float f, float f2, float f3, float f4) {
        float f5 = mPenX;
        float f6 = mPenY;
        quadraticBezierCurveTo(f + f5, f2 + f6, f3 + f5, f4 + f6);
    }

    private static void quadraticBezierCurveTo(float f, float f2, float f3, float f4) {
        mPivotX = f;
        mPivotY = f2;
        float f5 = f * 2.0f;
        float f6 = f2 * 2.0f;
        cubicTo((mPenX + f5) / 3.0f, (mPenY + f6) / 3.0f, (f3 + f5) / 3.0f, (f4 + f6) / 3.0f, f3, f4);
    }

    private static void smoothQuadraticBezierCurve(float f, float f2) {
        smoothQuadraticBezierCurveTo(f + mPenX, f2 + mPenY);
    }

    private static void smoothQuadraticBezierCurveTo(float f, float f2) {
        quadraticBezierCurveTo((mPenX * 2.0f) - mPivotX, (mPenY * 2.0f) - mPivotY, f, f2);
    }

    private static void arc(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5) {
        arcTo(f, f2, f3, z, z2, f4 + mPenX, f5 + mPenY);
    }

    private static void arcTo(float f, float f2, float f3, boolean z, boolean z2, float f4, float f5) {
        float f6;
        float f7;
        float f8;
        float f9;
        float f10 = mPenX;
        float f11 = mPenY;
        float fAbs = Math.abs(f2 == 0.0f ? f == 0.0f ? f5 - f11 : f : f2);
        float fAbs2 = Math.abs(f == 0.0f ? f4 - f10 : f);
        if (fAbs2 == 0.0f || fAbs == 0.0f || (f4 == f10 && f5 == f11)) {
            lineTo(f4, f5);
            return;
        }
        float radians = (float) Math.toRadians(f3);
        double d = radians;
        float fCos = (float) Math.cos(d);
        float fSin = (float) Math.sin(d);
        float f12 = f4 - f10;
        float f13 = f5 - f11;
        float f14 = ((fCos * f12) / 2.0f) + ((fSin * f13) / 2.0f);
        float f15 = -fSin;
        float f16 = ((f15 * f12) / 2.0f) + ((fCos * f13) / 2.0f);
        float f17 = fAbs2 * fAbs2;
        float f18 = f17 * fAbs * fAbs;
        float f19 = (f18 - ((f17 * f16) * f16)) - (((fAbs * fAbs) * f14) * f14);
        if (f19 < 0.0f) {
            float f20 = 1.0f - (f19 / f18);
            f6 = 0.0f;
            float fSqrt = (float) Math.sqrt(f20);
            fAbs2 *= fSqrt;
            f9 = fAbs * fSqrt;
            f8 = f12 / 2.0f;
            f7 = f13 / 2.0f;
        } else {
            f6 = 0.0f;
            float fSqrt2 = (float) Math.sqrt(f19 / (r16 + r18));
            if (z == z2) {
                fSqrt2 = -fSqrt2;
            }
            float f21 = (((-fSqrt2) * f16) * fAbs2) / fAbs;
            float f22 = ((fSqrt2 * f14) * fAbs) / fAbs2;
            f7 = (f13 / 2.0f) + (f21 * fSin) + (f22 * fCos);
            f8 = ((fCos * f21) - (fSin * f22)) + (f12 / 2.0f);
            f9 = fAbs;
        }
        float f23 = fCos / fAbs2;
        float f24 = fSin / fAbs2;
        float f25 = f15 / f9;
        float f26 = fCos / f9;
        float f27 = -f8;
        float f28 = -f7;
        float f29 = f8;
        float fAtan2 = (float) Math.atan2((f25 * f27) + (f26 * f28), (f27 * f23) + (f24 * f28));
        float f30 = f12 - f29;
        float f31 = f13 - f7;
        float fAtan22 = (float) Math.atan2((f25 * f30) + (f26 * f31), (f23 * f30) + (f24 * f31));
        float f32 = f29 + f10;
        float f33 = f7 + f11;
        float f34 = f12 + f10;
        float f35 = f13 + f11;
        setPenDown();
        mPivotX = f34;
        mPenX = f34;
        mPivotY = f35;
        mPenY = f35;
        if (fAbs2 != f9 || radians != f6) {
            arcToBezier(f32, f33, fAbs2, f9, fAtan2, fAtan22, z2, radians);
            return;
        }
        float degrees = (float) Math.toDegrees(fAtan2);
        float fAbs3 = Math.abs((degrees - ((float) Math.toDegrees(fAtan22))) % 360.0f);
        if (!z ? fAbs3 > 180.0f : fAbs3 < 180.0f) {
            fAbs3 = 360.0f - fAbs3;
        }
        if (!z2) {
            fAbs3 = -fAbs3;
        }
        float f36 = mScale;
        mPath.arcTo(new RectF((f32 - fAbs2) * f36, (f33 - fAbs2) * f36, (f32 + fAbs2) * f36, (f33 + fAbs2) * f36), degrees, fAbs3);
        elements.add(new PathElement(ElementType.kCGPathElementAddCurveToPoint, new Point[]{new Point(f34, f35)}));
    }

    private static void close() {
        if (mPenDown) {
            mPenX = mPenDownX;
            mPenY = mPenDownY;
            mPenDown = false;
            mPath.close();
            elements.add(new PathElement(ElementType.kCGPathElementCloseSubpath, new Point[]{new Point(mPenX, mPenY)}));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0067 A[LOOP:0: B:12:0x0065->B:13:0x0067, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void arcToBezier(float r23, float r24, float r25, float r26, float r27, float r28, boolean r29, float r30) {
        /*
            Method dump skipped, instruction units count: 288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.horcrux.svg.PathParser.arcToBezier(float, float, float, float, float, float, boolean, float):void");
    }

    private static void setPenDown() {
        if (mPenDown) {
            return;
        }
        mPenDownX = mPenX;
        mPenDownY = mPenY;
        mPenDown = true;
    }

    private static double round(double d) {
        return Math.round(d * r0) / Math.pow(10.0d, 4.0d);
    }

    private static void skip_spaces() {
        while (true) {
            int i2 = i;
            if (i2 >= l || !Character.isWhitespace(s.charAt(i2))) {
                return;
            } else {
                i++;
            }
        }
    }

    private static boolean is_absolute(char c) {
        return Character.isUpperCase(c);
    }

    private static boolean parse_flag() {
        skip_spaces();
        char cCharAt = s.charAt(i);
        if (cCharAt == '0' || cCharAt == '1') {
            int i2 = i + 1;
            i = i2;
            if (i2 < l && s.charAt(i2) == ',') {
                i++;
            }
            skip_spaces();
            return cCharAt == '1';
        }
        throw new Error(String.format("Unexpected flag '%c' (i=%d, s=%s)", Character.valueOf(cCharAt), Integer.valueOf(i), s));
    }

    private static float parse_list_number() {
        if (i == l) {
            throw new Error(String.format("Unexpected end (s=%s)", s));
        }
        float f = parse_number();
        skip_spaces();
        parse_list_separator();
        return f;
    }

    private static float parse_number() {
        char cCharAt;
        skip_spaces();
        int i2 = i;
        if (i2 == l) {
            throw new Error(String.format("Unexpected end (s=%s)", s));
        }
        char cCharAt2 = s.charAt(i2);
        if (cCharAt2 == '-' || cCharAt2 == '+') {
            int i3 = i + 1;
            i = i3;
            cCharAt2 = s.charAt(i3);
        }
        if (cCharAt2 >= '0' && cCharAt2 <= '9') {
            skip_digits();
            int i4 = i;
            if (i4 < l) {
                cCharAt2 = s.charAt(i4);
            }
        } else if (cCharAt2 != '.') {
            throw new IllegalArgumentException(String.format("Invalid number formating character '%c' (i=%d, s=%s)", Character.valueOf(cCharAt2), Integer.valueOf(i), s));
        }
        if (cCharAt2 == '.') {
            i++;
            skip_digits();
            int i5 = i;
            if (i5 < l) {
                cCharAt2 = s.charAt(i5);
            }
        }
        if (cCharAt2 == 'e' || cCharAt2 == 'E') {
            int i6 = i;
            if (i6 + 1 < l && (cCharAt = s.charAt(i6 + 1)) != 'm' && cCharAt != 'x') {
                int i7 = i + 1;
                i = i7;
                char cCharAt3 = s.charAt(i7);
                if (cCharAt3 == '+' || cCharAt3 == '-') {
                    i++;
                    skip_digits();
                } else if (cCharAt3 >= '0' && cCharAt3 <= '9') {
                    skip_digits();
                } else {
                    throw new IllegalArgumentException(String.format("Invalid number formating character '%c' (i=%d, s=%s)", Character.valueOf(cCharAt3), Integer.valueOf(i), s));
                }
            }
        }
        String strSubstring = s.substring(i2, i);
        float f = Float.parseFloat(strSubstring);
        if (Float.isInfinite(f) || Float.isNaN(f)) {
            throw new IllegalArgumentException(String.format("Invalid number '%s' (start=%d, i=%d, s=%s)", strSubstring, Integer.valueOf(i2), Integer.valueOf(i), s));
        }
        return f;
    }

    private static void parse_list_separator() {
        int i2 = i;
        if (i2 >= l || s.charAt(i2) != ',') {
            return;
        }
        i++;
    }

    private static void skip_digits() {
        while (true) {
            int i2 = i;
            if (i2 >= l || !Character.isDigit(s.charAt(i2))) {
                return;
            } else {
                i++;
            }
        }
    }
}
