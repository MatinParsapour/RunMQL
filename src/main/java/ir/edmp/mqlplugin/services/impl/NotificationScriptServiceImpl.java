package ir.edmp.mqlplugin.services.impl;

import com.intellij.openapi.ui.DialogWrapper;
import ir.edmp.mqlplugin.dialog.NotificationDialog;
import ir.edmp.mqlplugin.services.NotificationScriptService;

import java.util.StringJoiner;

public class NotificationScriptServiceImpl extends ScriptServiceImpl implements NotificationScriptService {

    private NotificationDialog notificationDialog;

    @Override
    public void init(DialogWrapper dialogWrapper) {
        this.notificationDialog = (NotificationDialog) dialogWrapper;
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

    private String getBodyHTML() {
        return "\"Body HTML\" \"" + notificationDialog.getBodyHTML() + "\" ";
    }

    private String getBodyText() {
        return "\"Body Text\" \"" + notificationDialog.getBodyText() + "\" ";
    }

    private String getDynamicBCCList() {
        return "\"Dynamic BCC List\" \"" + notificationDialog.getDynamicBCCList() + "\" ";
    }

    private String getDynamicCCList() {
        return "\"Dynamic CC List\" \"" + notificationDialog.getDynamicCCList() + "\" ";
    }

    private String getDynamicToList() {
        return "\"Dynamic To List\" \"" + notificationDialog.getDynamicToList() + "\" ";
    }

    private String getFilter() {
        return "\"Filter\" \"" + notificationDialog.getFilter() + "\" ";
    }

    private String getFromAgent() {
        return "\"From Agent\" \"" + notificationDialog.getFromAgent() + "\" ";
    }

    private String getConsolidationJPO() {
        return "\"Consolidation JPO\" \"" + notificationDialog.getConsolidationJPO() + "\" ";
    }

    private String getAttachments() {
        return "\"Attachments\" \"" + notificationDialog.getAttachments() + "\" ";
    }

    private String getPreprocessesJPO() {
        return "\"Preprocess JPO\" \"" + notificationDialog.getPreprocessesJPO() + "\" ";
    }

    private String getRegisteredSuite() {
        return "\"Registered Suite\" \"" + notificationDialog.getRegisteredSuite() + "\" ";
    }

    private String getReplyTo() {
        return "\"Reply To\" \"" + notificationDialog.getReplyTo() + "\" ";
    }

    private String getStaticBCCList() {
        return "\"Static BCC List\" \"" + notificationDialog.getStaticBCCList() + "\" ";
    }

    private String getStaticCCList() {
        return "\"Static CC List\" \"" + notificationDialog.getStaticCCList() + "\" ";
    }

    private String getStaticToList() {
        return "\"Static To List\" \"" + notificationDialog.getStaticToList() + "\" ";
    }

    private String getSubjectText() {
        return "\"Subject Text\" \"" + notificationDialog.getSubjectText() + "\" ";
    }

    private String getUrlSuffix() {
        return "\"URL Suffix\" \"" + notificationDialog.getUrlSuffix() + "\" ";
    }

    private String getCurrent() {
        return "current Active;";
    }
}
