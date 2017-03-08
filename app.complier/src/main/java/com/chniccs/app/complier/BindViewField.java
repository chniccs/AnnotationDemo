package com.chniccs.app.complier;


import com.chniccs.appannotation.BindView;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeMirror;

/**
 * Created by ccs on 17/3/8.
 * 绑定view的类，主要有于获得对应控件的名称、类名、id
 */

class BindViewField {
    private VariableElement mVariableElement;
    private int mResId;

    BindViewField(Element element) throws IllegalArgumentException {
        if (element.getKind() != ElementKind.FIELD) {
            throw new IllegalArgumentException(String.format("Only fields can be annotated with @%s",
                    BindView.class.getSimpleName()));
        }
        mVariableElement = (VariableElement) element;

        BindView bindView = mVariableElement.getAnnotation(BindView.class);
        mResId = bindView.value();
        if (mResId < 0) {
            throw new IllegalArgumentException(
                    String.format("value() in %s for field %s is not valid !", BindView.class.getSimpleName(),
                            mVariableElement.getSimpleName()));
        }
    }

    /**
     * 获取变量名称
     *
     * @return
     */
    Name getFieldName() {
        return mVariableElement.getSimpleName();
    }

    /**
     * 获取变量id
     *
     * @return
     */
    int getResId() {
        return mResId;
    }

    /**
     * 获取变量类型
     *
     * @return
     */
    TypeMirror getFieldType() {
        return mVariableElement.asType();
    }
}