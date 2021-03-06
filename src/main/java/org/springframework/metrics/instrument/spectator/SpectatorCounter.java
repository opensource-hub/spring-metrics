/**
 * Copyright 2017 Pivotal Software, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.metrics.instrument.spectator;

import org.springframework.metrics.instrument.Counter;
import org.springframework.metrics.instrument.Measurement;
import org.springframework.metrics.instrument.Tag;

public class SpectatorCounter implements Counter {
    private com.netflix.spectator.api.Counter counter;

    public SpectatorCounter(com.netflix.spectator.api.Counter counter) {
        this.counter = counter;
    }

    @Override
    public void increment() {
        counter.increment();
    }

    @Override
    public void increment(double amount) {
        counter.increment((long) amount);
    }

    @Override
    public double count() {
        return counter.count();
    }

    @Override
    public String getName() {
        return counter.id().name();
    }

    @Override
    public Iterable<Tag> getTags() {
        return SpectatorUtils.tags(counter);
    }

    @Override
    public Type getType() {
        return Type.Counter;
    }

    @Override
    public Iterable<Measurement> measure() {
        return SpectatorUtils.measurements(counter);
    }
}
