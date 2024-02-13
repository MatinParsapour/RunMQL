package ir.edmp.mqlplugin.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jdesktop.swingx.VerticalLayout;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class ConfigurationDialog extends DialogWrapper {

    private final JTextField mqlLocation;
    private final JTextField username;
    private final JPasswordField password;

    public ConfigurationDialog(String username, String password, String location) {
        super(true);
        this.username = new JTextField(username);
        this.password = new JPasswordField(password);
        this.mqlLocation = new JTextField(location);
        setTitle("Run MQL Settings");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel(new VerticalLayout());
        panel.add(addProperty(mqlLocation, "Run MQL Location : "));
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
