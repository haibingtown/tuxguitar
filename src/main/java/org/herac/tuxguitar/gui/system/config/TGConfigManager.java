/*
 * Created on 09-ene-2006
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
package org.herac.tuxguitar.gui.system.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.herac.tuxguitar.gui.editors.chord.ChordSelector;

/**
 * @author julian
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class TGConfigManager {

  private Properties properties;


  public void clear() {
    this.properties.clear();
  }

  public boolean getBooleanConfigValue(String key) {
    return this.getBooleanConfigValue(key, false);
  }

  public boolean getBooleanConfigValue(String key, boolean defaultValue) {
    try {
      String value = getProperty(key);
      return (value == null) ? defaultValue : Boolean.valueOf(value.trim())
          .booleanValue();
    } catch (Throwable throwable) {
      LOG.error(throwable);
    }
    return defaultValue;
  }

  /** The Logger for this class. */
  public static final transient Logger LOG = Logger
      .getLogger(TGConfigManager.class);
  

  public abstract Properties getDefaults();

  public double getDoubleConfigValue(String key) {
    return this.getDoubleConfigValue(key, 0.0);
  }

  public double getDoubleConfigValue(String key, double defaultValue) {
    try {
      String value = getProperty(key);
      return (value == null) ? defaultValue : Double.parseDouble(value.trim());
    } catch (Throwable throwable) {
      LOG.error(throwable);
    }
    return defaultValue;
  }

  public abstract String getFileName();

  public float getFloatConfigValue(String key) {
    return this.getFloatConfigValue(key, 0f);
  }

  public float getFloatConfigValue(String key, float defaultValue) {
    try {
      String value = getProperty(key);
      return (value == null) ? defaultValue : Float.parseFloat(value.trim());
    } catch (Throwable throwable) {
      LOG.error(throwable);
    }
    return defaultValue;
  }

  public FontData getFontDataConfigValue(String key) {
    try {
      String value = getProperty(key);
      if (value != null) {
        String[] values = value.trim().split(",");
        if (values != null && values.length == 3) {
          try {
            String name = values[0].trim();
            int size = Integer.parseInt(values[1].trim());
            int style = Integer.parseInt(values[2].trim());
            return new FontData((name == null ? "" : name), size, style);
          } catch (NumberFormatException e) {
            LOG.error(e);
          }
        }
      }
    } catch (Throwable throwable) {
      LOG.error(throwable);
    }
    return new FontData();
  }

  public int getIntConfigValue(String key) {
    return this.getIntConfigValue(key, 0);
  }

  public int getIntConfigValue(String key, int defaultValue) {
    try {
      String value = getProperty(key);
      return (value == null) ? defaultValue : Integer.parseInt(value.trim());
    } catch (Throwable throwable) {
      LOG.error(throwable);
    }
    return defaultValue;
  }

  public abstract String getName();

  private String getProperty(String key) {
    return this.properties.getProperty(key);
  }

  public RGB getRGBConfigValue(String key) {
    try {
      String value = getProperty(key);
      if (value != null) {
        String[] values = value.trim().split(",");
        if (values != null && values.length == 3) {
          try {
            int red = Integer.parseInt(values[0].trim());
            int green = Integer.parseInt(values[1].trim());
            int blue = Integer.parseInt(values[2].trim());

            return new RGB(red, green, blue);
          } catch (NumberFormatException e) {
            LOG.error(e);
          }
        }
      }
    } catch (Throwable throwable) {
      LOG.error(throwable);
    }
    return null;
  }

  public String getStringConfigValue(String key) {
    return this.getStringConfigValue(key, null);
  }

  public String getStringConfigValue(String key, String defaultValue) {
    try {
      String property = getProperty(key);
      return (property == null) ? defaultValue : property.trim();
    } catch (Throwable throwable) {
      LOG.error(throwable);
    }
    return defaultValue;
  }

  public void init() {
    this.properties = new Properties(getDefaults());
    this.load();
  }

  public void load() {
    try {
      if (new File(getFileName()).exists()) {
        InputStream inputStream = new FileInputStream(getFileName());
        this.properties.clear();
        this.properties.load(inputStream);
      } else {
        this.save();
      }
    } catch (Exception e) {
      LOG.error(e);
    }
  }

  public void removeProperty(String key) {
    this.properties.remove(key);
  }

  public void save() {
    try {
      File file = new File(getFileName());
      if (!file.exists()) {
        File folder = file.getParentFile();
        if (folder != null && !folder.exists()) {
          folder.mkdirs();
        }
      }
      this.properties.store(new FileOutputStream(file), getName());
    } catch (FileNotFoundException e1) {
      LOG.error(e1);
    } catch (IOException e1) {
      LOG.error(e1);
    }
  }

  public void setDefaults() {
    Properties defaults = new TGConfigDefaults().getProperties();
    for (final Entry<Object, Object> property : defaults.entrySet()) {
      setProperty((String) property.getKey(), (String) property.getValue());
    }
    this.save();
  }

  public void setProperty(String key, boolean value) {
    this.setProperty(key, Boolean.toString(value));
  }

  public void setProperty(String key, double value) {
    this.setProperty(key, Double.toString(value));
  }

  public void setProperty(String key, float value) {
    this.setProperty(key, Float.toString(value));
  }

  public void setProperty(String key, FontData fd) {
    this.setProperty(key, (fd.getName() + "," + fd.getHeight() + "," + fd
        .getStyle()));
  }

  public void setProperty(String key, int value) {
    this.setProperty(key, Integer.toString(value));
  }

  public void setProperty(String key, RGB rgb) {
    this.setProperty(key, (rgb.red + "," + rgb.green + "," + rgb.blue));
  }

  public void setProperty(String key, String value) {
    this.properties.setProperty(key, (value != null ? value : new String()));
  }

}