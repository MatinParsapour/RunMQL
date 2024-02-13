package ir.edmp.mqlplugin.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jdesktop.swingx.VerticalLayout;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class TriggerDialog extends DialogWrapper {

    private final JTextField name = new JTextField();
    private final JTextField revision = new JTextField();
    private final JTextField programName = new JTextField();
    private final JTextField sequenceNumber = new JTextField();
    private final JTextField methodName = new JTextField();
    private final JTextField arg1 = new JTextField();
    private final JTextField arg2 = new JTextField();
    private final JTextField arg3 = new JTextField();
    private final JTextField arg4 = new JTextField();
    private final JTextField arg5 = new JTextField();
    private final JTextField arg6 = new JTextField();
    private final JTextField arg7 = new JTextField();
    private final JTextField arg8 = new JTextField();
    private final JTextField arg9 = new JTextField();
    private final JTextField arg10 = new JTextField();
    private final JTextField arg11 = new JTextField();
    private final JTextField arg12 = new JTextField();
    private final JTextField arg13 = new JTextField();
    private final JTextField arg14 = new JTextField();
    private final JTextField arg15 = new JTextField();

    public TriggerDialog() {
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
        dialogPanel.add(addAttributeField(programName, "Program Name : "));
        dialogPanel.add(addAttributeField(methodName, "Method Name : "));
        dialogPanel.add(addAttributeField(sequenceNumber, "Sequence Number : "));
        dialogPanel.add(addAttributeField(arg1, "Argument 1 : "));
        dialogPanel.add(addAttributeField(arg2, "Argument 2 : "));
        dialogPanel.add(addAttributeField(arg3, "Argument 3 : "));
        dialogPanel.add(addAttributeField(arg4, "Argument 4 : "));
        dialogPanel.add(addAttributeField(arg5, "Argument 5 : "));
        dialogPanel.add(addAttributeField(arg6, "Argument 6 : "));
        dialogPanel.add(addAttributeField(arg7, "Argument 7 : "));
        dialogPanel.add(addAttributeField(arg8, "Argument 8 : "));
        dialogPanel.add(addAttributeField(arg9, "Argument 9 : "));
        dialogPanel.add(addAttributeField(arg10, "Argument 10 : "));
        dialogPanel.add(addAttributeField(arg11, "Argument 11 : "));
        dialogPanel.add(addAttributeField(arg12, "Argument 12 : "));
        dialogPanel.add(addAttributeField(arg13, "Argument 13 : "));
        dialogPanel.add(addAttributeField(arg14, "Argument 14 : "));
        dialogPanel.add(addAttributeField(arg15, "Argument 15 : "));
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

    public String getName() {
        return name.getText();
    }

    public String getRevision() {
        return revision.getText();
    }

    public String getProgramName() {
        return programName.getText();
    }

    public String getSequenceNumber() {
        return sequenceNumber.getText();
    }

    public String getMethodName() {
        return methodName.getText();
    }

    public String getArg1() {
        return arg1.getText();
    }

    public String getArg2() {
        return arg2.getText();
    }

    public String getArg3() {
        return arg3.getText();
    }

    public String getArg4() {
        return arg4.getText();
    }

    public String getArg5() {
        return arg5.getText();
    }

    public String getArg6() {
        return arg6.getText();
    }

    public String getArg7() {
        return arg7.getText();
    }

    public String getArg8() {
        return arg8.getText();
    }

    public String getArg9() {
        return arg9.getText();
    }

    public String getArg10() {
        return arg10.getText();
    }

    public String getArg11() {
        return arg11.getText();
    }

    public String getArg12() {
        return arg12.getText();
    }

    public String getArg13() {
        return arg13.getText();
    }

    public String getArg14() {
        return arg14.getText();
    }

    public String getArg15() {
        return arg15.getText();
    }
}
