package com.ybj.gateWay.constants;

import lombok.Getter;


/**
 * 免认证的URL
 * @author caicai.gao
 */
@Getter
public enum AnonUrlEnum {
    /**
     *枚举免认证的url
     */
    LOGIN("login", ConfigConstants.REQUEST_LOGIN),
    STATIC("RepositoryEntry", ConfigConstants.REPOSITORY_PATH),
    INTERFACE("interface", ConfigConstants.INTERFACE_PATH),
    API("apiDoc", ConfigConstants.API_DOC_PATH),
    WEBJARS("webjars", ConfigConstants.WEBJARS),
    SWAGGER("swagger", ConfigConstants.SWAGGER),
    API_VERSION("api_v2", ConfigConstants.API_VERSION),
    AO_DOWNLOAD("ao_download", ConfigConstants.AO_DOWNLOAD),
    AO_UPLOAD("ao_upload", ConfigConstants.AO_UPLOAD);
    AnonUrlEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private final String name;

    private final String value;
}
