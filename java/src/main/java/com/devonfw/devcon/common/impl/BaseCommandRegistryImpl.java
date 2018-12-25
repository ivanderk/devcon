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
package com.devonfw.devcon.common.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.devonfw.devcon.common.api.Command;
import com.devonfw.devcon.common.api.CommandModuleInfo;
import com.devonfw.devcon.common.api.CommandRegistry;
import com.devonfw.devcon.common.api.data.CommandInfoLite;
import com.devonfw.devcon.common.api.data.ModuleInfoLite;
import com.google.common.base.Optional;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ivanderk
 */
public class BaseCommandRegistryImpl implements CommandRegistry {

  protected HashMap<String, CommandModuleInfo> modules;

  public BaseCommandRegistryImpl() {
    this.modules = new HashMap<>();
  }

  @Override
  public Optional<CommandModuleInfo> getCommandModule(String module) {

    if (this.modules.containsKey(module)) {
      return Optional.of(this.modules.get(module));
    } else {
      return Optional.absent();
    }
  }

  @Override
  public Optional<Command> getCommand(String module, String command) {

    Optional<CommandModuleInfo> mod_ = getCommandModule(module);
    if (mod_.isPresent())
      return mod_.get().getCommand(command);
    else
      return Optional.absent();
  }

  @Override
  public List<CommandModuleInfo> getCommandModules() {

    // TODO Refactot to Collection?
    return new ArrayList<CommandModuleInfo>(this.modules.values());
  }

  /**
   * @return modules
   */
  protected HashMap<String, CommandModuleInfo> getModules() {

    return this.modules;
  }

  /**
   * @param modules the modules to set
   */
  protected void setModules(HashMap<String, CommandModuleInfo> modules) {

    this.modules = modules;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void add(CommandRegistry otherRegistry) {

    BaseCommandRegistryImpl other = (BaseCommandRegistryImpl) otherRegistry;
    getModules().putAll(other.getModules());
  }

  @Override
  public void writeAsJSON(OutputStream out) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {

      List<ModuleInfoLite> modules = new ArrayList<ModuleInfoLite>();
      for(CommandModuleInfo info: this.modules.values()){
          List<CommandInfoLite> commands = new ArrayList<CommandInfoLite>();
          for(Command cmd : info.getCommands()){
            commands.add(new CommandInfoLite(cmd.getName(), 
                                             cmd.getDescription(), 
                                             cmd.getHelpText(), 
                                             cmd.getDefinedParameters(),
                                             cmd.getSortValue()));
             
          }

          Collections.sort(commands, (c1, c2) -> c1.compareTo(c2));
          modules.add(new ModuleInfoLite(info.getName(), 
                                         info.getDescription(), 
                                         info.getSortValue(),
                                         commands));
      }
    
      Collections.sort(modules, (m1, m2) -> m1.compareTo(m2));
      objectMapper.writeValue(out, modules);

    } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

}
