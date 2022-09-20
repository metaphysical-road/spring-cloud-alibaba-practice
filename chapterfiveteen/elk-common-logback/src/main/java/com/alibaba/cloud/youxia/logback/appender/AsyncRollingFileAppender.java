package com.alibaba.cloud.youxia.logback.appender;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.rolling.RollingFileAppender;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class AsyncRollingFileAppender extends RollingFileAppender<ILoggingEvent> {

    BlockingQueue<ILoggingEvent> blockingQueue;
    int queueSize = 256;

    int discardingThreshold = 0;

    int maxFlushTime = 1000;

    //默认该为true
    boolean includeCallerData = true;
    Worker worker = new Worker();
    public AsyncRollingFileAppender() {
    }

    @Override
    public void start() {
        if (this.queueSize < 1) {
            this.addError("Invalid queue size [" + this.queueSize + "]");
        } else {
            this.blockingQueue = new ArrayBlockingQueue(this.queueSize);
            this.addInfo("Setting discardingThreshold to " + this.discardingThreshold);
            this.worker.setDaemon(true);
            this.worker.setName("AsyncAppender-Worker-" + this.getName());
//            make sure this instance is marked as "started" before staring the worker Thread
            super.start();
            this.worker.start();
        }
    }

    @Override
    public void stop() {
        if (!isStarted()) {
            return;
        }
        super.stop();
        worker.interrupt();
        try {
            worker.join(maxFlushTime);
            if (worker.isAlive()) {
                addWarn("Max queue flush timeout (" + maxFlushTime + " ms) exceeded. Approximately " + blockingQueue.size() +
                        " queued events were possibly discarded.");
            } else {
                addInfo("Queue flush finished successfully within timeout.");
            }

        } catch (InterruptedException e) {
            addError("Failed to join worker thread. " + blockingQueue.size() + " queued events may be discarded.", e);
        }
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        if (!this.isQueueBelowDiscardingThreshold()) {
            eventObject.prepareForDeferredProcessing();
            eventObject.getCallerData();
            this.put(eventObject);
        }
    }

    private boolean isQueueBelowDiscardingThreshold() {
        return this.blockingQueue.remainingCapacity() < this.discardingThreshold;
    }


    private void put(ILoggingEvent eventObject) {
        this.blockingQueue.offer(eventObject);
    }

    public BlockingQueue<ILoggingEvent> getBlockingQueue() {
        return blockingQueue;
    }

    public void setBlockingQueue(BlockingQueue<ILoggingEvent> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public int getDiscardingThreshold() {
        return discardingThreshold;
    }

    public void setDiscardingThreshold(int discardingThreshold) {
        this.discardingThreshold = discardingThreshold;
    }

    public int getMaxFlushTime() {
        return maxFlushTime;
    }

    public void setMaxFlushTime(int maxFlushTime) {
        this.maxFlushTime = maxFlushTime;
    }

    public boolean isIncludeCallerData() {
        return includeCallerData;
    }

    public void setIncludeCallerData(boolean includeCallerData) {
        this.includeCallerData = includeCallerData;
    }

    class Worker extends Thread {
        Worker() {
        }

        @Override
        public void run() {
            AsyncRollingFileAppender parent = AsyncRollingFileAppender.this;

            // loop while the parent is started
            while (parent.isStarted()) {
                try {
                    ILoggingEvent e = (ILoggingEvent) parent.blockingQueue.take();
                    parent.subAppend(e);
                } catch (InterruptedException var4) {
                    break;
                }
            }
            AsyncRollingFileAppender.this.addInfo("Worker thread will flush remaining events before exiting. ");
            Iterator iterator = parent.blockingQueue.iterator();

            while (iterator.hasNext()) {
                ILoggingEvent ex = (ILoggingEvent) iterator.next();
                parent.subAppend(ex);
                parent.blockingQueue.remove(ex);
            }

        }
    }
}