package org.herac.tuxguitar.community;

import org.herac.tuxguitar.community.auth.TGCommunityAuth;
import org.herac.tuxguitar.gui.system.config.TGConfigManager;
import org.herac.tuxguitar.gui.system.plugins.TGPluginConfigManager;

public class TGCommunitySingleton {

  private static TGCommunitySingleton instance;

  public static TGCommunitySingleton getInstance() {
    synchronized (TGCommunitySingleton.class) {
      if (instance == null) {
        instance = new TGCommunitySingleton();
      }
    }
    return instance;
  }

  private TGCommunityAuth auth;

  private TGConfigManager config;

  private TGCommunitySingleton() {
    this.auth = new TGCommunityAuth();
  }

  public TGCommunityAuth getAuth() {
    return this.auth;
  }

  public TGConfigManager getConfig() {
    if (this.config == null) {
      this.config = new TGPluginConfigManager("tuxguitar-community");
      this.config.init();
    }
    return this.config;
  }

  public void loadSettings() {
    TGConfigManager config = getConfig();
    this.auth.load(config);
  }

  public void saveSettings() {
    TGConfigManager config = getConfig();
    this.auth.save(config);
    config.save();
  }
}
