/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.intellij.plugins.hcl.terraform.il.codeinsight

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import org.intellij.plugins.hcl.terraform.il.TILElementTypes.INTERPOLATION_END
import org.intellij.plugins.hcl.terraform.il.TILElementTypes.INTERPOLATION_START
import org.intellij.plugins.hcl.terraform.il.TILElementTypes.L_PAREN
import org.intellij.plugins.hcl.terraform.il.TILElementTypes.R_PAREN

public class TILBraceMatcher : PairedBraceMatcher {

  override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int): Int {
    return openingBraceOffset
  }

  override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean {
    return true
  }

  companion object {
    private val BRACE_PAIRS = arrayOf(BracePair(INTERPOLATION_START, INTERPOLATION_END, true), BracePair(L_PAREN, R_PAREN, true))
  }

  override fun getPairs(): Array<BracePair> {
    return BRACE_PAIRS
  }
}