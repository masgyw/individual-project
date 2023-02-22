package cn.gyw.individual.commons.model;

import java.util.List;

public class SwaggerProperties {

    /**
     * API文档生成基础路径
     */
    private String apiBasePackage;
    /**
     * 是否要启用登录认证
     */
    private boolean enableSecurity;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档描述
     */
    private String description;
    /**
     * 文档版本
     */
    private String version;
    /**
     * 文档联系人姓名
     */
    private String contactName;
    /**
     * 文档联系人网址
     */
    private String contactUrl;
    /**
     * 文档联系人邮箱
     */
    private String contactEmail;
    /**
     * 标签列表，标签名 标签描述
     */
    private List<String> tagsList;

    public String getApiBasePackage() {
        return apiBasePackage;
    }

    public void setApiBasePackage(String apiBasePackage) {
        this.apiBasePackage = apiBasePackage;
    }

    public boolean isEnableSecurity() {
        return enableSecurity;
    }

    public void setEnableSecurity(boolean enableSecurity) {
        this.enableSecurity = enableSecurity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactUrl() {
        return contactUrl;
    }

    public void setContactUrl(String contactUrl) {
        this.contactUrl = contactUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public List<String> getTagsList() {
        return tagsList;
    }

    public void setTagsList(List<String> tagsList) {
        this.tagsList = tagsList;
    }

    public static final class SwaggerPropertiesBuilder {
        private String apiBasePackage;
        private boolean enableSecurity;
        private String title;
        private String description;
        private String version;
        private String contactName;
        private String contactUrl;
        private String contactEmail;
        private List<String> tagsList;

        private SwaggerPropertiesBuilder() {
        }

        public static SwaggerPropertiesBuilder aSwaggerProperties() {
            return new SwaggerPropertiesBuilder();
        }

        public SwaggerPropertiesBuilder apiBasePackage(String apiBasePackage) {
            this.apiBasePackage = apiBasePackage;
            return this;
        }

        public SwaggerPropertiesBuilder enableSecurity(boolean enableSecurity) {
            this.enableSecurity = enableSecurity;
            return this;
        }

        public SwaggerPropertiesBuilder title(String title) {
            this.title = title;
            return this;
        }

        public SwaggerPropertiesBuilder description(String description) {
            this.description = description;
            return this;
        }

        public SwaggerPropertiesBuilder version(String version) {
            this.version = version;
            return this;
        }

        public SwaggerPropertiesBuilder contactName(String contactName) {
            this.contactName = contactName;
            return this;
        }

        public SwaggerPropertiesBuilder contactUrl(String contactUrl) {
            this.contactUrl = contactUrl;
            return this;
        }

        public SwaggerPropertiesBuilder contactEmail(String contactEmail) {
            this.contactEmail = contactEmail;
            return this;
        }

        public SwaggerPropertiesBuilder tagsList(List<String> tagsList) {
            this.tagsList = tagsList;
            return this;
        }

        public SwaggerProperties build() {
            SwaggerProperties swaggerProperties = new SwaggerProperties();
            swaggerProperties.setApiBasePackage(apiBasePackage);
            swaggerProperties.setEnableSecurity(enableSecurity);
            swaggerProperties.setTitle(title);
            swaggerProperties.setDescription(description);
            swaggerProperties.setVersion(version);
            swaggerProperties.setContactName(contactName);
            swaggerProperties.setContactUrl(contactUrl);
            swaggerProperties.setContactEmail(contactEmail);
            swaggerProperties.setTagsList(tagsList);
            return swaggerProperties;
        }
    }
}
