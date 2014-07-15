/*
 * Copyright 2010 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 */
package com.ning.http.client.providers.netty;

import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.util.Timer;

import com.ning.http.client.AsyncHttpProviderConfig;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;

/**
 * This class can be used to pass Netty's internal configuration options. See Netty documentation for more information.
 */
public class NettyAsyncHttpProviderConfig implements AsyncHttpProviderConfig<String, Object> {
    
    private final ConcurrentHashMap<String, Object> properties = new ConcurrentHashMap<String, Object>();

    /**
     * Add a property that will be used when the AsyncHttpClient initialize its {@link com.ning.http.client.AsyncHttpProvider}
     * 
     * @param name
     *            the name of the property
     * @param value
     *            the value of the property
     * @return this instance of AsyncHttpProviderConfig
     */
    public NettyAsyncHttpProviderConfig addProperty(String name, Object value) {
        properties.put(name, value);
        return this;
    }

    /**
     * Return the value associated with the property's name
     * 
     * @param name
     * @return this instance of AsyncHttpProviderConfig
     */
    public Object getProperty(String name) {
        return properties.get(name);
    }

    /**
     * Return the value associated with the property's name
     * 
     * @param name
     * @return this instance of AsyncHttpProviderConfig
     */
    public <T> T getProperty(String name, Class<T> type, T defaultValue) {
        Object value = properties.get(name);
        if (value != null && type.isAssignableFrom(value.getClass())) {
            return type.cast(value);
        }
        return defaultValue;
    }

    /**
     * Remove the value associated with the property's name
     * 
     * @param name
     * @return true if removed
     */
    public Object removeProperty(String name) {
        return properties.remove(name);
    }

    /**
     * Return the curent entry set.
     * 
     * @return a the curent entry set.
     */
    public Set<Map.Entry<String, Object>> propertiesSet() {
        return properties.entrySet();
    }

    /**
     * Enable Netty DeadLockChecker
     */
    private boolean useDeadLockChecker;

    /**
     * Allow configuring the Netty's boss executor service.
     */
    private ExecutorService bossExecutorService;

    /**
     * Allow configuring Netty's HttpClientCodecs.
     */
    private int httpClientCodecMaxInitialLineLength = 4096;
    private int httpClientCodecMaxHeaderSize = 8192;
    private int httpClientCodecMaxChunkSize = 8192;
    
    /**
     * Allow configuring the Netty's socket channel factory.
     */
    private NioClientSocketChannelFactory socketChannelFactory;

    /**
     * Allow one to disable zero copy for bodies and use chunking instead;
     */
    private boolean disableZeroCopy;

    private Timer nettyTimer;

    private long handshakeTimeoutInMillis = 10000L;

    private ChannelPool channelPool;

    /**
     * chunkedFileChunkSize
     */
    private int chunkedFileChunkSize = 8192;

    public boolean isUseDeadLockChecker() {
        return useDeadLockChecker;
    }

    public void setUseDeadLockChecker(boolean useDeadLockChecker) {
        this.useDeadLockChecker = useDeadLockChecker;
    }

    public ExecutorService getBossExecutorService() {
        return bossExecutorService;
    }

    public void setBossExecutorService(ExecutorService bossExecutorService) {
        this.bossExecutorService = bossExecutorService;
    }

    public int getHttpClientCodecMaxInitialLineLength() {
        return httpClientCodecMaxInitialLineLength;
    }

    public void setHttpClientCodecMaxInitialLineLength(int httpClientCodecMaxInitialLineLength) {
        this.httpClientCodecMaxInitialLineLength = httpClientCodecMaxInitialLineLength;
    }

    public int getHttpClientCodecMaxHeaderSize() {
        return httpClientCodecMaxHeaderSize;
    }

    public void setHttpClientCodecMaxHeaderSize(int httpClientCodecMaxHeaderSize) {
        this.httpClientCodecMaxHeaderSize = httpClientCodecMaxHeaderSize;
    }

    public int getHttpClientCodecMaxChunkSize() {
        return httpClientCodecMaxChunkSize;
    }

    public void setHttpClientCodecMaxChunkSize(int httpClientCodecMaxChunkSize) {
        this.httpClientCodecMaxChunkSize = httpClientCodecMaxChunkSize;
    }

    public NioClientSocketChannelFactory getSocketChannelFactory() {
        return socketChannelFactory;
    }

    public void setSocketChannelFactory(NioClientSocketChannelFactory socketChannelFactory) {
        this.socketChannelFactory = socketChannelFactory;
    }

    public void setDisableZeroCopy(boolean disableZeroCopy) {
        this.disableZeroCopy = disableZeroCopy;
    }

    public boolean isDisableZeroCopy() {
        return disableZeroCopy;
    }

    public Timer getNettyTimer() {
        return nettyTimer;
    }

    public void setNettyTimer(Timer nettyTimer) {
        this.nettyTimer = nettyTimer;
    }

    public long getHandshakeTimeoutInMillis() {
        return handshakeTimeoutInMillis;
    }

    public void setHandshakeTimeoutInMillis(long handshakeTimeoutInMillis) {
        this.handshakeTimeoutInMillis = handshakeTimeoutInMillis;
    }

    public ChannelPool getChannelPool() {
        return channelPool;
    }

    public void setChannelPool(ChannelPool channelPool) {
        this.channelPool = channelPool;
    }

    public int getChunkedFileChunkSize() {
        return chunkedFileChunkSize;
    }

    public void setChunkedFileChunkSize(int chunkedFileChunkSize) {
        this.chunkedFileChunkSize = chunkedFileChunkSize;
    }
}