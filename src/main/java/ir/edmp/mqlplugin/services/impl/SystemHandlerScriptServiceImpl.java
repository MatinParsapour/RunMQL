package ir.edmp.mqlplugin.services.impl;

import com.intellij.openapi.ui.DialogWrapper;
import ir.edmp.mqlplugin.dialog.SystemHandlerDialog;
import ir.edmp.mqlplugin.services.SystemHandlerScriptService;

import java.util.StringJoiner;

public class SystemHandlerScriptServiceImpl extends ScriptServiceImpl implements SystemHandlerScriptService {

    private SystemHandlerDialog wrapper;


    @Override
    public void init(DialogWrapper dialogWrapper) {
        this.wrapper = (SystemHandlerDialog) dialogWrapper;
    }

    @Override
    public String generateScript() {
        StringJoiner script = new StringJoiner("\n");
        return script
                .add(getAddBus())
                .add(getPolicy())
                .add(getVault())
                .add(getTitle())
                .add(getCategory())
                .add(getProgram())
                .add(getMethod())
                .add(getParam1())
                .add(getParam2())
                .add(getParam3())
                .add(getParam4())
                .add(getParam5())
                .add(getParam6())
                .add(getParam7())
                .add(getParam8())
                .add(getParam9())
                .add(getParam10())
                .add(getParam1Type())
                .add(getParam2Type())
                .add(getParam3Type())
                .add(getParam4Type())
                .add(getParam5Type())
                .add(getParam6Type())
                .add(getParam7Type())
                .add(getParam8Type())
                .add(getParam9Type())
                .add(getParam10Type())
                .add(getCurrent())
                .toString();
    }

    private String getAddBus() {
        return "add bus \"XBC_SystemHandler\" \"" + wrapper.getName() + "\" - ";
    }

    private String getPolicy() {
        return "policy \"XBC_Handler\" ";
    }

    private String getVault() {
        return "vault \"eService Production\" ";
    }

    private String getTitle() {
        return "\"Title\" \"" + wrapper.getTitle() + "\"";
    }

    private String getCategory() {
        return "\"XBC_HandlerCategory\" \"" + wrapper.getCategory() + "\"";
    }

    private String getProgram() {
        return "\"XBC_HandlerProgram\" \"" + wrapper.getProgram() + "\"";
    }

    private String getMethod() {
        return "\"XBC_HandlerMethod\" \"" + wrapper.getMethod() + "\"";
    }

    private String getParam1() {
        return "\"XBC_Parameter1\" \"" + wrapper.getParam1() + "\"";
    }

    private String getParam2() {
        return "\"XBC_Parameter2\" \"" + wrapper.getParam2() + "\"";
    }

    private String getParam3() {
        return "\"XBC_Parameter3\" \"" + wrapper.getParam3() + "\"";
    }

    private String getParam4() {
        return "\"XBC_Parameter4\" \"" + wrapper.getParam4() + "\"";
    }

    private String getParam5() {
        return "\"XBC_Parameter5\" \"" + wrapper.getParam5() + "\"";
    }

    private String getParam6() {
        return "\"XBC_Parameter6\" \"" + wrapper.getParam6() + "\"";
    }

    private String getParam7() {
        return "\"XBC_Parameter7\" \"" + wrapper.getParam7() + "\"";
    }

    private String getParam8() {
        return "\"XBC_Parameter8\" \"" + wrapper.getParam8() + "\"";
    }

    private String getParam9() {
        return "\"XBC_Parameter9\" \"" + wrapper.getParam9() + "\"";
    }

    private String getParam10() {
        return "\"XBC_Parameter10\" \"" + wrapper.getParam10() + "\"";
    }

    private String getParam1Type() {
        return "\"XBC_ParameterType1\" \"" + wrapper.getParam1Type() + "\"";
    }

    private String getParam2Type() {
        return "\"XBC_ParameterType2\" \"" + wrapper.getParam2Type() + "\"";
    }

    private String getParam3Type() {
        return "\"XBC_ParameterType3\" \"" + wrapper.getParam3Type() + "\"";
    }

    private String getParam4Type() {
        return "\"XBC_ParameterType4\" \"" + wrapper.getParam4Type() + "\"";
    }

    private String getParam5Type() {
        return "\"XBC_ParameterType5\" \"" + wrapper.getParam5Type() + "\"";
    }

    private String getParam6Type() {
        return "\"XBC_ParameterType6\" \"" + wrapper.getParam6Type() + "\"";
    }

    private String getParam7Type() {
        return "\"XBC_ParameterType7\" \"" + wrapper.getParam7Type() + "\"";
    }

    private String getParam8Type() {
        return "\"XBC_ParameterType8\" \"" + wrapper.getParam8Type() + "\"";
    }

    private String getParam9Type() {
        return "\"XBC_ParameterType9\" \"" + wrapper.getParam9Type() + "\"";
    }

    private String getParam10Type() {
        return "\"XBC_ParameterType10\" \"" + wrapper.getParam10Type() + "\"";
    }

    private String getCurrent() {
        return "current Active;";
    }
}
