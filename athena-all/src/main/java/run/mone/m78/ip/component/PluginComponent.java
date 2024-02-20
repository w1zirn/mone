/*
 *  Copyright 2020 Xiaomi
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package run.mone.m78.ip.component;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.project.Project;
import run.mone.m78.ip.ui.BuildUi;

/**
 * @author goodjava@qq.com
 */
public class PluginComponent implements ApplicationComponent {


    @Override
    public String getComponentName() {
        return "PluginComponent";
    }

    public void show(Project project) {
        String projectName = project.getName();
        String basePath = project.getBasePath();
        BuildUi dialog = new BuildUi(projectName,basePath);
        dialog.setSize(600, 800);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        dialog.setResizable(false);
        dialog.requestFocus();
    }

}
