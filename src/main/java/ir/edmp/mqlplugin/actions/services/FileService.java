package ir.edmp.mqlplugin.actions.services;

import java.util.Map;

public interface FileService {

    String read(String key);

    void write(Map<String, String> properties);
}
