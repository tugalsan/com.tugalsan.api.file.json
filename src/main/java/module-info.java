module com.tugalsan.api.file.json {
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.tugalsan.api.unsafe;
    requires com.tugalsan.api.file.txt;
    exports com.tugalsan.api.file.json.client;
    exports com.tugalsan.api.file.json.server;
}
