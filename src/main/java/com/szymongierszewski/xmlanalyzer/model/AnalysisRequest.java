package com.szymongierszewski.xmlanalyzer.model;

import javax.validation.constraints.NotNull;
import java.net.URL;

public class AnalysisRequest {

    @NotNull
    private URL url;

    public URL getUrl() {
        return url;
    }
}
