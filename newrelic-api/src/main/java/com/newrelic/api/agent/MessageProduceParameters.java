/*
 *
 *  * Copyright 2020 New Relic Corporation. All rights reserved.
 *  * SPDX-License-Identifier: Apache-2.0
 *
 */

package com.newrelic.api.agent;

/**
 * Creates the parameters to report a message that was sent to a message queue. This should be used with
 * {@link TracedMethod#reportAsExternal(ExternalParameters)}. A fluent builder is provided to allow for easy usage and
 * management of this API.
 *
 * @since 3.36.0
 */
public class MessageProduceParameters implements ExternalParameters {
    private final String library;
    private final DestinationType destinationType;
    private final String destinationName;
    private final OutboundHeaders outboundHeaders;

    protected MessageProduceParameters(String library, DestinationType destinationType, String destinationName,
            OutboundHeaders outboundHeaders) {
        this.library = library;
        this.destinationType = destinationType;
        this.destinationName = destinationName;
        this.outboundHeaders = outboundHeaders;
    }

    protected MessageProduceParameters(MessageProduceParameters messageProduceParameters) {
        this.library = messageProduceParameters.library;
        this.destinationType = messageProduceParameters.destinationType;
        this.destinationName = messageProduceParameters.destinationName;
        this.outboundHeaders = messageProduceParameters.outboundHeaders;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public DestinationType getDestinationType() {
        return destinationType;
    }

    public OutboundHeaders getOutboundHeaders() {
        return outboundHeaders;
    }

    public String getLibrary() {
        return library;
    }

    protected static class Builder implements DestinationTypeParameter, DestinationNameParameter,
            OutboundHeadersParameter, Build {
        private String library;
        private DestinationType destinationType;
        private String destinationName;
        private OutboundHeaders outboundHeaders;

        public Builder(String library) {
            this.library = library;
        }

        public DestinationNameParameter destinationType(DestinationType destinationType) {
            this.destinationType = destinationType;
            return this;
        }

        public OutboundHeadersParameter destinationName(String destinationName) {
            this.destinationName = destinationName;
            return this;
        }

        public Build outboundHeaders(OutboundHeaders outboundHeaders) {
            this.outboundHeaders = outboundHeaders;
            return this;
        }

        public MessageProduceParameters build() {
            return new MessageProduceParameters(library, destinationType, destinationName, outboundHeaders);
        }
    }

    /**
     * Set the name of the library.
     *
     * @param library the name of the library
     * @return the next builder interface
     */
    public static DestinationTypeParameter library(String library) {
        return new MessageProduceParameters.Builder(library);
    }

    public interface DestinationTypeParameter {

        /**
         * Set the destination type of the external call.
         *
         * @param destinationType the destination type of the external call
         * @return the next builder interface
         */
        DestinationNameParameter destinationType(DestinationType destinationType);
    }

    public interface DestinationNameParameter {

        /**
         * Set the destination name of the external call.
         *
         * @param destinationName the destination name of the external call
         * @return the next builder interface
         */
        OutboundHeadersParameter destinationName(String destinationName);
    }

    public interface OutboundHeadersParameter {

        /**
         * Set the outbound headers on the external call.  Pass null if you
         * don't want the headers on the request/transaction to be modified.
         *
         * @param outboundHeaders the outbound headers for the external call
         * @return the completed HttpParameters object
         */
        Build outboundHeaders(OutboundHeaders outboundHeaders);
    }

    public interface Build {

        /**
         * Build the final {@link MessageProduceParameters} for the API call.
         *
         * @return the completed GenericParameters object
         */
        MessageProduceParameters build();
    }

}
