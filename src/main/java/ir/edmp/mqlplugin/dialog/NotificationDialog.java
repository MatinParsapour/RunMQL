package ir.edmp.mqlplugin.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jdesktop.swingx.VerticalLayout;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class NotificationDialog extends DialogWrapper {



    private JTextField name = new JTextField();
    private JTextField revision = new JTextField();
    private JTextField bodyHTML = new JTextField();
    private JTextField bodyText = new JTextField();
    private JTextField dynamicBCCList = new JTextField();
    private JTextField dynamicCCList = new JTextField();
    private JTextField dynamicToList = new JTextField();
    private JTextField filter = new JTextField();
    private JTextField fromAgent = new JTextField();
    private JTextField consolidationJPO = new JTextField();
    private JTextField attachments = new JTextField();
    private JTextField preprocessesJPO = new JTextField();
    private JTextField registeredSuite = new JTextField();
    private JTextField replyTo = new JTextField();
    private JTextField staticBCCList = new JTextField();
    private JTextField staticCCList = new JTextField();
    private JTextField staticToList = new JTextField();
    private JTextField subjectText = new JTextField();
    private JTextField urlSuffix = new JTextField();
    private JPanel panel;

    public NotificationDialog() {
        super(true); // use current window as parent
        setTitle("Trigger Attributes");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new VerticalLayout());
        dialogPanel.add(addAttributeField(name, "Name : "));
        dialogPanel.add(addAttributeField(revision, "Revision : "));
        dialogPanel.add(addAttributeField(subjectText, "Subject Text : "));
        dialogPanel.add(addAttributeField(bodyText, "Body Text : "));
        dialogPanel.add(addAttributeField(bodyHTML, "Body HTML : "));
        dialogPanel.add(addAttributeField(dynamicToList, "Dynamic To List : "));
        dialogPanel.add(addAttributeField(filter, "Filter : "));
        dialogPanel.add(addAttributeField(fromAgent, "From Agent : "));
        dialogPanel.add(addAttributeField(dynamicBCCList, "Dynamic BCC List : "));
        dialogPanel.add(addAttributeField(dynamicCCList, "Dynamic CC List : "));
        dialogPanel.add(addAttributeField(consolidationJPO, "Consolidation JPO : "));
        dialogPanel.add(addAttributeField(attachments, "Attachments : "));
        dialogPanel.add(addAttributeField(preprocessesJPO, "Preprocesses JPO : "));
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
