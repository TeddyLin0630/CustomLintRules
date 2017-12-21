/*
 * Copyright (C) 2017 The Android Open Source Project
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
package com.example.lint.checks;

import com.android.tools.lint.checks.infrastructure.LintDetectorTest;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Issue;

import java.util.Collections;
import java.util.List;

public class BracesDetectorTest extends LintDetectorTest {

    public void testIfStatement() {
        lint().files(
                java("" +
                        "package test.pkg;\n" +
                        "public class TestClass1 {\n" +
                        "    // In a comment, mentioning \"lint\" has no effect\n" +
                        "    private static String s1 = \"Ignore non-word usages: linting\";\n" +
                        "    private static String s2 = \"Let's say it: lint\";\n" +
                        "    public void init() {\n" +
                        "       if (true && (true)) return; \n"+
                        "    }\n"+
                        "    public void init2() {\n" +
                                "       String stringForTest = \"\".equals(\"\") ? \"0\" :\"1\"; \n"+
                                "    }\n"+
                        "}"))
                .run()
                .expect("src/test/pkg/TestClass1.java:7: Warning: Always use curly braces for \"if/else/for/while/do\" statements [Braces Formatting]\n" +
                        "       if (true && (true)) return; \n" +
                        "       ~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
                        "0 errors, 1 warnings\n");
    }

    @Override
    protected Detector getDetector() {
        return new BracesDetector();
    }

    @Override
    protected List<Issue> getIssues() {
        return Collections.singletonList(BracesDetector.ISSUE);
    }
}