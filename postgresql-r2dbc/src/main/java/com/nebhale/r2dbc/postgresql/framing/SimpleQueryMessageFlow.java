/*
 * Copyright 2017-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nebhale.r2dbc.postgresql.framing;

import com.nebhale.r2dbc.postgresql.message.backend.BackendMessage;
import com.nebhale.r2dbc.postgresql.message.frontend.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * A helper class that encapsulates the <a href="https://www.postgresql.org/docs/current/static/protocol-flow.html#idm46428663987712">Simple Query message flow</a>.
 */
public final class SimpleQueryMessageFlow {

    private SimpleQueryMessageFlow() {
    }

    /**
     * Initiates the message flow.
     *
     * @param client the client to use to send and receive messages
     * @param query  the query to execute
     * @return the messages received as part of a simple query
     * @throws NullPointerException if {@code client} or {@code query} is {@code null}
     */
    public static Flux<BackendMessage> exchange(Client client, String query) {
        Objects.requireNonNull(client, "client must not be null");
        Objects.requireNonNull(query, "query must not be null");

        return client.exchange(Mono.just(new Query(query)));
    }

}
