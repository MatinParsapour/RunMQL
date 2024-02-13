package ir.edmp.mqlplugin.services.impl;

import ir.edmp.mqlplugin.dialog.NotificationDialog;
import ir.edmp.mqlplugin.services.NotificationScriptService;

import java.util.StringJoiner;

public class NotificationScriptServiceImpl extends ScriptServiceImpl implements NotificationScriptService {

    private final NotificationDialog notificationDialog;

    public NotificationScriptServiceImpl(NotificationDialog notificationDialog) {
        this.notificationDialog = notificationDialog;
    }

    @Override
    public String generateScript() {
        StringJoiner script = new StringJoiner("\n");
        return script
                .add(getAddBus())
                .add(getPolicy())
                .add(getVault())
                .add(getSubjectText())
                .add(getBodyText())
                .add(getBodyHTML())
                .add(getDynamicToList())
                .add(getFilter())
                .add(getFromAgent())
                .add(getDynamicBCCList())
                .add(getDynamicCCList())
                .add(getConsolidationJPO())
                .add(getAttachments())
                .add(getPreprocessesJPO())
                .add(getRegisteredSuite())
                .add(getReplyTo())
                .add(getStaticBCCList())
                .add(getStaticCCList())
                .add(getStaticToList())
                .add(getUrlSuffix())
                .add(getCurrent())
                .toString();
    }

    private String getAddBus() {
        return "add bus Notification \"" + notificationDialog.getName() + "\" \"" + notificationDialog.getRevision() + "\" ";
    }

    private String getPolicy() {
        return "policy \"Business Rule\" ";
    }

    private String getVault() {
        return "vault \"eService Administration\" ";
    }

    public String getBodyHTML() {
        return "\"Body HTML\" \"" + notificationDialog.getBodyHTML() + "\" ";
    }

    public String getBodyText() {
        return "\"Body Text\" \"" + notificationDialog.getBodyText() + "\" ";
    }

    public String getDynamicBCCList() {
        return "\"Dynamic BCC List\" \"" + notificationDialog.getDynamicBCCList() + "\" ";
    }

    public String getDynamicCCList() {
        return "\"Dynamic CC List\" \"" + notificationDialog.getDynamicCCList() + "\" ";
    }

    public String getDynamicToList() {
        return "\"Dynamic To List\"\"" + notificationDialog.getDynamicToList() + "\" ";
    }

    public String getFilter() {
        return "\"Filter\" \"" + notificationDialog.getFilter() + "\" ";
    }

    public String getFromAgent() {
        return "\"From Agent\" \"" + notificationDialog.getFromAgent() + "\" ";
    }

    public String getConsolidationJPO() {
        return "\"Consolidation JPO\" \"" + notificationDialog.getConsolidationJPO() + "\" ";
    }

    public String getAttachments() {
        return "\"Attachments\" \"" + notificationDialog.getAttachments() + "\" ";
    }

    public String getPreprocessesJPO() {
        return "\"Preprocesses JPO\" \"" + notificationDialog.getPreprocessesJPO() + "\" ";
    }

    public String getRegisteredSuite() {
        return "\"Registered Suite\" \"" + notificationDialog.getRegisteredSuite() + "\" ";
    }

    public String getReplyTo() {
        return "\"Reply To\" \"" + notificationDialog.getReplyTo() + "\" ";
    }

    public String getStaticBCCList() {
        return "\"Static BCC List\" \"" + notificationDialog.getStaticBCCList() + "\" ";
    }

    public String getStaticCCList() {
        return "\"Static CC List\" \"" + notificationDialog.getStaticCCList() + "\" ";
    }

    public String getStaticToList() {
        return "\"Static To List\" \"" + notificationDialog.getStaticToList() + "\" ";
    }

    public String getSubjectText() {
        return "\"Subject Text\" \"" + notificationDialog.getSubjectText() + "\" ";
    }

    public String getUrlSuffix() {
        return "\"URL Suffix\" \"" + notificationDialog.getUrlSuffix() + "\" ";
    }

    private String getCurrent() {
        return "current Active;";
    }
}
