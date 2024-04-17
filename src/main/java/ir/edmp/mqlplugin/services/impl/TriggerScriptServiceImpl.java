package ir.edmp.mqlplugin.services.impl;

import com.intellij.openapi.ui.DialogWrapper;
import ir.edmp.mqlplugin.dialog.TriggerDialog;
import ir.edmp.mqlplugin.services.TriggerScriptService;

import java.util.StringJoiner;

public class TriggerScriptServiceImpl extends ScriptServiceImpl implements TriggerScriptService {

    private final TriggerDialog wrapper;


    public TriggerScriptServiceImpl(DialogWrapper dialogWrapper) {
        this.wrapper = (TriggerDialog) dialogWrapper;
    }

    @Override
    public String generateScript() {
        StringJoiner script = new StringJoiner("\n");
        return script
                .add(getAddBus())
                .add(getPolicy())
                .add(getVault())
                .add(getProgramName())
                .add(getMethodName())
                .add(getSequenceNumber())
                .add(getArg1())
                .add(getArg2())
                .add(getArg3())
                .add(getArg4())
                .add(getArg5())
                .add(getArg6())
                .add(getArg7())
                .add(getArg8())
                .add(getArg9())
                .add(getArg10())
                .add(getArg11())
                .add(getArg12())
                .add(getArg13())
                .add(getArg14())
                .add(getArg15())
                .add(getCurrent())
                .toString();
    }

    @Override
    public String generateRawScript() {
        StringBuilder script = new StringBuilder();
        return script
                .append(getAddBus())
                .append(getPolicy())
                .append(getVault())
                .append(getProgramName())
                .append(getMethodName())
                .append(getSequenceNumber())
                .append(getArg1())
                .append(getArg2())
                .append(getArg3())
                .append(getArg4())
                .append(getArg5())
                .append(getArg6())
                .append(getArg7())
                .append(getArg8())
                .append(getArg9())
                .append(getArg10())
                .append(getArg11())
                .append(getArg12())
                .append(getArg13())
                .append(getArg14())
                .append(getArg15())
                .append(getCurrent())
                .toString();
    }

    private String getAddBus() {
        return "add bus \"eService Trigger Program Parameters\" \"" + wrapper.getName() + "\" \"" + wrapper.getRevision() + "\" ";
    }

    private String getPolicy() {
        return "policy \"eService Trigger Program Policy\" ";
    }

    private String getVault() {
        return "vault \"eService Administration\" ";
    }

    private String getProgramName() {
        return "\"eService Program Name\" \"" + wrapper.getProgramName()  + "\" ";
    }

    private String getMethodName() {
        return "\"eService Method Name\" \"" + wrapper.getMethodName() + "\" ";
    }

    private String getSequenceNumber() {
        return "\"eService Sequence Number\" \"" + wrapper.getSequenceNumber() + "\" ";
    }

    private String getArg1() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg1() + "\" ";
    }

    private String getArg2() {
        return "\"eService Program Argument 2\" \"" + wrapper.getArg2() + "\" ";
    }

    private String getArg3() {
        return "\"eService Program Argument 3\" \"" + wrapper.getArg3() + "\" ";
    }

    private String getArg4() {
        return "\"eService Program Argument 4\" \"" + wrapper.getArg4() + "\" ";
    }

    private String getArg5() {
        return "\"eService Program Argument 5\" \"" + wrapper.getArg5() + "\" ";
    }

    private String getArg6() {
        return "\"eService Program Argument 6\" \"" + wrapper.getArg6() + "\" ";
    }

    private String getArg7() {
        return "\"eService Program Argument 7\" \"" + wrapper.getArg7() + "\" ";
    }

    private String getArg8() {
        return "\"eService Program Argument 8\" \"" + wrapper.getArg8() + "\" ";
    }

    private String getArg9() {
        return "\"eService Program Argument 9\" \"" + wrapper.getArg9() + "\" ";
    }

    private String getArg10() {
        return "\"eService Program Argument 10\" \"" + wrapper.getArg10() + "\" ";
    }

    private String getArg11() {
        return "\"eService Program Argument 11\" \"" + wrapper.getArg11() + "\" ";
    }

    private String getArg12() {
        return "\"eService Program Argument 12\" \"" + wrapper.getArg12() + "\" ";
    }

    private String getArg13() {
        return "\"eService Program Argument 13\" \"" + wrapper.getArg13() + "\" ";
    }

    private String getArg14() {
        return "\"eService Program Argument 14\" \"" + wrapper.getArg14() + "\" ";
    }

    private String getArg15() {
        return "\"eService Program Argument 15\" \"" + wrapper.getArg15() + "\" ";
    }

    private String getCurrent() {
        return "current Active;";
    }
}
