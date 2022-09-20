package com.alibaba.cloud.youxia.logback.appender.dubbo;

import com.alibaba.cloud.youxia.log.ObjectJsonFormat;
import com.alibaba.cloud.youxia.log.util.LogUtil;
import com.alibaba.cloud.youxia.logback.appender.logger.LoggerMarker;
import com.alibaba.cloud.youxia.logback.appender.logger.Loggers;
import com.alibaba.fastjson.JSON;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.utils.ConfigUtils;
import org.apache.dubbo.rpc.*;
import org.apache.dubbo.rpc.service.GenericService;
import static com.alibaba.cloud.youxia.logback.appender.logger.LoggerMarker.*;
import static org.apache.dubbo.rpc.Constants.ACCESS_LOG_KEY;

@Activate(group = {CommonConstants.PROVIDER}, value = Constants.ACCESS_LOG_KEY, order = 90000)
public class DubboLogFilter implements Filter {
    private final static String PACKAGE_ALIBABA = "com.apache.dubbo";
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        DubboLogProperties properties = DubboLogProperties.getInstance();
        String accessLogKey = invoker.getUrl().getParameter(ACCESS_LOG_KEY);
        if (!properties.isEnable() || invoker.getUrl().getServiceInterface().startsWith(PACKAGE_ALIBABA) || ConfigUtils.isEmpty(accessLogKey)) {
            return invoker.invoke(invocation);
        }
        Result result;
        LogInfo logInfo = buildLogInfo(invoker, invocation);
        long start = System.currentTimeMillis();
        try {
            result = invoker.invoke(invocation);
            Long time = System.currentTimeMillis() - start;
            logInfo.setTime(time);
            String side = logInfo.getSide();
            if (!result.hasException()) {
                logInfo.setResponse(JSON.toJSONString(result.getValue()));
                if (recordSlow(side, time)) {
                    printDubboLog(logInfo, DUBBO_SLOW_LOG, null);
                } else if (recordAccess(side, time)) {
                    printDubboLog(logInfo, DUBBO_ACCESS_LOG, null);
                }
                if (recordDeprecated(invoker.getUrl().getServiceInterface())) {
                    printDubboLog(logInfo, DUBBO_DEPRECATED_LOG, null);
                }
                return result;
            }
            Throwable t = result.getException();
            if (properties.isExceptionLogEnable() && GenericService.class != invoker.getInterface()) {
                printDubboLog(logInfo, DUBBO_EXCEPTION_LOG, t);
            }
        } catch (Throwable t) {
            if (properties.isExceptionLogEnable()) {
                printDubboLog(logInfo, DUBBO_EXCEPTION_LOG, t);
            }
            throw t;
        }
        return result;
    }

    private boolean recordSlow(String side, Long time) {
        DubboLogProperties properties = DubboLogProperties.getInstance();
        return (CommonConstants.CONSUMER_SIDE.equals(side) && time > properties.getConsumerSlowLogTime())
                || CommonConstants.PROVIDER_SIDE.equals(side) && time > properties.getProviderSlowLogTime();
    }

    private boolean recordAccess(String side, Long time) {
        DubboLogProperties properties = DubboLogProperties.getInstance();
        return (CommonConstants.CONSUMER_SIDE.equals(side) && time > properties.getConsumerAccessLogTime())
                || CommonConstants.PROVIDER_SIDE.equals(side) && time > properties.getProviderAccessLogTime();
    }

    private boolean recordDeprecated(String service) {
        DubboLogProperties properties = DubboLogProperties.getInstance();
        return properties.getDeprecatedService() != null && properties.getDeprecatedService().size() > 0
                && properties.getDeprecatedService().contains(service);
    }

    private void printDubboLog(LogInfo logInfo, String dubboMarker, Throwable t) {
        if (dubboMarker.equals(DUBBO_ACCESS_LOG) || dubboMarker.equals(LoggerMarker.DUBBO_SLOW_LOG)) {
            Loggers.DubboAccessLogger.info(LogUtil.formatLog(ObjectJsonFormat.title(dubboMarker).obj(logInfo)));
        } else if (dubboMarker.equals(DUBBO_DEPRECATED_LOG)) {
            Loggers.AlertLogger.info(LogUtil.formatLog(ObjectJsonFormat.title(dubboMarker).obj(logInfo)));
        } else {
            Loggers.ExceptionLogger.error(LogUtil.formatLog(ObjectJsonFormat.title(dubboMarker).obj(logInfo)), t);
        }
    }

    private LogInfo buildLogInfo(Invoker<?> invoker, Invocation invocation) {
        RpcContext context = RpcContext.getContext();
        LogInfo logInfo = new LogInfo();
        try {
            boolean providerSide = context.isProviderSide();
            if (providerSide) {
                logInfo.setProviderIp(context.getLocalHost());
                logInfo.setConsumerIp(context.getRemoteHost());
                logInfo.setSide(CommonConstants.PROVIDER_SIDE);
            } else {
                logInfo.setProviderIp(context.getRemoteHost());
                logInfo.setConsumerIp(context.getLocalHost());
                logInfo.setSide(CommonConstants.CONSUMER_SIDE);
            }
            logInfo.setMethod(invocation.getMethodName());
            logInfo.setService(invoker.getUrl().getServiceInterface());
            logInfo.setInvoke(getInvokeInfo(invoker, invocation));
        } catch (Throwable e) {
        }
        return logInfo;
    }

    private String getInvokeInfo(Invoker<?> invoker, Invocation invocation) {
        StringBuilder sb = new StringBuilder(128);
        sb.append("invoke ")
                .append(invoker.getUrl().getServiceInterface())
                .append(".")
                .append(invocation.getMethodName())
                .append("(");
        Object[] args = invocation.getArguments();
        if (args != null) {
            String str = JSON.toJSONString(args);
            str = str.substring(1, str.length() - 1);
            str = str.replace("\"", "'");
            sb.append(str);
        }
        sb.append(")");
        return sb.toString();
    }
}