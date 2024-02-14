package ir.edmp.mqlplugin.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBUI;
import org.jdesktop.swingx.VerticalLayout;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class NotificationDialog extends DialogWrapper {



    private final JTextField name = new JTextField();
    private final JTextField revision = new JTextField();
    private final JTextArea bodyHTML = new JTextArea();
    private final JTextArea bodyText = new JTextArea();
    private final JTextField dynamicBCCList = new JTextField();
    private final JTextField dynamicCCList = new JTextField();
    private final JTextField dynamicToList = new JTextField();
    private final JTextField filter = new JTextField();
    private final JTextField fromAgent = new JTextField();
    private final JTextField consolidationJPO = new JTextField();
    private final JTextField attachments = new JTextField();
    private final JTextField preprocessesJPO = new JTextField();
    private final JTextField registeredSuite = new JTextField();
    private final JTextField replyTo = new JTextField();
    private final JTextField staticBCCList = new JTextField();
    private final JTextField staticCCList = new JTextField();
    private final JTextField staticToList = new JTextField();
    private final JTextField subjectText = new JTextField();
    private final JTextField urlSuffix = new JTextField();

    public NotificationDialog() {
        super(true); // use current window as parent
        setTitle("Notification Attributes");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new VerticalLayout());
        dialogPanel.add(addAttributeField(name, "Name : "));
        dialogPanel.add(addAttributeField(revision, "Revision : "));
        dialogPanel.add(addAttributeField(bodyText, "Body Text : "));
        dialogPanel.add(addAttributeField(subjectText, "Subject Text : "));
        dialogPanel.add(addAttributeField(bodyHTML, "Body HTML : "));
        dialogPanel.add(addAttributeField(dynamicToList, "Dynamic To List : "));
        dialogPanel.add(addAttributeField(filter, "Filter : "));
        dialogPanel.add(addAttributeField(fromAgent, "From Agent : "));
        dialogPanel.add(addAttributeField(dynamicBCCList, "Dynamic BCC List : "));
        dialogPanel.add(addAttributeField(dynamicCCList, "Dynamic CC List : "));
        dialogPanel.add(addAttributeField(consolidationJPO, "Consolidation JPO : "));
        dialogPanel.add(addAttributeField(attachments, "Attachments : "));
        dialogPanel.add(addAttributeField(preprocessesJPO, "Preprocess JPO : "));
        dialogPanel.add(addAttributeField(registeredSuite, "Registered Suite : "));
        dialogPanel.add(addAttributeField(replyTo, "Reply To : "));
        dialogPanel.add(addAttributeField(staticBCCList, "Static BCC List : "));
        dialogPanel.add(addAttributeField(staticCCList, "Static CC List : "));
        dialogPanel.add(addAttributeField(staticToList, "Static To List : "));
        dialogPanel.add(addAttributeField(urlSuffix, "URL Suffix : "));
        return dialogPanel;
    }

    private JPanel addAttributeField(JTextField field, String labelString) {
        field.setPreferredSize(new Dimension(250, 35));
        JPanel fieldPanel = new JPanel(new GridLayout());
        JLabel label = new JLabel(labelString);
        label.setPreferredSize(new Dimension(20, 35));
        fieldPanel.add(label);
        fieldPanel.add(field);
        return fieldPanel;
    }
    private JPanel addAttributeField(JTextArea field, String labelString) {
        field.setPreferredSize(new Dimension(200, 150));
        field.setMargin(JBUI.insets(10, 0));
        JPanel fieldPanel = new JPanel(new GridLayout());
        JLabel label = new JLabel(labelString);
        label.setPreferredSize(new Dimension(20, 35));
        fieldPanel.add(label);
        fieldPanel.add(field);
        return fieldPanel;
    }

    public String getBodyHTML() {
        return bodyHTML.getText();
    }

    public String getBodyText() {
        return bodyText.getText();
    }

    public String getDynamicBCCList() {
        return dynamicBCCList.getText();
    }

    public String getDynamicCCList() {
        return dynamicCCList.getText();
    }

    public String getDynamicToList() {
        return dynamicToList.getText();
    }

    public String getFilter() {
        return filter.getText();
    }

    public String getFromAgent() {
        return fromAgent.getText();
    }

    public String getConsolidationJPO() {
        return consolidationJPO.getText();
    }

    public String getAttachments() {
        return attachments.getText();
    }

    public String getPreprocessesJPO() {
        return preprocessesJPO.getText();
    }

    public String getRegisteredSuite() {
        return registeredSuite.getText();
    }

    public String getReplyTo() {
        return replyTo.getText();
    }

    public String getStaticBCCList() {
        return staticBCCList.getText();
    }

    public String getStaticCCList() {
        return staticCCList.getText();
    }

    public String getStaticToList() {
        return staticToList.getText();
    }

    public String getSubjectText() {
        return subjectText.getText();
    }

    public String getUrlSuffix() {
        return urlSuffix.getText();
    }

    public String getName() {
        return name.getText();
    }

    public String getRevision() {
        return revision.getText();
    }
}
