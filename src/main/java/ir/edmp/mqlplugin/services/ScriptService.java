package ir.edmp.mqlplugin.services;

import com.intellij.openapi.ui.DialogWrapper;

public interface ScriptService extends Service {

    void init(DialogWrapper dialogWrapper);

    String generateScript();
}
