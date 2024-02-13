package ir.edmp.mqlplugin.services.impl;

import ir.edmp.mqlplugin.dialog.TriggerDialog;
import ir.edmp.mqlplugin.services.TriggerScriptService;

import java.util.StringJoiner;

public class TriggerScriptServiceImpl extends ScriptServiceImpl implements TriggerScriptService {

    private final TriggerDialog wrapper;

    public TriggerScriptServiceImpl(TriggerDialog wrapper) {
        this.wrapper = wrapper;
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
        return "\"eService Program Argument 1\" \"" + wrapper.getArg2() + "\" ";
    }

    private String getArg3() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg3() + "\" ";
    }

    private String getArg4() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg4() + "\" ";
    }

    private String getArg5() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg5() + "\" ";
    }

    private String getArg6() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg6() + "\" ";
    }

    private String getArg7() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg7() + "\" ";
    }

    private String getArg8() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg8() + "\" ";
    }

    private String getArg9() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg9() + "\" ";
    }

    private String getArg10() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg10() + "\" ";
    }

    private String getArg11() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg11() + "\" ";
    }

    private String getArg12() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg12() + "\" ";
    }

    private String getArg13() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg13() + "\" ";
    }

    private String getArg14() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg14() + "\" ";
    }

    private String getArg15() {
        return "\"eService Program Argument 1\" \"" + wrapper.getArg15() + "\" ";
    }

    private String getCurrent() {
        return "current Active;";
    }
}
