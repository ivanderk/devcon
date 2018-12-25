/*******************************************************************************
 * Copyright 2015-2018 Capgemini SE.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 ******************************************************************************/
package com.devonfw.devcon.basic;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

import com.devonfw.devcon.common.api.CommandRegistry;
import com.devonfw.devcon.common.impl.CommandRegistryImpl;
import com.devonfw.devcon.input.ConsoleInputManager;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the JSON generation of the CommandRegistry
 *
 * @author ivanderk
 */
public class JSONFromRegistryTest {
  ConsoleInputManager inputMgr;

  private CommandRegistry registry;


  @SuppressWarnings("javadoc")
  @Before
  public void setup() {

    this.registry = new CommandRegistryImpl("com.devonfw.devcon.modules.*");
    
  }

  /**
   * Checks if the CommandRegistry is correctly generating the JSON exepected
   * 
   * @throws UnsupportedEncodingException
   */
  @Test
  public void getJsonFromRegistry() throws UnsupportedEncodingException {

    ByteArrayOutputStream out = new ByteArrayOutputStream();

    registry.writeAsJSON(out);
      
    String s = out.toString("UTF-8");
    //System.out.println(s);

    String persist = "[{\"name\":\"devon4j\",\"description\":\"devon4j (Java server project) related commands\",\"sortValue\":3,\"commands\":[{\"name\":\"build\",\"description\":\"This command will build the server project\"";
    
    assertEquals(persist, s.substring(0, persist.length()));
   
  }


}
