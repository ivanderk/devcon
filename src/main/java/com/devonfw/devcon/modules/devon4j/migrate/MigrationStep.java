package com.devonfw.devcon.modules.devon4j.migrate;

import com.devonfw.devcon.modules.devon4j.migrate.version.VersionIdentifier;

/**
 * TODO hohwille This type ...
 *
 * @since 1.5.0
 */
public interface MigrationStep extends Migration {

  /**
   * @return the {@link VersionIdentifier} this {@link MigrationStep} expects to migrate from.
   */
  VersionIdentifier getFrom();

  /**
   * @return the {@link VersionIdentifier} that is applied after this {@link MigrationStep} has been
   *         {@link #migrate(java.io.File) completed}.
   */
  VersionIdentifier getTo();

}
