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
package com.devonfw.devcon.common.api.data;

import java.util.Collection;

public class ModuleInfoLite implements Info, Comparable<ModuleInfoLite> {

    private String name;
    private String description;
    private int sortValue;
    private Collection<CommandInfoLite> commands;

    public ModuleInfoLite(String name, 
                          String description, 
                          int sortValue,
                          Collection<CommandInfoLite> commands) {
        this.name = name;
        this.description = description;
        this.sortValue = sortValue;
        this.commands = commands;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getSortValue() {
        return sortValue;
    }

    public Collection<CommandInfoLite> getCommands() {
        return commands;
    }

	public int compareTo(ModuleInfoLite m2) {
		return this.name.compareTo(m2.getName());
	}

}