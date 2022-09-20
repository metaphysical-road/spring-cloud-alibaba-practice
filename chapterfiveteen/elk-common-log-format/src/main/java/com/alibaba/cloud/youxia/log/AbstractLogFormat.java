package com.alibaba.cloud.youxia.log;
import org.apache.commons.lang3.StringUtils;
public abstract class AbstractLogFormat implements LogFormat {
    protected String title;

    public AbstractLogFormat(String title) {
        this.title = title;
    }
    private String getTitle() {
        StringBuilder sb = new StringBuilder(32);
        if (StringUtils.isNotBlank(title)) {
            sb.append("[").append(title).append("] ");
        }
        return sb.toString();
    }
    @Override
    public String log() {
        return getTitle() + buildLogMsg();
    }
    protected abstract String buildLogMsg();
}