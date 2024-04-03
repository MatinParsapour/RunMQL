package ir.edmp.mqlplugin.dialog;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import ir.edmp.mqlplugin.util.ModuleProjectUtil;
import org.jdesktop.swingx.HorizontalLayout;
import org.jdesktop.swingx.VerticalLayout;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.function.Consumer;

import static ir.edmp.mqlplugin.constants.Constant.*;

public class ConfigurationDialog extends DialogWrapper {

    private final JTextField mqlLocation;
    private final JTextField username;
    private final JPasswordField password;
    private final JButton selectFolder;

    public ConfigurationDialog(String username, String password, String location) {
        super(true);
        this.username = new JTextField(username);
        this.password = new JPasswordField(password);
        this.mqlLocation = new JTextField(location);
        this.selectFolder = generateSelectFolderButton();
        setTitle("Run MQL Settings");
        init();
    }

    private JButton generateSelectFolderButton() {
        JButton button = new JButton();
        button.addActionListener(this::onSelectFolder);
        button.setText("...");
        button.setToolTipText("Select folder of your projects path");
        button.setPreferredSize(new Dimension(36, 1));
        button.setHorizontalAlignment(0);
        button.setHorizontalTextPosition(11);
        return button;
    }

    private void onSelectFolder(ActionEvent actionEvent) {
        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor();
        VirtualFile virtualFile = FileChooser.chooseFile(descriptor, ModuleProjectUtil.getInstance().getModuleProject().getProject(), null);
        File mqlPath = new File(virtualFile.getPath());
        if (!mqlPath.isDirectory()) {
            Messages.showErrorDialog(ERROR_INCORRECT_MQL_PATH, ERROR_INVALID_DIRECTORY);
        } else {
            mqlLocation.setText(virtualFile.getPath());
        }
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel(new VerticalLayout());
        panel.add(addMQLLocation(mqlLocation, "Run MQL Location : "));
        panel.add(addProperty(username, "Username : "));
        panel.add(addProperty(password, "Password : "));
        return panel;
    }

    private JPanel addProperty(JTextField field, String labelString) {
        field.setPreferredSize(new Dimension(250, 35));
        JPanel fieldPanel = new JPanel(new GridLayout());
        JLabel label = new JLabel(labelString);
        label.setPreferredSize(new Dimension(20, 35));
        fieldPanel.add(label);
        fieldPanel.add(field);
        return fieldPanel;
    }
    
    private JPanel addMQLLocation(JTextField field, String labelString) {
        field.setPreferredSize(new Dimension(215, 35));
        JPanel fieldPanel = new JPanel(new HorizontalLayout());
        JLabel label = new JLabel(labelString);
        label.setPreferredSize(new Dimension(250, 35));
        fieldPanel.add(label);
        fieldPanel.add(field);
        fieldPanel.add(selectFolder);
        return fieldPanel;
    }

    public JTextField getMqlLocation() {
        return mqlLocation;
    }

    public JTextField getUsername() {
        return username;
    }

    public JPasswordField getPassword() {
        return password;
    }

}
