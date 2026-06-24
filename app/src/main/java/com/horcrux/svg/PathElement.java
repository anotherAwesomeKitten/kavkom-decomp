package com.horcrux.svg;

/* JADX INFO: compiled from: PathParser.java */
/* JADX INFO: loaded from: classes2.dex */
class PathElement {
    Point[] points;
    ElementType type;

    PathElement(ElementType elementType, Point[] pointArr) {
        this.type = elementType;
        this.points = pointArr;
    }
}
