module com.tugalsan.api.file.json {
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires com.tugalsan.api.file.txt;
    requires com.tugalsan.api.union;
    exports com.tugalsan.api.file.json.server;
}
