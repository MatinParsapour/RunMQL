package ir.edmp.mqlplugin.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import org.jdesktop.swingx.VerticalLayout;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class SystemHandlerDialog extends DialogWrapper {
    private final JTextField name = new JTextField();
    private final JTextField title = new JTextField();
    private final JTextField category = new JTextField();
    private final JTextField program = new JTextField();
    private final JTextField method = new JTextField();
    private final JTextField param1 = new JTextField();
    private final JTextField param2 = new JTextField();
    private final JTextField param3 = new JTextField();
    private final JTextField param4 = new JTextField();
    private final JTextField param5 = new JTextField();
    private final JTextField param6 = new JTextField();
    private final JTextField param7 = new JTextField();
    private final JTextField param8 = new JTextField();
    private final JTextField param9 = new JTextField();
    private final JTextField param10 = new JTextField();
    private final JTextField param1Type = new JTextField("text");
    private final JTextField param2Type = new JTextField("text");
    private final JTextField param3Type = new JTextField("text");
    private final JTextField param4Type = new JTextField("text");
    private final JTextField param5Type = new JTextField("text");
    private final JTextField param6Type = new JTextField("text");
    private final JTextField param7Type = new JTextField("text");
    private final JTextField param8Type = new JTextField("text");
    private final JTextField param9Type = new JTextField("text");
    private final JTextField param10Type = new JTextField("text");

    public SystemHandlerDialog() {
        super(true); // use current window as parent
        setTitle("System Handler Attributes");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new VerticalLayout());
        dialogPanel.add(addAttributeField(name, "Name : "));
        dialogPanel.add(addAttributeField(title, "Title : "));
        dialogPanel.add(addAttributeField(category, "Category : "));
        dialogPanel.add(addAttributeField(program, "Program : "));
        dialogPanel.add(addAttributeField(method, "Method : "));
        dialogPanel.add(addAttributeField(param1, "Parameter 1 : "));
        dialogPanel.add(addAttributeField(param2, "Parameter 2 : "));
        dialogPanel.add(addAttributeField(param3, "Parameter 3 : "));
        dialogPanel.add(addAttributeField(param4, "Parameter 4 : "));
        dialogPanel.add(addAttributeField(param5, "Parameter 5 : "));
        dialogPanel.add(addAttributeField(param6, "Parameter 6 : "));
        dialogPanel.add(addAttributeField(param7, "Parameter 7 : "));
        dialogPanel.add(addAttributeField(param8, "Parameter 8 : "));
        dialogPanel.add(addAttributeField(param9, "Parameter 9 : "));
        dialogPanel.add(addAttributeField(param10, "Parameter 10 : "));
        dialogPanel.add(addAttributeField(param1Type, "Parameter Type 1 : "));
        dialogPanel.add(addAttributeField(param2Type, "Parameter Type 2 : "));
        dialogPanel.add(addAttributeField(param3Type, "Parameter Type 3 : "));
        dialogPanel.add(addAttributeField(param4Type, "Parameter Type 4 : "));
        dialogPanel.add(addAttributeField(param5Type, "Parameter Type 5 : "));
        dialogPanel.add(addAttributeField(param6Type, "Parameter Type 6 : "));
        dialogPanel.add(addAttributeField(param7Type, "Parameter Type 7 : "));
        dialogPanel.add(addAttributeField(param8Type, "Parameter Type 8 : "));
        dialogPanel.add(addAttributeField(param9Type, "Parameter Type 9 : "));
        dialogPanel.add(addAttributeField(param10Type, "Parameter Type 10 : "));
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

    @Override
    public String getTitle() {
        return title.getText();
    }

    public String getCategory() {
        return category.getText();
    }

    public String getProgram() {
        return program.getText();
    }

    public String getMethod() {
        return method.getText();
    }

    public String getParam1() {
        return param1.getText();
    }

    public String getParam2() {
        return param2.getText();
    }

    public String getParam3() {
        return param3.getText();
    }

    public String getParam4() {
        return param4.getText();
    }

    public String getParam5() {
        return param5.getText();
    }

    public String getParam6() {
        return param6.getText();
    }

    public String getParam7() {
        return param7.getText();
    }

    public String getParam8() {
        return param8.getText();
    }

    public String getParam9() {
        return param9.getText();
    }

    public String getParam10() {
        return param10.getText();
    }

    public String getParam1Type() {
        return param1Type.getText();
    }

    public String getParam2Type() {
        return param2Type.getText();
    }

    public String getParam3Type() {
        return param3Type.getText();
    }

    public String getParam4Type() {
        return param4Type.getText();
    }

    public String getParam5Type() {
        return param5Type.getText();
    }

    public String getParam6Type() {
        return param6Type.getText();
    }

    public String getParam7Type() {
        return param7Type.getText();
    }

    public String getParam8Type() {
        return param8Type.getText();
    }

    public String getParam9Type() {
        return param9Type.getText();
    }

    public String getParam10Type() {
        return param10Type.getText();
    }
}
