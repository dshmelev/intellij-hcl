/*
 * Copyright 2000-2017 JetBrains s.r.o.
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
package org.intellij.plugins.hcl.terraform.run

import com.intellij.execution.lineMarker.ExecutorAction
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement
import org.intellij.plugins.hcl.psi.HCLBlock
import org.intellij.plugins.hcl.terraform.config.patterns.TerraformPatterns

class TerraformRunLineMarkerContributor : RunLineMarkerContributor() {
  override fun getInfo(element: PsiElement): Info? {
    if (element !is HCLBlock) return null
    if (!TerraformPatterns.ResourceRootBlock.accepts(element)) return null
    TerraformResourceConfigurationProducer.getResourceTarget(element) ?: return null
    return Info(AllIcons.RunConfigurations.TestState.Run, null, *ExecutorAction.getActions(0))
  }
}