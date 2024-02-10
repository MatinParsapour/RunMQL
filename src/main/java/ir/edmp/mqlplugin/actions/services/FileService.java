package ir.edmp.mqlplugin.actions.services;

import java.io.IOException;
import java.util.Map;

public interface FileService {

    String read(String key) throws IOException;

    void write(Map<String, String> properties);
}
