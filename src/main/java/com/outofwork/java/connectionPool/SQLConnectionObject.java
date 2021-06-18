package com.outofwork.java.connectionPool;

/**
 * @author outofwork
 * created on 4/28/2020
 */
public class SQLConnectionObject {

    private String url;
    private String dbUsername;
    private String dbPassword;


    public String getUrl() {
        return url;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public static final class SQLConnectionObjectBuilder {
        private String url;
        private String dbUsername;
        private String dbPassword;

        public SQLConnectionObjectBuilder() {
        }

        public static SQLConnectionObjectBuilder aSQLConnectionObject() {
            return new SQLConnectionObjectBuilder();
        }

        public SQLConnectionObjectBuilder setUrl(String url) {
            this.url = url;
            return this;
        }

        public SQLConnectionObjectBuilder setDbUsername(String dbUsername) {
            this.dbUsername = dbUsername;
            return this;
        }

        public SQLConnectionObjectBuilder setDbPassword(String dbPassword) {
            this.dbPassword = dbPassword;
            return this;
        }

        public SQLConnectionObject build() {
            SQLConnectionObject sQLConnectionObject = new SQLConnectionObject();
            sQLConnectionObject.dbUsername = this.dbUsername;
            sQLConnectionObject.url = this.url;
            sQLConnectionObject.dbPassword = this.dbPassword;
            return sQLConnectionObject;
        }
    }
}
