/*
 * Copyright 2016 the original author or authors.
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
package ro.pippo.session.xmemcached;

import de.flapdoodle.embed.memcached.MemcachedExecutable;
import de.flapdoodle.embed.memcached.MemcachedProcess;
import de.flapdoodle.embed.memcached.MemcachedStarter;
import de.flapdoodle.embed.memcached.config.MemcachedConfig;
import de.flapdoodle.embed.memcached.distribution.Version;
import java.io.IOException;
import net.rubyeye.xmemcached.CommandFactory;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.command.BinaryCommandFactory;
import org.junit.AfterClass;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;
import org.junit.Test;
import ro.pippo.core.Application;

/**
 * @author Herman Barrantes
 */
public class XmemcachedFactoryTest {

    private static final String HOST = "localhost:11211";
    private static final Integer PORT = 11211;
    private static Application application;
    private static MemcachedExecutable memcachedExe;
    private static MemcachedProcess memcached;

    @BeforeClass
    public static void setUpClass() throws IOException {
        application = new Application();
        MemcachedStarter runtime = MemcachedStarter.getDefaultInstance();
        memcachedExe = runtime.prepare(
                new MemcachedConfig(Version.Main.PRODUCTION, PORT));
        memcached = memcachedExe.start();
    }

    @AfterClass
    public static void tearDownClass() {
        memcached.stop();
        memcachedExe.stop();
    }

    /**
     * Test of create method, of class SpymemcachedUtil.
     */
    @Test
    public void testCreate_PippoSettings() throws IOException {
        System.out.println("create");
        MemcachedClient result = XmemcachedFactory.create(application.getPippoSettings());
        assertNotNull(result);
        assertFalse(result.isShutdown());
        result.shutdown();
        assertTrue(result.isShutdown());
    }

    /**
     * Test of create method, of class SpymemcachedUtil.
     */
    @Test
    public void testCreate_5args() throws IOException {
        System.out.println("create");
        CommandFactory protocol = new BinaryCommandFactory();
        String user = "";
        String pass = "";
        String[] authMechanisms = new String[]{"PLAIN"};
        MemcachedClient result = XmemcachedFactory.create(HOST, protocol, user, pass, authMechanisms);
        assertNotNull(result);
        assertFalse(result.isShutdown());
        result.shutdown();
        assertTrue(result.isShutdown());
    }

}
