package ir.edmp.mqlplugin.settings;

import com.intellij.ide.DataManager;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBPanel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;

import javax.annotation.Nullable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

import static ir.edmp.mqlplugin.constants.Constant.*;
public class RunMQLSettingComponent {

    private JBTextField mqlLocation = new JBTextField();
    private JBTextField username = new JBTextField();
    private JBTextField password = new JBTextField();
    private JButton selectFolderButton = new JButton();
    private JBCheckBox printProgramImmediately = new JBCheckBox();
    private JPanel panel;

    public RunMQLSettingComponent() {
        this("","","", true);
    }

    public RunMQLSettingComponent(String mqlLocation, String username, String password, boolean printProgramImmediately) {
        this.mqlLocation.setText(mqlLocation);
        this.username.setText(username);
        this.password.setText(password);
        this.printProgramImmediately.setSelected(printProgramImmediately);
        createSelectFolderButton();
        createCenterPanel();
    }

    private void createSelectFolderButton() {
        selectFolderButton.addActionListener(this::onSelectFolder);
        selectFolderButton.setText("...");
        selectFolderButton.setToolTipText("Select folder of your projects path");
        selectFolderButton.setPreferredSize(new Dimension(36, 1));
        selectFolderButton.setHorizontalAlignment(0);
        selectFolderButton.setHorizontalTextPosition(11);
    }

    private void onSelectFolder(ActionEvent actionEvent) {
        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor();
        DataContext dataContext = DataManager.getInstance().getDataContext();
        Project project = CommonDataKeys.PROJECT.getData(dataContext);
        VirtualFile virtualFile = FileChooser.chooseFile(descriptor, project, null);
        File mqlPath = new File(virtualFile.getPath());
        if (!mqlPath.isDirectory()) {
            ApplicationManager.getApplication().runReadAction(() -> {
                Messages.showErrorDialog(ERROR_INCORRECT_MQL_PATH, ERROR_INVALID_DIRECTORY);
            });
        } else {
            mqlLocation.setText(virtualFile.getPath());
        }
    }

    protected void createCenterPanel() {
        panel = FormBuilder.createFormBuilder()
                .addComponent(addMQLLocation(mqlLocation, "Run MQL Location : "),1)
                .addComponent(addProperty(username, "Username : "),1)
                .addComponent(addProperty(password, "Password : "),1)
                .addComponent(addProperty(printProgramImmediately, "Print JPO after inserting immediately?"), 1)
                .addComponentFillVertically(new JPanel(), 0)
                .getPanel();
    }

    private JBPanel addProperty(JComponent field, String labelString) {
        field.setPreferredSize(new Dimension(336,35));
        JBPanel fieldPanel = new JBPanel(new HorizontalLayout());
        JBLabel label = new JBLabel(labelString);
        label.setPreferredSize(new Dimension(370,35));
        fieldPanel.add(label);
        fieldPanel.add(field);
        return fieldPanel;
    }

    private JBPanel addMQLLocation(JBTextField field, String labelString) {
        field.setPreferredSize(new Dimension(300, 35));
        JBPanel fieldPanel = new JBPanel(new HorizontalLayout());
        JBLabel label = new JBLabel(labelString);
        label.setPreferredSize(new Dimension(370, 35));
        fieldPanel.add(label);
        fieldPanel.add(field);
        fieldPanel.add(selectFolderButton);
        return fieldPanel;
    }

    public String getMqlLocation() {
        return mqlLocation.getText();
    }

    public void setMqlLocation(String mqlLocation) {
        this.mqlLocation.setText(mqlLocation);
    }

    public String getUsername() {
        return username.getText();
    }

    public void setUsername(String username) {
        this.username.setText(username);
    }

    public String getPassword() {
        return password.getText();
    }

    public void setPassword(String password) {
        this.password.setText(password);
    }

    public JPanel getPanel() {
        return panel;
    }

    public boolean getPrintProgramImmediately() {
        return printProgramImmediately.isSelected();
    }

    public void setPrintProgramImmediately(boolean printProgramImmediately) {
        this.printProgramImmediately.setSelected(printProgramImmediately);
    }
}
