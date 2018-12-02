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
package com.devonfw.devcon.common.api;

import java.io.OutputStream;
import java.util.List;

import com.google.common.base.Optional;

/**
 * The central repository where all {@link CommandModule}s with their respective {@link Command}s are loaded and stored
 *
 * @author ivanderk
 */
public interface CommandRegistry {

  public void writeAsJSON(OutputStream out);

  List<CommandModuleInfo> getCommandModules();

  Optional<CommandModuleInfo> getCommandModule(String module);

  Optional<Command> getCommand(String module, String command);

  /**
   * @param registry Add modules & commands from otherRegistry to this one
   */
  void add(CommandRegistry otherRegistry);

}
