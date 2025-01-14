package org.intellij.sequencer.generator.filters;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.util.PropertyUtil;
import org.intellij.sequencer.openapi.filters.PsiElementFilter;

/**
 * Exclude getter/setter method.
 */
public class NoGetterSetterFilter implements PsiElementFilter {
    private boolean _noGetterSetters = true;

    public NoGetterSetterFilter(boolean noGetterSetters) {
        _noGetterSetters = noGetterSetters;
    }

    public boolean isNoGetterSetters() {
        return _noGetterSetters;
    }

    public void setNoGetterSetters(boolean noGetterSetters) {
        _noGetterSetters = noGetterSetters;
    }

    public boolean allow(PsiElement psiElement) {
        if(_noGetterSetters
                && (psiElement instanceof PsiMethod)
                && isGetterSetter((PsiMethod) psiElement))
            return false;
        return true;
    }

    private boolean isGetterSetter(PsiMethod psiMethod) {
        return PropertyUtil.isSimplePropertyGetter(psiMethod) ||
              PropertyUtil.isSimplePropertySetter(psiMethod);
    }
}
