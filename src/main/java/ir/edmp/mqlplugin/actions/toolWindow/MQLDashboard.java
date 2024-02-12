package ir.edmp.mqlplugin.actions.toolWindow;


import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.content.ContentFactoryImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MQLDashboard implements ToolWindowFactory, DumbAware {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        MQLDashboardContent toolWindowContent = new MQLDashboardContent();
    }

    private static class MQLDashboardContent {


        private final JPanel contentPanel = new JPanel();


        public JPanel getContentPanel() {
            return contentPanel;
        }

    }
}
