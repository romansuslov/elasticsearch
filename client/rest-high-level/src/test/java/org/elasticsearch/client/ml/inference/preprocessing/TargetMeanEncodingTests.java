/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.elasticsearch.client.ml.inference.preprocessing;

import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.test.AbstractXContentTestCase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;


public class TargetMeanEncodingTests extends AbstractXContentTestCase<TargetMeanEncoding> {

    @Override
    protected TargetMeanEncoding doParseInstance(XContentParser parser) throws IOException {
        return TargetMeanEncoding.fromXContent(parser);
    }

    @Override
    protected boolean supportsUnknownFields() {
        return true;
    }

    @Override
    protected Predicate<String> getRandomFieldsExcludeFilter() {
        return field -> !field.isEmpty();
    }

    @Override
    protected TargetMeanEncoding createTestInstance() {
        return createRandom();
    }

    public static TargetMeanEncoding createRandom() {
        return createRandom(randomAlphaOfLength(10));
    }

    public static TargetMeanEncoding createRandom(String inputField) {
        int valuesSize = randomIntBetween(1, 10);
        Map<String, Double> valueMap = new HashMap<>();
        for (int i = 0; i < valuesSize; i++) {
            valueMap.put(randomAlphaOfLength(10), randomDoubleBetween(0.0, 1.0, false));
        }
        return new TargetMeanEncoding(inputField,
            randomAlphaOfLength(10),
            valueMap,
            randomDoubleBetween(0.0, 1.0, false),
            true);
    }

}