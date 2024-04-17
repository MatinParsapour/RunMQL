package ir.edmp.mqlplugin.settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static ir.edmp.mqlplugin.constants.Constant.*;

@State(
        name = "ir.edmp.mqlplugin.services.impl.RunMQLSettings",
        storages = @Storage("RunMQLSettings.xml")
)
public class RunMQLSettings implements PersistentStateComponent<RunMQLSettings> {

    private String mqlLocation = DEFAULT_PROJECTS_DIRECTORY;
    private String username = DEFAULT_USERNAME;
    private String password = DEFAULT_PASSWORD;
    private boolean printProgramImmediately = true;

    public static RunMQLSettings getInstance() {
        return ApplicationManager.getApplication().getService(RunMQLSettings.class);
    }

    @Override
    public @Nullable RunMQLSettings getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull RunMQLSettings state) {
        XmlSerializerUtil.copyBean(state, this);
    }

    public String getMqlLocation() {
        return mqlLocation;
    }

    public void setMqlLocation(String mqlLocation) {
        this.mqlLocation = mqlLocation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPrintProgramImmediately() {
        return printProgramImmediately;
    }

    public void setPrintProgramImmediately(boolean printProgramImmediately) {
        this.printProgramImmediately = printProgramImmediately;
    }
}
